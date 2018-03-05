package com.example.cgallen.asynctasks1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String BASE_URL = "http://www.free-map.org.uk/course/mad/ws/hits.php";
    private static final String ARTIST_QUERY="artist=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button fetchSongs = (Button) findViewById(R.id.btnGetSongs);
        fetchSongs.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.async_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.addArtist) {
            // react to the menu item being selected...
            Intent intent = new Intent(this,PostArtistActivity.class);
            startActivityForResult(intent,0);
            // startActivity(intent);
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View view) {
        EditText artist = (EditText) findViewById(R.id.artist);
        MainActivity.GetSongsTask t = new MainActivity.GetSongsTask();
        t.execute(artist.getText().toString());
    }

    class GetSongsTask extends AsyncTask<String, Void, String> {

        @Override
        public String doInBackground(String... input) {
            String artist = input[0];
            String queryUrl = BASE_URL+"?"+ARTIST_QUERY+artist;
            HttpURLConnection conn = null;
            try {
                URL url = new URL(queryUrl);
                conn = (HttpURLConnection) url.openConnection();
                InputStream in = conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuffer result = new StringBuffer();
                    String line;
                    while ((line = br.readLine()) != null) {
                        result.append(line).append("\n");
                    }
                    return result.toString();
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
            TextView songList = (TextView) findViewById(R.id.songList);
            songList.setText(result);
        }
    }

}


