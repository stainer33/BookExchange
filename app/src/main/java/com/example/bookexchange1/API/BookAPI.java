package com.example.bookexchange1.API;

import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Response.GeneralResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookAPI {
    @FormUrlEncoded
    @POST("add")
    Call<GeneralResponse> add(@Field("name")String name,@Field("author")String author,@Field("image")String image);

    @GET("books")
    Call<List<Book>> getAll();

    @GET("books/{id}")
    Call<List<Book>> getMyBooks(@Path("id") String id);
}
