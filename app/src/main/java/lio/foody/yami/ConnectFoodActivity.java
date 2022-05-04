package lio.foody.yami;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import lio.foody.yami.databinding.ActivityConnectFoodBinding;
import lio.foody.yami.databinding.ActivityMainBinding;

public class ConnectFoodActivity extends AppCompatActivity {

    ActivityConnectFoodBinding binding;
    Button chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ChatsFragment());
        binding.bottomNavigationViewConnect.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.chats_page_connect:
                    replaceFragment(new ChatsFragment());

                    break;
                case R.id.posts_page_connect:
                    replaceFragment(new PostsFragment());
                    break;
                case R.id.saved_page_connect:
                    replaceFragment(new SavedFragment());
                    break;
            }
            return true;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.chats_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.yami_connect_top_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homeConnectMenu:
                startActivity(new Intent(getApplicationContext(), HomePage.class));
                break;
            case R.id.logoutMenu:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}