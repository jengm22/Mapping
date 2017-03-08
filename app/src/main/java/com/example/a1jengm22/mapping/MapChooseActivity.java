package com.example.a1jengm22.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Double3;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;

//
    public class MapChooseActivity extends Activity implements View.OnClickListener
    {
        public void onCreate(Bundle savedInstanceState)

        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mca);
            Button regular = (Button) findViewById(R.id.btnRegular);
            regular.setOnClickListener(this);
            Button cyclemap = (Button) findViewById(R.id.btnCyclemap);
            cyclemap.setOnClickListener(this);

        }

        public void onClick(View v)
        {
            Intent intent = new Intent();
            Bundle bundle=new Bundle();
            boolean cyclemap=false;
            if (v.getId()==R.id.btnCyclemap)
            {
                cyclemap=true;
            }
            bundle.putBoolean("com.example.cyclemap",cyclemap);
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
            finish();


        }

}
