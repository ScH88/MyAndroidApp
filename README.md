# MyAndroidApp

My first attempt at developing a practice Android application. This is a base version, and I plan on adding more features to it in the future.

All files here were extracted from my "AndroidAppProject" base project file > app > src, as uploading all Gradle/Build files was not necessary. The app was built from a "DrawerLayout" base selected upon setting up the new project.

Background Image: http://www.myfreetextures.com/wp-content/uploads/2011/06/brushedmetal103.jpg

CURRENT BRANCH DETAILS - "Localized XML Holiday Files"

As opposed to translating the "Month" and Details" values in each instance of the "Holiday" class like in Branch #1 (translation using APIs), the "holidays" list file now has a translated version for each of the supported languages in-program (in the style of strings.xml in the "values" subdirectory in res/resources).

Firstly, the JSON format is replaced by XML (as JSON files does not support the Russian and Chinese languages). This means that the Menu_Page_5 now uses an XmlPullParser to read through each element. The XmlPullParser is initalized in two ways (one of which uses the "raw" subdirectory like the previous JSON format did in the master branch. Said way is commented out because, to be honest, I do not know which way is best, even though both work fine). This means that the hotel month and details do not need AsyncTasks to translate, but the prices all remain the same because exchange rates are ever changing. As a result, the hotel price value still require an API to translate.
