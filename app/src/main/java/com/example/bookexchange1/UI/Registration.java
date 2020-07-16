package com.example.bookexchange1.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookexchange1.API.UserAPI;
import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;
import com.example.bookexchange1.Response.ImageResponse;
import com.example.bookexchange1.URL.URL;

import java.io.File;
import java.io.IOException;

import StrictMode.StrictModeClass;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registration extends AppCompatActivity {
    EditText etName, etAddress, etPhone,etEmail,etPassword;
    Button btnSignUp;
    CircleImageView imgProfile;
    private String imagePath;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etAddress = findViewById(R.id.etAdress);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        imgProfile=findViewById(R.id.imgProfile);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
                BrowseImage();
            }
        });
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
    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFormUri(uri);
    }

    private String getRealPathFormUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        Retrofit retrofit = URL.retrofit;
        UserAPI userAPI = URL.userAPI;
        // Call<Void>voidCall=userAPI.signUp(email,fullName,password,mobileNo,address);
        Call<ImageResponse> responseBodyCall = userAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous method
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error: " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
}