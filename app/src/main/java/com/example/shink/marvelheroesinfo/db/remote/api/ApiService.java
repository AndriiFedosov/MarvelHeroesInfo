package com.example.shink.marvelheroesinfo.db.remote.api;

import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by shink on 06.03.2018.
 */

public interface ApiService {
    //create GET request to api to get al charters and order by name thay
    @GET("characters?orderBy=name")
    Observable<CharacterDto> getCharacters(@Query("offset")int offset);

    //create GET request to api to get one hero by id
    @GET("characters/{id}")
    Observable<CharacterDto> getCharacter(@Path("id") int id);
}
