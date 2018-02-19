package com.example.gallenc.exceptionsandroid;

/**
 * Created by admin on 19/02/2018. Create another new class - TimeException -
 * to represent errors with the Clock class. The TimeException need only have two methods,
 * a constructor, which takes a message as a parameter, and a toString() method.
 */

public class TimeException extends Exception {

    public TimeException(String message){
        super(message);
    }

}
