package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.charset.StandardCharsets;

public class pgregister extends AppCompatActivity {

    EditText pname,pregno,nearestCllg,pownname,State,City,District,Pincode,Landmark,Locality,own_phone;
    Button save,REG;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pgregister);


        Spinner spinner=findViewById(R.id.spinner);
        pname=findViewById(R.id.name);
        pregno=findViewById(R.id.pregno);
        nearestCllg=findViewById(R.id.nearestCllg);
        pownname=findViewById(R.id.pownname);
        State=findViewById(R.id.State);
        City=findViewById(R.id.City);
        District=findViewById(R.id.District);
        Pincode=findViewById(R.id.Pincode);
        Landmark=findViewById(R.id.Landmark);
        Locality=findViewById(R.id.Locality);
        own_phone=findViewById(R.id.own_phone);
        save=findViewById(R.id.save);
        REG=findViewById(R.id.REG);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Property_Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prname=pname.getText().toString();
                String prno=pregno.getText().toString();
                String nearclg=nearestCllg.getText().toString();
                String prowname=pownname.getText().toString();
                String state=State.getText().toString();
                String city=City.getText().toString();
                String district=District.getText().toString();
                String pincode=Pincode.getText().toString();
                String landmark=Landmark.getText().toString();
                String locality=Locality.getText().toString();
                String Own_phone=own_phone.getText().toString();

                if(!prname.isEmpty() && !nearclg.isEmpty() && !prno.isEmpty() && !prowname.isEmpty() && !state.isEmpty() && !city.isEmpty() && !district.isEmpty()  && !pincode.isEmpty() && !landmark.isEmpty() && !locality.isEmpty() && !Own_phone.isEmpty()){

                    User user=new User(prname,prno,nearclg,prowname,state,city,district,pincode,landmark,locality,Own_phone);
                    mDatabase= FirebaseDatabase.getInstance().getReference("User");
                    mDatabase.child(prname).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            pname.setText("");
                            pregno.setText("");
                            nearestCllg.setText("");
                            pownname.setText("");
                            State.setText("");
                            City.setText("");
                            District.setText("");
                            Pincode.setText("");
                            Landmark.setText("");
                            Locality.setText("");
                            own_phone.setText("");
                            Toast.makeText(pgregister.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });



    }
}