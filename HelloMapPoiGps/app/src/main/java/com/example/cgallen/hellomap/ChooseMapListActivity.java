package com.example.cgallen.hellomap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseMapListActivity extends ListActivity {
    String[] names, details;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        names = new String[]{"Regular View", "Cycle Map"};
        details = new String[]{"Simple default map", "Map with cycle paths"};
        ListMapAdapter adapter = new ListMapAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id) {
        // handle list item selection
        Intent intent = new Intent();
        Bundle bundle=new Bundle();

        String[] mapcode = {Constants.DEFAULT_MAP, Constants.CYCLE_MAP };

        bundle.putString("com.example.mapcode",mapcode[index]);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    public class ListMapAdapter extends ArrayAdapter<String> {
        public ListMapAdapter() {
            // We have to use ExampleListActivity1.this to refer to the outer class (the activity)
            super(ChooseMapListActivity.this, android.R.layout.simple_list_item_1, names);
        }

        // enhanced getview
        public View getView(int index, View convertView, ViewGroup parent){
            View view = convertView;
            if(view==null) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.listmapentry, parent, false);
            }
            TextView title = (TextView)view.findViewById(R.id.map_name), detail =
                    (TextView)view.findViewById(R.id.map_desc);
            title.setText(names[index]);
            detail.setText(details[index]);
            return view;
        }
    }
}