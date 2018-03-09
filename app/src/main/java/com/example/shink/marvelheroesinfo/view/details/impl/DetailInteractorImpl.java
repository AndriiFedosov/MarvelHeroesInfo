package com.example.shink.marvelheroesinfo.view.details.impl;

import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;
import com.example.shink.marvelheroesinfo.db.local.impl.DetailLocalRepositoryImpl;
import com.example.shink.marvelheroesinfo.db.local.repo.DetailLocalRepository;
import com.example.shink.marvelheroesinfo.db.remote.repo.DetailRepo;
import com.example.shink.marvelheroesinfo.db.remote.repo.impl.DetailRepoImpl;
import com.example.shink.marvelheroesinfo.view.details.DetailInteractor;

import rx.Observable;

/**
 * Created by shink on 07.03.2018.
 */

//create present interactor of deteail information of hero
public class DetailInteractorImpl implements DetailInteractor {

    private DetailRepo remoteRepo;
    private DetailLocalRepository localRepository;


    public DetailInteractorImpl() {
        this.remoteRepo = new DetailRepoImpl();
        this.localRepository = new DetailLocalRepositoryImpl();
    }

    //return remote character
    @Override
    public Observable<CharacterDto> getCharacter(int id) {
        return remoteRepo.getCharacter(id);
    }
    //return local character
    @Override
    public Character getLocalCharacter(int id) {
        return localRepository.getCharacter(id);
    }


}