package com.example.cgallen.hellomap;

import android.app.Activity;

import android.os.Bundle;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import java.util.ArrayList;

import android.preference.PreferenceManager;
import android.widget.Toast;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

public class ExampleMapOverlayActivity extends Activity {

    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_example_overlay_map);
        mv = (MapView) findViewById(R.id.overlaymap);

        mv.getController().setZoom(14);
        mv.getController().setCenter(new GeoPoint(51.05, -0.72));


        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), null);
        OverlayItem fernhurst = new OverlayItem("Fernhurst", "Village in West Sussex", new GeoPoint(51.05, -0.72));
        OverlayItem blackdown = new OverlayItem("Blackdown", "highest point in West Sussex", new GeoPoint(51.0581, -0.6897));
        items.addItem(fernhurst);
        items.addItem(blackdown);
        mv.getOverlays().add(items);


    }
}
