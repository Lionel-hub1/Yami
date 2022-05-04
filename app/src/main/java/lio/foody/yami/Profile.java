package lio.foody.yami;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference profileReference;
    private StorageReference storageProfileRef;
    private FirebaseDatabase profileDatabase;
    public TextView userFirstName, userLastName, userPhone, userCountry, userCity, userEmail;
    public ImageView userProfilePic;
    private static final String USER = "InfoUsers";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Settings.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View thisFrag = inflater.inflate(R.layout.fragment_profile, container, false);
        userFirstName = thisFrag.findViewById(R.id.user_first_name);
        userLastName = thisFrag.findViewById(R.id.user_last_name);
        userPhone = thisFrag.findViewById(R.id.user_phone);
        userCountry = thisFrag.findViewById(R.id.user_country);
        userCity = thisFrag.findViewById(R.id.user_city);
        userEmail = thisFrag.findViewById(R.id.user_email);
        userProfilePic = thisFrag.findViewById(R.id.userprofilePicture);
//        profileReference = FirebaseDatabase.getInstance().getReference().child("InfoUsers");
        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        profileDatabase = FirebaseDatabase.getInstance();
        profileReference = profileDatabase.getReference(USER);
        storageProfileRef = FirebaseStorage.getInstance().getReference("profileImage");

        profileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    if (ds.child("usrEmail").getValue().equals(firebaseUser.getEmail())){
                        userFirstName.setText(ds.child("usrFirstName").getValue(String.class));
                        userLastName.setText(ds.child("usrLastName").getValue(String.class));
                        userPhone.setText(ds.child("usrPhone").getValue(String.class));
                        userCountry.setText(ds.child("usrCountry").getValue(String.class));
                        userCity.setText(ds.child("usrDistrict").getValue(String.class));
                        userEmail.setText(ds.child("usrEmail").getValue(String.class));
                        String link = ds.child("usrImage").getValue(String.class);
                        Picasso.get().load(link).into(userProfilePic);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Inflate the layout for this fragment
        return thisFrag;

    }

}