package com.example.bookexchange1.URL;

import com.example.bookexchange1.API.BookAPI;
import com.example.bookexchange1.API.ExchangeAPI;
import com.example.bookexchange1.API.UserAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {
    public static  final String base_url="http://10.0.2.2:8000/api/";

    public static String token = "Bearer ";
    public static final Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(base_url)

            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final UserAPI userAPI =retrofit.create(UserAPI.class);
    public  static final BookAPI bookAPI=retrofit.create(BookAPI.class);
    public static final ExchangeAPI exchangeAPI=retrofit.create(ExchangeAPI.class);
}
