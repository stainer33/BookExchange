package com.example.bookexchange1.API;

import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.Response.GeneralResponse;
import com.example.bookexchange1.Response.ImageResponse;
import com.example.bookexchange1.Response.UserResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAPI {
    @FormUrlEncoded
    @POST("/api/signup")
    Call<GeneralResponse> signUp(@Field("email")String email, @Field("name")String fullName, @Field("password")String password, @Field("phone")String mobileNo, @Field("address")String address, @Field("profileImg")String profileImg );

    @FormUrlEncoded
    @POST("/api/login")
    Call<UserResponse>login(@Field("email")String email, @Field("password") String password);

    @GET("me")
    Call<User>getProfile(@Header("token") String token);

    @Multipart
    @POST("upload")
    Call<ImageResponse>uploadImage(@Part MultipartBody.Part imageFile);
}
