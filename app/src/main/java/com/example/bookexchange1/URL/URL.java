package com.example.bookexchange1.URL;

import com.example.bookexchange1.API.BookAPI;
import com.example.bookexchange1.API.UserAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {
    public static  final String base_url="http://127.0.0.1:8000/api/";


//    static OkHttpClient client = new OkHttpClient.Builder()
//            .connectTimeout(2000, TimeUnit.SECONDS)
//            .readTimeout(2000,TimeUnit.SECONDS).build();

//    OkHttpClient.Builder client = new OkHttpClient.Builder();
//    client.connectTimeout(15, TimeUnit.SECONDS);
//    client.readTimeout(15, TimeUnit.SECONDS);
//    client.writeTimeout(15, TimeUnit.SECONDS);

    public static final Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public  static final BookAPI bookAPI=retrofit.create(BookAPI.class);

    public static final UserAPI userAPI =retrofit.create(UserAPI.class);
}
