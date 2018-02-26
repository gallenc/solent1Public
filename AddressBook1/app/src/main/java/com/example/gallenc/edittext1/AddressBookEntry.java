package com.example.gallenc.edittext1;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by admin on 26/02/2018.
 */

public class AddressBookEntry implements Serializable {

    private String firstName = null;
    private String secondName = null;
    private String phoneNo = null;

    public String getKey() {
        String name1 = (getSecondName() == null) ? "" : getSecondName().toUpperCase();
        String name2 = (getFirstName() == null) ? "" : getFirstName().toUpperCase();
        String key = name1 + name2;
        return key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
