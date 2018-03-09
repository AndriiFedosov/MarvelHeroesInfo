package com.example.shink.marvelheroesinfo.db.local.repo;

import com.example.shink.marvelheroesinfo.db.entity.Character;

/**
 * Created by shink on 06.03.2018.
 */
//create interface to get detail information about one marvel hero
public interface DetailLocalRepository {
    Character getCharacter(int id);
}