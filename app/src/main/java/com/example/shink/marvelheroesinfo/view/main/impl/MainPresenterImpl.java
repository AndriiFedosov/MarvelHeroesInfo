package com.example.shink.marvelheroesinfo.view.main.impl;

import com.example.shink.marvelheroesinfo.activity.main.MainView;
import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;
import com.example.shink.marvelheroesinfo.view.main.MainInteractor;
import com.example.shink.marvelheroesinfo.view.main.MainPresenter;

import java.util.List;

import io.realm.Realm;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by shink on 07.03.2018.
 */
//create present of all hero
public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;
    private MainInteractor mainInteractor;
    private CompositeSubscription mSubscriptions;
    private Realm realm;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
        this.mSubscriptions = new CompositeSubscription();
    }

    //when on create method get realm database
    @Override
    public void onCreate() {
        realm= Realm.getDefaultInstance();
    }

    //when destroy set view null and close database
    @Override
    public void onDestroy() {
        this.mainView = null;
        mSubscriptions.clear();
        realm.close();
    }
    //present of all  characters
    @Override
    public void getCharacters(int offset) {
        //show progerss bar to load heroes
        mainView.showProgressBar();
        //create subscription
        Subscription subs = mainInteractor.getAllCharacters(offset).subscribe(new Observer<CharacterDto>() {
            @Override
            public void onCompleted() {

            }
            // create error massage
            @Override
            public void onError(Throwable e) {
                getCharacterError(e.getMessage());
            }
            //create all heroes from JSON
            @Override
            public void onNext(CharacterDto characterDto) {
                getCharacterSuccess(characterDto);
            }
        });
        addSubscription(subs);
    }

    @Override
    //show information about sll local characters
    public void getLocalCharacters() {
        mainView.showProgressBar();
        List<Character> chars = mainInteractor.getLocalCharacters();
        getLocalCharacterSuccess(chars);
    }

    private void addSubscription(Subscription subscription) {
        mSubscriptions.add(subscription);
    }


    private void getCharacterSuccess(CharacterDto response) {
        if (mainView != null) {
            //work with remote character
            CharacterDto.Data dto = response.getData();
            //get list of heroes
            List<Character> chars = dto.getResults();
            //copy to realm database all characters
            copyToRealmOrUpdate(chars);
            //show characters on view
            mainView.setItems(chars, dto.getTotal());
            //hide progress bar
            mainView.hideProgressBar();
        }
    }
    //create character error
    private void getCharacterError(String error) {
        if (mainView != null) {
            mainView.showErrorMessage(error);
            mainView.hideProgressBar();
        }
    }
    //work with realm database
    public void copyToRealmOrUpdate(List<Character> chars) {
        //open transaction
        realm.beginTransaction();
        //copy characters in database or update thay
        realm.copyToRealmOrUpdate(chars);
        //close transaction
        realm.commitTransaction();
    }

    private void getLocalCharacterSuccess(List<Character> results) {
        //look that character is present on view
        if (mainView != null) {
            //show result
            mainView.setItems(results, 1000);
            mainView.hideProgressBar();
        }
    }




}