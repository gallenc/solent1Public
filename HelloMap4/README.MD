## Mobile Application Development - Part 5 Persistence I - Preferences

http://www.free-map.org.uk/course//mad/part5.php

## My implimentation of Preferences Exercise


The idea of this exercise is to develop a version of your mapping app which uses preferences, rather than a custom activity. Add an extra menu item to launch the preferences, and add a PreferenceActivity which includes the following:

Current Latitude and Longitude of the map (EditTextPreference; incorporate the code provided above)

Default zoom level for the map (EditTextPreference)

Add code in the onStart() to get these working. Once this is working, add the following further preference:

Map style - Regular or hike/bike map(use a ListPreference)

Make sure the preferences are read when the main activity is started, so that the app immediately changes when the PreferenceActivity is dismissed. Also save the selected mapping provider when the app is destroyed, so that when the user starts the app again, the same mapping provider will be used as last time. 
