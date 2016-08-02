package com.example.charlie.weatherforecastapp.pageFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.charlie.weatherforecastapp.MainActivity;
import com.example.charlie.weatherforecastapp.R;
import com.example.charlie.weatherforecastapp.content.Content_Contract;
import com.example.charlie.weatherforecastapp.content.Content_Presenter;
import com.example.charlie.weatherforecastapp.models.City;
import com.example.charlie.weatherforecastapp.models.weatherResult;

import java.util.List;

/**
 * Created by Charlie on 02/08/2016.
 */
public class LocationAddFragment extends android.support.v4.app.DialogFragment implements Content_Contract.View{

    View view;
   // ProfileFragment fragment;
    TextView userEditText;
    Button confirmButton;
    Button searchButton;
    Content_Presenter mPresenter;
    int selectedID = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        mPresenter = new Content_Presenter(this);
        view = inflater.inflate(R.layout.location_add, container, false);
    //    sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
      //  Log.d("sharedprefUserID", ""+sharedPreferences.getInt("DefaultID", 0));
        userEditText = (TextView)view.findViewById(R.id.location_field);

        //Search Button
        searchButton = (Button)view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){

            //FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.dialog_profile_holder);

            @Override
            public void onClick(View v) {
                //initializeProfileView(view);
                mPresenter.returnLocationByName(userEditText.getText().toString());

            }
        });
        return view;
    }

    @Override
    public void setPresenter(Content_Contract.Presenter presenter) {

    }

    @Override
    public void setLocation(weatherResult wR) {
        MainActivity main = (MainActivity) getActivity();
        main.selectFragment(1, wR.getCity().getId());
    }

    @Override
    public void setFavourites(List<City> cities) {

    }
}
