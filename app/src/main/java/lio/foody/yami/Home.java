package lio.foody.yami;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    Spinner distancesSpinner;
    TextView fragDistanceTitle;
    List<Distances> distancesSpinnerList = new ArrayList<>();
    MyAdapter distanceAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragHome = inflater.inflate(R.layout.fragment_home, container, false);
        distancesSpinner = fragHome.findViewById(R.id.distanceSelectSpinner);
        fragDistanceTitle = fragHome.findViewById(R.id.fragDistanceTitle);
        distancesSpinnerList.add(new Distances(500, "m"));
        distancesSpinnerList.add(new Distances(1, "km"));
        distancesSpinnerList.add(new Distances(5, "km"));
        distancesSpinnerList.add(new Distances(10, "km"));
        distancesSpinnerList.add(new Distances(20, "km"));
        distancesSpinnerList.add(new Distances(50, "km"));
        distancesSpinnerList.add(new Distances(100, "km"));
        distancesSpinnerList.add(new Distances(500, "km"));
        distanceAdapter=new MyAdapter(getContext(),distancesSpinnerList);
        distancesSpinner.setAdapter(distanceAdapter);
        distancesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Distances distances =(Distances) adapterView.getItemAtPosition(i);
                fragDistanceTitle.setText(""+distances.getBoundLength()+" "+distances.getUnitMeasure());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Inflate the layout for this fragment
        return fragHome;
    }
}