# Journal
A Simple, lightweight and Private Journal
# <p>ChangeLog</p>
<p>
 -January, 2019: I hadn't realized that I was calling the garbage collector every 1 millisecond (I know, bad idea). 
 This version is orders of maginitude more responsive than the older version. 
 The older version was, at any moment in time of the app's exeuction(even when it was idle), needing 100% of the CPU. 
 The new(current) version will barely hit 30% of CPU usage when doing some I/O, sometimes less. These CPU percentages came in a machine with the following specs:
-OS X Mojave
 
-2.5 GHz Intel Core i5

-8 GB 1600 MHz DDR3

 </p>

# Compatibility
I built this app entirely on (and as a target for) OS X. Yes if you run the Journal.jar file, it will run on linux and Windows; I have done this myself. However, it looks kind-of horrendous on Windows and looses its sleekness when you run it on linux. In the foreseeable future I will fix this. Not sure when though. If you want to run Journal at its best, as of right now, OS X is the ideal platform.

# I don't want all of that source code sitting on my drive, how do I just run it without the source files?
All you need to run the app is the journal_Official.jar. So when you download the .zip ball, just get rid of everything else execept for the journal_Official.jar and double click the journal_Official.jar file to start up Journal.

# How dows Journal even work?
If you open up the app and have no clue how to even use it, just click on the "?" on top of the welcoming panel. This will show a helpful prompt and will tell you about things like shortcuts to start up a new Entry. After you read that little prompt, which is only 5 or 6 lines, you should be ready to go!

Every time you type up a bunch of pages and save them, Journal stores them on  your disk as whatis known to the app as an "Entry". Every time you save an Entry, it'lll save a .entry file that represents your Entry. All these entries are managed by the app in a folder called called "Entries". This folder is created, if it does not exist, by Journal. It'll create on the same directory where your journal_Official.jar file is. So for instance, if your journal_Official.jar is sitting on "/Users/lorenzogomez/journal_Official.jar", then when you start up Journal for the first time, a folder "/Users/lorenzogomez/Entries" will be created and all of your entries will be saved here.
