package com.example.charlie.weatherforecastapp.content;

import android.util.Log;

import com.example.charlie.weatherforecastapp.Realm.RealmController;
import com.example.charlie.weatherforecastapp.models.cityWeatherResult;
import com.example.charlie.weatherforecastapp.observables.IWeather_API;
import com.example.charlie.weatherforecastapp.utilities.Constants;

import retrofit.RestAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Charlie on 02/08/2016.
 */
public class Content_Presenter implements Content_Contract.Presenter {

    private CompositeSubscription _subscriptions = new CompositeSubscription();
    private final Content_Contract.View mContentView;


    IWeather_API iWeather_api;

    RestAdapter restapi;
    RealmController realmController;

    public Content_Presenter (Content_Contract.View v){

        mContentView = v;
        mContentView.setPresenter(this);


        realmController = RealmController.getInstance();


    }

    @Override
    public void returnLocationByName(String name) {

        final int[] id = {0};
        restapi = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        iWeather_api = restapi.create(IWeather_API.class);
        _subscriptions.add(iWeather_api.getCityByName(name, Constants.API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<cityWeatherResult>() {
                    @Override
                    public void onCompleted() {
                        //Send Results to View
                        Log.i("onCOMPLETED", "DONE");


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onERROR", e+"");
                    }

                    @Override
                    public void onNext(cityWeatherResult datum) {
                        Log.d("CITY ", datum.getName());
                       realmController.addCity(datum);
                      //  idNo[0] = datum.getId();
                        // mContentView.setSummoner(realmController.getSummoner(datum.getId()));
                        mContentView.setLocation(datum);
                    }
                })


        );


    }

    @Override
    public void returnLocationByID(int id) {

        mContentView.setLocation(realmController.getCityById(id));

    }

    @Override
    public void returnFavourites() {
        mContentView.setFavourites(realmController.getCities());
    }
}
