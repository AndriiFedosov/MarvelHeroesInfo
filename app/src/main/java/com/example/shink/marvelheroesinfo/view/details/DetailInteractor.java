package com.example.shink.marvelheroesinfo.view.details;

import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;

import rx.Observable;

/**
 * Created by shink on 07.03.2018.
 */

public interface DetailInteractor {

    Observable<CharacterDto> getCharacter(int id);

    Character getLocalCharacter(int id);
}
