package com.example.cgallen.examples;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;

import android.app.AlertDialog;

import com.example.cgallen.asynctasks1.R;

public class MainActivityPost extends AppCompatActivity implements View.OnClickListener {

    class MyTask extends AsyncTask<Void, Void, String> {
        public String doInBackground(Void... unused) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://server.com/add_person.php");
                conn = (HttpURLConnection) url.openConnection();

                String postData = "name=Fred&dob=140462";
                // For POST
                conn.setDoOutput(true);
                conn.setFixedLengthStreamingMode(postData.length());

                OutputStream out = null;
                out = conn.getOutputStream();
                out.write(postData.getBytes());
                if (conn.getResponseCode() == 200) {
                    InputStream in = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String all = "", line;
                    while ((line = br.readLine()) != null)
                        all += line;
                    return all;
                } else {
                    return "HTTP ERROR: " + conn.getResponseCode();
                }
            } catch (IOException e) {
                return e.toString();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }

        public void onPostExecute(String result) {

            new AlertDialog.Builder(MainActivityPost.this).
                    setMessage("Server sent back: " + result).
                    setPositiveButton("OK", null).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button go = (Button) findViewById(R.id.btnGetSongs);
        go.setOnClickListener(this);
    }

    public void onClick(View v) {
        MyTask t = new MyTask();
        t.execute();
    }
}

