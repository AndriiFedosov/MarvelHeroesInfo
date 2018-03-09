package com.example.shink.marvelheroesinfo.db.remote.repo;

import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;

import rx.Observable;

/**
 * Created by shink on 06.03.2018.
 */

public interface MainRepo {
    Observable<CharacterDto> getAllCharacters(int offset);
}
