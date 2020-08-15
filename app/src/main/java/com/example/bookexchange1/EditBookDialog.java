package com.example.bookexchange1;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import com.example.bookexchange1.BLL.BookBLL;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class EditBookDialog extends AppCompatDialogFragment {
    private EditText etBookName, etAuthorName, etDes;
    private Button btnEditBook, btnImage;
    private TextView txtImageName;
    private Spinner conditionSpinner;
    private String imagePath;
    ImageView bookImage;
    ImageButton btnAddImage;
    private  int id;
    private String name, author, des, image;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    public EditBookDialog(int id,String name, String author, String des, String image) {
        this.id=id;
        this.name = name;
        this.author = author;
        this.des = des;
        this.image = image;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate((R.layout.layout_edit_book), null);

        builder.setView(view)
                .setTitle("Edit Book");
        bookImage = view.findViewById(R.id.bookImg);
        btnAddImage = view.findViewById(R.id.btnAddImage);
        etDes = view.findViewById(R.id.etDescription);
        etAuthorName = view.findViewById(R.id.etAuthorName);
        etBookName = view.findViewById(R.id.etBookName);
        radioGroup=view.findViewById(R.id.radio_group);
        btnEditBook = view.findViewById(R.id.btnEditBook);
        btnImage = view.findViewById(R.id.btnImage);
        txtImageName = view.findViewById(R.id.txtImageName);
        conditionSpinner = view.findViewById(R.id.Conditionspinner);
        String[] conditions = {"new", "Used"};
        Picasso.with(getActivity())
                .load(image)

                .into(bookImage);
        etBookName.setText(name);
        etAuthorName.setText(author);
        etDes.setText(des);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, conditions);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        conditionSpinner.setAdapter(adapter);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
                BrowseImage();
            }
        });
        btnEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = view.findViewById(selectedId);

                BookBLL bookBLL = new BookBLL();
               boolean res= bookBLL.edit(id,etBookName.getText().toString(),etAuthorName.getText().toString(),etDes.getText().toString(),conditionSpinner.getSelectedItem().toString(),radioButton.getText().toString());
               if(res)
               {
                   Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(getActivity(), "couldn't update", Toast.LENGTH_SHORT).show();
               }
            }
        });

        return builder.create();
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
                Toast.makeText(getActivity(), "Please select image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        bookImage.setImageURI(uri);
        imagePath = getRealPathFormUri(uri);

    }

    private String getRealPathFormUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity().getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }
}
