package lio.foody.yami;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context context;
    List<Distances> distancesList;

    public MyAdapter(Context context, List<Distances> distancesList) {
        this.context = context;
        this.distancesList = distancesList;
    }

    @Override
    public int getCount() {
        return distancesList.size();
    }

    @Override
    public Object getItem(int i) {
        return distancesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       view = LayoutInflater.from(context).inflate(R.layout.distance_text,viewGroup,false);
        TextView distanceBound = view.findViewById(R.id.distanceBound);
        TextView unitMeasure = view.findViewById(R.id.unitMeasure);
        Distances distance = distancesList.get(i);

        distanceBound.setText(""+distance.getBoundLength());
        unitMeasure.setText(distance.getUnitMeasure());

        return view;
    }
}
