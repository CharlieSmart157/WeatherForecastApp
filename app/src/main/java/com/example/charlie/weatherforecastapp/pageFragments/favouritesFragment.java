package com.example.charlie.weatherforecastapp.pageFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charlie.weatherforecastapp.Adapters.Location_Adapter;
import com.example.charlie.weatherforecastapp.R;
import com.example.charlie.weatherforecastapp.content.Content_Contract;
import com.example.charlie.weatherforecastapp.content.Content_Presenter;
import com.example.charlie.weatherforecastapp.models.City;
import com.example.charlie.weatherforecastapp.models.weatherResult;

import java.util.List;

/**
 * Created by Charlie on 02/08/2016.
 */
public class favouritesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,Content_Contract.View{

    Boolean local = true;
    View view;
    SharedPreferences sharedPreferences;
    SwipeRefreshLayout mSwipeRefresher;
    RecyclerView locationRecyclerView;
    Location_Adapter mAdapter;
    Content_Presenter mPresenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.favourite_location_layout, container, false);
        sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        mSwipeRefresher = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefresher.setOnRefreshListener(this);
        mPresenter = new Content_Presenter(this);

        initializeRecyclerView(view);
        setRetainInstance(true);
        return view;
    }



    public void initializeRecyclerView(View v){
        Log.d("INIT","initialising");
        locationRecyclerView = (RecyclerView)v.findViewById(R.id.favourite_location_recycler_view);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        locationRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPresenter.returnFavourites();

    }




    @Override
    public void onRefresh() {
        mSwipeRefresher.setRefreshing(false);
        mPresenter.returnFavourites();
    }

    public void setAdapter(List<City> cities) {
        mAdapter = new Location_Adapter(getContext(),cities,R.layout.city_row_layout);
        locationRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void setLocation(weatherResult wR) {


    }

    @Override
    public void setFavourites(List<City> cities) {
        setAdapter(cities);
    }

    @Override
    public void setPresenter(Content_Contract.Presenter presenter) {

    }
}