package com.example.bookexchange1.BLL;

import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.Response.GeneralResponse;
import com.example.bookexchange1.Response.UserResponse;


import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.userAPI;

public class UserBLL {
    boolean isSuccess;
    public boolean signUp(User user) {
//        String fullName = "test";
//        String email = "test@gmail.com";
//        String address = "test";
//        String password = "test123";
//        String mobileNo = "545454";
//        String imageName = "image.jpg";

        Call<GeneralResponse> call = userAPI.signUp(user.getEmail(), user.getFullName(), user.getPassword(), user.getMobileNo(), user.getAddress(), user.getProfileImg());
        try {
            Response<GeneralResponse> response=call.execute();
            GeneralResponse generalResponse=response.body();
            if(generalResponse.getStatus()=="201")
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
        Call<UserResponse>call=userAPI.login(email,password);
        try {
            Response<UserResponse>response=call.execute();
            UserResponse userResponse=response.body();
            if(userResponse.getStatus()=="201")
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
