package com.example.cgallen.examples;

import android.os.AsyncTask;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.app.AlertDialog;
import android.os.Environment;

public class TestTask extends AsyncTask<Void, Void, String> {

    Context parent;

    public TestTask(Context p) {

        parent = p;
    }


    public String doInBackground(Void... unused) {
        String message = "Successfully downloaded!";
        try {
            throw new IOException("test");// Network communication would go here;
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