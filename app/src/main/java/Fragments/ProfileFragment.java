package Fragments;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;
import com.example.bookexchange1.UI.EditProfileActivity;
import com.example.bookexchange1.UI.MyActivityActivity;
import com.squareup.picasso.Picasso;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }
EditText txtAddress,txtPhone,txtEmail;
TextView txtName;
Button btnEditProfile, btnMyActivity;
ImageView imgProfile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        btnEditProfile=view.findViewById(R.id.btnEditProfile);
        btnMyActivity=view.findViewById(R.id.btnMyActivity);
        txtName=view.findViewById(R.id.txtName);
        txtAddress=view.findViewById(R.id.txtAddress);
        txtPhone=view.findViewById(R.id.txtPhone);
        txtEmail=view.findViewById(R.id.txtEmail);
        imgProfile=view.findViewById(R.id.imgProfile);

        btnMyActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyActivityActivity.class);
                startActivity(intent);
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        UserBLL userBLL=new UserBLL();
        User user=userBLL.profile(User.t_email);

        txtName.setText(user.getFullName());
        txtAddress.setText(user.getAddress());
        txtPhone.setText(user.getMobileNo());
        txtEmail.setText(user.getEmail());

        Picasso.with(getActivity())
                .load(user.getProfileImg())

                .into(imgProfile);
        return view;
    }
}