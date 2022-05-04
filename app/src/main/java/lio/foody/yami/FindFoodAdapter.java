package lio.foody.yami;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FindFoodAdapter extends RecyclerView.Adapter<FindFoodAdapter.VH> {
    Context context;
    List<FoodProviderItem> foodProviderList;

    public FindFoodAdapter(Context context, List<FoodProviderItem> foodProviderList) {
        this.context = context;
        this.foodProviderList = foodProviderList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.find_food_item_row, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        FoodProviderItem item = foodProviderList.get(position);
        holder.restaurantImage.setImageResource(item.getRestaurantImage());
        holder.restaurantName.setText(item.getRestaurantName());
        holder.restaurantDescription.setText(item.getRestaurantDescription());
        holder.restaurantDistance.setText(item.getRestaurantDistance());
        holder.currentRating.setText(item.getCurrentRating());
        holder.currentReviews.setText(item.getCurrentReviews());
        holder.restaurantImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(context);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

                view = inflater.inflate( R.layout.image_zoom_in, null );

                alert.setView(view);
                alert.show();
                return true;
            }
        });
        holder.linearLayoutRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ratingDialog = new AlertDialog.Builder(context);

                LayoutInflater ratingInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                view = ratingInflater.inflate( R.layout.rating_dialog_body, null );
                ratingDialog.setView(view);
                ratingDialog.setMessage("Tell others what you think");
                ratingDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Thank you for your feedback", Toast.LENGTH_SHORT).show();
                    }
                });
                ratingDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ratingDialog.setCancelable(false);
                ratingDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodProviderList.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView restaurantImage;
        TextView restaurantName, restaurantDescription, restaurantDistance, currentRating, currentReviews;
        CardView linearLayoutRating, linearLayoutLocate, linearLayoutMore;

        public VH(@NonNull View itemView) {
            super(itemView);
            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantDescription = itemView.findViewById(R.id.restaurantDescription);
            restaurantDistance = itemView.findViewById(R.id.restaurantDistance);
            currentRating = itemView.findViewById(R.id.ratingsText);
            currentReviews = itemView.findViewById(R.id.reviewsText);
            linearLayoutRating = itemView.findViewById(R.id.linearLayoutRating);
            linearLayoutLocate = itemView.findViewById(R.id.linearLayoutLocate);
            linearLayoutMore = itemView.findViewById(R.id.linearLayoutMore);
        }
    }
}