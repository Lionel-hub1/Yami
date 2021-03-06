package lio.foody.yami;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FindFood#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FindFood extends Fragment {
    /*RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FindFoodAdapter findFoodAdapter;
    List<FoodProviderItem> foodProviderList;*/

    //from Cleus
    private Button searchBtn;
    private EditText inputText;
    private RecyclerView searchList;
    private String searchInput;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FindFood() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindFood.
     */
    // TODO: Rename and change types and number of parameters
    public static FindFood newInstance(String param1, String param2) {
        FindFood fragment = new FindFood();
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
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_find_food, container, false);
        /*recyclerView = rootView.findViewById(R.id.find_food_recycler_id);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        foodProviderList = new ArrayList<>();
        foodProviderList = fillTheList();
        findFoodAdapter = new FindFoodAdapter(getContext(), foodProviderList);
        recyclerView.setAdapter(findFoodAdapter);*/

        //From Cleus
        inputText = rootView.findViewById(R.id.search_product_name);
        searchBtn = rootView.findViewById(R.id.search_btn);
        searchList = rootView.findViewById(R.id.search_list);
        searchList.setLayoutManager(new LinearLayoutManager(getContext()));
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchInput = inputText.getText().toString();
                onStart();


            }
        });

        return rootView;

    }

    /*private List<FoodProviderItem> fillTheList() {
        List<FoodProviderItem> foodProviderList= new ArrayList<>();
        foodProviderList.add(new FoodProviderItem(R.drawable.mezefreshimage, "Meze Fresh", "This restaurant is so refreshing, we have smoothies, ...", "1.2km"));
        foodProviderList.add(new FoodProviderItem(R.drawable.habesha_image, "Habesha", "We have special atmosphere, and ethiopian style coffee...", "674m", "4.8", "83"));
        foodProviderList.add(new FoodProviderItem(R.drawable.thehut_rest_image, "The Hut", "The Hut Restaurant & Boutique Hotel is located at close proximity to the...", "74m", "4.4", "65"));
        foodProviderList.add(new FoodProviderItem(R.drawable.cocobean_image, "Cocobean", "Cocobean offers the best African and European cuisine! Our coffee shop has the ...", "152m", "4.9", "169"));
        return  foodProviderList;
    }*/


    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Businesses");
        FirebaseRecyclerOptions<Business> options = new FirebaseRecyclerOptions.Builder<Business>()
                .setQuery(reference.orderByChild("p_name").startAt(searchInput), Business.class)
                .build();
        FirebaseRecyclerAdapter<Business, BusinessViewHolder> adapter =
                new FirebaseRecyclerAdapter<Business, BusinessViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull BusinessViewHolder holder, int position, @NonNull final Business model) {
                        holder.txtBusinessName.setText(model.getPname());
                        holder.txtBusinessDescription.setText(model.getDescription());
                        holder.txtBusinessCategory.setText("Category: " + model.getCategory());
                        Picasso.get().load(model.getImage()).into(holder.imageView);
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                /*Intent intent =new Intent(getContext(),BusinessDetailsActivity.class);
                                intent.putExtra("pid",model.getPid());
                                startActivity(intent);*/
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_food_item_row, parent, false);
                        BusinessViewHolder holder = new BusinessViewHolder(view);
                        return holder;
                    }
                };
        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}