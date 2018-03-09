package com.example.shink.marvelheroesinfo.view.main;

import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;

import java.util.List;

import rx.Observable;

/**
 * Created by shink on 07.03.2018.
 */

public interface MainInteractor {

    Observable<CharacterDto> getAllCharacters(int offset);

    List<Character> getLocalCharacters();
}