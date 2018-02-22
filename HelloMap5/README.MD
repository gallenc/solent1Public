## week 4
Mobile Application Development - Part 6 Lists
http://www.free-map.org.uk/course//mad/part6.php

## Exercise

    With your map app from the previous topics, add a list activity to allow the user 
    to select the map style (regular or hike/bike map) as a sub-activity of the main activity. 
    This should be launched from a new menu item from your existing map app activity:
    keep your existing menu items and sub-activities from last time. Launch the list activity with a result code of
    0 - why? - and in onListItemClick(), 
    send either true or false back to the main activity using an Intent (the same as topic 4), 
    depending on whether the hike/bike map or regular list item was selected.
    Use a custom adapter to give each map style entry a short description in addition to the title.
