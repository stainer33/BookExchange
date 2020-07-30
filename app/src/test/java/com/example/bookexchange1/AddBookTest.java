package com.example.bookexchange1;

import com.example.bookexchange1.BLL.BookBLL;
import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Model.User;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


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

    @Test
    public void GetBookList() {
        List<Book> myList = new ArrayList<>();
        User.t_email="admin@admin.com";
        boolean list;

        BookBLL bookBLL = new BookBLL();
        myList = bookBLL.getAllBook();
        int size = myList.size();

        if (size>0){
            list = true;
        }
        else {
            list = false;
        }
        assertEquals(true, list);



    }
    @Test
    public void GetMyBook(){
        List<Book> myList = new ArrayList<>();
        User.t_email="admin@admin.com";
        boolean list;

        BookBLL bookBLL = new BookBLL();
        myList = bookBLL.getMyBook();
        int size = myList.size();

        if (size>0){
            list = true;
        }
        else {
            list = false;
        }
        assertEquals(true, list);

    }
}