## Mobile Application Development - Part 4 Menus and Multiple Activities

## Exercises
Comment out the text fields which allow the user to set the latitude and longitude from the main activity that you did in Topic 3.

You are now going to do it from a separate activity, as described below.
Add a third activity to allow the user to enter a latitude and longitude and set the map to this location.  

To do this:    

Add a further menu option to your app from above labelled    "Set Location".
Create a third activity to allow the user to enter a latitude and    longitude. 

Give it an XML layout file with two EditTexts, one for latitude    and one for longitude, and a button.    
When the button is clicked, the latitude and longitude should be read     
from the EditTexts and sent back to the main activity in a Bundle in    an Intent.

In your onOptionsItemSelected() method, launch the     third activity, with a request code of 1, if the "Set Location" menu    
option is selected.In your onActivityResult() method, set the latitude and    
longitude of the map to the contents of the Bundle from the Intent if the   
request code is 1.
