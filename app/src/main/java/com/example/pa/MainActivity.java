package com.example.pa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText editTextemail;
    EditText editTextpassword;
    Button loginbutton;
    TextView signuptext;
    ProgressBar progressbar;
    FirebaseAuth mAuth;

    //using onStart method to check if the user has an account,
    // if the user has an account then the app transfers him to Home page
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        progressbar = findViewById(R.id.progressbar);
        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);
        loginbutton = findViewById(R.id.loginbutton);
        signuptext = findViewById(R.id.signuptext);

        // if the user clicks on the signuptext the app will transfer him to the SignUp page
        signuptext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });

        // using the loginbutton onclicklistener to check if the user added the email and username.
        // then in the mAuth.signInWithEmailAndPassword(email, password)
        // we send this data to the firebase Authentication where it checks if the user with this data exists and if it does then the app signs in the user to the account
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextemail.getText());
                password = String.valueOf(editTextpassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this,"Login Successful.",Toast.LENGTH_SHORT).show();

                                   Intent intent = new Intent(getApplicationContext(), Home.class);
                                   startActivity(intent);
                                   finish();

                                } else {
                                    Toast.makeText(MainActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }


        });
    }

}