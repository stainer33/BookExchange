package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;

public class Registration extends AppCompatActivity {
    EditText etName, etAddress, etPhone,etEmail,etPassword;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etAddress = findViewById(R.id.etAdress);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = String.valueOf(etAddress.getText());
                String name = etName.getText().toString();
                String phone=etAddress.getText().toString();
                String password=etPassword.getText().toString();
                String email="";
                String image= "";
                User user = new User (email,name,password,phone,image,address);
                UserBLL userBLL= new UserBLL();
                Boolean result = userBLL.signUp(user);
                if(result==true)
                {
                    Toast.makeText(Registration.this, "Registration done", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}