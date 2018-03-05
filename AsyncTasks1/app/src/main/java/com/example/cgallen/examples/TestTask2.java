package com.example.cgallen.examples;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.app.AlertDialog;
import android.os.Environment;

public class TestTask2 extends AsyncTask<String, Void, String> {

    Context parent;

    public TestTask2(Context p) {

        parent = p;
    }

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
        new AlertDialog.Builder(parent).setMessage(message).
                setPositiveButton("OK", null).show();
    }
}