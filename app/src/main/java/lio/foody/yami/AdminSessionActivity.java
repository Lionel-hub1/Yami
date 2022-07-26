package lio.foody.yami;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminSessionActivity extends AppCompatActivity {
    ConstraintLayout restaurantCard, cafeteriaCard, hotelsCard, dServicesCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_session);
        restaurantCard = findViewById(R.id.restaurant_category);
        cafeteriaCard = findViewById(R.id.cafeteria_category);
        hotelsCard = findViewById(R.id.hotels_category);
        dServicesCard = findViewById(R.id.d_services_category);

        restaurantCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminAddBusinessActivity.class);
                intent.putExtra("category", "restaurant");
                startActivity(intent);
            }
        });
        cafeteriaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminAddBusinessActivity.class);
                intent.putExtra("category", "cafeteria");
                startActivity(intent);
            }
        });
        hotelsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminAddBusinessActivity.class);
                intent.putExtra("category", "hotel");
                startActivity(intent);
            }
        });
        dServicesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminAddBusinessActivity.class);
                intent.putExtra("category", "delivery");
                startActivity(intent);
            }
        });
    }
}