package com.example.gallenc.miscexamples;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by admin on 01/03/2018.
 */

public class AddressBookDAO {

    SortedMap<String,AddressBookEntry> addressBook = new TreeMap<String,AddressBookEntry>();

    public void save() throws IOException {

        for(String key: addressBook.keySet()){
            AddressBookEntry abEntry  = addressBook.get(key);

        }

    }

    public void load() throws IOException {


    }

}
