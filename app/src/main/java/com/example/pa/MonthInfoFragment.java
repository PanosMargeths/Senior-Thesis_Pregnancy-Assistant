package com.example.pa;

import android.annotation.SuppressLint;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthInfoFragment extends Fragment {

    TextView Month_Number;
    TextView category1, category2 , category3, category4, category5;
    TextView category1_text_1;
    TextView category2_text_1, category2_text_2, category2_text_3;
    TextView category3_text_1, category3_text_2;
    TextView category4_text_1, category4_text_2;
    TextView category5_text_1;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    String userID;
    TextView hub_usersname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_month_info, container, false);

        category1 = view.findViewById(R.id.Month_Category1);
        category2 = view.findViewById(R.id.Month_Category2);
        category3 = view.findViewById(R.id.Month_Category3);
        category4 = view.findViewById(R.id.Month_Category4);
        category5 = view.findViewById(R.id.Month_Category5);

        category1_text_1 = view.findViewById(R.id.Category1_Text_1);

        category2_text_1 = view.findViewById(R.id.Category2_Text_1);
        category2_text_2 = view.findViewById(R.id.Category2_Text_2);
        category2_text_3 = view.findViewById(R.id.Category2_Text_3);


        category3_text_1 = view.findViewById(R.id.Category3_Text_1);
        category3_text_2 = view.findViewById(R.id.Category3_Text_2);


        category4_text_1 = view.findViewById(R.id.Category4_Text_1);
        category4_text_2 = view.findViewById(R.id.Category4_Text_2);

        category5_text_1 = view.findViewById(R.id.Category5_Text_1);


        hub_usersname = view.findViewById(R.id.hub_usersname);




        Month_Number = view.findViewById(R.id.Month_Number);
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // using documentreference to access the pregnancy_data and the document with the users id and then assigns the Pregnancy Date2 from the firestore to the String users_date
        // calling the calculateDateTrimester(users_date) and adds the return value to the String months.
        // calling the getMonthinfo method and assigning it the String months
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("pregnancy_data").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                String users_date;
                users_date = snapshot.getString("Pregnancy Date2");

                try {
                    String months = calculateDateTrimester(users_date);
                    getMonthinfo(months);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        // using documentreference to access the users table and the document of the users id and then takes the Username from the document and adds it to the hub_username
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

    // using documentReference to access the Months table with the String months value in the document so it uses the correct month
    // using the categories and text from the specific months document and adds them to the categories and categorytexts so that it displays the categories and text from the database
    public void getMonthinfo(String months) {
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Months").document(months);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                    category1.setText(snapshot.getString("Category_1"));

                    category1_text_1.setText(snapshot.getString("Category1_Text_1"));

                    category2.setText(snapshot.getString("Category_2"));

                    category2_text_1.setText(snapshot.getString("Category2_Text_1"));
                    category2_text_2.setText(snapshot.getString("Category2_Text_2"));
                    category2_text_3.setText(snapshot.getString("Category2_Text_3"));


                    category3.setText(snapshot.getString("Category_3"));

                    category3_text_1.setText(snapshot.getString("Category3_Text_1"));
                    category3_text_2.setText(snapshot.getString("Category3_Text_2"));

                    category4.setText(snapshot.getString("Category_4"));

                    category4_text_1.setText(snapshot.getString("Category4_Text_1"));
                    category4_text_2.setText(snapshot.getString("Category4_Text_2"));

                    category5.setText(snapshot.getString("Category_5"));

                    category5_text_1.setText(snapshot.getString("Category5_Text_1"));

            }
        });
    }

    // calculates the months difference between the 2 dates, 1st the users pregnancy date and the 2nd is the current date
    // returns the months number through the String month_number
    public String calculateDateTrimester(String userdate) throws ParseException {

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
        Month_Number.setText("Month:  "+ months);

        String month_number = "Month_"+months;

        return month_number;

    }

}