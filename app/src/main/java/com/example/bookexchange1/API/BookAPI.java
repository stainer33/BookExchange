package com.example.bookexchange1.API;

import com.example.bookexchange1.Response.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BookAPI {
    @FormUrlEncoded
    @POST("add")
    Call<GeneralResponse> add(@Field("name")String name,@Field("author")String author,@Field("image")String image);
}
