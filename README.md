# Journal
A Simple, lightweight and Private Journal
ChangeLog
 -January, 2019: I hadn't realized that I was calling the garbage collector every 1 millisecond (I know bad idea). 
 This version is orders of maginitude more responsive than the older version. 
 The older version was, at any moment in time of the app's exeuction(even when it was idle), needing 100% of the CPU. 
 The new(current) version will barely hit 30% of CPU usage when doing some I/O, sometimes less.
 
