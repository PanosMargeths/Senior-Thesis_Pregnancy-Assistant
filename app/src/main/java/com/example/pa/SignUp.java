package com.example.pa;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText EditTextpassword;
    EditText EditTextemail;
    Button signupbutton;
    TextView switchtologin;
    FirebaseAuth mAuth;
    ProgressBar progressbar;
    EditText EditTextusername , EditTextcountry ;
    FirebaseFirestore fstore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        password = findViewById(R.id.password);
        progressbar = findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        EditTextpassword = findViewById(R.id.password);
        signupbutton = findViewById(R.id.signupbutton);
        EditTextemail = findViewById(R.id.email);
        switchtologin = findViewById(R.id.switchtologin);
        EditTextusername = findViewById(R.id.username);
        EditTextcountry = findViewById(R.id.country);
        fstore = FirebaseFirestore.getInstance();

        Intent intent = this.getIntent();

        // using the switchtologin.setOnclickListener to transfer the user to mainActivity when the user clicks on the switchtologin.
        switchtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // using the setOnClickListener for the singupbutton to check if the username, country, email, password, are not empty and if they are empty the app tells the user to add the data.
        // if the user gave all the required data then the app uses the mAuth to acces the Authentication and calls the method createUserWithEmailAndPassword to create an account for the user
        signupbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                String email, password, username, country;
                email = String.valueOf(EditTextemail.getText());
                password = String.valueOf(EditTextpassword.getText());
                username = String.valueOf(EditTextusername.getText());
                country = String.valueOf(EditTextcountry.getText());

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(SignUp.this, "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(country)) {
                    Toast.makeText(SignUp.this, "Enter country", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUp.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUp.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this,"Account Created.",Toast.LENGTH_SHORT).show();

                                    userID = mAuth.getCurrentUser().getUid();
                                    DocumentReference documentReference = fstore.collection("users").document(userID);
                                    Map<String,Object> user = new HashMap<>();
                                    user.put("Username", username);
                                    user.put("Country", country);
                                    user.put("Email", email);
                                    user.put("Password", password);

                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.d(TAG, "onSuccess: user profile is created for"+userID);
                                        }
                                    });
                                    documentReference.set(user).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "onFailure:" + e.toString());
                                        }
                                    });
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(SignUp.this,"Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }



}

