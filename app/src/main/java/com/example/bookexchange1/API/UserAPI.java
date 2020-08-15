package com.example.bookexchange1.API;

import com.example.bookexchange1.Model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserAPI {
    @Multipart
    @POST("register")
    Call<ResponseBody> signUp(@Part("email")RequestBody email,
                              @Part("name")RequestBody fullName,
                              @Part("password")RequestBody password,
                              @Part("phone")RequestBody mobileNo,
                              @Part("address") RequestBody address,
                              @Part MultipartBody.Part avatar);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody>login(@Field("email")String email, @Field("password") String password);


    @GET("user/{email}")
    Call<ResponseBody>getProfile(@Path("email") String email);

    @FormUrlEncoded
    @PUT("user/{id}")
    Call<ResponseBody>update(@Path("id") int id,@Field("name") String name,@Field("email")String email,@Field("address")String address,@Field("phone")String phone);


//    @Multipart
//    @POST("upload")
//    Call<ImageResponse>uploadImage(@Part MultipartBody.Part imageFile);
}
