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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.API.UserAPI;
import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;

import com.example.bookexchange1.URL.URL;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import StrictMode.StrictModeClass;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.bookexchange1.URL.URL.userAPI;

public class Registration extends AppCompatActivity {
    EditText etName, etAddress, etPhone, etEmail, etPassword, etConfirmPassword;
    Button btnSignUp;
    TextView linkLogin;
    CircleImageView imgProfile;
    TextInputLayout phone_input_layout, email_input_layout, password_input_layout, name_input_layout, address_input_layout, repassword_input_layout;
    private String imagePath;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etAddress = findViewById(R.id.etAddress);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etConfirmPassword = findViewById(R.id.etconfirmpassword);
        email_input_layout = findViewById(R.id.tvEmail);
        name_input_layout = findViewById(R.id.tvName);
        address_input_layout = findViewById(R.id.tvAddress);
        password_input_layout = findViewById(R.id.tvPassword);
        phone_input_layout = findViewById(R.id.tvPhone);
        repassword_input_layout = findViewById(R.id.tvConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        imgProfile = findViewById(R.id.imgProfile);
        linkLogin = findViewById(R.id.linkLogin);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
                BrowseImage();
            }
        });

        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    name_input_layout.setErrorEnabled(false);
                }
            }
        });
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    email_input_layout.setErrorEnabled(false);
                } else {
                    if (etEmail.getText().toString().trim().length() > 0) {
                        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                                "[a-zA-Z0-9_+&*-]+)*@" +
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                "A-Z]{2,7}$";
                        Pattern pattern = Pattern.compile(emailRegex);
                        Matcher matcher = pattern.matcher(etEmail.getText());
                        if (!matcher.matches()) {
                            email_input_layout.setErrorEnabled(true);
                            email_input_layout.setError("Invalid email format");
                        }

                    }
                }
            }
        });
        etAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    address_input_layout.setErrorEnabled(false);
                }
            }
        });
        etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    phone_input_layout.setErrorEnabled(false);
                }
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password_input_layout.setErrorEnabled(false);
                } else {
                    if (etPassword.getText().toString().length() < 7) {
                        password_input_layout.setErrorEnabled(true);
                        password_input_layout.setError(" password must longer than 7 character");
                    }
                }
            }
        });
        etConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    repassword_input_layout.setErrorEnabled(false);
                } else {
                    if (TextUtils.isEmpty(etPassword.getText())) {
                        password_input_layout.setErrorEnabled(true);
                        password_input_layout.setError("Please enter password");

                    } else {
                        if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                            repassword_input_layout.setErrorEnabled(true);
                            repassword_input_layout.setError("Password doesnot match");

                        }
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valid = validation();
                if (valid) {
                    String address = etAddress.getText().toString();
                    String name = etName.getText().toString();
                    String phone = etAddress.getText().toString();
                    String password = etPassword.getText().toString();
                    String email = etEmail.getText().toString();


                    File file = new File(imagePath);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);

                    UserBLL userBLL = new UserBLL();
                    Boolean result = userBLL.signUp(email, name, password, phone, address, body);
                    if (result == true) {
                        Toast.makeText(Registration.this, "Registration done", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
        CursorLoader loader = new CursorLoader(this, uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    public boolean validation() {//checking name edit text
        boolean check = true;
        if (TextUtils.isEmpty(etName.getText())) {
            name_input_layout.setErrorEnabled(true);
            name_input_layout.setError("Please enter full name");
            check = false;
            return check;
        }

        //checking address edit text
        else if (TextUtils.isEmpty(etAddress.getText())) {
            address_input_layout.setErrorEnabled(true);
            address_input_layout.setError("Please enter address");
            check = false;
            return check;
        }
        //checking phone edit text
        else if (TextUtils.isEmpty(etPhone.getText())) {
            phone_input_layout.setErrorEnabled(true);
            phone_input_layout.setError("Please enter phone number");
            check = false;
            return check;
        }
        //checking email edit text
        else if (TextUtils.isEmpty(etEmail.getText())) {
            email_input_layout.setErrorEnabled(true);
            email_input_layout.setError("Please enter email address");
            check = false;
            return check;
        }
        //checking password edit text
        else if (TextUtils.isEmpty(etPassword.getText())) {
            password_input_layout.setErrorEnabled(true);
            password_input_layout.setError("Please enter password");
            check = false;
            return check;
        }
        //checking repassword edit text
        else if (TextUtils.isEmpty(etEmail.getText())) {
            repassword_input_layout.setErrorEnabled(true);
            repassword_input_layout.setError("Please enter repassword");
            check = false;
            return check;
        }
        return check;
    }
}