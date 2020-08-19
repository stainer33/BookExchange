package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.R;

import StrictMode.StrictModeClass;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                String email = sharedPreferences.getString("Email","");
                String password = sharedPreferences.getString("Password","");

                UserBLL userBLL = new UserBLL();
                StrictModeClass.StrictMode();
                if(userBLL.login(email, password)){
                    Intent intent =  new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent1 = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent1);
                    Toast.makeText(SplashScreenActivity.this, "Email and password not found", Toast.LENGTH_SHORT).show();
                }
            }
        },2000);
    }
}