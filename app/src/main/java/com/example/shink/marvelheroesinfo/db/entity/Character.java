package com.example.shink.marvelheroesinfo.db.entity;

import com.example.shink.marvelheroesinfo.db.entity.dto.ComicsDto;
import com.example.shink.marvelheroesinfo.db.entity.dto.EventsDto;
import com.example.shink.marvelheroesinfo.db.entity.dto.SeriesDto;
import com.example.shink.marvelheroesinfo.db.entity.dto.StoriesDto;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by shink on 06.03.2018.
 */
//create realm database model that saved from JSON that cam from Marvel API
public class Character extends RealmObject {
    @SerializedName("id")
    @PrimaryKey
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("modified")
    private String modified;
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @SerializedName("comicsDto")
    private ComicsDto comicsDto;
    @SerializedName("seriesDto")
    private SeriesDto seriesDto;
    @SerializedName("storiesDto")
    private StoriesDto storiesDto;
    @SerializedName("eventsDto")
    private EventsDto eventsDto;
    @SerializedName("urls")
    private RealmList<Url> urls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getThumbnail() {
        return thumbnail.getFullPath();
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public ComicsDto getComicsDto() {
        return comicsDto;
    }

    public void setComicsDto(ComicsDto comicsDto) {
        this.comicsDto = comicsDto;
    }

    public SeriesDto getSeriesDto() {
        return seriesDto;
    }

    public void setSeriesDto(SeriesDto seriesDto) {
        this.seriesDto = seriesDto;
    }

    public StoriesDto getStoriesDto() {
        return storiesDto;
    }

    public void setStoriesDto(StoriesDto storiesDto) {
        this.storiesDto = storiesDto;
    }

    public EventsDto getEventsDto() {
        return eventsDto;
    }

    public void setEventsDto(EventsDto eventsDto) {
        this.eventsDto = eventsDto;
    }

    public RealmList<Url> getUrls() {
        return urls;
    }

    public void setUrls(RealmList<Url> urls) {
        this.urls = urls;
    }
}