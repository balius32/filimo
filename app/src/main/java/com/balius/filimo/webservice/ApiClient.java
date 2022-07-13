package com.balius.filimo.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://mobilemasters.ir/apps/filimo-android/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return retrofit;
    }


}
