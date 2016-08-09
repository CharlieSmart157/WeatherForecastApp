package com.example.charlie.weatherforecastapp.pageFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.charlie.weatherforecastapp.R;
import com.example.charlie.weatherforecastapp.content.Content_Contract;
import com.example.charlie.weatherforecastapp.content.Content_Presenter;
import com.example.charlie.weatherforecastapp.models.cityWeatherResult;

import java.util.List;

/**
 * Created by Charlie on 02/08/2016.
 */
public class detailViewFragment extends Fragment implements Content_Contract.View{


    Boolean local = true;
    View view;
    SharedPreferences sharedPreferences;
    Content_Presenter mPresenter;
    TextView nameView, mainView, rainView, windView, cloudsView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.detail_view_fragment_layout, container, false);
        sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        mPresenter = new Content_Presenter(this);

        mPresenter.returnLocationByID(getArguments().getInt("id"));

        setRetainInstance(true);
        return view;
    }

    @Override
    public void setLocation(cityWeatherResult wR) {

        nameView = (TextView)view.findViewById(R.id.Name);
        nameView.setText(wR.getName());

        mainView = (TextView)view.findViewById(R.id.Main);
        mainView.setText("Temperature: "+wR.getMain().getTemp());

        rainView = (TextView)view.findViewById(R.id.Rain);
        rainView.setText("Weather: "+wR.getWeather().get(0).getDescription());

        cloudsView = (TextView)view.findViewById(R.id.Clouds);
        cloudsView.setText("Clouds: "+wR.getClouds().getAll());

        windView = (TextView)view.findViewById(R.id.Wind);
        windView.setText("Wind: "+wR.getWind().getSpeed());
    }

    @Override
    public void setFavourites(List<cityWeatherResult> cities) {

    }

    @Override
    public void setPresenter(Content_Contract.Presenter presenter) {

    }
}
