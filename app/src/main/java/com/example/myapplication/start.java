package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class start extends AppCompatActivity {

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private FirebaseAuth mAuth;


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(start.this, home.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FirebaseApp.initializeApp(this);
        mAuth=FirebaseAuth.getInstance();

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        Button Sendotp=findViewById(R.id.Sendotp);
        EditText Mobile=findViewById(R.id.Mobile);
        Button Login=findViewById(R.id.Login);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(start.this, login.class);
                startActivity(intent);
            }
        });


        // set OnClickListener to the button

        Sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Mobile.getText().toString().isEmpty()){
                    Toast.makeText(start.this, "Please Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                }else if(Mobile.getText().toString().length()!=10){
                    Toast.makeText(start.this, "Enter Valid Phone Number", Toast.LENGTH_SHORT).show();
                }else{
                    otpsend();
                }

            }
        });
    }

    private void otpsend(){
        final EditText Mobile = findViewById(R.id.Mobile);
        final String getMobile =Mobile.getText().toString();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {



            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(start.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Intent intent =new Intent(start.this, otp.class);

                intent.putExtra("Mobile",getMobile);


                intent.putExtra("verificationId",verificationId);
                startActivity(intent);

            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+Mobile.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


    }


}