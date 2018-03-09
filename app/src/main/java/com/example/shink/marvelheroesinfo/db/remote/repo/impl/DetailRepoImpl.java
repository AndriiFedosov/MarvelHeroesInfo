package com.example.shink.marvelheroesinfo.db.remote.repo.impl;

import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;
import com.example.shink.marvelheroesinfo.db.remote.api.Api;
import com.example.shink.marvelheroesinfo.db.remote.api.ApiService;
import com.example.shink.marvelheroesinfo.db.remote.repo.DetailRepo;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shink on 06.03.2018.
 */
//implement method of getting one character by id
public class DetailRepoImpl implements DetailRepo {
    private ApiService apiService;

    public DetailRepoImpl(){
        apiService = Api.getApiService();
    }

    //create reactive get request to api that getting all character
    @Override
    public Observable<CharacterDto> getCharacter(int id) {
        return apiService.getCharacter(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }
}
