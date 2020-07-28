package com.example.bookexchange1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.loader.content.CursorLoader;

import com.example.bookexchange1.BLL.BookBLL;
import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Model.User;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.BuildConfig;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class AddBookTest {




    @Test
    public void PostBook(){

//        Uri uri = Uri.parse("android.resource://"+BuildConfig.APPLICATION_ID+"/" + R.drawable.book1);
//        String imagePath = uri.toString();
//        File file = new File(imagePath);
//        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);


        BookBLL bookBLL = new BookBLL();
        boolean result = bookBLL.add("kalo pothi", "kali", "test story","new", 2);
        assertEquals(true, result);


    }

//    private String getRealPathFormUri(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        CursorLoader loader = new CursorLoader(InstrumentationRegistry.getInstrumentation().getContext(), uri, projection, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(colIndex);
//        cursor.close();
//        return result;
//    }
}