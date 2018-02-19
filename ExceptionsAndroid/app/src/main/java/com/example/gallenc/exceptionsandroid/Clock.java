package com.example.gallenc.exceptionsandroid;

/**
 * Created by admin on 19/02/2018.
 * three attributes: hours, minutes and seconds;
 * a no-parameters constructor, which sets all of them to 0;
 * a toString() method, which displays the time in a standard format e.g. hour:minute:second;
 * and a method, setTime() which takes three parameters (hoursIn, minutesIn and secondsIn)
 * and sets the attributes accordingly.
 *
 */

public class Clock {

    private Integer hours=null;
    private Integer minutes=null;
    private Integer seconds=null;

    public Clock(){
        super();
    }


    public void setTime(int hoursIn, int minutesIn, int secondsIn) throws TimeException{
        if (hoursIn>24 || hoursIn<0) throw new  TimeException("out of bounds hoursIn: "+hoursIn);
        if (minutesIn>60 || minutesIn<0) throw new  TimeException("out of bounds minutesIn: "+minutesIn);
        if (secondsIn>60 || secondsIn<0) throw new  TimeException("out of bounds minutesIn: "+secondsIn);
        hours = hoursIn;
        minutes=minutesIn;
        seconds=secondsIn;
    }

    public String toString(){
        String msg="";
        msg = msg + ((hours==null) ? "null" :hours.toString())+":";
        msg = msg + ((minutes==null) ? "null" :minutes.toString())+":";
        msg = msg + ((seconds==null) ? "null" :seconds.toString());
        return msg;
    }

}
