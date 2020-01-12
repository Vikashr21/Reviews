package com.example.reviews.TVshow;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTv {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.themoviedb.org/3/tv/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
