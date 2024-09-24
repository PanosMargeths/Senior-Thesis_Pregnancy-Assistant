package com.example.pa;
import android.annotation.SuppressLint;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateUtils;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.type.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
public class HomeFragment extends Fragment  {
   TextView result_age , result_weight, result_bp, result_epilepsy, result_diabetes, result_obese, users_startdate, hub_usersname, users_pgmonth, users_startweek, users_remainingdays;
   TextView usersName, usersCountry, cyclebutton;
   FirebaseAuth mAuth;
   FirebaseFirestore fStore;
   String userID;
   TextView personalinfobutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        users_startweek = view.findViewById(R.id.users_startdate);
        users_pgmonth = view.findViewById(R.id.users_pgmonth);
        users_startdate = view.findViewById(R.id.users_startdate2);
        result_age = view.findViewById(R.id.result_age);
        result_weight = view.findViewById(R.id.result_weight);
        result_bp = view.findViewById(R.id.result_bp);
        result_epilepsy = view.findViewById(R.id.result_epilepsy);
        result_diabetes = view.findViewById(R.id.result_diabetes);
        result_obese = view.findViewById(R.id.result_obese);
        hub_usersname = view.findViewById(R.id.hub_usersname);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        users_remainingdays= view.findViewById(R.id.users_remainingdays);
        personalinfobutton = view.findViewById(R.id.personalinfobutton);
        cyclebutton = view.findViewById(R.id.cyclebutton);


        // entering the firebase firestore table pregnacny_data and assigning the data in the table to the variables
        // using DocumentReference to reference the pregnancy_data table with the specific document that has the users id
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("pregnancy_data").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                result_age.setText(snapshot.getString("Age"));
                result_weight.setText(snapshot.getString("Weight"));
                result_bp.setText(snapshot.getString("Blood Pressure"));
                result_epilepsy.setText(snapshot.getString("Epilepsy"));
                result_diabetes.setText(snapshot.getString("Diabetes"));
                result_obese.setText(snapshot.getString("Obese"));
                users_startdate.setText(snapshot.getString("Pregnancy Date"));
            }
        });

        // referencing the users table in firestore users with the specific document that has the users id and takes the username assigned to the user and adds it to the hub_username variable
        DocumentReference documentReference2 = fStore.collection("users").document(userID);
        documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                hub_usersname.setText(snapshot.getString("Username"));

            }
        });

        // referencing the pregnancy_data table and the document that has the users id and takes the Pregnancy Date2 and adds it to String users_data
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                String users_date;
                users_date = snapshot.getString("Pregnancy Date2");

                // calling the calculateDateDiff method and giving the String users_date so it calculates the date difference of the users date and the current date.
                // calling the calculateDaysLeft(users_date) method and giving the String users_date so ti calculates the days difference of the current date to the end date of pregnancy
                // using try to catch the RuntimeExceptions that the methods throw
                try {
                    calculateDateDiff(users_date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
               }
                try {
                    calculateDaysLeft(users_date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // using the personalinfobutton to transfer the user to the personal_InfoFragment with the Fragment transaction.
        personalinfobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Personal_InfoFragment fragment = new Personal_InfoFragment();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("HomeFragment");
                fragmentTransaction.commit();
            }
        });


       // using the cyclebutton to transfer the user to the CycleFragment with the Fragment transaction.
        cyclebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CycleFragment fragment = new CycleFragment();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.addToBackStack("HomeFragment");
                fragmentTransaction.commit();
            }
        });


        return view;
    }

    // Calculates the difference of 2 dates, 1st the pregnancy date of the user, and 2nd the current date.
    // caluclates the difference with milliseconds.
    // assignes the results in weeks and puts the weeks in the users_startweek
    public void calculateDateDiff(String userdate) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date1 = simpleDateFormat.parse("7/24/2024");
        Date date2 = simpleDateFormat.parse("8/2/2024");//9

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

        long elapsedWeeks = different / weeksInMilli;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String weeks = String.valueOf(elapsedWeeks);
        users_startweek.setText(weeks);

    }

    // calculates the days difference between 2 dates,  1st the date of the current date, and 2nd the pregnancy date + 9 months.
    // the calculation happens using milliseconds
    // assigns the result in days to the String Days
    // assigns the days difference in the users_remainingdays.
    public void calculateDaysLeft(String userdate) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date1 = simpleDateFormat.parse("7/24/2024");
        Date date2 = simpleDateFormat.parse("8/2/2024");//9

        Calendar calendar;
        calendar = Calendar.getInstance();
        String date = simpleDateFormat.format(calendar.getTime());
        Date currentdate = simpleDateFormat.parse(date);


        Date usersdate = simpleDateFormat.parse(userdate);
        usersdate.setMonth(usersdate.getMonth() + 9);


        String Format = "MM/dd";
        users_pgmonth .setText(String.format("%1$tb %1$td", usersdate));


        long different = usersdate.getTime() - currentdate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        String days = String.valueOf(elapsedDays);
        users_remainingdays.setText(days);
    }
}

