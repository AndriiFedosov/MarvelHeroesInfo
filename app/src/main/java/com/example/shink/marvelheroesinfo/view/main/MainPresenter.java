package com.example.shink.marvelheroesinfo.view.main;

/**
 * Created by shink on 07.03.2018.
 */

public interface MainPresenter {

    void onCreate();

    void onDestroy();

    void getCharacters(int offset);

    void getLocalCharacters();

}