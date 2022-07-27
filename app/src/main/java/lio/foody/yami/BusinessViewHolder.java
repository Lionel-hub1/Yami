package lio.foody.yami;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BusinessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtBusinessName, txtBusinessDescription, txtBusinessCategory;
    public ImageView imageView;
    public ItemClickListner listner;
    public BusinessViewHolder(View itemView)
    {
        super(itemView);


        imageView = (ImageView) itemView.findViewById(R.id.restaurantImage);
        txtBusinessName = (TextView) itemView.findViewById(R.id.restaurantName);
        txtBusinessDescription = (TextView) itemView.findViewById(R.id.restaurantDescription);
        txtBusinessCategory = (TextView) itemView.findViewById(R.id.restaurantDistance);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}