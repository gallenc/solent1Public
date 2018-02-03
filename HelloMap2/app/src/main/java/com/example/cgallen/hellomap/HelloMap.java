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

public class HelloMap extends AppCompatActivity implements OnClickListener {

    public static final Double DEFAULT_LAT = 50.9246;
    public static final Double DEFAULT_LON = -1.3719;

    MapView mv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // This line sets the user agent, a requirement to download OSM maps
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_hello_map);

        // set default values lat lon
        EditText lonEditText = (EditText) findViewById(R.id.longitude);
        lonEditText.setText(DEFAULT_LON.toString());
        EditText latEditText = (EditText) findViewById(R.id.latitude);
        latEditText.setText(DEFAULT_LAT.toString());

        Button c = (Button)findViewById(R.id.btn1);
        c.setOnClickListener(this);

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true);
        mv.getController().setZoom(18);
        // zoom was 16
        // mv.getController().setCenter(new GeoPoint(51.05,-0.72));
        // aLatitude: 50.9246, aLongitude:  -1.3705 burnett close
        // http://www.informationfreeway.org/
        mv.getController().setCenter(new GeoPoint(DEFAULT_LAT, DEFAULT_LON));
    }

    // lat +90 to -90
    private Double parseLat(EditText geoEditText){
        String input = geoEditText.getText().toString();
        try{
            Double latitude=Double.parseDouble(input);
            if(latitude>90 || latitude <-90) {
                geoEditText.setText("");
                geoEditText.setHint("invalid latitude: "+input);
                String message="invalid latitude";
                popupMessage(message);
                return null;
            }
            return latitude;
        } catch (Exception e){
            geoEditText.setText("");
            geoEditText.setHint("invalid latitude: "+input);
            String message="invalid latitude";
            popupMessage(message);
            return null;
        }
    }

    private void popupMessage(String message){
        new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage(message).show();
    }

    //  long +180 to -180
    private Double  parseLong(EditText geoEditText){
        String input = geoEditText.getText().toString();
        try{
            Double  longitude=Double.parseDouble(input);
            if(longitude>180 || longitude <-180) {
                geoEditText.setText("");
                geoEditText.setHint("invalid longitude: "+input);
                String message="invalid logitude";
                popupMessage(message);
                return null;
            }
            return longitude;
        } catch (Exception e){
            geoEditText.setText("");
            geoEditText.setHint("invalid longitude: "+input);
            String message="invalid longitude";
            popupMessage(message);
            return null;
        }
    }

    @Override
    public void onClick(View view) {

        // load and check values
        EditText lonEditText = (EditText) findViewById(R.id.longitude);
        Double  lon = parseLat(lonEditText);
        EditText latEditText = (EditText) findViewById(R.id.latitude);
        Double  lat = parseLat(latEditText);
        if(lon!=null && lat!=null){
            mv.getController().setCenter(new GeoPoint( lat, lon));
        }

    }
}