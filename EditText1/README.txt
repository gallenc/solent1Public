
# Part 8 File IO
http://www.free-map.org.uk/course//mad/part8.php

Exercise

NOTE  in gradel file setting target greater then 22 will stop file permissions from working
        targetSdkVersion 22

Write a simple text-editor/notepad app with Android. 

The main activity should consist of a single EditText occupying the whole of the screen. 
The user should be able to enter some text in the EditText. 
Note that you can make the text inside the EditText align to the top 
by adding the property android:gravity="top" to the <EditText> tag 
in the layout XML file. 

There should be two menu options labelled "Save" and "Load". 
When the user selects "Save", it should save the contents 
of the EditText to a text file "textedit.txt". 
The text file should be saved in the directory returned by

Environment.getExternalStorageDirectory().getAbsolutePath()

If the text file preference has not been set, use the filename "textedit.txt". 
When the user selects "Load", the EditText should be filled with the contents of "textedit.txt".

Handle exceptions with the use of AlertDialog.Builder() as shown above.
Test that it has worked by navigating the file system of your emulator. 
To view the file, use the DDMS (Tools-Android-Android Device Monitor). 
Your file will be saved in /storage/sdcard. Download it from the virtual 
device to your computer using the icon highlighted in the diagram below.

Change your app so that the user can specify the filename in the preferences.
