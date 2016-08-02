package com.example.charlie.weatherforecastapp.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.charlie.weatherforecastapp.R;
import com.example.charlie.weatherforecastapp.models.City;
import com.example.charlie.weatherforecastapp.utilities.ItemClickListener;

import java.util.List;

/**
 * Created by Charlie on 27/07/2016.
 */
public class Location_Adapter extends RecyclerView.Adapter<Location_Adapter.ItemHolder>{

    private List<City> cities;
    private int rowLayout;
    private Context context;
    private FragmentManager fragManager;

    public Location_Adapter(Context context, List<City> cities, int rowLayout){

        this.cities = cities;
        this.context = context;
        this.rowLayout = rowLayout;
        fragManager = ((AppCompatActivity)context).getSupportFragmentManager();
        notifyDataSetChanged();
    }


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ItemHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final City city = cities.get(position);
        holder.nameText.setText(city.getName());



        holder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                Log.i("Clicked",""+position);

            }
        });
    }

    private String boolToResult(boolean b){

        if (b)
            return "Win";
        else
            return "Loss";
    }

    @Override
    public int getItemCount() {
        return cities ==null ? 0: cities.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements  View.OnClickListener, View.OnLongClickListener{

        //@BindView(R.id.titleText)
        TextView nameText;

        private ItemClickListener clickListener;

        public void setClickListener(ItemClickListener itemClickListener){
            this.clickListener = itemClickListener;
        }


        public ItemHolder(View row) {
            super(row);
            nameText = (TextView)row.findViewById(R.id.nameText);

            //  ButterKnife.bind(this, row);
            row.setTag(row);
            row.setOnClickListener(this);
            row.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getPosition());
            return false;
        }

    }
}
