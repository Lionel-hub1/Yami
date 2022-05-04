package lio.foody.yami;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileViewHolder extends RecyclerView.ViewHolder  {
    public TextView userFirstName, userLastName, userPhone, userCountry, userCity, userEmail;
    public ImageView userProfilePic;

    public ProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        userFirstName = itemView.findViewById(R.id.user_first_name);
        userLastName = itemView.findViewById(R.id.user_last_name);
        userPhone = itemView.findViewById(R.id.user_phone);
        userCountry = itemView.findViewById(R.id.user_country);
        userCity = itemView.findViewById(R.id.user_city);
        userEmail = itemView.findViewById(R.id.user_email);
        userProfilePic = itemView.findViewById(R.id.userprofilePicture);

    }
}
