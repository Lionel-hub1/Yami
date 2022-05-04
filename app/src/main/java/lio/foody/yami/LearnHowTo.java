package lio.foody.yami;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearnHowTo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnHowTo extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecipeAdapter recipeAdapter;
    List<RecipeItem> recipeItemList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearnHowTo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearnHowTo.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnHowTo newInstance(String param1, String param2) {
        LearnHowTo fragment = new LearnHowTo();
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
        View rootView = inflater.inflate(R.layout.fragment_learn_how_to, container, false);
        recyclerView = rootView.findViewById(R.id.recipes_recycler_id);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recipeItemList = new ArrayList<>();
        recipeItemList = fillTheList();
        recipeAdapter = new RecipeAdapter(getContext(), recipeItemList);
        recyclerView.setAdapter(recipeAdapter);
        return rootView;
    }
    private List<RecipeItem> fillTheList() {
        List<RecipeItem> recipeItemList= new ArrayList<>();
        recipeItemList.add(new RecipeItem(R.drawable.sample_recipe_image, "Recipe 1", "This is the description of the Recipe 1..."));
        recipeItemList.add(new RecipeItem(R.drawable.sample_recipe_image, "Recipe 2", "This is the description of the Recipe 2..."));
        recipeItemList.add(new RecipeItem(R.drawable.sample_recipe_image, "Recipe 3", "This is the description of the Recipe 3..."));
        recipeItemList.add(new RecipeItem(R.drawable.sample_recipe_image, "Recipe 4", "This is the description of the Recipe 4..."));
        return  recipeItemList;
    }
}