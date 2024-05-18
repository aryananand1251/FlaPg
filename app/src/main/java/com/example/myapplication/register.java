package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {

    private  boolean passwordshowing=false;
    private boolean cnfpasswordshowing =false;
    FirebaseAuth mAuth;
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(), home.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth=FirebaseAuth.getInstance();
        final EditText Fullname = findViewById(R.id.Fullname);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText cnfpassword = findViewById(R.id.cnfpassword);
        final EditText Mobile = findViewById(R.id.Mobile);
        final AppCompatButton signup=findViewById(R.id.signup);
        final ImageView cnfpassicon = findViewById(R.id.cnfpassicon);
        final ImageView pimgshow = findViewById(R.id.passicon);
        final ProgressBar progressBar=findViewById(R.id.progressbar);

        final String Name=Fullname.getText().toString();
        final String MoileNumber=getIntent().getStringExtra("otpMobile");
        Mobile.setText(MoileNumber);








        pimgshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordshowing) {
                    passwordshowing = false;
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pimgshow.setImageResource(R.drawable.hide);
                } else {
                    passwordshowing = true;
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pimgshow.setImageResource(R.drawable.view);

                }
                password.setSelection(password.length());

            }
        });
        cnfpassicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cnfpasswordshowing) {
                    cnfpasswordshowing = false;
                    cnfpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    cnfpassicon.setImageResource(R.drawable.hide);
                } else {
                    cnfpasswordshowing = true;
                    cnfpassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    cnfpassicon.setImageResource(R.drawable.view);

                }
                cnfpassword.setSelection(password.length());
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Email=String.valueOf(email.getText());
                String Pass=String.valueOf(password.getText());
                String Cnfpass=String.valueOf(cnfpassword.getText());
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Pass)|| TextUtils.isEmpty(Cnfpass)){
                    Toast.makeText(register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }if(password.getText().toString().equals(cnfpassword.getText().toString())) {

                    mAuth.createUserWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(register.this, "Account Created", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(register.this, home.class);
                                        intent.putExtra("Fullname", Name);
                                        intent.putExtra("Mobile",MoileNumber);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(register.this, "Both password not matched", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}