package com.example.bookexchange1.BLL;

import com.example.bookexchange1.Response.GeneralResponse;
import com.example.bookexchange1.Response.UserResponse;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.bookAPI;
import static com.example.bookexchange1.URL.URL.userAPI;

public class BookBLL {
    boolean isSuccess;
    public boolean add (String name, String author,String image)
    {
        Call<GeneralResponse> call=bookAPI.add(name,author,image);
        try {
            Response<GeneralResponse> response=call.execute();
            GeneralResponse generalResponse=response.body();
            if(generalResponse.getStatus()=="201")
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
}
