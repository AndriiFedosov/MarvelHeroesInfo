package com.example.shink.marvelheroesinfo.db.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by shink on 06.03.2018.
 */
//create realm database model of path to image of hero and
public class Thumbnail extends RealmObject {
    @SerializedName("path")
    private String path;
    @SerializedName("extension")
    private String extension;

    public String getFullPath() {
        return String.format("%s.%s", path, extension);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}