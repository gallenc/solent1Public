package com.example.gallenc.feettometresactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;



public class FeetToMetresActivity extends AppCompatActivity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.btn1);
        b.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        TextView tv = (TextView)findViewById(R.id.tv1);
        EditText et = (EditText)findViewById(R.id.et1);
        Double feet=null;
        Double metres=null;
        String feetStr=et.getText().toString();
        try {
            feet = Double.parseDouble(feetStr);
            metres = feet*0.305;
            tv.setText("In metres that is: " + metres);
        } catch (NumberFormatException | NullPointerException ex ){
            new AlertDialog.Builder(this).setPositiveButton("OK",null).setMessage("cannot convert" +
                    feetStr+ " to a Double value ").show();
        }

    }
}
