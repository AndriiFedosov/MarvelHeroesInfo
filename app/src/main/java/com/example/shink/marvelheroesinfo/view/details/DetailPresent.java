package com.example.shink.marvelheroesinfo.view.details;

/**
 * Created by shink on 07.03.2018.
 */

public interface DetailPresent {

    void onCreate();
    void onDestroy();


    void getCharacter(int id);
    void getLocalCharacter(int id);
}
