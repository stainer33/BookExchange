package com.example.bookexchange1.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExchangeAPI {
    @FormUrlEncoded
    @POST("exchange/add")
    Call<ResponseBody> add(@Field("book_wanted")Integer bookReqId, @Field("book_offered")Integer bookOfferedId, @Field("requested_to")Integer userId, @Field("requested_by")Integer id, @Field("status")String status);

    @GET("exchange/requestedby/{id}")
    Call<ResponseBody> requestedBy(@Path("id") int id);

    @GET("exchange/requestedto/{id}")
    Call<ResponseBody> requestedTo(@Path("id") int id);

    @DELETE("exchange/{id}")
    Call<ResponseBody>delete(@Path("id") Integer id);
    @FormUrlEncoded
    @PUT("exchange/{id}")
    Call<ResponseBody>accept(@Path("id")Integer id,@Field("status") String status);

    @PUT("exchange/confirm/{id}")
    Call<ResponseBody>confirm(@Path("id")Integer id);

}
