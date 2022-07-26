package lio.foody.yami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddBusinessActivity extends AppCompatActivity {

    private String CategoryName, Description, ChefName, Pname, saveCurrentDate, saveCurrentTime;
    private Button AddNewBusinessButton;
    private ImageView InputBusinessImage;
    private EditText InputBusinessName, InputBusinessDescription, InputBusinessChefName;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String businessRandomKey, downloadImageUrl;
    private StorageReference BusinessImagesRef;
    private DatabaseReference BusinessRef;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_business);

        CategoryName = getIntent().getExtras().get("category").toString();
        BusinessImagesRef = FirebaseStorage.getInstance().getReference().child("Business Images");
        BusinessRef = FirebaseDatabase.getInstance().getReference().child("Businesses");

        AddNewBusinessButton = (Button) findViewById(R.id.add_new_business);
        InputBusinessImage = (ImageView) findViewById(R.id.select_business_image);
        InputBusinessName = (EditText) findViewById(R.id.business_name);
        InputBusinessDescription = (EditText) findViewById(R.id.business_description);
        InputBusinessChefName = (EditText) findViewById(R.id.business_chef_name);
        loadingBar = new ProgressDialog(this);


        InputBusinessImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });


        AddNewBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateBusinessData();
            }
        });
    }


    private void OpenGallery(){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null) {
            ImageUri = data.getData();
            InputBusinessImage.setImageURI(ImageUri);
        }
    }
    private void ValidateBusinessData() {
        Description = InputBusinessDescription.getText().toString();
        ChefName = InputBusinessChefName.getText().toString();
        Pname = InputBusinessName.getText().toString();
        if (ImageUri == null) {
            Toast.makeText(this, "Business image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Description)) {
            Toast.makeText(this, "Please write business description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(ChefName)) {
            Toast.makeText(this, "Please write business ChefName...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Pname)) {
            Toast.makeText(this, "Please write business name...", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreBusinessInformation();
        }
    }
    private void StoreBusinessInformation() {
        loadingBar.setTitle("Add New Business");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new business.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        businessRandomKey = saveCurrentDate + saveCurrentTime;


        final StorageReference filePath = BusinessImagesRef.child(ImageUri.getLastPathSegment() + businessRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(), "Business Image uploaded Successfully...", Toast.LENGTH_SHORT).show();
                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(getApplicationContext(), "got the Business image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveBusinessInfoToDatabase();
                        }
                    }
                });
            }
        });
    }
    private void SaveBusinessInfoToDatabase() {
        HashMap<String, Object> businessMap = new HashMap<>();
        businessMap.put("pid", businessRandomKey);
        businessMap.put("date", saveCurrentDate);
        businessMap.put("time", saveCurrentTime);
        businessMap.put("description", Description);
        businessMap.put("image", downloadImageUrl);
        businessMap.put("category", CategoryName);
        businessMap.put("chef_name", ChefName);
        businessMap.put("p_name", Pname);

        BusinessRef.child(businessRandomKey).updateChildren(businessMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), AdminSessionActivity.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(getApplicationContext(), "Business is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}