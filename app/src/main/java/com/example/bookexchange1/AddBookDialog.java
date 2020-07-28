package com.example.bookexchange1;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
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
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;

import java.io.File;
import android.content.pm.PackageManager;
import StrictMode.StrictModeClass;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.example.bookexchange1.URL.URL.bookAPI;

public class AddBookDialog extends AppCompatDialogFragment {
    private EditText etBookName, etAuthorName, etDes;
    private Button btnAddBooks, btnImage;
    private TextView txtImageName;
    private Spinner conditionSpinner;
    private String imagePath;
    private String imageName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate((R.layout.layout_add_book), null);

        builder.setView(view)
                .setTitle("Add Book");

        etDes = view.findViewById(R.id.etDescription);
        etAuthorName = view.findViewById(R.id.etAuthorName);
        etBookName = view.findViewById(R.id.etBookName);
        btnAddBooks = view.findViewById(R.id.btnAddBooks);
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
        btnAddBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(imagePath);
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

                BookBLL bookBLL=new BookBLL();
//              boolean res=  bookBLL.add(etBookName.getText().toString(),etAuthorName.getText().toString(),etDes.getText().toString(),body,conditionSpinner.getSelectedItem().toString(), User.id);
//              if(res){
//              Toast.makeText(getActivity(), "Book added successfully", Toast.LENGTH_SHORT).show();}
//              else
//              {
//                  Toast.makeText(getActivity(), "Can't add book", Toast.LENGTH_SHORT).show();
//              }
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