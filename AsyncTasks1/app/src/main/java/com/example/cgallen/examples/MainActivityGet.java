package com.example.cgallen.examples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;

import com.example.cgallen.asynctasks1.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivityGet extends AppCompatActivity implements View.OnClickListener {

    class MyTask extends AsyncTask<Void, Void, String> {

        @Override
        public String doInBackground(Void... unused) {
            HttpURLConnection conn = null;
            try {
                URL url = new URL("http://server.com/people.php");
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String result = "", line;
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                    return result;
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

        @Override
        public void onPostExecute(String result) {
            EditText et1 = (EditText) findViewById(R.id.artist);
            et1.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button go = (Button) findViewById(R.id.btnGetSongs);
        go.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MyTask t = new MyTask();
        t.execute();
    }
}