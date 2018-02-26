package com.example.gallenc.edittext1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MainActivity extends AppCompatActivity {

    public static final String DEFAULT_FILENAME = "textedit.txt";

    private String fileName = DEFAULT_FILENAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        fileName = prefs.getString("filename", DEFAULT_FILENAME);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_textedit, menu);
        return true;
    }

    public void tryEndApplication() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set the title of the Alert Dialog
        alertDialogBuilder.setTitle("Exit Application");

        // set dialog message
        alertDialogBuilder
                .setMessage("do you want to exit application now?")
                .setCancelable(false)
                .setPositiveButton("Exit Now",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // what to do if YES is tapped
                                finishAffinity();
                                System.exit(0);
                            }
                        })
                .setNeutralButton("CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // code to do on CANCEL tapped
                                dialog.cancel();
                            }
                        })

                .setNegativeButton("Do not Exit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // code to do on NO tapped
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        EditText textPanel = (EditText) findViewById(R.id.et1);

        if (item.getItemId() == R.id.loadmenutext) {
            String text = loadText();
            textPanel.setText(text);
            return true;
        } else if (item.getItemId() == R.id.savemenutext) {
            String text = textPanel.getText().toString();
            saveText(text);
            return true;
        } else if (item.getItemId() == R.id.preferences) {
            Intent requestIntent = new Intent(this,PreferencesActivity.class);
            startActivityForResult(requestIntent,0);
            return true;
        } else if (item.getItemId() == R.id.quit) {
            tryEndApplication();
            return true;
        }
        return false;
    }

    private void popupMessage(String message) {
        new AlertDialog.Builder(this).setPositiveButton("OK", null).setMessage(message).show();
    }

    private String loadText() {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
            if (!file.exists()) {
                popupMessage("file does not exist: " + file.getAbsolutePath());
            } else {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                stringBuffer = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            popupMessage(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException ex) {
            }
        }
        return stringBuffer.toString();
    }

    private void saveText(String text) {
        PrintWriter printWriter = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
            printWriter = new PrintWriter(new FileWriter(file));
            printWriter.print(text);
        } catch (IOException e) {
            popupMessage(e.getMessage());
        } finally {
            if (printWriter != null)
                printWriter.close(); // close the file to ensure data is flushed to file
        }
    }
}
