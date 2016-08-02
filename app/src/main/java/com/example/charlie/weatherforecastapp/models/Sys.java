package com.example.charlie.weatherforecastapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Charlie on 02/08/2016.
 */
public class Sys extends RealmObject {

    @SerializedName("population")
    @Expose
    private int population;

    /**
     *
     * @return
     * The population
     */
    public int getPopulation() {
        return population;
    }

    /**
     *
     * @param population
     * The population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

}