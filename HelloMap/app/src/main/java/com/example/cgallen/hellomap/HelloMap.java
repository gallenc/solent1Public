package com.example.cgallen.hellomap;


        import android.support.v7.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;

        import org.osmdroid.config.Configuration;
        import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
        import org.osmdroid.util.GeoPoint;
        import org.osmdroid.views.MapView;

public class HelloMap extends AppCompatActivity
{

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_hello_map);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(18);
        // zoom was 16
        // mv.getController().setCenter(new GeoPoint(51.05,-0.72));
        // aLatitude: 50.9246, aLongitude:  -1.3705 burnett close
        // http://www.informationfreeway.org/
        mv.getController().setCenter(new GeoPoint(50.9246,-1.3719));
    }
}