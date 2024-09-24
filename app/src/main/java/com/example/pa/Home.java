package com.example.pa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.internal.NavigationMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class Home extends AppCompatActivity {

    TextView usersname;

    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String userID;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //usersname = findViewById(R.id.userName);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        fStore = FirebaseFirestore.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.framelayout);

        loadFragment(new PregnancyInfoFragment(), true);

        //logoutbutton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.logout, 0, 0);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemid = item.getItemId();

                if (itemid == R.id.home){
                    loadFragment(new HomeFragment(), true);

                }else if (itemid == R.id.settings){
                    loadFragment(new SettingsFragment(), true);


                }else if (itemid == R.id.diet){
                    loadFragment(new FoodFragment(), true);

                }else if (itemid == R.id.profile) {
                    loadFragment(new ProfileFragment(), true);

                }
                //loadFragment(new HomeFragment(), true);

                return true;
            }
        });


        if (user == null) {
           Intent intent = new Intent(getApplicationContext(), MainActivity.class);
           startActivity(intent);
           finish();
        }


    }

    public void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (isAppInitialized) {
            fragmentTransaction.add(R.id.framelayout, fragment);

        }else {
            fragmentTransaction.replace(R.id.framelayout, new ProfileFragment());
        }


        fragmentTransaction.commit();

    }
}
