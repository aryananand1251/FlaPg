package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.databinding.ActivityHomeBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class home extends AppCompatActivity {
    ActivityHomeBinding binding;
    BottomNavigationView bottomNavigationView;


    DrawerLayout drawerLayout;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private final String TAG_Home = "Home";
    private final String TAG_Bookings = "My Bookings";
    private final String TAG_Wallet = "My Wallet";
    private final String TAG_Account = "Account";
    private String currentFragmentTag = TAG_Home;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    private final Fragment homeFragment = new f_home();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bnview.setBackground(null);

        fragmentManager.beginTransaction().replace(R.id.container, homeFragment , TAG_Home).commit();


        binding.bnview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId=item.getItemId();
                if(itemId==R.id.navhome)
                {
                    replacefragment(new f_home(),TAG_Home);
                }
                if(itemId==R.id.navbook)
                {
                    replacefragment(new f_bookings(),TAG_Bookings);
                }
                if(itemId==R.id.navwallet)
                {
                    replacefragment(new f_wallet(),TAG_Wallet);
                }
                if(itemId==R.id.navaccount)
                {
                    replacefragment(new f_account(),TAG_Account);
                }

                return  true;



            }





        });
    }

    private  void replacefragment(Fragment fragment,String tag){
        if(!currentFragmentTag.equals(tag)) {
            FragmentManager fragmentManger = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManger.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment,tag);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            currentFragmentTag=tag;
        }


    }
    @Override
    public void onBackPressed(){
        if(fragmentManager.getBackStackEntryCount() >0){
            fragmentManager.popBackStack();
        }else
            super.onBackPressed();
    }

}