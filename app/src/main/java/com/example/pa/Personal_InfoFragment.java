package com.example.pa;

import android.os.Bundle;

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

import org.w3c.dom.Text;


public class Personal_InfoFragment extends Fragment {
    TextView editbutton;
    TextView Epilepsy_text1, Epilepsy_text2, Epilepsy_text3, Epilespy_Category2, Epilespy_Category1;
    TextView BloodP_text1, BloodP_text2, BloodP_text3, BloodP_Category2, BloodP_Category1;
    TextView Diabetes_text1, Diabetes_text2, Diabetes_text3, Diabetes_Category2, Diabetes_Category1;
    TextView Obese_text1, Obese_text2, Obese_text3, Obese_text4, Obese_Category2, Obese_Category1;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    String healthissue;
    String userID;
    TextView hub_usersname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal__info, container, false);

        Epilepsy_text1 = view.findViewById(R.id.Epilepsy_text1);
        Epilepsy_text2 = view.findViewById(R.id.Epilepsy_text2);
        Epilepsy_text3 = view.findViewById(R.id.Epilepsy_text3);
        Epilespy_Category2 = view.findViewById(R.id.Epilespy_Category2);
        Epilespy_Category1 = view.findViewById(R.id.Epilespy_Category1);

        BloodP_text1 = view.findViewById(R.id.BloodP_text1);
        BloodP_text2 = view.findViewById(R.id.BloodP_text2);
        BloodP_text3 = view.findViewById(R.id.BloodP_text3);
        BloodP_Category2 = view.findViewById(R.id.BloodP_Category2);
        BloodP_Category1 = view.findViewById(R.id.BloodP_Category1);

        Diabetes_text1 = view.findViewById(R.id.Diabetes_text1);
        Diabetes_text2 = view.findViewById(R.id.Diabetes_text2);
        Diabetes_text3 = view.findViewById(R.id.Diabetes_text3);
        Diabetes_Category2 = view.findViewById(R.id.Diabetes_Category2);
        Diabetes_Category1 = view.findViewById(R.id.Diabetes_Category1);

        Obese_text1 = view.findViewById(R.id.Obese_text1);
        Obese_text2 = view.findViewById(R.id.Obese_text2);
        Obese_text3 = view.findViewById(R.id.Obese_text3);
        Obese_text4 = view.findViewById(R.id.Obese_text4);
        Obese_Category2 = view.findViewById(R.id.Obese_Category2);
        Obese_Category1 = view.findViewById(R.id.Obese_Category1);

        hub_usersname = view.findViewById(R.id.hub_usersname);

        editbutton = view.findViewById(R.id.Editinfo_button);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // using DocumentReference to access the users table with the specific document that contains the users id
        // takes the Username from the database and adds it to the hub_username
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReferencename = fStore.collection("users").document(userID);
        documentReferencename.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                hub_usersname.setText(snapshot.getString("Username"));

            }
        });

        // using the DocumentReference to access the Health_Information with the specific document with the name Epilepsy and then adds the containing data to the Epilespy_Text1-3 and Epilepsy_Category1-2
        DocumentReference documentReference = fStore.collection("Health_Information").document("Epilepsy");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                   Epilepsy_text1.setText(snapshot.getString("Text_1"));
                   Epilepsy_text2.setText(snapshot.getString("Text_2"));
                   Epilepsy_text3.setText(snapshot.getString("Text_3"));
                   Epilespy_Category2.setText(snapshot.getString("Category_1"));
                   Epilespy_Category1.setText(snapshot.getString("Category_2"));
                }
            });
        // using the DocumentReference to access the Health_Information with the document with the name Blood_Pressure then adds its data to the BloodP_text1-3 and BloodP_Category1-2
        DocumentReference documentReference2 = fStore.collection("Health_Information").document("Blood_Pressure");
        documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                BloodP_text1.setText(snapshot.getString("Text_1"));
                BloodP_text2.setText(snapshot.getString("Text_2"));
                BloodP_text3.setText(snapshot.getString("Text_3"));
                BloodP_Category2.setText(snapshot.getString("Category_1"));
                BloodP_Category1.setText(snapshot.getString("Category_2"));
            }
        });
        // using the DocumentReference to access the Health_Information with the document with the name Diabetes then adds its data to the Diabetes_text1-3 and Diabetes_Category1-2
        DocumentReference documentReference3 = fStore.collection("Health_Information").document("Diabetes");
        documentReference3.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Diabetes_text1.setText(snapshot.getString("Text_1"));
                Diabetes_text2.setText(snapshot.getString("Text_2"));
                Diabetes_text3.setText(snapshot.getString("Text_3"));
                Diabetes_Category2.setText(snapshot.getString("Category_1"));
                Diabetes_Category1.setText(snapshot.getString("Category_2"));
            }
        });
        // using the DocumentReference to access the Health_Information with the document with the name obese then adds its data to the obese_text1-3 and Diabetes_Category1-2
        DocumentReference documentReference4 = fStore.collection("Health_Information").document("obese");
        documentReference4.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException error) {

                Obese_text1.setText(snapshot.getString("Text_1"));
                Obese_text2.setText(snapshot.getString("Text_2"));
                Obese_text3.setText(snapshot.getString("Text_3"));
                Obese_text4.setText(snapshot.getString("Text_4"));
                Obese_Category2.setText(snapshot.getString("Category_1"));
                Obese_Category1.setText(snapshot.getString("Category_2"));
            }
        });


        //using the ditbutton.setOnclickListener to transfer the user to the EditHealthInfoFragment with the FragmentTransaction
            editbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    EditHealthInfoFragment fragment = new EditHealthInfoFragment();
                    fragmentTransaction.add(R.id.framelayout, fragment);
                    fragmentTransaction.addToBackStack("Personal_InfoFragment");
                    fragmentTransaction.commit();
                }
            });


            return view;
        }


        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

        }
    }


