package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class f_account extends Fragment {

    FirebaseAuth mAuth;
    Fragment button;
    FirebaseUser user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth= FirebaseAuth.getInstance();
        button= getParentFragmentManager().findFragmentById(R.id.Logout);
        user= mAuth.getCurrentUser();
        if(user==null){
            Intent intent = new Intent(getActivity(), login.class);
            startActivity(intent);
            requireActivity().finish();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_f_account, container, false);
        Button Logout=view.findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(), login.class);
                startActivity(intent);
                requireActivity().finish();

            }
        });
        TextView Bookings=view.findViewById(R.id.My_Bookings);
        TextView My_wallet=view.findViewById(R.id.My_wallet);
        TextView Favourite=view.findViewById(R.id.Favorite);
        TextView Payments=view.findViewById(R.id.Payments);
        ImageView back_arrow=view.findViewById(R.id.back_arrow);

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new f_home());
            }
        });

        Bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new f_bookings());

            }
        });
        My_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new f_wallet());
            }
        });


        return view;
    }

    private void switchFragment( Fragment fragment) {

        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    }

