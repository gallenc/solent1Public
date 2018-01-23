package com.example.nickw.helloworld2;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import java.util.Date;
import java.util.Calendar;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.btn1);
        b.setOnClickListener(this);
    }

    public void onClick(View view) {
        TextView tv = (TextView) findViewById(R.id.tv1);
        EditText et = (EditText) findViewById(R.id.et1);

        String input = et.getText().toString();
        // error checking code exercise 1

        if (input == null || "".equals(input)) {
            tv.setText("Error age not entered");
        } else {
            try {

                Integer yearOfBirth = Integer.parseInt(et.getText().toString());

                Date date = new Date();

                // error checking code exercise 1
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int currentYear = cal.get(Calendar.YEAR);
                int age = currentYear - yearOfBirth;
                if (yearOfBirth < 0) {
                    // tv.setText("Error: Birth year : " + String.valueOf(yearOfBirth) + " cannot be negative");
                    // using internationalisation
                    String msg1 = getString(R.string.negativeyear); //<string name="negativeyear">Error: Birth year %s cannot be negative</string>
                    msg1 = String.format(msg1, String.valueOf(yearOfBirth));
                    tv.setText(msg1);
                } else if (age < 0) {
                    //tv.setText("Error: Birth year : " + String.valueOf(yearOfBirth)+ " cannot be greater than " + String.valueOf(currentYear));
                    // using internationalisation
                    String msg2 = getString(R.string.greateryear); //<string name="negativeyear">Error: Birth year %s cannot be negative</string>
                    msg2 = String.format(msg2, String.valueOf(yearOfBirth), String.valueOf(currentYear));
                    tv.setText(msg2);
                } else {
                    // tv.setText("Your age is: " + String.valueOf(age));
                    // using internationalisation
                    String msg3 = getString(R.string.ageis);
                    msg3 = msg3.format(msg3, String.valueOf(age));
                    tv.setText(msg3);
                }
            } catch (Exception ex) {
                tv.setText("Error:"+ex.getMessage());
            }
        }
    }
}
