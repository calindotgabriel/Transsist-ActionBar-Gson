Transsist-ActionBar-Gson
========================

Ever got annoyed at public transport ? Ever wanted to have the solution to it right in your pocket ? We have the solution for it.

__________________________________________________________________________________________________________________________________


Steps made in order to make ABS work:

1. Downloaded and added it as a module in Idea.
2. Used latest API Level for Project. 
3. Added in Manifest.xml minSdkVersion as 10, if ICS then it will use its native implementation.
4. Added to my module supportv4 library.
5. Deleted 'test' folder from ABS module.

Ta-dam. It workeed!

__________________________________________________________________________________________________________________________________
