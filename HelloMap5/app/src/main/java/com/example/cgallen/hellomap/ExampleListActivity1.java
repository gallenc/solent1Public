package com.example.cgallen.hellomap;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;

public class ExampleListActivity1 extends ListActivity
{
    String[] data;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new String[] { "Firefox", "Chrome", "Internet Explorer" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        // handle list item selection
    }
}

