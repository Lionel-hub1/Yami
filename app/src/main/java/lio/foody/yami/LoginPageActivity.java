package lio.foody.yami;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class LoginPageActivity extends AppCompatActivity {

    //These were in MainActivity.Java

    Button btnLogin, btnRegister, login_as_admin_btn, register_as_admin_btn;
    Coms coms;
    TextView usernameInput;
    EditText emailEt, passwordEt;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference profileReference;
    private StorageReference storageProfileRef;
    private FirebaseDatabase profileDatabase;
    private static final String USER = "InfoUsers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        emailEt = findViewById(R.id.usernameInput);
        passwordEt = findViewById(R.id.passwordInput);
        coms = new Coms(this);

        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        profileDatabase = FirebaseDatabase.getInstance();
        profileReference = profileDatabase.getReference(USER);
        storageProfileRef = FirebaseStorage.getInstance().getReference("InfoUsers");


        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        login_as_admin_btn = findViewById(R.id.login_as_admin_btn);
        register_as_admin_btn = findViewById(R.id.register_as_admin_btn);
        usernameInput = findViewById(R.id.usernameInput);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Logging in ...");
        progressDialog.setCanceledOnTouchOutside(false);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Login");

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent mIntent2 = new Intent(getApplicationContext(),HomePage.class);
//                mIntent2.putExtra("keyname",usernameInput.getText().toString());
//                startActivity(mIntent2);
//            }
//        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check validity of email and password
                loginCheck();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });
        login_as_admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAsAdminCheck();
            }
        });
        register_as_admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterAsAdmin.class));
                finish();
            }
        });
    }

    private void loginAsAdminCheck() {
        String email = emailEt.getText().toString();
        String pass = passwordEt.getText().toString();
        profileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    if (ds.child("usrEmail").getValue().equals(firebaseUser.getEmail())){
                        Toast.makeText(LoginPageActivity.this, "Your Admin Account is pending", Toast.LENGTH_SHORT).show();
                    }
                    if (ds.child("account_Status").getValue().equals("user")){
                        Toast.makeText(LoginPageActivity.this, "Your are a normal user", Toast.LENGTH_SHORT).show();
                    }
                    if (ds.child("account_Status").getValue().equals("approved")){
                        Toast.makeText(LoginPageActivity.this, "Your Admin Account is approved", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

            }
        });


        //These mean to move to the Admin Session
        /*startActivity(new Intent(getApplicationContext(), AdminSessionActivity.class));
        finish();*/
    }

    private void loginCheck() {
        String email = emailEt.getText().toString();
        String pass = passwordEt.getText().toString();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Invalid email");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            passwordEt.setError("Password is required");
            return;
        }
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        coms.message("Error Login", e.getMessage());
                    }
                });
    }
}