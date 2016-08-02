package com.example.charlie.weatherforecastapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlie on 02/08/2016.
 */
public class Clouds extends RealmObject {

    @SerializedName("all")
    @Expose
    private int all;

    /**
     *
     * @return
     * The all
     */
    public int getAll() {
        return all;
    }

    /**
     *
     * @param all
     * The all
     */
    public void setAll(int all) {
        this.all = all;
    }

}