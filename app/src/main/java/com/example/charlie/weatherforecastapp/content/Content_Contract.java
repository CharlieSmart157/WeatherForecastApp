package com.example.charlie.weatherforecastapp.content;

import com.example.charlie.weatherforecastapp.BaseTemplates.BasePresenter;
import com.example.charlie.weatherforecastapp.BaseTemplates.BaseView;
import com.example.charlie.weatherforecastapp.models.cityWeatherResult;

import java.util.List;

/**
 * Created by Charlie on 02/08/2016.
 */
public interface Content_Contract {

    interface View extends BaseView<Presenter> {
        void setLocation(cityWeatherResult wR);

        void setFavourites(List<cityWeatherResult> cities);
    }

    interface Presenter extends BasePresenter {

        void returnLocationByName(String str);

        void returnFavourites();
    }
}
