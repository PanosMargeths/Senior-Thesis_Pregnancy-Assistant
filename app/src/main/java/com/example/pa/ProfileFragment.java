package com.example.pa;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    TextView profileusersusername;
    TextView profileusername, profilepassword, profilecountry, profileemail, profileusersname;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    Button editbutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileusername = view.findViewById(R.id.profileusername);
        profileusersname = view.findViewById(R.id.profileusersname);
        profilepassword = view.findViewById(R.id.profileuserpassword);
        profilecountry = view.findViewById(R.id.profileusercountry);
        profileemail = view.findViewById(R.id.profileuseremail);
        mAuth = FirebaseAuth.getInstance();
        editbutton = view.findViewById(R.id.editbutton);
        fStore = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // uses the DocumentReference to access the users table and the document with the users id.
        // it takes the users data from the users id document and adds them to the class's variables that are referencing the layout xml
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                profileusername.setText(snapshot.getString("Username"));
                profilecountry.setText(snapshot.getString("Country"));
                profileemail.setText(snapshot.getString("Email"));
                profilepassword.setText("*********");
                profileusersname.setText(snapshot.getString("Username"));

            }
        });

        // using the setOnClickListener so when the user clickes on the editbutton to transfer him to the ProfileEditData
        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager()  ;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ProfileEditData fragment = new ProfileEditData();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("HomeFragment");
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {



        }
    }
}