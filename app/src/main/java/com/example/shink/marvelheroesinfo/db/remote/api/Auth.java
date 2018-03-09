package com.example.shink.marvelheroesinfo.db.remote.api;

import com.example.shink.marvelheroesinfo.utils.Hash;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shink on 06.03.2018.
 */

//creath auth class to connect to marvel api
public class Auth implements Interceptor {
    private static final String TIMESTAMP_KEY = "ts";
    private static final String APIKEY_KEY = "apikey";
    private static final String HASH_KEY = "hash";

    private final String publicKey;
    private final String privateKey;
    private final long timestamp;

    public Auth(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.timestamp = 1;
    }
     //create intercept to create url request to api
    @Override
    public Response intercept(Chain chain) throws IOException {
        //create hash
        String hash = Hash.hashCreate(timestamp + privateKey + publicKey);
        //create request
        Request request = chain.request();

        //create url with query parameter
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter(TIMESTAMP_KEY,String.valueOf(this.timestamp))
                .addQueryParameter(APIKEY_KEY,this.publicKey)
                .addQueryParameter(HASH_KEY,hash)
                .build();

        Request requestBuilder = request.newBuilder().url(url).build();
        //return full part of request
        return chain.proceed(requestBuilder);
    }
}
