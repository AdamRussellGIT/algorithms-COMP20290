# *Algorithms Portfolio - Adam Russell*

This is a repository that I maintained and updated throughout the semester for Algorithms COMP20290. It contains all algorithms developed over the course of the moduel in practicals, along with some elementary timings for some of the algorithms.

# Navigating The Repo

## *Classes*

### Main.java
This class can be run to see implementations of all the other classes (e.g. sorted arrays after calling sorting algorithms from the Sorting.java class).
*N.B. All classes here care called via there main function except the sorting class. This is because in the sorting class, the sorted arrays are no actually printed, only timings are. I have run seperate code in the main class that prints out the sorted arrays after calling each sort, so it can be seen that each sorting method actually sorts.*

### RussianPeasant.java
This class contains the implementation for the Russian Peasant Multiplication Algorithm, alongside timings for different sizes of integers (e.g. 1x1, 235x651 etc).

### FibRecursion.java
This class contains an implementation of a recursive solution to finding the nth fibonacci numbers, along with timings for differet values of n.

### TowersOfHanoi.java
This class contains a method which prints out instructions on how to solve the towers of Hanoi problem for an inputted N number of disks and 3 towers. The method works recursively.

### ThreeSumA.java && ThreeSumB.java
These classes contain two different implementations of a method which returns the number of triples i, j, k such that a[i] + a[j] + a[k] = 0. In each class timings were considered to see which implementation was better.
*N.B. Due to the use of a special In.class class, these classes were ran and tested outside of my IDE, in the command prompt. The timings can be seen in the Analysis Timings Google Sheet associated with this repo.*

### Sorting.java
This class contains the following sorting algorithms:
- Selection Sort
- Insertion Sort
- Stalin Sort (For fun)
- Merge Sort
- Merge Sort Enhanced
- Quick Sort
- Quick Sort Enhanced

Both Merge Sort Enhanced and Quick Sort Enhanced use various tweaks to the original sorts to improve their efficieny, such as using Insertion Sort for small sub arrays etc. This class contains timing methods for testing the various implementations of the sorts, the results of which can be seen in the accompanying Goole Sheets analysis spreadsheet.

### Trie.java
This class contains an implementation of a Trie data structure, along with simple tests to ensure it's working correctly.

### StringPatternMatching.java
This class contains both a brute force approach aswel as the Knuth-Morris-Pratt Algorithm for finding specified patterns in text. Also included is timings comparing the two algorithms, which can be seen in the Algorithm Anaylsis Charts spreadsheet in this repo.

### RunLengthEncoding

## *Miscellaneous*

### Etc

# Versioning

Regular commits were made over the course of the module, as practicals were completed and as certain algorithms and files were edited and refined.

# Authors

* **Adam Russell** - [AdamRussellGIT](https://github.com/AdamRussellGIT)
