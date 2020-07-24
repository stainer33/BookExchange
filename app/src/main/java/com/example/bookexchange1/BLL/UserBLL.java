package com.example.bookexchange1.BLL;

import android.widget.Toast;

import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.Response.GeneralResponse;
import com.example.bookexchange1.Response.UserResponse;
import com.example.bookexchange1.UI.LoginActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import StrictMode.StrictModeClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.userAPI;

public class UserBLL {
    boolean isSuccess;

    public boolean signUp(User user) {


        Call<ResponseBody> call = userAPI.signUp(user.getEmail(), user.getFullName(), user.getPassword(), user.getMobileNo(), user.getAddress());
        try {
            Response<ResponseBody> response=call.execute();

            if(response.code()==200)
            {
                isSuccess= true;
            }
            else
            {
                isSuccess= false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean login (String email, String password)
    {

        final Call<ResponseBody>call=userAPI.login(email,password);

//        StrictModeClass.StrictMode();
                try {
                    Response<ResponseBody>   response = call.execute();
                    final Response<ResponseBody> finalResponse = response;
                    int value=response.code();
                    JSONObject jobj = new JSONObject((response.body().string()));


                   String status = (jobj.getString("status"));
                    JSONObject object1 = jobj.getJSONObject("success");
                    String token=object1.getString("token");
                  if(response.code()==200)
                  {
                      isSuccess=true;
                  }
                  else {isSuccess=false;}

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }



       return isSuccess;
    }
}
