package com.example.shink.marvelheroesinfo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by shink on 08.03.2018.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //when create app ,,create realm DB
        Realm.init(this);
        ///configure DB
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        //add config
        Realm.setDefaultConfiguration(config);

    }
}