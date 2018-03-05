package com.example.cgallen.asynctasks1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.cgallen.asynctasks1.R;
import com.example.cgallen.examples.TestTask;
import com.example.cgallen.examples.TestTask2;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    // Example onClick() which would be linked to a button press
    public void onClick(View view) {
        TestTask task = new TestTask(this);
        task.execute();

        TestTask2 task2 = new TestTask2(this);
        task2.execute("http://www.free-map.org.uk/index.php");
    }


}


