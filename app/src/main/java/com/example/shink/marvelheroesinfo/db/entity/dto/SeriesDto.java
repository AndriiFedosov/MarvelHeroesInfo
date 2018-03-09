package com.example.shink.marvelheroesinfo.db.entity.dto;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by shink on 06.03.2018.
 */
//create data transfer object(response from API) of series that parsed from JSON
public class SeriesDto extends RealmObject {
    @SerializedName("available")
    private int available;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}