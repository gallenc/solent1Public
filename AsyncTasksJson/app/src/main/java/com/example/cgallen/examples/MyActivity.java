package com.example.cgallen.examples;

import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;

import java.io.IOException;

import android.app.AlertDialog;
import android.os.Environment;
import android.view.View;

public class MyActivity extends AppCompatActivity {
    class InnerTestTask extends AsyncTask<String, Void, String> {
        public String doInBackground(String... urls) {
            String message = "Successfully downloaded!";
            try {
                String urlToDownloadFrom = urls[0];
                // Download from that URL...
                throw new IOException("test");
            } catch (IOException e) {
                message = e.toString();
            }
            return message;
        }

        public void onPostExecute(String message) {
            new AlertDialog.Builder(MyActivity.this).setMessage(message).
                    setPositiveButton("OK", null).show();
        }
    }

    // rest of activity code, omitted

    // Example onClick() which would be linked to a button press
    public void onClick(View view) {
        InnerTestTask task = new InnerTestTask();

        // this could also be read in from the UI
        String filename = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/async2.txt";
        task.execute(filename);
    }
}