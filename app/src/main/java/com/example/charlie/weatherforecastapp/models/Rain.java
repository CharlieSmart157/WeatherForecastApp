package com.example.charlie.weatherforecastapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlie on 02/08/2016.
 */
public class Rain extends RealmObject {

    @SerializedName("3h")
    @Expose
    private double _3h;

    /**
     *
     * @return
     * The _3h
     */
    public double get3h() {
        return _3h;
    }

    /**
     *
     * @param _3h
     * The 3h
     */
    public void set3h(double _3h) {
        this._3h = _3h;
    }

}