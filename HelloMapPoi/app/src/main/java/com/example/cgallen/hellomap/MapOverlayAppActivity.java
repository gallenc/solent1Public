package com.example.cgallen.hellomap;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.util.Log;

public class MapOverlayAppActivity extends Activity {
    private static final String LOG_TAG = MapOverlayAppActivity.class.getName();

    private static final String POI_FILE="poi.txt";

    MapView mv;
    ItemizedIconOverlay<OverlayItem> items;
    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> markerGestureListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_overlay_map_app);
        mv = (MapView) findViewById(R.id.overlayappmap);
        mv.setBuiltInZoomControls(true);

        mv.getController().setZoom(14);
        //-1.4011,50.9319 crown pub
        mv.getController().setCenter(new GeoPoint(50.9319, -1.4011));

        markerGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            public boolean onItemLongPress(int i, OverlayItem item)
            {
                Toast.makeText(MapOverlayAppActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }

            public boolean onItemSingleTapUp(int i, OverlayItem item)
            {
                Toast.makeText(MapOverlayAppActivity.this, item.getSnippet(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        items = new ItemizedIconOverlay<OverlayItem>(this, new ArrayList<OverlayItem>(), markerGestureListener);

        // TODO REMOVE this simply adds one marker for test
        //OverlayItem blackdown = new OverlayItem("Blackdown", "highest point in West Sussex", new GeoPoint(51.0581, -0.6897));
        // NOTE is just this.getDrawable() if supporting API 21+ only
       // blackdown.setMarker(getResources().getDrawable(R.drawable.marker));
        // items.addItem(blackdown);
       // ArrayList<OverlayItem> itemslist2 = new ArrayList<OverlayItem>();
       // itemslist2.add(blackdown);
       // items.addItems(itemslist2);

        // this adds from file
        ArrayList<OverlayItem> itemslist = csvFileToOverlays();
        items.addItems(itemslist);

        mv.getOverlays().add(items);

    }



    private ArrayList<OverlayItem> csvFileToOverlays() {
        ArrayList<OverlayItem> overalyItems= new ArrayList<OverlayItem>();

       // StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + POI_FILE);
            if (!file.exists()) {
                popupMessage("file does not exist: " + file.getAbsolutePath());
            } else {
                Log.i(LOG_TAG,"loading points of interest from:"+file.getAbsolutePath());
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);

                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    try {
                        Log.i(LOG_TAG,"adding point of interest from:"+line);
                        // csv format The Crown,pub,nice pub,-1.4011,50.9319
                        String[] components = line.split(",");
                        if (components.length != 5)
                            throw new IllegalArgumentException("invalid string " + line +
                                    " cannot be split into title, snippet, detail ,lat, lon");
                        String title = components[0];
                        String type = components[1];
                        String snippet = components[2];
                        String lonstr = components[3];
                        String latstr = components[4];
                        double lat = Double.parseDouble(latstr);
                        double lon = Double.parseDouble(lonstr);

                        OverlayItem overlayItem = new OverlayItem(title, snippet, new GeoPoint(lat, lon));
                        Log.i(LOG_TAG,"added point of interest from: title:" +title+
                                ", snippet " +snippet+
                                        ", type " +type+
                                        ", lat " +lat+
                                        ", lon " +lon);

                        Drawable marker =  typeToMarker(type);
                        if (marker!=null) overlayItem.setMarker(marker);

                        overalyItems.add(overlayItem);
                    } catch (Exception e){
                        Log.e(LOG_TAG,"problem parsing line:"+line,e);
                    }
                }
            }
        } catch (IOException e) {
            popupMessage(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException ex) {
            }
        }

        return overalyItems;
    }

    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }

    private Drawable typeToMarker(String type){
        // NOTE is just this.getDrawable() if supporting API 21+ only
        Drawable marker =null;
        switch (type) {
            case "pub":  marker = getResources().getDrawable(R.drawable.pub);
                break;
            case "restaurant":  marker = getResources().getDrawable(R.drawable.restaurant);
                break;
            case "city":  marker = getResources().getDrawable(R.drawable.marker);
                break;
            default: marker=null;
                break;
        }



        return marker;
    }

}
