package com.example.shink.marvelheroesinfo.db.local.impl;

import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.local.repo.MainLocalRepository;
import com.example.shink.marvelheroesinfo.utils.Constants;

import java.util.List;

import io.realm.Realm;

/**
 * Created by shink on 06.03.2018.
 */

public class MainLocalRepositoryImpl implements MainLocalRepository {
    @Override
    public List<Character> getCharacters() {
        //look at th realm database local
        Realm realm = Realm.getDefaultInstance();
        //return list of all heroes
        return realm.where(Character.class)
                .findAll();
    }
}
