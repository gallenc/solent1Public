


# solent1Public
Miscellaneous solent work
http://www.free-map.org.uk/course

username dfti2018 (password seperate)

When you start up Studio and it asks to import settings, the students should enter this path for the settings.
C:\Android2.3.3\.AndroidStudio2.3
This will avoid that annoying behaviour where it downloads the SDK, even though the SDK is already installed.
Once this is done, the SDK path should be set automatically (students will not need to do it themselves) but in case of problems, students should ensure that the SDK path (in the settings) is C:\Android2.3.3\SDK

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
