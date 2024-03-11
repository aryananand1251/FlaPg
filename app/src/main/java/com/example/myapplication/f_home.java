package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;


public class f_home extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_f_home, container, false);

        CardView List_property=view.findViewById(R.id.List_property);
        TextView Username=view.findViewById(R.id.Username);
        final String username=getActivity().getIntent().getStringExtra("Fullname");
        if(username!=null) {
            Username.setText(username);

        }
        ImageView near_me=view.findViewById(R.id.near_me);
        near_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        List_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),pgregister.class);
                startActivity(intent);
            }
        });

        return view;

        // Inflate the layout for this fragment

    }
}