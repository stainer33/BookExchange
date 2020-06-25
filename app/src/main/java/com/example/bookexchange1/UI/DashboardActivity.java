package com.example.bookexchange1.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bookexchange1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import Fragments.HomeFragment;
import Fragments.MyBooksFragment;
import Fragments.NotificationFragment;
import Fragments.ProfileFragment;

public class DashboardActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        loadFragment(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                     //   toolbar.setTitle("Home");
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_myBooks:
                      //  toolbar.setTitle("Register");
                        fragment = new MyBooksFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_notification:
                        //toolbar.setTitle("About us");
                        fragment = new NotificationFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.nav_profile:
                        //toolbar.setTitle("About us");
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });


    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

}