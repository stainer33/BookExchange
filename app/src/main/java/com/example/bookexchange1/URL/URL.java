package com.example.bookexchange1.URL;

import com.example.bookexchange1.API.UserAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class URL {
    public static  final String base_url="http://10.0.2.2:3003/";


    public static final Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static final UserAPI userAPI =retrofit.create(UserAPI.class);
}
