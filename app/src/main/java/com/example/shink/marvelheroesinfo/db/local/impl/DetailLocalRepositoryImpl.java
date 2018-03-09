package com.example.shink.marvelheroesinfo.db.local.impl;

import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.local.repo.DetailLocalRepository;
import com.example.shink.marvelheroesinfo.utils.Constants;

import io.realm.Realm;

/**
 * Created by shink on 06.03.2018.
 */
//implement detail interface and find charter in database by id of hero
public class DetailLocalRepositoryImpl implements DetailLocalRepository {
    @Override
    public Character getCharacter(int id) {
        //get realm database
        Realm realm = Realm.getDefaultInstance();
        //return charter in database by id of hero
        return realm.where(Character.class).
                equalTo(Constants.ID,id)
                .findFirst();
    }
}
