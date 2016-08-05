package com.example.charlie.weatherforecastapp.Realm;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.charlie.weatherforecastapp.models.cityWeatherResult;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Charlie on 17/06/2016.
 */

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm instance
   // public void refresh() {

    //    realm.
   // }

    //clear all objects from Book.class
    public void clearAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    //find all objects in the Result.class
    public RealmResults<cityWeatherResult> getCities() {

        return realm.where(cityWeatherResult.class).findAll();
    }

    //find all objects in the Result.class
    public ArrayList<Integer> getCityIDs() {
        List<cityWeatherResult> RSList= new ArrayList<cityWeatherResult>();
        ArrayList<Integer>intList = new ArrayList<Integer>();
        RSList.addAll(realm.where(cityWeatherResult.class).equalTo("WatchList",true).findAll());
        for(int i =0; i<RSList.size();i++){
            intList.add(RSList.get(i).getId());
        }
        return intList;

    }


    //query a single item with the given id
    public cityWeatherResult getCityByName(String id) {

        return realm.where(cityWeatherResult.class).equalTo("name",id).findFirst();
    }

    //query a single item with the given id
    public cityWeatherResult getSummoner(int id) {

        return realm.where(cityWeatherResult.class).equalTo("id",id).findFirst();
    }


    //check if RealmSummoner.class is empty
    public boolean hasResults() {

        //return !realm.where(RealmSummoner.class).isNotEmpty();
        return true;
    }


  //  public ArrayList<City> getLocalList(){
 //       ArrayList<City> n = new ArrayList<SummonerModel>();
 //       ArrayList<City>r = new ArrayList<RealmSummoner>();
//
  //      r.addAll(getSummoners());
  //      for(int i=0;i<r.size();i++){
   //         SummonerModel a = new SummonerModel(r.get(i));
  //          n.add(a);
  //      }

   //     return n;
 //   }

    public void addLocalList(List<cityWeatherResult> L){
        for(int i=0; i < L.size();i++){

         //   if(RealmController.getInstance().getSummoner(L.get(i).getId())==null)

                realm.beginTransaction();
                realm.copyToRealmOrUpdate(L.get(i));
                realm.commitTransaction();


        }
    }

    public void addCity(cityWeatherResult S){

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(S);
        realm.commitTransaction();
    }



    //query example
    public RealmResults<cityWeatherResult> queriedResults() {

        return realm.where(cityWeatherResult.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();

    }



}
