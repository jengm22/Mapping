package com.example.a1jengm22.mapping;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Button;

import org.osmdroid.config.Configuration;

/**
 * Created by 1jengm22 on 06/03/2017.
 */
public class MyPrefsActivity extends PreferenceActivity {
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
}
