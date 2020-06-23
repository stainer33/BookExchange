package com.example.bookexchange1.API;

import com.example.bookexchange1.Response.GeneralResponse;
import com.example.bookexchange1.Response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {
    @FormUrlEncoded
    @POST("signup")
    Call<GeneralResponse> signUp(@Field("email")String email, @Field("fullName")String fullName, @Field("password")String password, @Field("mobileNo")String mobileNo, @Field("address")String address, @Field("profileImg")String profileImg );

    @FormUrlEncoded
    @POST("login")
    Call<UserResponse>login(@Field("email")String email, @Field("password") String password);
}
