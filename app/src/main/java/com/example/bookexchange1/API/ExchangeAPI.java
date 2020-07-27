package com.example.bookexchange1.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExchangeAPI {
    @FormUrlEncoded
    @POST("exchange/add")
    Call<ResponseBody> add(@Field("book_wanted")Integer bookReqId, @Field("book_offered")Integer bookOfferedId, @Field("requested_to")Integer userId, @Field("requested_by")Integer id, @Field("status")String status);

    @GET("exchange/requestedby/{id}")
    Call<ResponseBody> myActivity(@Path("id") int id);

    @GET("exchange/requestedby/{id}")
    Call<ResponseBody> notification(@Path("id") int id);

}
