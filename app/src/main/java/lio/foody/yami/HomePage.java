package lio.foody.yami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           Fragment fragmentContainerView = null;

           switch (item.getItemId()){

               case R.id.home4:
                   fragmentContainerView =  new Home();
                   break;
               case R.id.findFood:
                   fragmentContainerView =  new FindFood();
                   break;
               case R.id.learnHowTo:
                   fragmentContainerView =  new LearnHowTo();
                   break;
               case R.id.settings:
                   fragmentContainerView =  new Profile();
                   break;
           }

           getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragmentContainerView).commit();

           return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        bottomNavigationView.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new Home()).commit();

//        Intent myIntent =getIntent();
//        Toast.makeText(getApplicationContext(), "Welcome "+myIntent.getStringExtra("keyname"), Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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