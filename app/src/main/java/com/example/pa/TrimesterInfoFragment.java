package com.example.pa;

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


public class TrimesterInfoFragment extends Fragment {


    TextView category1, category2 , category3, category4, category5, category6;
    TextView category1_text_1;
    TextView category2_text_1, category2_text_2, category2_text_3;
    TextView category3_text_1, category3_text_2;
    TextView category4_text_1;
    TextView category5_text_1, category5_text_2;
    TextView category6_text_1;

     TextView Trimester;
     FirebaseAuth mAuth;
     FirebaseFirestore fStore;
     String userID;
     TextView hub_usersname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_trimester_info, container, false);

        category1 = view.findViewById(R.id.Trimester_Category1);
        category2 = view.findViewById(R.id.Trimester_Category2);
        category3 = view.findViewById(R.id.Trimester_Category3);
        category4 = view.findViewById(R.id.Trimester_Category4);
        category5 = view.findViewById(R.id.Trimester_Category5);
        category6 = view.findViewById(R.id.Trimester_Category6);

        category1_text_1 = view.findViewById(R.id.Category1_Text_1);

        category2_text_1 = view.findViewById(R.id.Category2_Text_1);
        category2_text_2 = view.findViewById(R.id.Category2_Text_2);
        category2_text_3 = view.findViewById(R.id.Category2_Text_3);


        category3_text_1 = view.findViewById(R.id.Category3_Text_1);
        category3_text_2 = view.findViewById(R.id.Category3_Text_2);


        category4_text_1 = view.findViewById(R.id.Category4_Text_1);

        category5_text_1 = view.findViewById(R.id.Category5_Text_1);
        category5_text_2 = view.findViewById(R.id.Category5_Text_2);

        category6_text_1 = view.findViewById(R.id.Category6_Text_1);

        hub_usersname = view.findViewById(R.id.hub_usersname);

        Trimester = view.findViewById(R.id.Trimester);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // using the DocumentReference to access the pregnancy_data table and the document with the users id then adds the Pregnancy Date2 to the String users_date
        // calling the calculateDateTrimester(users_date) using the String users_date and then adds its return value to the String trimester
        // calling the getTrimesterinfo(trimester) using the String trimester.
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("pregnancy_data").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                String users_date;
                users_date = snapshot.getString("Pregnancy Date2");

                try {
                    String trimester = calculateDateTrimester(users_date);
                    getTrimesterinfo(trimester);

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        // using the DocumentReference to access the table users with the document containing the users id
        // assigns the Username value from the document to the hub_username
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

    // using the DocumentReference to access the Trimester table with the specific document containing the number of the specific trimester
    // adds all the data from the trimester document to the class's variables referencing the Layout XML.
    public void getTrimesterinfo(String trimester) {

        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("Trimester").document(trimester);
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

                category5.setText(snapshot.getString("Category_5"));

                category5_text_1.setText(snapshot.getString("Category5_Text_1"));
                category5_text_2.setText(snapshot.getString("Category5_Text_2"));

                category6.setText(snapshot.getString("Category_6"));

                category6_text_1.setText(snapshot.getString("Category6_Text_1"));

            }
        });
    }

    // calculates at what trimester in pregnancy the woman is with this method by calculating the months difference of the current date and the users pregnancy date.
    // the calculatation is happenning with milliseconds and then takes the months number and enters the if to calculate the trimester by checking if the months are between 1-3 for 1st trimester
    // 4-6 for the second trimester and then the 7-10 for the 3rd trimester
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

        String trimester1 = "Empty";
        String trimester2 = "Empty";
        String months = String.valueOf(elapsedMonths);
        if (months.equals("1") || months.equals("2") || months.equals("3")){
            trimester1 = "First Trimester";
            trimester2 = "First_Trimester";}

        else if (months.equals("4") || months.equals("5") || months.equals("6")){
            trimester1 = "Second_Trimester";
            trimester2 = "Second_Trimester";}
        else if (months.equals("7") || months.equals("8") || months.equals("9") || months.equals("10")){
            trimester1 = "Third_Trimester";
            trimester2 = "Third_Trimester";}

        Trimester.setText(trimester1);
        return trimester2;

    }
}