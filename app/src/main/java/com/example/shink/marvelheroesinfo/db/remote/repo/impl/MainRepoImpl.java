package com.example.shink.marvelheroesinfo.db.remote.repo.impl;

import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;
import com.example.shink.marvelheroesinfo.db.remote.api.Api;
import com.example.shink.marvelheroesinfo.db.remote.api.ApiService;
import com.example.shink.marvelheroesinfo.db.remote.repo.MainRepo;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shink on 06.03.2018.
 */
//implement method of getting all character by id
public class MainRepoImpl implements MainRepo {
    private ApiService apiService;

    public MainRepoImpl(){
        apiService = Api.getApiService();
    }

    //create reactive request to get all characters
    @Override
    public Observable<CharacterDto> getAllCharacters(int offset) {
        return apiService.getCharacters(offset)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io());
    }
}
