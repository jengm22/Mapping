package com.example.a1jengm22.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

/**
 * Created by 1jengm22 on 13/02/2017.
 */
public class SetLocationActivity extends Activity implements View.OnClickListener{

    MapView mv;

    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.setlocation);
        Button setlocation = (Button) findViewById(R.id.button);
        setlocation.setOnClickListener(this);



        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));


    }


    public void onClick(View view){
        EditText longitudeEditText = (EditText)findViewById(R.id.longitude);
        EditText latitudeEditText = (EditText)findViewById(R.id.latitude);

        double longitude = Double.parseDouble(longitudeEditText.getText().toString());
        double latitude = Double.parseDouble(latitudeEditText.getText().toString());

        Intent intent = new Intent();
        Bundle bundle=new Bundle();


        bundle.putDouble("com.example.latitude",latitude);
        bundle.putDouble("com.example.longitude",longitude);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();



    }
}
