package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.API.UserAPI;
import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.R;
import com.example.bookexchange1.Response.UserResponse;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.userAPI;

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

          if(result)
          {
              Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
              Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
              startActivity(intent);
          }
          else
          {
              Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
          }



            }
        });

    }
}