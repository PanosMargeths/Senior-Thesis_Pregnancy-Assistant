package com.example.pa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class BadFoodsFragment extends Fragment {

    FirebaseFirestore fStore;
    FirebaseAuth mAuth;


    TextView Dairy_Category_1, Dairy_Category_2, Dairy_Category_3;
    TextView Dairy_Category_1_Text_1, Dairy_Category_1_Text_2, Dairy_Category_1_Text_3, Dairy_Category_1_Text_4, Dairy_Category_1_Text_5;
    TextView Dairy_Category_2_Text_1, Dairy_Category_2_Text_2, Dairy_Category_2_Text_3, Dairy_Category_2_Text_4;
    TextView Dairy_Category_3_Text_1, Dairy_Category_3_Text_2, Dairy_Category_3_Text_3, Dairy_Category_3_Text_4;


    TextView Eggs_Category_1, Eggs_Category_2, Eggs_Category_3;
    TextView Eggs_Category_1_Text_1, Eggs_Category_1_Text_2, Eggs_Category_1_Text_3, Eggs_Category_1_Text_4;
    TextView Eggs_Category_2_Text_1, Eggs_Category_2_Text_2;
    TextView Eggs_Category_3_Text_1, Eggs_Category_3_Text_2, Eggs_Category_3_Text_3;


    TextView Fish_Category_1, Fish_Category_2, Fish_Category_3, Fish_Category_4;
    TextView Fish_Category_1_Text_1, Fish_Category_1_Text_2, Fish_Category_1_Text_3;
    TextView Fish_Category_2_Text_1, Fish_Category_2_Text_2, Fish_Category_2_Text_3, Fish_Category_2_Text_4,Fish_Category_2_Text_5;
    TextView Fish_Category_3_Text_1, Fish_Category_3_Text_2, Fish_Category_3_Text_3, Fish_Category_3_Text_4;
    TextView Fish_Category_4_Text_1, Fish_Category_4_Text_2;


    TextView Meat_Category_1, Meat_Category_2, Meat_Category_3, Meat_Category_4;
    TextView Meat_Category_1_Text_1, Meat_Category_1_Text_2;
    TextView Meat_Category_2_Text_1, Meat_Category_2_Text_2, Meat_Category_2_Text_3, Meat_Category_2_Text_4;
    TextView Meat_Category_3_Text_1, Meat_Category_3_Text_2, Meat_Category_3_Text_3, Meat_Category_3_Text_4;
    TextView Meat_Category_4_Text_1, Meat_Category_4_Text_2;


    TextView Other_Category_1, Other_Category_2, Other_Category_3, Other_Category_4, Other_Category_5, Other_Category_6, Other_Category_7;
    TextView Other_Category_1_Text_1;
    TextView Other_Category_2_Text_1, Other_Category_2_Text_2, Other_Category_2_Text_3;
    TextView Other_Category_3_Text_1, Other_Category_3_Text_2, Other_Category_3_Text_3;
    TextView Other_Category_4_Text_1;
    TextView Other_Category_5_Text_1, Other_Category_5_Text_2;
    TextView Other_Category_6_Text_1;
    TextView Other_Category_7_Text_1, Other_Category_7_Text_2;

    TextView hub_usersname;
    String userID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bad_foods, container, false);

        hub_usersname = view.findViewById(R.id.hub_usersname);

        Dairy_Category_1 = view.findViewById(R.id.Dairy_Category1);
        Dairy_Category_2 = view.findViewById(R.id.Dairy_Category2);
        Dairy_Category_3 = view.findViewById(R.id.Dairy_Category3);

        Dairy_Category_1_Text_1 = view.findViewById(R.id.Dairy_Category1_Text_1);
        Dairy_Category_1_Text_2 = view.findViewById(R.id.Dairy_Category1_Text_2);
        Dairy_Category_1_Text_3 = view.findViewById(R.id.Dairy_Category1_Text_3);
        Dairy_Category_1_Text_4 = view.findViewById(R.id.Dairy_Category1_Text_4);
        Dairy_Category_1_Text_5 = view.findViewById(R.id.Dairy_Category1_Text_5);

        Dairy_Category_2_Text_1 = view.findViewById(R.id.Dairy_Category2_Text_1);
        Dairy_Category_2_Text_2 = view.findViewById(R.id.Dairy_Category2_Text_2);
        Dairy_Category_2_Text_3 = view.findViewById(R.id.Dairy_Category2_Text_3);
        Dairy_Category_2_Text_4 = view.findViewById(R.id.Dairy_Category2_Text_4);

        Dairy_Category_3_Text_1 = view.findViewById(R.id.Dairy_Category3_Text_1);
        Dairy_Category_3_Text_2 = view.findViewById(R.id.Dairy_Category3_Text_2);
        Dairy_Category_3_Text_3 = view.findViewById(R.id.Dairy_Category3_Text_3);
        Dairy_Category_3_Text_4 = view.findViewById(R.id.Dairy_Category3_Text_4);


        Eggs_Category_1 = view.findViewById(R.id.Eggs_Category1);
        Eggs_Category_2 = view.findViewById(R.id.Eggs_Category2);
        Eggs_Category_3 = view.findViewById(R.id.Eggs_Category3);

        Eggs_Category_1_Text_1 = view.findViewById(R.id.Eggs_Category1_Text_1);
        Eggs_Category_1_Text_2 = view.findViewById(R.id.Eggs_Category1_Text_2);
        Eggs_Category_1_Text_3 = view.findViewById(R.id.Eggs_Category1_Text_3);
        Eggs_Category_1_Text_4 = view.findViewById(R.id.Eggs_Category1_Text_4);

        Eggs_Category_2_Text_1 = view.findViewById(R.id.Eggs_Category2_Text_1);
        Eggs_Category_2_Text_2 = view.findViewById(R.id.Eggs_Category2_Text_2);


        Eggs_Category_3_Text_1 = view.findViewById(R.id.Eggs_Category3_Text_1);
        Eggs_Category_3_Text_2 = view.findViewById(R.id.Eggs_Category3_Text_2);
        Eggs_Category_3_Text_3 = view.findViewById(R.id.Eggs_Category3_Text_3);


        Fish_Category_1 = view.findViewById(R.id.Fish_Category1);
        Fish_Category_2 = view.findViewById(R.id.Fish_Category2);
        Fish_Category_3 = view.findViewById(R.id.Fish_Category3);
        Fish_Category_4 = view.findViewById(R.id.Fish_Category4);

        Fish_Category_1_Text_1 = view.findViewById(R.id.Fish_Category1_Text_1);
        Fish_Category_1_Text_2 = view.findViewById(R.id.Fish_Category1_Text_2);
        Fish_Category_1_Text_3 = view.findViewById(R.id.Fish_Category1_Text_3);


        Fish_Category_2_Text_1 = view.findViewById(R.id.Fish_Category2_Text_1);
        Fish_Category_2_Text_2 = view.findViewById(R.id.Fish_Category2_Text_2);
        Fish_Category_2_Text_3 = view.findViewById(R.id.Fish_Category2_Text_3);
        Fish_Category_2_Text_4 = view.findViewById(R.id.Fish_Category2_Text_4);
        Fish_Category_2_Text_5 = view.findViewById(R.id.Fish_Category2_Text_5);


        Fish_Category_3_Text_1 = view.findViewById(R.id.Fish_Category3_Text_1);
        Fish_Category_3_Text_2 = view.findViewById(R.id.Fish_Category3_Text_2);
        Fish_Category_3_Text_3 = view.findViewById(R.id.Fish_Category3_Text_3);
        Fish_Category_3_Text_4 = view.findViewById(R.id.Fish_Category3_Text_4);

        Fish_Category_4_Text_1 = view.findViewById(R.id.Fish_Category4_Text_1);
        Fish_Category_4_Text_2 = view.findViewById(R.id.Fish_Category4_Text_2);


        Meat_Category_1 = view.findViewById(R.id.Meat_Category1);
        Meat_Category_2 = view.findViewById(R.id.Meat_Category2);
        Meat_Category_3 = view.findViewById(R.id.Meat_Category3);
        Meat_Category_4 = view.findViewById(R.id.Meat_Category4);

        Meat_Category_1_Text_1 = view.findViewById(R.id.Meat_Category1_Text_1);
        Meat_Category_1_Text_2 = view.findViewById(R.id.Meat_Category1_Text_2);

        Meat_Category_2_Text_1 = view.findViewById(R.id.Meat_Category2_Text_1);
        Meat_Category_2_Text_2 = view.findViewById(R.id.Meat_Category2_Text_2);
        Meat_Category_2_Text_3 = view.findViewById(R.id.Meat_Category2_Text_3);
        Meat_Category_2_Text_4 = view.findViewById(R.id.Meat_Category2_Text_4);

        Meat_Category_3_Text_1 = view.findViewById(R.id.Meat_Category3_Text_1);
        Meat_Category_3_Text_2 = view.findViewById(R.id.Meat_Category3_Text_2);
        Meat_Category_3_Text_3 = view.findViewById(R.id.Meat_Category3_Text_3);
        Meat_Category_3_Text_4 = view.findViewById(R.id.Meat_Category3_Text_4);

        Meat_Category_4_Text_1 = view.findViewById(R.id.Meat_Category4_Text_1);
        Meat_Category_4_Text_2 = view.findViewById(R.id.Meat_Category4_Text_2);


        Other_Category_1 = view.findViewById(R.id.Other_Category1);
        Other_Category_2 = view.findViewById(R.id.Other_Category2);
        Other_Category_3 = view.findViewById(R.id.Other_Category3);
        Other_Category_4 = view.findViewById(R.id.Other_Category4);
        Other_Category_5 = view.findViewById(R.id.Other_Category5);
        Other_Category_6 = view.findViewById(R.id.Other_Category6);
        Other_Category_7 = view.findViewById(R.id.Other_Category7);

        Other_Category_1_Text_1 = view.findViewById(R.id.Other_Category1_Text_1);

        Other_Category_2_Text_1 = view.findViewById(R.id.Other_Category2_Text_1);
        Other_Category_2_Text_2 = view.findViewById(R.id.Other_Category2_Text_2);
        Other_Category_2_Text_3 = view.findViewById(R.id.Other_Category2_Text_3);

        Other_Category_3_Text_1 = view.findViewById(R.id.Other_Category3_Text_1);
        Other_Category_3_Text_2 = view.findViewById(R.id.Other_Category3_Text_2);
        Other_Category_3_Text_3 = view.findViewById(R.id.Other_Category3_Text_3);

        Other_Category_4_Text_1 = view.findViewById(R.id.Other_Category4_Text_1);

        Other_Category_5_Text_1 = view.findViewById(R.id.Other_Category5_Text_1);
        Other_Category_5_Text_2 = view.findViewById(R.id.Other_Category5_Text_2);

        Other_Category_6_Text_1 = view.findViewById(R.id.Other_Category6_Text_1);

        Other_Category_7_Text_1 = view.findViewById(R.id.Other_Category7_Text_1);
        Other_Category_7_Text_2 = view.findViewById(R.id.Other_Category7_Text_2);



        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        DocumentReference documentReference = fStore.collection("Avoid_Foods").document("Dairy");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Dairy_Category_1.setText(snapshot.getString("Category_1"));
                Dairy_Category_2.setText(snapshot.getString("Category_2 "));
                Dairy_Category_3.setText(snapshot.getString("Category_3"));

                Dairy_Category_1_Text_1.setText(snapshot.getString("Category_1_Text_1"));
                Dairy_Category_1_Text_2.setText(snapshot.getString("Category_1_Text_2"));
                Dairy_Category_1_Text_3.setText(snapshot.getString("Category_1_Text_3"));
                Dairy_Category_1_Text_4.setText(snapshot.getString("Category_1_Text_4"));
                Dairy_Category_1_Text_5.setText(snapshot.getString("Category_1_Text_5"));

                Dairy_Category_2_Text_1.setText(snapshot.getString("Category_2_Text_1"));
                Dairy_Category_2_Text_2.setText(snapshot.getString("Category_2_Text_2"));
                Dairy_Category_2_Text_3.setText(snapshot.getString("Category_2_Text_3"));
                Dairy_Category_2_Text_4.setText(snapshot.getString("Category_2_Text_4"));

                Dairy_Category_3_Text_1.setText(snapshot.getString("Category_3_Text_1"));
                Dairy_Category_3_Text_2.setText(snapshot.getString("Category_3_Text_2"));
                Dairy_Category_3_Text_3.setText(snapshot.getString("Category_3_Text_3"));
                Dairy_Category_3_Text_4.setText(snapshot.getString("Category_3_Text_4"));

            }
        });


        DocumentReference documentReference2 = fStore.collection("Avoid_Foods").document("Eggs");
        documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Eggs_Category_1.setText(snapshot.getString("Category_1"));
                Eggs_Category_2.setText(snapshot.getString("Category_2"));
                Eggs_Category_3.setText(snapshot.getString("Category_3"));

                Eggs_Category_1_Text_1.setText(snapshot.getString("Category_1_Text_1"));
                Eggs_Category_1_Text_2.setText(snapshot.getString("Category_1_Text_2"));
                Eggs_Category_1_Text_3.setText(snapshot.getString("Category_1_Text_3"));
                Eggs_Category_1_Text_4.setText(snapshot.getString("Category_1_Text_4"));


                Eggs_Category_2_Text_1.setText(snapshot.getString("Category_2_Text_1"));
                Eggs_Category_2_Text_2.setText(snapshot.getString("Category_2_Text_2"));


                Eggs_Category_3_Text_1.setText(snapshot.getString("Category_3_Text_1"));
                Eggs_Category_3_Text_2.setText(snapshot.getString("Category_3_Text_2"));
                Eggs_Category_3_Text_3.setText(snapshot.getString("Category_3_Text_3"));


            }
        });


        DocumentReference documentReference3 = fStore.collection("Avoid_Foods").document("Fish");
        documentReference3.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Fish_Category_1.setText(snapshot.getString("Category_1"));
                Fish_Category_2.setText(snapshot.getString("Category_2"));
                Fish_Category_3.setText(snapshot.getString("Category_3"));
                Fish_Category_4.setText(snapshot.getString("Category_4"));

                Fish_Category_1_Text_1.setText(snapshot.getString("Category_1_Text_1"));
                Fish_Category_1_Text_2.setText(snapshot.getString("Category_1_Text_2"));
                Fish_Category_1_Text_3.setText(snapshot.getString("Category_1_Text_3"));

                Fish_Category_2_Text_1.setText(snapshot.getString("Category_2_Text_1"));
                Fish_Category_2_Text_2.setText(snapshot.getString("Category_2_Text_2"));
                Fish_Category_2_Text_3.setText(snapshot.getString("Category_2_Text_3"));
                Fish_Category_2_Text_4.setText(snapshot.getString("Category_2_Text_4"));
                Fish_Category_2_Text_5.setText(snapshot.getString("Category_2_Text_5"));


                Fish_Category_3_Text_1.setText(snapshot.getString("Category_3_Text_1"));
                Fish_Category_3_Text_2.setText(snapshot.getString("Category_3_Text_2"));
                Fish_Category_3_Text_3.setText(snapshot.getString("Category_3_Text_3"));
                Fish_Category_3_Text_4.setText(snapshot.getString("Category_3_Text_4"));

                Fish_Category_4_Text_1.setText(snapshot.getString("Category_4_Text_1"));
                Fish_Category_4_Text_2.setText(snapshot.getString("Category_4_Text_2"));


            }
        });


        DocumentReference documentReference4 = fStore.collection("Avoid_Foods").document("Meat_Poultry");
        documentReference4.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Meat_Category_1.setText(snapshot.getString("Category_1"));
                Meat_Category_2.setText(snapshot.getString("Category_2"));
                Meat_Category_3.setText(snapshot.getString("Category_3"));
                Meat_Category_4.setText(snapshot.getString("Category_4"));

                Meat_Category_1_Text_1.setText(snapshot.getString("Category_1_Text_1"));
                Meat_Category_1_Text_2.setText(snapshot.getString("Category_1_Text_2"));

                Meat_Category_2_Text_1.setText(snapshot.getString("Category_2_Text_1"));
                Meat_Category_2_Text_2.setText(snapshot.getString("Category_2_Text_2"));
                Meat_Category_2_Text_3.setText(snapshot.getString("Category_2_Text_3"));
                Meat_Category_2_Text_4.setText(snapshot.getString("Category_2_Text_4"));


                Meat_Category_3_Text_1.setText(snapshot.getString("Category_3_Text_1"));
                Meat_Category_3_Text_2.setText(snapshot.getString("Category_3_Text_2"));
                Meat_Category_3_Text_3.setText(snapshot.getString("Category_3_Text_3"));
                Meat_Category_3_Text_4.setText(snapshot.getString("Category_3_Text_4"));

                Meat_Category_4_Text_1.setText(snapshot.getString("Category_4_Text_1"));
                Meat_Category_4_Text_2.setText(snapshot.getString("Category_4_Text_2"));


            }
        });


        DocumentReference documentReference5 = fStore.collection("Avoid_Foods").document("Other");
        documentReference5.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Other_Category_1.setText(snapshot.getString("Category_1"));
                Other_Category_2.setText(snapshot.getString("Category_2"));
                Other_Category_3.setText(snapshot.getString("Category_3"));
                Other_Category_4.setText(snapshot.getString("Category_4"));
                Other_Category_5.setText(snapshot.getString("Category_5"));
                Other_Category_6.setText(snapshot.getString("Category_6"));
                Other_Category_7.setText(snapshot.getString("Category_7"));

                Other_Category_1_Text_1.setText(snapshot.getString("Category_1_Text_1"));

                Other_Category_2_Text_1.setText(snapshot.getString("Category_2_Text_1"));
                Other_Category_2_Text_2.setText(snapshot.getString("Category_2_Text_2"));
                Other_Category_2_Text_3.setText(snapshot.getString("Category_2_Text_3"));

                Other_Category_3_Text_1.setText(snapshot.getString("Category_3_Text_1"));
                Other_Category_3_Text_2.setText(snapshot.getString("Category_3_Text_2"));
                Other_Category_3_Text_3.setText(snapshot.getString("Category_3_Text_3"));

                Other_Category_4_Text_1.setText(snapshot.getString("Category_4_Text_1"));

                Other_Category_5_Text_1.setText(snapshot.getString("Category_5_Text_1"));
                Other_Category_5_Text_2.setText(snapshot.getString("Category_5_Text_2"));

                Other_Category_6_Text_1.setText(snapshot.getString("Category_6_Text_1"));

                Other_Category_7_Text_1.setText(snapshot.getString("Category_7_Text_1"));
                Other_Category_7_Text_2.setText(snapshot.getString("Category_7_Text_2"));



            }
        });


        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReferencefood = fStore.collection("users").document(userID);
        documentReferencefood.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                hub_usersname.setText(snapshot.getString("Username"));

            }
        });
        return view;
    }
}