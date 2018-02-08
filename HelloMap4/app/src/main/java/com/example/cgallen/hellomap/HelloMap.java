package com.example.cgallen.hellomap;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import android.app.Activity;

//public class HelloMap extends AppCompatActivity implements OnClickListener {
public class HelloMap extends Activity {

    Double latitude = Constants.DEFAULT_LAT;
    Double longitude = Constants.DEFAULT_LON;
    Integer zoom = Constants.DEFAULT_ZOOM;

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_hello_map);

        centerMap();

    }

    private void centerMap(){
        mv = (MapView)findViewById(R.id.map1);
        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(zoom);
        mv.getController().setCenter(new GeoPoint(latitude, longitude));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.choosemap) {
            // react to the menu item being selected...
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivityForResult(intent,0);
            // startActivity(intent);
            return true;
        } else  if(item.getItemId() == R.id.setlocation) {
            // react to the menu item being selected...
            Intent requestIntent = new Intent(this,ChooseLocationActivity.class);
            Bundle bundle=new Bundle();
            bundle.putDouble("com.example.cgallen.hellomap.laitude",latitude);
            bundle.putDouble("com.example.cgallen.hellomap.longitude",longitude);
            bundle.putInt("com.example.cgallen.hellomap.zoom",zoom);
            requestIntent.putExtras(bundle);

            startActivityForResult(requestIntent,1);
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        if(requestCode==0){
            // result from choose map
            if (resultCode==RESULT_OK) {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true) {
                    mv.setTileSource(TileSourceFactory.HIKEBIKEMAP);
                }
                else {
                    mv.setTileSource(TileSourceFactory.MAPNIK);
                }
            }

        } else  if(requestCode==1){
            // result from  choose location activity
            if (resultCode==RESULT_OK) {
                Bundle extras=intent.getExtras();
                latitude =extras.getDouble("com.example.cgallen.hellomap.laitude");
                longitude =extras.getDouble("com.example.cgallen.hellomap.longitude");
                zoom = extras.getInt("com.example.cgallen.hellomap.zoom");
                centerMap();
            }
        }
    }

}