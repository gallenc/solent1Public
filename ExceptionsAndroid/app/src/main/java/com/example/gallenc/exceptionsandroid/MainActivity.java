package com.example.gallenc.exceptionsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    Clock clock = new Clock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.btn1);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        EditText ethrs = (EditText)findViewById(R.id.ethrs);
        EditText etmins = (EditText)findViewById(R.id.etmins);
        EditText etsecs = (EditText)findViewById(R.id.etsecs);
        Integer mins=null;
        Integer hrs=null;
        Integer secs=null;

        try {
            hrs = Integer.parseInt(ethrs.getText().toString());
        } catch (Exception e){
            popupAlert("cannot parse hours string to integer:"+e.getMessage());
        }
        try {
            mins = Integer.parseInt(etmins.getText().toString());
        } catch (Exception e){
            popupAlert("cannot parse minutes string to integer:"+e.getMessage());
        }
        try {
            secs = Integer.parseInt(etsecs.getText().toString());
        } catch (Exception e){
            popupAlert("cannot parse seconds string to integer:"+e.getMessage());
        }

        if (hrs!=null && mins !=null && secs!=null) try {
            clock.setTime(hrs,mins,secs);
            TextView timeResult = (TextView)findViewById(R.id.timeResult);
            String timeres = getString(R.string.timeResult);
            timeResult.setText(timeres+ clock.toString());
        } catch (TimeException ex) {
            popupAlert("problem saving time: " + ex.getMessage());
        }

    }

    private void popupAlert(String message){
        new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage(message).show();
    }
}
