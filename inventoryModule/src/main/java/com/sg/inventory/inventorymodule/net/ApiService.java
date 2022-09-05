package com.sg.inventory.inventorymodule.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final String TAG = ApiService.class.getSimpleName();

    private static Retrofit instance;


    private static Retrofit getRetrofit(String environment) {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(environment)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client())
                    .build();
        }
        return instance;
    }

    private static OkHttpClient client() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static synchronized ApiInterface getApiClient(String environment) {
        return getRetrofit(environment).create(ApiInterface.class);
    }


}
