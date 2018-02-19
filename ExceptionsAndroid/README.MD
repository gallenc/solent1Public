## Exceptions exercise
http://www.free-map.org.uk/course//mad/part7.php


 Create a new Android project. Add a new class called Clock
 (representing time) to this project. The class should contain:

    three attributes: hours, minutes and seconds;

    a no-parameters constructor, which sets all of them to 0;

    a toString() method, which displays the time in a standard format e.g. hour:minute:second;
    
    and a method, setTime() which takes three parameters (hoursIn, minutesIn and secondsIn) and sets the attributes accordingly.

Create another new class - TimeException - to represent errors with the 
Clock class. The TimeException need only have two methods, 
a constructor, which takes a message as a parameter, 
and a toString() method.
In setTime(), throw a TimeException if the hours, 
minutes or seconds are not valid. Pass an appropriate message 
to the TimeException when creating it.

Test your system via a simple Android Activity. 
This should allow the user to enter hours, minutes and seconds
 via three EditTexts. When the user clicks a "Go!" button,
 the formatted time (returned from toString()) should be 
displayed in a TextView below the EditTexts and the button, 
by creating a Clock and calling its toString() method. 
Catch the TimeException thrown, by displaying an appropriate 
error message via an Alert Dialog (see above).