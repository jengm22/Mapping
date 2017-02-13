package com.example.a1jengm22.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

/**
 * Created by 1jengm22 on 13/02/2017.
 */
public class SetLocationActivity extends Activity {

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


    public void onClick(View view){
        EditText longitudeEditText = (EditText)findViewById(R.id.longitude);
        EditText latitudeEditText = (EditText)findViewById(R.id.latitude);

        double longitude = Double.parseDouble(longitudeEditText.getText().toString());
        double latitude = Double.parseDouble(latitudeEditText.getText().toString());

        mv.getController().setCenter(new GeoPoint(longitude, latitude));
    }
}
