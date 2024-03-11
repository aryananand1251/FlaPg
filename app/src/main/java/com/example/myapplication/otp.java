package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class otp extends AppCompatActivity {

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        final EditText OTP = findViewById(R.id.OTP);
        final Button verify=findViewById(R.id.verify);
        final ProgressBar progressbar=findViewById(R.id.progressbar);
        final TextView otpMobile=findViewById(R.id.otpMobile);

        final String phoneNumber=getIntent().getStringExtra("Mobile");


        otpMobile.setText(phoneNumber);

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);

        verificationId = getIntent().getStringExtra("verificationId");

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OTP.getText().toString().isEmpty()){
                    Toast.makeText(otp.this, "please Enter OTP", Toast.LENGTH_SHORT).show();
                }else {
                    if(verificationId!=null){
                        String code=OTP.getText().toString();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    progressbar.setVisibility(View.VISIBLE);
                                    verify.setVisibility(View.INVISIBLE);
                                    Intent intent=new Intent(getApplicationContext(), register.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    progressbar.setVisibility(View.GONE);
                                    verify.setVisibility(View.VISIBLE);
                                    Toast.makeText(otp.this, "OTP is not valid", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                }



            }
        });
    }
}