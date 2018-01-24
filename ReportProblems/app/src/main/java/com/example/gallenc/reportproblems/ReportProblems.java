package com.example.gallenc.reportproblems;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import java.util.concurrent.atomic.AtomicInteger;


public class ReportProblems extends AppCompatActivity implements OnClickListener {
    /** Called when the activity is first created. */

    private static AtomicInteger numberClicks = new AtomicInteger(0);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.btn1);
        Button c = (Button)findViewById(R.id.btn2);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
    }

    // see https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android
    public void onClick(View view) {
        EditText textPanel = (EditText)findViewById(R.id.et1);

        switch (view.getId()) {

            case R.id.btn1: // ok
                String input = textPanel.getText().toString();
                if (! "".equals(input)) {
                    // just clicking OK with no input ignored
                   int i = numberClicks.addAndGet(1);
                   String msg1 = getString(R.string.acknowlege);
                   msg1 = String.format(msg1, Integer.toString(i)); // Thank you for reporting your problem. Problem id:%s
                   textPanel.setText("");
                   textPanel.setHint(msg1);
                }
                break;

            case R.id.btn2: // clear
                String msg2 = getString(R.string.initialMessage);
                textPanel.setText("");
                textPanel.setHint(msg2);
                break;

            default:
                break;
        }
    }
}
