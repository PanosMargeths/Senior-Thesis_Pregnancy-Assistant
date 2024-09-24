package com.example.pa;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class EditHealthInfoFragment extends Fragment {



    final String[] selection = new String[]{
            "Yes", "No"
    };

    EditText date1, Date_Edit, Age_Edit, Weight_Edit;
    //TextInputLayout BloodPressure_input,  Epilepsy_input, Obese_input ;//Diabetes_input;
    AutoCompleteTextView Diabetes_input, BloodPressure_input, Epilepsy_input, Obese_input;
    TextView username;
    Calendar calendar;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    String userID;
    Button submit;



    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_health_info, container, false);

        final ArrayAdapter<String> adapterType = new ArrayAdapter<>(getActivity(), R.layout.dropdown_item, selection);
        AutoCompleteTextView textView = (AutoCompleteTextView) view.findViewById(R.id.BloodPressure_autocomplete);
        textView.setThreshold(3);
        textView.setAdapter(adapterType);

        AutoCompleteTextView textView2 = (AutoCompleteTextView) view.findViewById(R.id.Epilepsy_autocomplete);
        textView2.setThreshold(3);
        textView2.setAdapter(adapterType);

        AutoCompleteTextView textView3 = (AutoCompleteTextView) view.findViewById(R.id.Obese_autocomplete);
        textView3.setThreshold(3);
        textView3.setAdapter(adapterType);

        AutoCompleteTextView textView4 = (AutoCompleteTextView) view.findViewById(R.id.Diabetes_autocomplete);
        textView4.setThreshold(3);
        textView4.setAdapter(adapterType);

        date1 = view.findViewById(R.id.Date_Edit);
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalendar();
            }

            @SuppressLint("DefaultLocale")
            private void updateCalendar() {
                String Format = "MM/dd/yy";
                //SimpleDateFormat sdf = new SimpleDateFormat(Format, Locale.US);
                date1.setText(String.format("%1$tb %1$td %1$tY", calendar));

                //date1.setText(dateFormat.format(calendar.getTime()));
            }
        };

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        username = view.findViewById(R.id.PI_usersname);
        BloodPressure_input = view.findViewById(R.id.BloodPressure_autocomplete);
        Epilepsy_input = view.findViewById(R.id.Epilepsy_autocomplete);
        Obese_input = view.findViewById(R.id.Obese_autocomplete);
        Diabetes_input = view.findViewById(R.id.Diabetes_autocomplete);
        Age_Edit = view.findViewById(R.id.Age_Edit);
        Weight_Edit = view.findViewById(R.id.Weight_Edit);
        submit = view.findViewById(R.id.enterbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bloodpressure, epilespy, obese, diabetes, weight, age;
                bloodpressure = String.valueOf(BloodPressure_input.getText());
                epilespy = String.valueOf(Epilepsy_input.getText());
                obese = String.valueOf(Obese_input.getText());
                diabetes = String.valueOf(Diabetes_input.getText());
                age = String.valueOf(Age_Edit.getText());
                weight = String.valueOf(Weight_Edit.getText());

                if (TextUtils.isEmpty(bloodpressure)) {
                    Toast.makeText(getActivity(), "Enter Blood Pressure", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(epilespy)) {
                    Toast.makeText(getActivity(), "Enter Epilespy", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(obese)) {
                    Toast.makeText(getActivity(), "Enter Obese", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(diabetes)) {
                    Toast.makeText(getActivity(), "Enter Diabetes", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(age)) {
                    Toast.makeText(getActivity(), "Enter Age", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(weight)) {
                    Toast.makeText(getActivity(), "Enter Weight", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(date1.getText().toString())) {
                    Toast.makeText(getActivity(), "Enter Date", Toast.LENGTH_SHORT).show();
                    return;
                }

                String Month1 = String.valueOf(calendar.get(Calendar.MONTH));
                Integer Month2 = Integer.valueOf(Month1);
                Month2 +=1 ;
                String Month = String.valueOf(Month2);
                String Day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                String Year = String.valueOf(calendar.get(Calendar.YEAR));
                String Date = Month +"/"+ Day +"/"+ Year;
                userID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("pregnancy_data").document(userID);
                Map<String, Object> pregnancy = new HashMap<>();
                pregnancy.put("Blood Pressure", bloodpressure);
                pregnancy.put("Epilepsy", epilespy);
                pregnancy.put("Obese", obese);
                pregnancy.put("Diabetes", diabetes);
                pregnancy.put("Pregnancy Date", date1.getText().toString());
                pregnancy.put("Pregnancy Date2", Date);
                pregnancy.put("Pregnancy Month",Month);
                pregnancy.put("Pregnancy Day", Day);
                pregnancy.put("Pregnancy Year", Year);
                pregnancy.put("Age", age);
                pregnancy.put("Weight", weight);

                documentReference.set(pregnancy).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: user profile is created for" + userID);
                        Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();


                    }
                });
                documentReference.set(pregnancy).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure:" + e.toString());
                    }
                });

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HomeFragment fragment = new HomeFragment();
                fragmentTransaction.add(R.id.framelayout, fragment);
                fragmentTransaction.commit();

            }
        });


        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
