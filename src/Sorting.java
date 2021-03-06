import java.util.Arrays;

/**
 * A class containing multiple different sorting algorithms alongside testing for each.
 */
public class Sorting
{
    /**
     * Implementation of selection sort.
     *
     * @param a int array to be sorted
     */
    public static void selectionSort(int[] a)
    {
        int temp;
        int min_index;

        for (int i = 0; i < a.length-1; i++)
        {
            min_index = i;

            for (int j = i + 1; j < a.length; j++)
            {
                //check if we find smaller number
                if (a[min_index] > a[j])
                {
                    min_index = j;
                }
            }

            //swap first element in current array with min_index
            temp = a[i];
            a[i] = a[min_index];
            a[min_index] = temp;
        }
    }

    /**
     * Implementation of Insertion sort.
     *
     * @param a int array to be sorted
     */
    public static void insertionSort(int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            int valueToSort = a[i];
            int j = i;

            //move value down till not greater than previous
            while (j> 0 && a[j-1] > valueToSort)
            {
                a[j] = a[j-1];
                j--;
            }

            a[j] = valueToSort;
        }
    }

    /**
     * Implementation of joke Stalin sort.
     *
     * @param a int array to be sorted
     */
    public static void stalinSort(int[] a)
    {
        int max = a[0];

        for (int i = 1; i < a.length; i++)
        {
            if (a[i] > max)
            {
                max = a[i];
            }

            else
            {
                a[i] = 0;
            }
        }
    }

    /**
     * Implementation of recursive mergeSort.
     *
     * @param a int array to be sorted
     * @param n size of the array
     */
    public static void mergeSort(int[] a, int n)
    {
        if (n < 2)
        {
            return;
        }

        int mid = n/2;
        int[] left = new int[mid];
        int[] right = new int[n-mid];

        for (int i = 0; i < mid; i++)
        {
            left[i] = a[i];
        }

        for (int j = mid; j < n; j++)
        {
            right[j-mid] = a[j];
        }

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(a, left, right, mid, n-mid);
    }

    /**
     * Helper functon ofr mergeSort.
     *
     * @param a int array
     * @param left first left half of a
     * @param right right half of a
     * @param l number that indexes the left subarray
     * @param r number that indexes the right subarray
     */
    public static void merge(int[] a, int[] left, int[] right, int l, int r)
    {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length)
        {
            if (left[i] <= right[j])
            {
                a[k++] = left[i++];
            }

            else
            {
                a[k++] = right[j++];
            }
        }

        while (i < left.length)
        {
            a[k++] = left[i++];
        }

        while (j < right.length)
        {
            a[k++] = right[j++];
        }
    }

    /**
     * An enhancement on the mergeSort with some alterations.
     * @param a int array to be sorted
     * @param n size of array
     */
    public static void mergeSortEnhanced(int[] a, int n)
    {
        //check if the array is sorted
        if (sorted(a))
        {
            return;
        }

        int mid = n/2;
        int[] left = new int[mid];
        int[] right = new int[n-mid];

        for (int i = 0; i < mid; i++)
        {
            left[i] = a[i];
        }

        for (int j = mid; j < n; j++)
        {
            right[j-mid] = a[j];
        }

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(a, left, right, mid, n-mid);
    }

    public static void mergeEnhanced(int arr[], int l, int m, int r)
    {
        //do insertion sort for small subarrays
        if (r <= l + 7)
        {
            insertionSort(arr);
            return;
        }

        int n1 = m - l + 1;
        int n2 = r - m;

        int[] left = new int[n1];
        int[] right = new int [n2];

        for (int a = 0; a < n1; a++)
        {
            left[a] = arr[l+a];
        }

        for (int b = 0; b < n2; b++)
        {
            right[b] = arr[m+1+b];
        }

        int i = 0;
        int j = 0;
        int k = l;

        while (i < n1 && j < n2)
        {
            if (left[i] <= right[j])
            {
                arr[k] = left[i];
                i++;
            }

            else
            {
                arr[k] = right[j];
                j++;
            }

            k++;
        }

        while (i < n1)
        {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    /**
     * Helper method to check if an array is sorted.
     *
     * @param a array to check if sorted
     * @return true or false if array is sorted or not
     */
    public static boolean sorted(int[] a)
    {
        for (int i = 1; i < a.length; i++) {
            if (a[i] <= a[i - 1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * Implementation of quickSort.
     *
     * @param arr array to be sorted
     * @param low index of lower portion of array to be sorted
     * @param high index of higher portion of array to be sorted
     */
    public static void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Helper method for quickSort to sort partitions.
     *
     * @param arr array to be sorted
     * @param low lower index of subarray
     * @param high higher index of subarray
     * @return int index of where array should be split to be sorted
     */
    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[high];

        int i = low - 1;

        for (int j = low; j <= (high - 1); j++)
        {
            if (arr[j] < pivot)
            {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp2 = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp2;

        return i + 1;
    }


    public static void quickSortEnhanced(int[] arr, int low, int high)
    {
        /*if (high <= (low + 7))
        {
            insertionSort(arr);
        }*/

        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partitionEnhanced(int[] arr, int low, int high)
    {
        int pivot = (low + (high - low)/2 + high)/3;

        int i = low - 1;

        for (int j = low; j <= (high - 1); j++)
        {
            if (arr[j] < pivot)
            {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp2 = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp2;

        return i + 1;
    }

    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    public static int medianOf3(int one, int two, int three)
    {
        if (one > two)
        {
            if (three > two)
            {
                if (one > three)
                {
                    return three;
                }

                return two;
            }
        }

        if (three > two)
        {
            return two;
        }

        if (one > three)
        {
            return three;
        }

        return one;
    }

    public static void main(String[] args)
    {
        int size = 100;
        int temp;

        for (int j = 0; j < 6; j++) {
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

            for (int i = 0; i < size; i++) {
                temp = (int) (Math.random() * ((1000 - 0) + 1)) + 0;
                arrayA[i] = temp;
                arrayB[i] = temp;
                arrayC[i] = temp;
                arrayD[i] = temp;
                arrayE[i] = temp;
                arrayF[i] = temp;
                arrayG[i] = temp;
            }

            long startTimeSelection = System.currentTimeMillis();
            selectionSort(arrayA);
            long estimatedTimeSelection = System.currentTimeMillis() - startTimeSelection;

            long startTimeInsertion = System.currentTimeMillis();
            insertionSort(arrayB);
            long estimatedTimeInsertion = System.currentTimeMillis() - startTimeInsertion;

            long startTimeStalin = System.currentTimeMillis();
            stalinSort(arrayC);
            long estimatedTimeStalin = System.currentTimeMillis() - startTimeStalin;

            long startTimeMergeSort = System.currentTimeMillis();
            mergeSort(arrayD, arrayD.length);
            long estimatedTimeMergeSort = System.currentTimeMillis() - startTimeMergeSort;

            long startTimeMergeSortEnhanced = System.currentTimeMillis();
            mergeSortEnhanced(arrayE, arrayE.length);
            long estimatedTimeMergeSortEnhanced = System.currentTimeMillis() - startTimeMergeSortEnhanced;

            long startTimeQuickSort = System.currentTimeMillis();
            quickSort(arrayF, 0, arrayF.length - 1);
            long estimatedTimeQuickSort = System.currentTimeMillis() - startTimeQuickSort;

            shuffle(arrayG);
            long startTimeQuickSortEnhanced = System.currentTimeMillis();
            quickSortEnhanced(arrayG, 0, arrayF.length - 1);
            long estimatedTimeQuickSortEnhanced = System.currentTimeMillis() - startTimeQuickSortEnhanced;

            /*System.out.println("Selection Sort took : " + estimatedTimeSelection + " milliseconds.");
            System.out.println("Insertion Sort took : " + estimatedTimeInsertion + " milliseconds.");*/
            System.out.println("Stalin Sort took : " + estimatedTimeStalin + " milliseconds.");
            System.out.println("Merge Sort took : " + estimatedTimeMergeSort + " milliseconds.");
            System.out.println("Merge Sort Enhanced took : " + estimatedTimeMergeSortEnhanced + " milliseconds.");
            System.out.println("Quick Sort took : " + estimatedTimeQuickSort + " milliseconds.");
            System.out.println("Quick Sort Enhanced took : " + estimatedTimeQuickSortEnhanced + " milliseconds.");

            size *= 10;
        }
    }
}
