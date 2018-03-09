package com.example.shink.marvelheroesinfo.view.main.impl;

import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;
import com.example.shink.marvelheroesinfo.db.local.impl.MainLocalRepositoryImpl;
import com.example.shink.marvelheroesinfo.db.local.repo.MainLocalRepository;
import com.example.shink.marvelheroesinfo.db.remote.repo.MainRepo;
import com.example.shink.marvelheroesinfo.db.remote.repo.impl.MainRepoImpl;
import com.example.shink.marvelheroesinfo.view.main.MainInteractor;

import java.util.List;

import rx.Observable;

/**
 * Created by shink on 07.03.2018.
 */

public class MainInteractorImpl implements MainInteractor {

    private MainRepo remoteRepository;
    private MainLocalRepository localRepository;


    public MainInteractorImpl() {
        this.remoteRepository = new MainRepoImpl();
        this.localRepository = new MainLocalRepositoryImpl();
    }
    //return all charters of marvel
    @Override
    public Observable<CharacterDto> getAllCharacters(int offset) {
        return remoteRepository.getAllCharacters(offset);
    }
    //return all  of local data base
    @Override
    public List<Character> getLocalCharacters() {
        return localRepository.getCharacters();
    }
}