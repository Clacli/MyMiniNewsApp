# MyMiniNewsApp
project 6 and project 7
Udacity - Google Android Basics Nanodegree Scholarship 2017/2018
#madewithudacity #googleudacityscholars #abndproject 

This is a mini news app. When it opens, it checks if there is an internet connection,
and in that case it query The Guardian API, fetch news information in form of 
a JSON string and subsequently parse it and extract section name, title of the news,
author and date and display them on the screen. Date and author might not be shown.

A SettingsActivity was added. 
Searches can be performed on selected topic and section news and they can be ordered by relevance, newest news and oldest news.
Under the preference name the user can see its value.

Important:
In order to test it, update the STUDENT_API_KEY String value with a student API key from The Guardian Open Platform:
https://open-platform.theguardian.com/access/

CardViews and RecyclerView
https://developer.android.com/guide/topics/ui/layout/cardview
https://developer.android.com/guide/topics/ui/layout/recyclerview
https://code.tutsplus.com/tutorials/getting-started-with-recyclerview-and-cardview-on-android--cms-23465

To implement intent from cardview:
https://www.codingdemos.com/android-share-intent-recyclerview/
https://developer.android.com/reference/android/content/Intent

Start an intent to open a web page from the browser - RicyclerView
https://stackoverflow.com/questions/46875247/recycle-view-on-click-to-open-up-url
https://antonioleiva.com/recyclerview-listener/

RecyclerView setVisibility gone for text
https://stackoverflow.com/questions/32501601/recyclerview-setvisibility-gone-for-image/32501814#32501814
	
On JSON parsing:
http://www.tutorialspoint.com/android/android_json_parser.htm

To format date
https://discussions.udacity.com/t/using-datetimeformatter-below-minsdk26/711152/6

I integrated Butter Knife library to the code for easy initialization by eliminating the use of findViewById. 
Butter Knife Copyright 2013 Jake Wharton
http://jakewharton.github.io/butterknife/
