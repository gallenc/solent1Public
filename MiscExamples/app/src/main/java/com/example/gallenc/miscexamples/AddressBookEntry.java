package com.example.gallenc.miscexamples;

/**
 * Created by admin on 01/03/2018.
 */

public class AddressBookEntry {


     private String address=null;
    private String firstName=null;
    private String secondName=null;
    private String phone=null;

    public String getAddress() {
        return address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public  String toCsv(){
        String s = ((firstName==null) ?"" : firstName)+"," +
                ((secondName==null) ?"" : secondName)+"," +
                ((address==null) ?"" : address)+"," +
                ((phone==null) ?"" : phone);
        return s;
    }

    public void fromCsv(String csvString){
        String[] components = csvString.split(",");
        if (components.length !=4) throw new IllegalArgumentException("invalid string cannot " +
                "be split into firstName,secondName,address,Phone");
        firstName = components[0].trim();
        secondName= components[1].trim();
        address=components[2].trim();
        phone=components[4].trim();
    }

    @Override
    public String toString() {
        return "AddressBookEntry{" +
                "address='" + address + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
