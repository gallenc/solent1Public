package com.example.cgallen.hellomap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import android.app.Activity;

public class ChooseLocationActivity extends Activity implements OnClickListener {

    private Double latitude = Constants.DEFAULT_LON;
    private Double longitude = Constants.DEFAULT_LAT;
    private Integer zoom = Constants.DEFAULT_ZOOM;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_choose_location);

            Bundle extras = this.getIntent().getExtras();
            latitude =extras.getDouble("com.example.cgallen.hellomap.latitude");
            longitude =extras.getDouble("com.example.cgallen.hellomap.longitude");
            zoom = extras.getInt("com.example.cgallen.hellomap.zoom");

            // set default values lat lon
            EditText lonEditText = (EditText) findViewById(R.id.longitude);
            lonEditText.setText(longitude.toString());
            EditText latEditText = (EditText) findViewById(R.id.latitude);
            latEditText.setText(latitude.toString());

            Button c = (Button)findViewById(R.id.btn1);
            c.setOnClickListener(this);
            Button d = (Button)findViewById(R.id.btn2);
            d.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {

            // create bundle and intent to store result of inputing values in this layout
            Intent intent = new Intent();
            Bundle bundle=new Bundle();

            //Double latitude = Constants.DEFAULT_LAT;
            //Double longitude = Constants.DEFAULT_LON;
           // Integer zoom = Constants.DEFAULT_ZOOM;

            EditText lonEditText = (EditText) findViewById(R.id.longitude);
            EditText latEditText = (EditText) findViewById(R.id.latitude);

            switch (view.getId()) {
                case R.id.btn1: // ok - just continue
                    break;
                case R.id.btn2: // reset default

                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String defaultLat =  prefs.getString("lat", Constants.DEFAULT_LAT.toString());
                    String defaultLon = prefs.getString("lon", Constants.DEFAULT_LON.toString());

                    lonEditText.setText(defaultLon);
                    latEditText.setText(defaultLat);

                    String defaultZoom = prefs.getString("zoom", Constants.DEFAULT_ZOOM.toString());
                    zoom = Integer.parseInt(defaultZoom);
                    break;
                default:
                    break;
            }

            // load and check values
            Double  lon = parseLat(lonEditText);
            Double  lat = parseLat(latEditText);
            if(lon!=null && lat!=null){
                // return result if successful
                latitude = lat;
                longitude = lon;
                bundle.putDouble("com.example.cgallen.hellomap.latitude",latitude);
                bundle.putDouble("com.example.cgallen.hellomap.longitude",longitude);
                bundle.putInt("com.example.cgallen.hellomap.zoom",zoom);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish(); // closes the Activity
            }
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
            String message="invalid latitude: "+input;
            popupMessage(message);
            return null;
        }
    }

    //  long +180 to -180
    private Double  parseLong(EditText geoEditText){
        String input = geoEditText.getText().toString();
        try{
            Double  longitude=Double.parseDouble(input);
            if(longitude>180 || longitude <-180) {
                geoEditText.setText("");
                geoEditText.setHint("invalid longitude: "+input);
                String message="invalid logitude: "+input;
                popupMessage(message);
                return null;
            }
            return longitude;
        } catch (Exception e){
            geoEditText.setText("");
            geoEditText.setHint("invalid longitude: "+input);
            String message="invalid longitude: "+input;
            popupMessage(message);
            return null;
        }
    }

    private void popupMessage(String message){
        new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage(message).show();
    }
    }

