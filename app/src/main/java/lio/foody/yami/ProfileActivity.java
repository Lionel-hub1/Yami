package lio.foody.yami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    TextView userprofileName, userprofileEmail, userprofilePhone, userprofileUid;
    ImageView userprofilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseUser=mAuth.getCurrentUser();
        if (firebaseUser!=null){
            startActivity(new Intent(getApplicationContext(), HomePage.class));
        }
        userprofileName = findViewById(R.id.user_first_name);
        userprofileEmail = findViewById(R.id.user_email);
//        userprofilePhone = findViewById(R.id.userprofilePhone);
        userprofilePicture = findViewById(R.id.userprofilePicture);
//        userprofileUid = findViewById(R.id.userprofileUid);

        mAuth=FirebaseAuth.getInstance();
        firebaseUser= mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        if (firebaseUser != null){
            userprofileName.setText(firebaseUser.getUid());
            userprofileEmail.setText(firebaseUser.getEmail());
        }
        if (firebaseUser.getPhotoUrl() != null){
            Glide.with(this)
                    .load(firebaseUser.getPhotoUrl())
                    .into(userprofilePicture);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.profile_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeConnectMenu:
                startActivity(new Intent(getApplicationContext(), HomePage.class));
                break;
            case R.id.connectFoodMenu:
                startActivity(new Intent(getApplicationContext(), ConnectFoodActivity.class));
                break;
            case R.id.logoutMenu:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}