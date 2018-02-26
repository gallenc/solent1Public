package com.example.gallenc.edittext1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 26/02/2018.
 */

public class AddressEntryActivity extends AppCompatActivity implements OnClickListener {

    EditText fnameEditText = null;
    EditText snameEditText = null;
    EditText phoneEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        fnameEditText = (EditText) findViewById(R.id.etfname);
        fnameEditText.setText(bundle.getString("firstname", ""));

        snameEditText = (EditText) findViewById(R.id.etsname);
        snameEditText.setText(bundle.getString("secondname", ""));

        phoneEditText = (EditText) findViewById(R.id.etphone);
        phoneEditText.setText(bundle.getString("phone", ""));

        Button addNewContact = (Button) findViewById(R.id.btn_add_or_edit_contact);
        addNewContact.setOnClickListener(this);
        Button deleteContact = (Button) findViewById(R.id.btndeletecontact);
        deleteContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        final Bundle extras = new Bundle();
        extras.putString("firstname", fnameEditText.getText().toString());
        extras.putString("secondname", snameEditText.getText().toString());
        extras.putString("phone", phoneEditText.getText().toString());


        // default do not delete
        extras.putBoolean("deletecontact", false);

        if (v.getId() == R.id.btndeletecontact) {
            // check if really want to delete
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder
//                    .setTitle("Delete Contact")
//                    .setMessage("do you really want to delete this contact?")
//                    .setPositiveButton("Delete Contact",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    extras.putBoolean("deletecontact", true);
//                                    dialog.cancel();
//                                }
//                            })
//                    .setNegativeButton("No Change",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog,
//                                                    int id) {
//                                    extras.putBoolean("deletecontact", false);
//                                    dialog.cancel();
//                                }
//                            });
//
//            AlertDialog alertDialog = alertDialogBuilder.create();
//            alertDialog.show();
            extras.putBoolean("deletecontact", true);

        } else if (v.getId() == R.id.btn_add_or_edit_contact) {
            extras.putBoolean("deletecontact", false);
        }

        intent.putExtras(extras);
        setResult(RESULT_OK, intent);
        finish();
    }

}