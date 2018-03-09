package com.example.shink.marvelheroesinfo.db.remote.api;

import com.example.shink.marvelheroesinfo.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shink on 06.03.2018.
 */

public class Api {
    //create base url to api service
    public static  final String URL = "http://gateway.marvel.com/v1/public/";
    //create okhttp client to construct path to api
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    //GET interface API SERVICE
    public static ApiService API_SERVICE;

    //create method to build url with timestamp private api_key and  public apikay
    public static ApiService getApiService() {

        if (API_SERVICE == null) {
            //create part with auth class to get last part with ts,public apikay and hash
            httpClient.addInterceptor(new Auth(
                    Constants.PUBLIC_API_KEY,
                    Constants.PRIVATE_API_KEY));
            //build path wih retrofit
            Retrofit.Builder builder = new Retrofit.Builder()
                    //get base url of api
                    .baseUrl(URL)
                    //add request builder okhhtp
                    .client(new OkHttpClient())
                    //add reactive adapter
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //convert gson
                    .addConverterFactory(GsonConverterFactory.create());
            //create client of retrofit
            Retrofit retrofit = builder.client(httpClient.build())
                    .build();
            //create full url with request to api
            API_SERVICE = retrofit.create(ApiService.class);
        }
        return API_SERVICE;
    }

}
