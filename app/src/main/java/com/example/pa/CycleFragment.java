package com.example.pa;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CycleFragment extends Fragment {

   TextView button_monthinfo, button_trimisterinfo, Month_Number, Month_Type, Trimister, hub_usersname;
   FirebaseAuth mAuth;
   FirebaseFirestore fStore;
   String userID;
   ImageView season_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cycle, container, false);


        button_monthinfo = view.findViewById(R.id.button_monthinfo);
        button_trimisterinfo = view.findViewById(R.id.button_trimisterinfo);
        Month_Type = view.findViewById(R.id.Month_Type);
        season_image = view.findViewById(R.id.season_image);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        hub_usersname = view.findViewById(R.id.hub_usersname);

        button_monthinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MonthInfoFragment fragment = new MonthInfoFragment();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("CycleFragment");
                fragmentTransaction.commit();
            }
        });

        button_trimisterinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TrimesterInfoFragment fragment = new TrimesterInfoFragment();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("CycleFragment");
                fragmentTransaction.commit();
            }
        });

        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("pregnancy_data").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                String users_date;
                users_date = snapshot.getString("Pregnancy Date2");

                try {
                    calculateDateTrimester(users_date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                try {
                    getCurrentMonth();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReferencename = fStore.collection("users").document(userID);
        documentReferencename.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                hub_usersname.setText(snapshot.getString("Username"));

            }
        });


        return view;
    }


    public void calculateDateTrimester(String userdate) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");

        Calendar calendar;
        calendar = Calendar.getInstance();
        String date = simpleDateFormat.format(calendar.getTime());
        Date currentdate = simpleDateFormat.parse(date);

        Date usersdate = simpleDateFormat.parse(userdate);

        long different = currentdate.getTime() - usersdate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long weeksInMilli = daysInMilli * 7;
        long MonthsInMilli = daysInMilli * 30;

        long elapsedMonths = different / MonthsInMilli;


        String months = String.valueOf(elapsedMonths);
        if (months.equals("0"))
            months = "1";
        //Month_Number.setText(months);

       /* if (months.equals("1") || months.equals("2") || months.equals("3"))
            Trimister.setText("First Trimester");
        else if (months.equals("4") || months.equals("5") || months.equals("6"))
            Trimister.setText("Second Trimester");
        else if (months.equals("7") || months.equals("8") || months.equals("9"))
            Trimister.setText("Third Trimester");*/



    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void getCurrentMonth() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM");
        Calendar calendar;
        calendar = Calendar.getInstance();
        String date = simpleDateFormat.format(calendar.getTime());

        Month_Type.setText(date);
        //  Summer
        if (date.equals("June") || date.equals("July") || date.equals("August")) {
            season_image.setBackgroundResource(R.drawable.seasson_summer);
        }
        //  Autumn
        else if (date.equals("September") || date.equals("Octomber") || date.equals("November")) {
            season_image.setBackgroundResource(R.drawable.seasson_automn);
        }
        //  Winter
        else if (date.equals("December") || date.equals("January") || date.equals("February")) {
            season_image.setBackgroundResource(R.drawable.seasson_winter);
        }
        //  Spring
        else if (date.equals("May") || date.equals("April") || date.equals("March")) {
            season_image.setBackgroundResource(R.drawable.seasson_spring);
        }




    }



}