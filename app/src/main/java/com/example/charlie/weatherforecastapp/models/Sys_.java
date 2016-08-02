package com.example.charlie.weatherforecastapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlie on 02/08/2016.
 */
public class Sys_ extends RealmObject {

    @SerializedName("pod")
    @Expose
    private String pod;

    /**
     *
     * @return
     * The pod
     */
    public String getPod() {
        return pod;
    }

    /**
     *
     * @param pod
     * The pod
     */
    public void setPod(String pod) {
        this.pod = pod;
    }

}