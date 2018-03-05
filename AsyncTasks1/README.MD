
## Mobile Application Development - Part 9 AsyncTask and Network Communication

http://www.free-map.org.uk/course//mad/part9.php 

## exercise 1
On freemap is a web service which delivers music data. Its address is http://www.free-map.org.uk/course/mad/ws/hits.php and expects one query string parameter, artist. For example, you can obtain all Oasis songs with:

http://www.free-map.org.uk/course/mad/ws/hits.php?artist=Oasis

Write an Android app containing an EditText, a "Download" button and a TextView below which occupies the rest of the Activity. 
When the user clicks the "Download" button, it should read in an artist from the EditText, connects to the web service and display all the matching songs in the TextView. 
Pass the text in the text view into the AsyncTask as a parameter.

## exercise 2 
At

http://www.free-map.org.uk/course/mad/ws/addhit.php

is a PHP script to add a new song to the database. It takes the following POST fields:

    song - the song title;
    artist - the artist;
    year - the year of the song.

Write a new app, or add a second Activity to the app above, to allow the user to enter the song, artist and year in EditTexts. The data should be sent as a POST request to the above script. Pass the song, artist and year to the AsyncTask as parameters. Test it worked by visiting

http://www.free-map.org.uk/course/mad/ws/hits.php

and see if the new song was added!
