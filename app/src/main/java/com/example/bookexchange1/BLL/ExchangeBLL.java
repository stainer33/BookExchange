package com.example.bookexchange1.BLL;

import com.example.bookexchange1.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import StrictMode.StrictModeClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;

import static com.example.bookexchange1.URL.URL.exchangeAPI;


public class ExchangeBLL {
    boolean isSuccess;
    public boolean add(Integer bookReqId, Integer bookOfferedId,Integer userId,Integer id, String status)
    {
        Call<ResponseBody> call=exchangeAPI.add(bookReqId,bookOfferedId,userId,id,status);

        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            final Response<ResponseBody> finalResponse = response;


            if(response.code()==200)
            {

                isSuccess=true;
            }
            else {isSuccess=false;}

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
