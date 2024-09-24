package com.example.pa;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FoodFragment extends Fragment {


    ImageView breakfast_1, breakfast_2, breakfast_3 , breakfast_4, breakfast_5;
    ImageView Lunch_1, Lunch_2, Lunch_3, Lunch_4, Lunch_5;
    ImageView Dinner_1, Dinner_2, Dinner_3, Dinner_4, Dinner_5;
    TextView whitebar, hub_usersname;
    String userID;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String food;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.fragment_food, container, false);

        breakfast_1 = view.findViewById(R.id.breakfast_1);
        breakfast_2 = view.findViewById(R.id.breakfast_2);
        breakfast_3 = view.findViewById(R.id.breakfast_3);
        breakfast_4 = view.findViewById(R.id.breakfast_4);
        breakfast_5 = view.findViewById(R.id.breakfast_5);

        Lunch_1 = view.findViewById(R.id.Lunch_1);
        Lunch_2 = view.findViewById(R.id.Lunch_2);
        Lunch_3 = view.findViewById(R.id.Lunch_3);
        Lunch_4 = view.findViewById(R.id.Lunch_4);
        Lunch_5 = view.findViewById(R.id.Lunch_5);

        Dinner_1 = view.findViewById(R.id.Dinner_1);
        Dinner_2 = view.findViewById(R.id.Dinner_2);
        Dinner_3 = view.findViewById(R.id.Dinner_3);
        Dinner_4 = view.findViewById(R.id.Dinner_4);
        Dinner_5 = view.findViewById(R.id.Dinner_5);

        whitebar = view.findViewById(R.id.whitebar);

        hub_usersname = view.findViewById(R.id.hub_usersname);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        whitebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BadFoodsFragment fragment = new BadFoodsFragment();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });



        // BREAKFAST




        breakfast_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Porridge_Fruit";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        breakfast_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Toast_Milk";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        breakfast_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Smoothie";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        breakfast_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Eggs_Bagel";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        breakfast_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Yoghurt_Toppings";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });



        // LUNCH



        Lunch_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Fish";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Lunch_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Burrito_Bowl";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Lunch_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Chicken_Salad";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Lunch_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Carrot_Soup";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Lunch_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "EggsSalad_Sandwich";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });



        // DINNER

        Dinner_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Rotini_Chicken";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Dinner_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "SesameVegi_Chicken";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Dinner_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "CornMeal_CrustedChicken";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Dinner_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Macaroni_Cheese";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });
        Dinner_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food = "Beef_Stew";


                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FoodInfoFragment fragment = new FoodInfoFragment();
                Bundle args = new Bundle();
                args.putString("Food", food);
                fragment.setArguments(args);
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodFragment");
                fragmentTransaction.commit();
            }
        });

        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference2 = fStore.collection("users").document(userID);
        documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                hub_usersname.setText(snapshot.getString("Username"));

            }
        });
        return view;
    }
}