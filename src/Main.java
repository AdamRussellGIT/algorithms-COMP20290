//class used to test implementations of all algorithms, and provide run time analysis

import java.util.Arrays;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("The following is all algorithms (that could be run outside of their class) ran with some various different test cases.");
        System.out.println("Each section will focus on a different algorithm (practical)");
        System.out.println("Descriptions of what is happening will be provided in each section.");
        System.out.println("N.B. For accurate timings, run each class individually, as calling classes from other methods will alter actual time taken.");
        System.out.println("Where timing was an important factor to measure, I implemented custom running of certain classes in main, to ignore timings.");

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("RUSSIAN PEASANT ALGORITHM");

        RussianPeasent.main(args);

        System.out.print("\n");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("RECURSION (FIBONACCI AND TOWERSOFHANOI ALGORITHMS)");

        FibRecursion.main(args);

        System.out.print("\n");

        TowersOfHanoi.main(args);

        System.out.print("\n");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("SORTING");

        Sorting.main(args);

        int size = 10;
        int temp;


        for (int i = 0; i < 4; i++) {
            System.out.print("\n");
            System.out.println("Sorting " + size + " elements: ");
            System.out.print("\n");
            int[] arrayA = new int[size];
            int[] arrayB = new int[size];
            int[] arrayC = new int[size];
            int[] arrayD = new int[size];
            int[] arrayE = new int[size];
            int[] arrayF = new int[size];
            int[] arrayG = new int[size];
            int[] aux = new int[size];

            for (int j = 0; j < size; j++) {
                temp = (int) (Math.random() * ((1000 - 0) + 1)) + 0;
                arrayA[j] = temp;
                arrayB[j] = temp;
                arrayC[j] = temp;
                arrayD[j] = temp;
                arrayE[j] = temp;
                arrayF[j] = temp;
                arrayG[j] = temp;
            }

            Sorting.selectionSort(arrayA);

            Sorting.insertionSort(arrayB);

            Sorting.stalinSort(arrayC);

            Sorting.mergeSort(arrayD, arrayD.length);

            Sorting.mergeSortEnhanced(arrayE, arrayE.length);

            Sorting.quickSort(arrayF, 0, arrayF.length - 1);

            //shuffle(arrayG);
            Sorting.quickSortEnhanced(arrayG, 0, arrayF.length - 1);

            System.out.println("Selection Sort: " + Arrays.toString(arrayA));
            System.out.println("Insertion Sort: " + Arrays.toString(arrayB));
            System.out.println("Stalin Sort: " + Arrays.toString(arrayC));
            System.out.println("Merge Sort: " + Arrays.toString(arrayD));
            System.out.println("Merge Sort Enhanced: " + Arrays.toString(arrayE));
            System.out.println("Quick Sort: " + Arrays.toString(arrayF));
            System.out.println("Quick Sort Enhanced: " + Arrays.toString(arrayG));

            size *= 10;
        }

        System.out.print("\n");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("STRING PATTERN MATCHING");

        StringPatternMatching.main(args);

        System.out.print("\n");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("TRIES\n");

        Trie.main(args);

        System.out.print("\n");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("RUN LENGTH ENCODING\n");

        RunLengthEncoding.main(args);
    }
}
