package com.example.bookexchange1.API;

import com.example.bookexchange1.Model.User;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserAPI {
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> signUp(@Field("email")String email, @Field("name")String fullName, @Field("password")String password, @Field("phone")String mobileNo, @Field("address")String address);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody>login(@Field("email")String email, @Field("password") String password);


    @GET("user/{email}")
    Call<ResponseBody>getProfile(@Path("email") String email);

//    @Multipart
//    @POST("upload")
//    Call<ImageResponse>uploadImage(@Part MultipartBody.Part imageFile);
}
