package com.example.shink.marvelheroesinfo.activity.main;

import com.example.shink.marvelheroesinfo.db.entity.Character;

import java.util.List;

/**
 * Created by shink on 08.03.2018.
 */

public interface MainView {
    void setItems(List<Character> items, int total);
    void showProgressBar();
    void hideProgressBar();
    void showErrorMessage(String error);

}