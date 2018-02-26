package com.example.gallenc.edittext1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by admin on 26/02/2018.
 */

public class AddressBookDao {

    private SortedMap<String, AddressBookEntry> addressBookMap =  new TreeMap<String,AddressBookEntry>();

    public SortedMap<String, AddressBookEntry> getAddressBookMap() {
        return addressBookMap;
    }

    public void loadFile(File addressBook){
        ObjectInputStream objectinputstream = null;
        LinkedList<AddressBookEntry> readList= new LinkedList<AddressBookEntry>();
        try {
            if (addressBook.exists()){
                FileInputStream streamIn = new FileInputStream(addressBook);
                objectinputstream = new ObjectInputStream(streamIn);
                readList = (LinkedList<AddressBookEntry>) objectinputstream.readObject();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Cannot Load address book:"+addressBook.getName(),ex);
        } finally {
            if(objectinputstream != null) try {
                objectinputstream.close();
            } catch (Exception ex) {
            }
        }
        addressBookMap.clear();
        for (AddressBookEntry entry: readList){
            String key=entry.getKey();
            addressBookMap.put(key,entry);
        }
    }

    public void saveFile(File addressBook){
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream(addressBook, false);
            oos = new ObjectOutputStream(fout);
            LinkedList<AddressBookEntry> writeList = new LinkedList<AddressBookEntry>(addressBookMap.values());
            oos.writeObject(writeList);
        } catch (Exception ex) {
            throw new RuntimeException("Cannot Save Address Book:"+addressBook.getName(),ex);
        } finally {
            if(oos != null) try {
                oos.close();
            } catch (IOException ex){
            }
        }
    }
}
