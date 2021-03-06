﻿




# solent1Public
Miscellaneous solent work in support of Mobile Application Design
http://www.free-map.org.uk/course

username dfti2018 (password seperate)

## contents
* Introduction (Session 1) 
* Layouts and Event Handling (Session 2) https://github.com/gallenc/solent1Public/tree/master/HelloWorld2
** https://github.com/gallenc/solent1Public/tree/master/FeetToMetresActivity
** https://github.com/gallenc/solent1Public/tree/master/ReportProblems
* Mapping (Sessions 3 and 4) https://github.com/gallenc/solent1Public/tree/master/HelloMap2
* Menus and Multiple Activities (Sessions 5 and 6) https://github.com/gallenc/solent1Public/tree/master/HelloMap3
* Preferences (Sessions 7 and 8) https://github.com/gallenc/solent1Public/tree/master/HelloMap4
* Lists (Sessions 9 and 10) https://github.com/gallenc/solent1Public/tree/master/HelloMap5
* Exceptions (Session 11) https://github.com/gallenc/solent1Public/tree/master/ExceptionsAndroid
** https://github.com/gallenc/solent1Public/blob/master/ExceptionsExercises Eclipse
* File I/O (Session 12) https://github.com/gallenc/solent1Public/tree/master/MiscExamples - android studio unit tests
** https://github.com/gallenc/solent1Public/tree/master/EditText1
** https://github.com/gallenc/solent1Public/tree/master/AddressBook1
* AsyncTask and Network Communication (Sessions 13 and 14)
** https://github.com/gallenc/solent1Public/tree/master/AsyncTasks1
* JSON parsing (Sessions 15 and 16) 
** https://github.com/gallenc/solent1Public/tree/master/AsyncTasksJson
* Further Mapping: Markers (Session 17; Session 18 is catchup)
** https://github.com/gallenc/solent1Public/tree/master/HelloMapPoi
* Location and GPS (Session 19; Session 20 is catchup)
** https://github.com/gallenc/solent1Public/tree/master/HelloMapPoi

* Intro to Android 6 Permissions Model (optional advanced session, Session 21)
* Interfaces (Session 22)
* Fragments (Sessions 23 and 24)

## Android Studio Tips
1. Import settings

When you start up Studio and it asks to import settings, the students should enter this path for the settings.
C:\Android2.3.3\.AndroidStudio2.3
This will avoid that annoying behaviour where it downloads the SDK, even though the SDK is already installed.
Once this is done, the SDK path should be set automatically (students will not need to do it themselves) but in case of problems, students should ensure that the SDK path (in the settings) is C:\Android2.3.3\SDK

2. to view logcat ( android logs) use Alt 6

3. to stop git saving password etc

AVOID checking the "Git Credential Manager" checkbox during the Git for Windows installer, or (after installation) run bash shell as Administrator and use 
```
git config --edit --system 

```
to remove the helper = manager line so that it is no longer registered as a credential helper.
To disable the OpenSSH credentials popup too. use git config --edit --global and insert:
```
[core]
    askpass =
```

# GIT
http://www.free-map.org.uk/course/mad/git_cheat_sheet.docx)

if you cant get git in android studio to work use the windows client
https://git-scm.com/download/win
download and install the 64-bit Git for Windows Portable. 
once installed right click on your folder and you will see git bash which is the command line for git.


# Week 1 Session 2 
http://www.free-map.org.uk/course//mad/part2.php

## Recap Session 1

1. set up Android Studio 3
2. set up phone simulator
3. built and deployed our first app
4. looked at /RES/layout/activity_main.xml (layouts)
5. looked at /RES/values/strings (internationalisation of strings)
6. looked at onCreate() and auto generated R class
7. looked at Android API levels and AppCompatActivity

Q Why is it good to separate layouts and strings from Java code?

## Session 2 Key teaching points

1. Event Handling OnClickListener
2. buttons
3. more advanced layouts LinearLayout, RelativeLayout
4. optional - LayoutWeight (See my ReportProblems example)

# Week 2 session 2 
http://www.free-map.org.uk/course//mad/part3.php

continuing development of map application

# session 2 key teaching points

1. Difference between “@id/” and “@+id/” in Android
See https://stackoverflow.com/questions/5025910/difference-between-id-and-id-in-android
Essentially @+id creates a new reference and @id references an existing refernece. However @+id will not change an existing reference so is safe to use (apparently).

2. layouts Linear / Relative
https://developer.android.com/guide/topics/ui/layout/linear.html
https://developer.android.com/guide/topics/ui/layout/relative.html

3. maps 
installing new library
centering map on coordinates


## Preferences exercise
http://www.free-map.org.uk/course//mad/part5.php 
Mobile Application Development - Part 5
Persistence I - Preferences
See https://github.com/gallenc/solent1Public/tree/master/HelloMap4 HelloMap4

## Lists exercise
http://www.free-map.org.uk/course//mad/part6.php 
Mobile Application Development - Part 6Lists
see https://github.com/gallenc/solent1Public/tree/master/HelloMap5 HelloMap5
