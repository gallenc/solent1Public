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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnItemClickListener {

    public static final String DEFAULT_FILENAME = "addressbook.ser";

    private String fileName = DEFAULT_FILENAME;

    private AddressBookDao addressBookDao = new AddressBookDao();
    private File addressBookFile=null;

    ListView mListView=null;
    String[] listItems= {};
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        fileName = prefs.getString("filename", DEFAULT_FILENAME);

        addressBookFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + fileName);
        try {
            addressBookDao.loadFile(addressBookFile);
        } catch (Exception ex){
            popupMessage("using empty address book: "+ex.getMessage());
        }

        Button addContact = (Button)findViewById(R.id.btnaddcontact);
        addContact.setOnClickListener(this);

        // list view
        mListView = (ListView) findViewById(R.id.nameslist);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        updateList();

    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id){
        AddressBookEntry[] addressentries = addressBookDao.getAddressBookMap().values().toArray(new AddressBookEntry[0]);
        AddressBookEntry addressBookEntry= addressentries[position];
        Intent intent = new Intent(this,AddressEntryActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("firstname",addressBookEntry.getFirstName());
        bundle.putString("secondname",addressBookEntry.getSecondName());
        bundle.putString("phone",addressBookEntry.getPhoneNo());
        intent.putExtras(bundle);
        startActivityForResult(intent,0);
    }

    public void updateList(){
        ArrayList<String> menuItems= new ArrayList<String>();
        for (AddressBookEntry addressEntry : addressBookDao.getAddressBookMap().values()){
            String menuItem= addressEntry.getSecondName()+" "+addressEntry.getFirstName();
            menuItems.add(menuItem);
            System.out.println("debugItem:"+menuItem);
        };
        listItems =  menuItems.toArray(new String[0]);

        //notify that the model changed
       //addressentries =  adapter.clear();
       // adapter.addAll(listItems);
        //adapter.notifyDataSetChanged();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_addresbook, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.btnaddcontact){
            Intent intent = new Intent(this,AddressEntryActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("firstname","");
            bundle.putString("secondname","");
            bundle.putString("phone","");
            intent.putExtras(bundle);
            startActivityForResult(intent,0);
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        if(requestCode==0){

            if (resultCode==RESULT_OK) {
                Bundle extras=intent.getExtras();
                AddressBookEntry abEntry = new AddressBookEntry();
                abEntry.setFirstName(extras.getString("firstname",""));
                abEntry.setSecondName(extras.getString("secondname",""));
                abEntry.setPhoneNo(extras.getString("phone",""));
                boolean delete = extras.getBoolean("deletecontact", false);

                if(delete){
                    // delete
                    addressBookDao.getAddressBookMap().remove(abEntry.getKey());

                } else {
                    // add or update
                    addressBookDao.getAddressBookMap().put(abEntry.getKey(),abEntry);
                }
                updateList();
            }
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.loadaddresfile) {
            if (addressBookFile!=null){
                try {
                    addressBookDao.loadFile(addressBookFile);
                } catch (Exception ex){
                    popupMessage("problem loading address file:"+ex.getMessage());
                }
            }
            updateList();
            return true;
        } else if (item.getItemId() == R.id.saveaddressfile) {
            if (addressBookFile!=null){
                try {
                addressBookDao.saveFile(addressBookFile);
                } catch (Exception ex){
                    popupMessage("problem saving address file:"+ex.getMessage());
                }
            }
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


}
