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
    private String imageName;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate((R.layout.layout_edit_book), null);

        builder.setView(view)
                .setTitle("Edit Book");

        etDes = view.findViewById(R.id.etDescription);
        etAuthorName = view.findViewById(R.id.etAuthorName);
        etBookName = view.findViewById(R.id.etBookName);
        btnEditBook = view.findViewById(R.id.btnEditBook);
        btnImage = view.findViewById(R.id.btnImage);
        txtImageName = view.findViewById(R.id.txtImageName);
        conditionSpinner = view.findViewById(R.id.Conditionspinner);
        String[] conditions = {"new", "Used"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, conditions);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        conditionSpinner.setAdapter(adapter);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
                BrowseImage();
            }
        });
        btnEditBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
                BookBLL bookBLL=new BookBLL();
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

        imagePath = getRealPathFormUri(uri);
        imageName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
        txtImageName.setText(imageName);
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
