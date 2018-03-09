package com.example.shink.marvelheroesinfo.activity.main.adapters;

/**
 * Created by shink on 08.03.2018.
 */
//create one click listener
public interface OnItemClickListener<ItemClass> {
    void onItemClicked(int position, ItemClass item);
}