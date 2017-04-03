package com.example.a1jengm22.mapping;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HelloMap extends Activity
{

    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;

    public void onCreate(Bundle savedInstanceState)
    {
//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(13.45,-16.57));

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>()
        {
            //in annonymous class
            public boolean onItemLongPress(int i, OverlayItem item)
            {
                Toast.makeText(HelloMap.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item)
            {
                Toast.makeText(HelloMap.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        //to link listener to the overlay layer
        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);
        OverlayItem banjul = new OverlayItem("Banjul", "Capital city of The Gambia", new GeoPoint(13.455, -16.58));

       //note is just this.getDrawable() if supporting API 21+ only
        banjul.setMarker(getResources().getDrawable(R.drawable.marker));

        OverlayItem kingFahadMosque = new OverlayItem("King_Fahad_Mosque", "Grand Mosque of Banjul", new GeoPoint(13.4570, -16.5828));


        try
        {
        BufferedReader reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath()+"poi.txt"));
        String line;
        while((line = reader.readLine()) != null) {
            String[] components = line.split("name,description,comments,latitude,longitude");
            if (components.length == 5) {
                Poi currentPoi = new Poi(components[0], components[1], components[2], components[3], components[4]);
                Poi.add(currentPoi);
            }

            OverlayItem crown = new OverlayItem("The Crown", "PUB", "nice pub", new GeoPoint(-1.4011, 50.9319));
            OverlayItem Charlie_Chans = new OverlayItem("restaurant", "A very interesting place", new GeoPoint(-1.39916, 50.8983));

            items.addItem(banjul);
            items.addItem(kingFahadMosque);
            items.addItem(crown);
            items.addItem(Charlie_Chans);

            mv.getOverlays().add(items);
        }
    }
        catch(IOException e){
        System.out.println("ERROR: " + e);

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

            return true;
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
            }
            else if (requestCode == 1)
            {

                Bundle extras=intent.getExtras();
                double longitude  = extras.getDouble("com.example.longitude");
                double latitude= extras.getDouble("com.example.latitude");
                extras.getDouble("com.example.longitude");
                mv.getController().setCenter(new GeoPoint(latitude,longitude));
            }

        }

    }
   @Override
    public void onStart()
    {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        double lat = Double.parseDouble ( prefs.getString("lat", "13.45") );
        double lon = Double.parseDouble ( prefs.getString("lon", "-16.57") );
        int zoom = Integer.parseInt ( prefs.getString("zoom", "14") );

        mv.getController().setZoom(zoom);

        mv.getController().setCenter(new GeoPoint(lat,lon));

        // do something with the preference data...
    }

}
