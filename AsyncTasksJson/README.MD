
## Mobile Application Development - Part 10 JSON Parsing

http://www.free-map.org.uk/course//mad/part10.php 

## exercise 1

As we saw last time, on Freemap is a web service which delivers music data.
 This web service can deliver JSON as well as XML or plain text. 
Its address is http://www.free-map.org.uk/course/mad/ws/hits.php 
and expects two query string parameters, artist (the artist) and format 
(the format: JSON, XML or plain text). 

For example, you can obtain all Oasis songs as JSON with:
```
http://www.free-map.org.uk/course/mad/ws/hits.php?artist=Oasis&format=json
```
If you run the web service from your browser, it will show you the format of the JSON data which is sent back.

Load your app from last time (which requested the data in plain text format)
. Make a copy of your old code (commenting out the original copy of your old code) 
and nodify your code so that it requests the data as JSON instead, parses the JSON and shows the details of all the matching songs in the TextView as plain text (not JSON!)