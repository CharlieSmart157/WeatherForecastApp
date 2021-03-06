package com.example.charlie.weatherforecastapp;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.charlie.weatherforecastapp.Realm.RealmController;
import com.example.charlie.weatherforecastapp.pageFragments.LocationAddFragment;
import com.example.charlie.weatherforecastapp.pageFragments.detailViewFragment;
import com.example.charlie.weatherforecastapp.pageFragments.favouritesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RealmController.with(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addLocation();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        selectFragment(0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action
            selectFragment(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


   public void selectFragment(int position, int... id){

       Bundle args = new Bundle();
       int selectedId = id.length > 0 ? id[0]:0;
       if(id.length >0)
           selectedId = id[0];

       //Insert Switch Statement here
       switch(position){
           case 0:
               Log.i("Case"," "+position);
               getSupportActionBar().setTitle("Favourites");
               fragment = new favouritesFragment();
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                   this.invalidateOptionsMenu();
               }
               break;
           case 1:
               Log.i("Case"," "+position);
               getSupportActionBar().setTitle("Details");
               fragment = new detailViewFragment();
               args.putInt("id", selectedId);
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                   this.invalidateOptionsMenu();
               }
               break;

       }
       fragment.setArguments(args);
       android.support.v4.app.FragmentManager frgManager = getSupportFragmentManager();
       frgManager.beginTransaction().replace(R.id.fragment_layout_holder, fragment).commit();



   }

    public void addLocation(){

        android.support.v4.app.FragmentManager frgManager = getSupportFragmentManager();

        LocationAddFragment locationAddFragment = new LocationAddFragment();
        locationAddFragment.show(frgManager, "LocationAddDialog");

    }
}
