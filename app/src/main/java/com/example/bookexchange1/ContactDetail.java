package com.example.bookexchange1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;
import com.squareup.picasso.Picasso;

public class ContactDetail extends AppCompatDialogFragment {
    EditText txtAddress,txtPhone,txtEmail;
    TextView txtName;
    ImageView imgProfile; String email;

    public ContactDetail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate((R.layout.contact_layout), null);

        builder.setView(view)
                .setTitle("Contact Info");
        txtName=view.findViewById(R.id.txtName);
        txtAddress=view.findViewById(R.id.txtAddress);
        txtPhone=view.findViewById(R.id.txtPhone);
        txtEmail=view.findViewById(R.id.txtEmail);
        imgProfile=view.findViewById(R.id.imgProfile);
        UserBLL userBLL=new UserBLL();
        User user=userBLL.profile(email);

        txtName.setText(user.getFullName());
        txtAddress.setText(user.getAddress());
        txtPhone.setText(user.getMobileNo());
        txtEmail.setText(user.getEmail());

        Picasso.with(getActivity())
                .load(user.getProfileImg())

                .into(imgProfile);
        return builder.create();
    }

}
