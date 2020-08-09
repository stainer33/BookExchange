package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {
EditText etName, etAddress, etEmail, etPhone;
Button btnEditProfile; CircleImageView imgProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etName=findViewById(R.id.etName);
        etAddress=findViewById(R.id.etAddress);
        etEmail=findViewById(R.id.etEmail);
        etPhone=findViewById(R.id.etPhone);
        btnEditProfile=findViewById(R.id.btnEditProfile);
        imgProfile=findViewById(R.id.imgProfile);
        final UserBLL userBLL=new UserBLL();
        final User user=userBLL.profile();

        etName.setText(user.getFullName());
        etAddress.setText(user.getAddress());
        etPhone.setText(user.getMobileNo());
        etEmail.setText(user.getEmail());
        Picasso.with(this)
                .load("http://10.0.2.2:8000/storage/users/July2020/"+user.getProfileImg())

                .into(imgProfile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = userBLL.update(User.id,etName.getText().toString(),etEmail.getText().toString(),etAddress.getText().toString(),etPhone.getText().toString());
                if(res)
                {
                    Toast.makeText(EditProfileActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(EditProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}