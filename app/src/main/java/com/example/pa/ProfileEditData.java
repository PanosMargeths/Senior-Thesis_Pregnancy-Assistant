package com.example.pa;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;
import java.util.Map;

public class ProfileEditData extends Fragment {

    Button editbutton ;
    EditText EditTextusername , EditTextpassword , EditTextcountry;
    FirebaseAuth mAuth;
    String userID;
    FirebaseFirestore fstore;
    //DatabaseReference reference;
    DocumentReference documentReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit_data, container, false);

        editbutton = view.findViewById(R.id.editbutton);
        EditTextusername = view.findViewById(R.id.profilesname);
        EditTextpassword = view.findViewById(R.id.profilepassword);
        EditTextcountry = view.findViewById(R.id.profilecountry);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateProfile();
            }
        });


        return view;
    }

    // this method takes the new data the user gave and replaces the old ones in the users tables
    private void updateProfile() {

        String name , password , email , country;
        name = EditTextusername.getText().toString();
        password = EditTextpassword.getText().toString();
        country = EditTextcountry.getText().toString();
        FragmentActivity profileEditData = getActivity();

        // using the DocumentReference to access the users table with the document that contains the users id
        // enters the if which checks if the variables are not empty and if they are not then the app replaces the previous data in the table with the new ones.
        // if the vriables are empty the app exits the method.
        userID = mAuth.getCurrentUser().getUid();
        final DocumentReference document = fstore.collection("users").document(userID);

        fstore.runTransaction(new Transaction.Function<Void>() {
                    @Override
                    public Void apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                       // DocumentSnapshot snapshot = transaction.get(sfDocRef);

                        if (name.length()>1)
                        transaction.update(document, "Username", name );

                        else if (password.length()>1)
                        transaction.update(document, "Password", password);

                        else if (country.length()>1)
                        transaction.update(document, "Country",  country );


                        // Success
                        return null;
                    }
                }).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getParentFragmentManager()  ;
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        ProfileFragment fragment = new ProfileFragment();
                        fragmentTransaction.add(R.id.framelayout, fragment);
                        fragmentTransaction.commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Update Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}