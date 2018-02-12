package com.example.cgallen.hellomap;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class MyPrefsActivity extends PreferenceActivity {
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // we are using instead of fragments
        addPreferencesFromResource(R.xml.preferences);

        //note problem when we cant validate preferences
    }
}