package com.example.shink.marvelheroesinfo.db.local.repo;

import com.example.shink.marvelheroesinfo.db.entity.Character;

import java.util.List;

/**
 * Created by shink on 06.03.2018.
 */
//create interface to get all count of marvel heroes
public interface MainLocalRepository {
    List<Character> getCharacters();
}