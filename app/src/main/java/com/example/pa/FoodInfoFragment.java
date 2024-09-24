package com.example.pa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class FoodInfoFragment extends Fragment {
    String userID;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    ImageView foodimage;

    TextView Category_1, Category_2, Category_3;
    TextView Category1_Text_1, Category1_Text_2, Category1_Text_3;
    TextView Category2_Text_1, Category2_Text_2, Category2_Text_3;
    TextView Category3_Text_1, Category3_Text_2, Category3_Text_3;
    TextView whitebar, hub_usersname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_info, container, false);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        whitebar = view.findViewById(R.id.whitebar);

        foodimage = view.findViewById(R.id.foodimage);

        Category_1 = view.findViewById(R.id.Category1);
        Category_2 = view.findViewById(R.id.Category2);
        Category_3 = view.findViewById(R.id.Category3);

        Category1_Text_1 = view.findViewById(R.id.Category1_Text_1);
        Category1_Text_2 = view.findViewById(R.id.Category1_Text_2);
        Category1_Text_3 = view.findViewById(R.id.Category1_Text_3);

        Category2_Text_1 = view.findViewById(R.id.Category2_Text_1);
        Category2_Text_2 = view.findViewById(R.id.Category2_Text_2);
        Category2_Text_3 = view.findViewById(R.id.Category2_Text_3);

        Category3_Text_1 = view.findViewById(R.id.Category3_Text_1);
        Category3_Text_2 = view.findViewById(R.id.Category3_Text_2);
        Category3_Text_3 = view.findViewById(R.id.Category3_Text_3);

        hub_usersname = view.findViewById(R.id.hub_usersname);

        whitebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BadFoodsFragment fragment = new BadFoodsFragment();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("FoodInfoFragment");
                fragmentTransaction.commit();
            }
        });
        String foodid = getArguments().getString("Food");
        getFoodinfo(foodid);

        if (foodid == "Porridge_Fruit")
            foodimage.setBackgroundResource(R.drawable.poridge);
        else if (foodid == "Beef_Stew")
            foodimage.setBackgroundResource(R.drawable.beefstew);
        else if (foodid == "Fish")
            foodimage.setBackgroundResource(R.drawable.fish);
        else if (foodid == "Eggs_Bagel")
            foodimage.setBackgroundResource(R.drawable.scrambledeggs);
        else if (foodid == "Macaroni_Cheese")
            foodimage.setBackgroundResource(R.drawable.macaronicheese);
        else if (foodid == "EggsSalad_Sandwich")
            foodimage.setBackgroundResource(R.drawable.eggsandwich);
        else if (foodid == "Rotini_Chicken")
            foodimage.setBackgroundResource(R.drawable.rotinichickengarden);
        else if (foodid == "Smoothie")
            foodimage.setBackgroundResource(R.drawable.smoothie);
        else if (foodid == "Toast_Milk")
            foodimage.setBackgroundResource(R.drawable.toast);
        else if (foodid == "Yoghurt_Toppings")
            foodimage.setBackgroundResource(R.drawable.yogurt);
        else if (foodid == "Burrito_Bowl")
            foodimage.setBackgroundResource(R.drawable.burritobowl);
        else if (foodid == "Carrot_Soup")
            foodimage.setBackgroundResource(R.drawable.carrotsoup);
        else if (foodid == "CornMeal_CrustedChicken")
            foodimage.setBackgroundResource(R.drawable.cornmealchicken);
        else if (foodid == "SesameVegi_Chicken")
            foodimage.setBackgroundResource(R.drawable.vegetablemedley);
        else if (foodid == "Chicken_Salad")
            foodimage.setBackgroundResource(R.drawable.chickensalad);


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


    public void getFoodinfo(String food) {


        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Food_Information").document(food);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Category_1.setText(snapshot.getString("Category_1"));

                Category1_Text_1.setText(snapshot.getString("Category_1_Text_1"));
                Category1_Text_2.setText(snapshot.getString("Category_1_Text_2"));
                Category1_Text_3.setText(snapshot.getString("Category_1_Text_3"));

                Category_2.setText(snapshot.getString("Category_2"));

                Category2_Text_1.setText(snapshot.getString("Category_2_Text_1"));
                Category2_Text_2.setText(snapshot.getString("Category_2_Text_2"));
                Category2_Text_3.setText(snapshot.getString("Category_2_Text_3"));


                Category_3.setText(snapshot.getString("Category_3"));

                Category3_Text_1.setText(snapshot.getString("Category_3_Text_1"));
                Category3_Text_2.setText(snapshot.getString("Category_3_Text_2"));
                Category3_Text_3.setText(snapshot.getString("Category_3_Text_2"));

            }
        });
    }
}