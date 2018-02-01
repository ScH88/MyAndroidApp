# MyAndroidApp

My first attempt at developing a practice Android application. This is a base version, and I plan on adding more features to it in the future.

All files here were extracted from my "AndroidAppProject" base project file > app > src, as uploading all Gradle/Build files was not necessary. The app was built from a "DrawerLayout" base selected upon setting up the new project.

Background Image: http://www.myfreetextures.com/wp-content/uploads/2011/06/brushedmetal103.jpg

Current branches -

BRANCH 1: "Branch #1 - Attempt at Translating ArrayAdapter" 

What the title implies is that for each Holiday object in Menu 5's ArrayAdapter, there are internal calls to classes to translate the Month, Details and Price values. Said new classes added, which extend AsyncTask, use online APIs to translate the values passed over from each Holiday, as well as the HolidayFullDetails page. 

I've also had to omit the Arabic Strings XML files, because the API the program uses does not do Saudi Riyals unfortunately.

As it stands, the HolidayFullDetails page works fine. The big problem I am having is that the Menu 5 page is having trouble showing the translations as intended. As expected, each row should show the translated values each upon loading, but they do not show unless I scroll down and up, for some weird reason (and even then, they can disappear spontaneously). If I click the "full details" button when the values are not loaded, the program crashes with a NullPointerException.

If you are reading this, I could really, REALLY do with some help here.
