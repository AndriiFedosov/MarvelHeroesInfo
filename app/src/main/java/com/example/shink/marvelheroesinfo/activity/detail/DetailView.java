package com.example.shink.marvelheroesinfo.activity.detail;

import com.example.shink.marvelheroesinfo.db.entity.Character;

/**
 * Created by shink on 08.03.2018.
 */

public interface DetailView {
        void setItem(Character item);
        void showProgressBar();
        void hideProgressBar();
        void showErrorMessage(String error);
}
