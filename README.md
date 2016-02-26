# Threads-Assignment

Thread Assignments

Submission Date: February 29, 2016 (A1) March 02, 2016 (A2)

1.	Suppose that two threads “t1” and “t2” access a shared integer “x”. Thread “t1” indefinitely increases “x” and thread “t2” indefinitely prints the value of “x”. That is, the threads run in an infinite loop. However, “t1” must not increase “x” till that value of “x” is printed by “t2” and “t2” must not print the same value of “x” more than once.
Define classes for implementing “t1”, “t2”, and “x”. Write appropriate methods for these classes.

2.	Consider the following parallel binary search algorithm for series a1, a2…an sorted in increasing order such that n mod 100 = 0. Element to be searched is e.
Create 4 threads each of which can handle 100 numbers.
Distribute the numbers among threads.  . 
Distribute the element e to all threads.
Each thread searches the element e in its sub-array using binary search algorithm.
Write a Java program using threading and print the thread index and location where the element has been found.

3.	Suppose that two threads “t1” and “t2” access a shared circular queue “q”. Queue has a capacity of 100 elements. Thread “t1” adds integers randomly to the “q” and thread “t2” indefinitely deletes integers from the “q”. However, “t2” must not delete if the queue is empty.
Modify the “queue” class (which is defined while answering Question 2) as required and define classes for implementing “t1”and  “t2”.

4.	Suppose that a shared 2-D array of numbers is to be written in a shared 1-D array. This has to be completed using 4 threads where each thread writes one column of the 2-D array at a time to the 1-D array. Define Java classes and the main method to accomplish the desired task.


