package lio.foody.yami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    //This information was from HomePage.java

    FirebaseAuth mAuth;
    FirebaseUser fbUser;
    Context context;
    BottomNavigationView bottomNavigationView;

    NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragmentContainerView = null;

            switch (item.getItemId()){
                case R.id.home4:
                    if (fbUser!=null){
                        //go to the other page
                        fragmentContainerView =  new Home();
                    }
                    else{
                        //require login
                        fragmentContainerView =  new Home();
                    }
                    break;

                case R.id.findFood:
                    if (fbUser!=null){
                        //go to the other page
                        fragmentContainerView =  new FindFood();
                    }
                    else{
                        //require login
                        fragmentContainerView =  new LoginFrag();
                    }
                    break;

                case R.id.learnHowTo:
                    if (fbUser!=null){
                        //go to the other page
                        fragmentContainerView =  new LearnHowTo();
                    }
                    else{
                        //require login
                        fragmentContainerView =  new LoginFrag();
                    }
                    break;

                case R.id.settings:
                    if (fbUser!=null){
                        //go to the other page
                        fragmentContainerView =  new Profile();
                    }
                    else{
                        //require login
                        fragmentContainerView =  new LoginFrag();
                    }
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragmentContainerView).commit();


            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        fbUser = mAuth.getCurrentUser();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Home()).commit();

//        Intent myIntent =getIntent();
//        Toast.makeText(getApplicationContext(), "Welcome "+myIntent.getStringExtra("keyname"), Toast.LENGTH_LONG).show();

    }




    //------------------------------------------------------------------------------------------------------------
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_menu, menu);
        return true;
    }*/
    //+++++++++++++++++++++++++++++++=
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu,menu);
        MenuItem mnLogin = menu.findItem(R.id.mnuLogin);
        MenuItem mnLogout = menu.findItem(R.id.mnuLogout);
        MenuItem mnConnectFoodMenu = menu.findItem(R.id.connectFoodMenu);
        //write logic: if some one logs in, login menu disapears and we show logout
        if (fbUser!=null){
            //hide login show logout
            mnConnectFoodMenu.setVisible(true);
            mnLogin.setVisible(false);
            mnLogout.setVisible(true);
        }
        else{
            //hide logout show login
            mnConnectFoodMenu.setVisible(false);
            mnLogin.setVisible(true);
            mnLogout.setVisible(false);
        }
        return true;
    }
    //----------------------------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.connectFoodMenu:
                startActivity(new Intent(getApplicationContext(), ConnectFoodActivity.class));
                break;
            case R.id.mnuLogin:
                startActivity(new Intent(getApplicationContext(), LoginPageActivity.class));
                break;
            case R.id.mnuLogout:
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}