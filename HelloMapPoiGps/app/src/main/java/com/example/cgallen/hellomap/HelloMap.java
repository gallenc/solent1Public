package com.example.cgallen.hellomap;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import android.widget.TextView;

import android.app.Activity;
import android.widget.Toast;

//public class HelloMap extends AppCompatActivity implements OnClickListener {
public class HelloMap extends Activity {

    private Double latitude = Constants.DEFAULT_LAT;
    private Double longitude = Constants.DEFAULT_LON;
    private Integer zoom = Constants.DEFAULT_ZOOM;
    private String mapCode = null;

    MapView mv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load last used mapCode if available
        if (savedInstanceState != null) {
            mapCode = savedInstanceState.getString("com.example.mapcode");
        }

        // load preferred map code if no last used mapcode
        if (mapCode == null) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            mapCode = prefs.getString("mapPref", Constants.DEFAULT_MAP);
        }

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_hello_map);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        // do something with the preference data...
        try {
            latitude = Double.parseDouble(prefs.getString("lat", Constants.DEFAULT_LAT.toString()));
            longitude = Double.parseDouble(prefs.getString("lon", Constants.DEFAULT_LON.toString()));
            zoom = Integer.parseInt(prefs.getString("zoom", Constants.DEFAULT_ZOOM.toString()));
        } catch (Exception ex) {
            popupMessage("invalid default preferenced entry: " + ex.getMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        centerMap();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // save the chosen map
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("com.example.mapcode", mapCode);
        editor.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("com.example.mapcode", mapCode);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.choosemap) {
            // open choose map menu activity
            Intent intent = new Intent(this, MapChooseActivity.class);
            startActivityForResult(intent, 0);
            // startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.setlocation) {
            // open choose set location menu activity
            Intent requestIntent = new Intent(this, ChooseLocationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("com.example.cgallen.hellomap.latitude", latitude);
            bundle.putDouble("com.example.cgallen.hellomap.longitude", longitude);
            bundle.putInt("com.example.cgallen.hellomap.zoom", zoom);
            requestIntent.putExtras(bundle);

            startActivityForResult(requestIntent, 1);
            return true;
        } else if (item.getItemId() == R.id.setDefaults) {
            // start set defaults activity
            Intent requestIntent = new Intent(this, MyPrefsActivity.class);
            startActivityForResult(requestIntent, 2);
            return true;
        } else if (item.getItemId() == R.id.selectMap2) {
            Intent requestIntent = new Intent(this, ChooseMapListActivity.class);
            startActivityForResult(requestIntent, 0); // same request code as ChooseLocationActivity
        } else if (item.getItemId() == R.id.listActivity1) {
            Intent requestIntent = new Intent(this, ExampleListActivity1.class);
            startActivityForResult(requestIntent, 3);
        } else if (item.getItemId() == R.id.listActivity2) {
            Intent requestIntent = new Intent(this, ExampleListActivity2.class);
            startActivityForResult(requestIntent, 3);
        } else if (item.getItemId() == R.id.mapOverlayActivity){
            Intent requestIntent = new Intent(this, ExampleMapOverlayActivity.class);
            startActivityForResult(requestIntent, 4);
        }   else if (item.getItemId() == R.id.mapOverlayAppActivity) {
            Intent requestIntent = new Intent(this, MapOverlayAppActivity.class);
            startActivityForResult(requestIntent, 4);
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            // result from choose map
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                mapCode = extras.getString("com.example.mapcode");
                if (Constants.CYCLE_MAP.equals(mapCode)) {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                } else {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }

        } else if (requestCode == 1) {
            // result from choose location activity
            if (resultCode == RESULT_OK) {
                Bundle extras = intent.getExtras();
                latitude = extras.getDouble("com.example.cgallen.hellomap.latitude");
                longitude = extras.getDouble("com.example.cgallen.hellomap.longitude");
                zoom = extras.getInt("com.example.cgallen.hellomap.zoom");
                centerMap();
            }
        } else if (requestCode == 2) {
            // result from set preferences activity
            // test results and relaunch if incorrect
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Double lat;
            Double lon;
            Integer zom;
            try {
                lat = Double.parseDouble(prefs.getString("lat", Constants.DEFAULT_LAT.toString()));
                lon = Double.parseDouble(prefs.getString("lon", Constants.DEFAULT_LON.toString()));
                zom = Integer.parseInt(prefs.getString("zoom", Constants.DEFAULT_ZOOM.toString()));

                // validate preferenc values
                if (lat < -180 || lat > 180) throw new RuntimeException("invalid latitude:" + lat);
                if (lon < -90 || lon > 90) throw new RuntimeException("invalid longitude:" + lon);
                if (zom < 1) throw new RuntimeException("invalid zoom:" + zom);
                latitude = lat;
                longitude = lon;
                zoom = zom;
            } catch (Exception ex) {
                // start set defaults activity
                Intent requestIntent = new Intent(this, MyPrefsActivity.class);
                startActivityForResult(requestIntent, 2);
                // the toast will display message when the new actitiy is launched
                Toast.makeText(getApplicationContext(),
                        "invalid default preference entry click to renter preference: " + ex.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    private void centerMap() {

        TextView tvlat = (TextView) findViewById(R.id.tvlat);
        tvlat.setText(latitude.toString());
        TextView tvlon = (TextView) findViewById(R.id.tvlon);
        tvlon.setText(longitude.toString());

        TextView tvmap = (TextView) findViewById(R.id.tvmap);

        mv = (MapView) findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(zoom);
        mv.getController().setCenter(new GeoPoint(latitude, longitude));
        if (Constants.CYCLE_MAP.equals(mapCode)) {
            mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
            tvmap.setText("Cycle Map");
        } else {
            mv.setTileSource(TileSourceFactory.MAPNIK);
            tvmap.setText("Regular View");
        }
    }

    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }

}