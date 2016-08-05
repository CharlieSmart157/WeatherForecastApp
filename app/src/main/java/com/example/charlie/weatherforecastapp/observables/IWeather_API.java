package com.example.charlie.weatherforecastapp.observables;

import com.example.charlie.weatherforecastapp.models.cityWeatherResult;
import com.example.charlie.weatherforecastapp.utilities.Constants;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Charlie on 02/08/2016.
 */
public interface IWeather_API {

    @GET(Constants.CITY_WEATHER_BY_NAME_URL)
    Observable<cityWeatherResult> getCityByName(@Query("q")String city_name, @Query("APPID")String key);
}
