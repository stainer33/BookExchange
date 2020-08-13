package com.example.bookexchange1.BLL;

import android.widget.Toast;

import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Model.Owner;
import com.example.bookexchange1.Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import StrictMode.StrictModeClass;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.bookAPI;
import static com.example.bookexchange1.URL.URL.userAPI;

public class BookBLL {
    boolean isSuccess;

    public boolean add (String name, String author,String description, MultipartBody.Part image,String condition,int id,String status)
    {
        RequestBody reqName=RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody reqAuthor=RequestBody.create(MediaType.parse("text/plain"), author);
        RequestBody reqDescription=RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody reqCondition=RequestBody.create(MediaType.parse("text/plain"), condition);
        RequestBody reqId=RequestBody.create(MediaType.parse("text/plain"), String.valueOf(id));
        RequestBody reqStatus=RequestBody.create(MediaType.parse("text/plain"), String.valueOf(status));

        Call<ResponseBody> call=bookAPI.add(reqName,reqAuthor,reqDescription,image,reqCondition,reqId,reqStatus);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response=call.execute();

            if(response.code()==200)
            {
                isSuccess=true;

            }
            else
            {
                isSuccess=false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public List<Book> getAllBook ()
    {
        List<Book>books=new ArrayList<>();
        Call<ResponseBody> call =bookAPI.getAll();
        StrictModeClass.StrictMode();
        try{
            Response<ResponseBody> response =call.execute();
            JSONObject jobj = new JSONObject((response.body().string()));

            //  String status = (jobj.getString("message"));

            JSONObject object1 = jobj.getJSONObject("data");
            JSONArray jsonArray =object1.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                JSONObject belongs = jsonObject.getJSONObject("belongs");
                JSONObject object = belongs.getJSONObject("data");
                int id=jsonObject.getInt("id");
                String name =jsonObject.getString("name");
                String author =jsonObject.getString("author");
                String owner =object.getString("name");
                String email=object.getString("email");
                int userId=object.getInt("id");
                String image =jsonObject.getString("image");
                String des=jsonObject.getString("description");

                if(!User.t_email.equals(email)){
                    books.add(new Book(id,name,author,image,new Owner(userId,owner),des));}
                //names.add(jsonObject.getString("name"));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        return books;
    }

    public List<Book> getMyBook()
    {
        List<Book>books=new ArrayList<>();;
        Call<ResponseBody> call =bookAPI.getMyBooks();
        StrictModeClass.StrictMode();
        try{
            Response<ResponseBody> response =call.execute();
            JSONObject jobj = new JSONObject((response.body().string()));


            JSONObject object1 = jobj.getJSONObject("data");
            JSONArray jsonArray =object1.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                JSONObject belongs = jsonObject.getJSONObject("belongs");
                JSONObject object = belongs.getJSONObject("data");
                int id=jsonObject.getInt("id");
                String name =jsonObject.getString("name");
                String author =jsonObject.getString("author");
                String owner =object.getString("name");
                String email=object.getString("email");
                int userId=object.getInt("id");
                String image =jsonObject.getString("image");

                String des=jsonObject.getString("description");

                if(User.t_email.equals(email)){
                    books.add(new Book(id,name,author,image,new Owner(userId,owner),des));}
                //names.add(jsonObject.getString("name"));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        return books;
    }
    public boolean delete(int id)
    {
        Call<ResponseBody> call =bookAPI.delete(id);
        StrictModeClass.StrictMode();
        try{
            Response<ResponseBody> response =call.execute();
            if(response.code()==200)
            {
                isSuccess=true;

            }
            else
            {
                isSuccess=false;
            }}
        catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
