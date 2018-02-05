package com.example.cgallen.hellomap;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MapChooseActivity extends Activity implements OnClickListener {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mca);
        Button regular = (Button)findViewById(R.id.btnRegular);
        regular.setOnClickListener(this);
        Button cyclemap = (Button)findViewById(R.id.btnCyclemap);
        cyclemap.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        boolean cyclemap=false;
        if (v.getId()==R.id.btnCyclemap) {
            cyclemap=true;
        }
        bundle.putBoolean("com.example.cyclemap",cyclemap);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }


}