package com.example.charlie.weatherforecastapp.observables;

import com.example.charlie.weatherforecastapp.models.weatherResult;
import com.example.charlie.weatherforecastapp.utilities.Constants;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Charlie on 02/08/2016.
 */
public interface IWeather_API {

    @GET(Constants.BASE_URL + Constants.API_KEY)
    Observable<weatherResult> getCityByName(@Path("cityname")String city_name );
}
