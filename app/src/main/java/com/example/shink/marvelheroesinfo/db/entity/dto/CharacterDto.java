package com.example.shink.marvelheroesinfo.db.entity.dto;

import com.example.shink.marvelheroesinfo.db.entity.Character;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shink on 06.03.2018.
 */

//create data transfer object(response from API) of charter that parsed from JSON

public class CharacterDto {
    @SerializedName("data")
    private Data data;
    @SerializedName("code")
    private int code;
    @SerializedName("etag")
    private String etag;
    @SerializedName("status")
    private String status;

    public Data getData() {
        return data;
    }

    //create inner class
    public class Data {
        @SerializedName("results")
        private List<Character> results;
        @SerializedName("count")
        private int count;
        @SerializedName("limit")
        private int limit;
        @SerializedName("offset")
        private int offset;
        @SerializedName("total")
        private int total;

        public List<Character> getResults() {
            return results;
        }

        public int getCount() {
            return count;
        }

        public int getLimit() {
            return limit;
        }

        public int getOffset() {
            return offset;
        }

        public int getTotal() {
            return total;
        }
    }
}