package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.R;

public class LoginActivity extends AppCompatActivity {
 EditText etEmail, etPassword;
 Button btnLogin;
 TextView linkRegister;
 UserBLL userBLL= new UserBLL();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        linkRegister=findViewById(R.id.link_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmail.getText().toString();
                String password =etPassword.getText().toString();
               boolean result= userBLL.login(email,password);
               if(result==true)
               {
                   Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
               }

            }
        });

    }
}