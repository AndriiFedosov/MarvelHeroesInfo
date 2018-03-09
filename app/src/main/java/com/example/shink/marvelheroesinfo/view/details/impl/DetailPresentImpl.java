package com.example.shink.marvelheroesinfo.view.details.impl;

import com.example.shink.marvelheroesinfo.activity.detail.DetailView;
import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.example.shink.marvelheroesinfo.db.entity.dto.CharacterDto;
import com.example.shink.marvelheroesinfo.view.details.DetailInteractor;
import com.example.shink.marvelheroesinfo.view.details.DetailPresent;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by shink on 07.03.2018.
 */

//create present of detail information about hero
public class DetailPresentImpl implements DetailPresent {

    private DetailView detailView;
    private DetailInteractor detailInteractor;
    private CompositeSubscription subscription;
    private Realm realm;

    public DetailPresentImpl(DetailView detailView) {
        this.detailView = detailView;
        this.detailInteractor = new DetailInteractorImpl();
        this.subscription = new CompositeSubscription();
    }

    //when on create method get realm database
    @Override
    public void onCreate() {
        realm = Realm.getDefaultInstance();
    }

    //when destroy set view null and close db
    @Override
    public void onDestroy() {
        this.detailView = null;
        subscription.clear();
        realm.close();
    }

    //present one character
    @Override
    public void getCharacter(int id) {
        //show progerss bar to load hero
        detailView.showProgressBar();
        //create subscription
        Subscription subs =detailInteractor.getCharacter(id).subscribe(new Observer<CharacterDto>() {
            @Override
            public void onCompleted() {
            }
            // create error massage
            @Override
            public void onError(Throwable e) {
                getCharacterError(e.getMessage());
            }

            //get character from JSON
            @Override
            public void onNext(CharacterDto characterDto) {
                getCharacterSuccess(characterDto);

            }
        });
        addSubscription(subs);
    }

    //show information about local character
    @Override
    public void getLocalCharacter(int id) {
        detailView.showProgressBar();
        Character character = detailInteractor.getLocalCharacter(id);
        getLocalCharacterSuccess(character);
    }
    private void addSubscription(Subscription subs) {
        subscription.add(subs);
    }

    //work with remote character
    private void getCharacterSuccess(CharacterDto dto){
        if(detailView != null){
            //get data from JSON character
            CharacterDto.Data chartData = dto.getData();
            //get list of heroes
            List<Character> chars = chartData.getResults();
            //get firs hero
            Character character = chars.get(0);
            //if this hero not exeist
            if(character != null){
                //copy character in database
                copyInRealmOrUpdate(character);
            }
            //show current character
            detailView.setItem(character);
            //hide progress bar
            detailView.hideProgressBar();
        }
    }

    //create error method message
    private void getCharacterError(String error){
        if(detailView !=null){
            detailView.showErrorMessage(error);
            detailView.hideProgressBar();
        }
    }

    //work with realm database
    public void copyInRealmOrUpdate(Character character) {
        //open transaction
        realm.beginTransaction();
        //copy character in database or update him
        realm.copyToRealmOrUpdate(character);
        //close transuction and commit result
        realm.commitTransaction();
    }

    //
    private void getLocalCharacterSuccess(Character character) {
        //look that character is present on view
        if (detailView != null) {
            if (character != null)
                //copy in realm data base character
                copyInRealmOrUpdate(character);
            detailView.setItem(character);
            detailView.hideProgressBar();
        }
    }
}
