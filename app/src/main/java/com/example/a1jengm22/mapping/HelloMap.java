package com.example.a1jengm22.mapping;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;

public class HelloMap extends Activity
{

    MapView mv;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(51.05,-0.72));
    }


    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    // comment

    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.choosemap)
        {
            // react to the menu item being selected...
           // System.exit(0); this code closes the app

            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent, 0);

            return true;
        }
        else if(item.getItemId() == R.id.setlocation)
        {
            // react to the menu item being selected...
            // System.exit(0); this code closes the app

            Intent intent = new Intent(this, SetLocationActivity.class);
            startActivityForResult(intent, 1);

            return true;
        }
        else if(item.getItemId()== R.id.preferences)
        {
            Intent intent = new Intent(this,MyPrefsActivity.class);
            startActivityForResult(intent, 2);
        }
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(resultCode == RESULT_OK)
        {

            if (requestCode == 0)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }else if (requestCode == 1){

                Bundle extras=intent.getExtras();
                double longitude  = extras.getDouble("com.example.longitude");
                double latitude= extras.getDouble("com.example.latitude");
                extras.getDouble("com.example.longitude");
                mv.getController().setCenter(new GeoPoint(latitude,longitude));
            }
        }

    }
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "13.45") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-16.57") );
        mv.getController().setCenter(new GeoPoint(lat,lon));

        // do something with the preference data...
    }
}
