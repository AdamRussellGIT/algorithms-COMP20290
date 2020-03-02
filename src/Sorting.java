import java.util.Arrays;

public class Sorting
{
    public static void selectionSort(int[] a)
    {
        int temp;
        int min_index;

        for (int i = 0; i < a.length-1; i++)
        {
            min_index = i;

            for (int j = i + 1; j < a.length; j++)
            {
                if (a[min_index] > a[j])
                {
                    min_index = j;
                }
            }

            temp = a[i];
            a[i] = a[min_index];
            a[min_index] = temp;
        }
    }

    public static void insertionSort(int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            int valueToSort = a[i];
            int j = i;

            while (j> 0 && a[j-1] > valueToSort)
            {
                a[j] = a[j-1];
                j--;
            }

            a[j] = valueToSort;
        }
    }

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

    public static void mergeSortEnhanced(int[] a, int n)
    {
        if (sorted(a))
        {
            return;
        }

        if (n < 7)
        {
            insertionSort(a);
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

    public static boolean sorted(int[] a)
    {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }

        return true;
    }
    public static void quickSort(int[] arr, int high, int low)
    {
        if (high <= (low + 30))
        {
            insertionSort(arr);
        }

        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

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


    public static void quickSortEnhanced(int[] arr, int high, int low)
    {
        if (high <= (low + 10))
        {
            insertionSort(arr);
        }

        if (low < high)
        {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partitionEnhanced(int[] arr, int low, int high)
    {
        int pivot = medianOf3( low, low + (high - low)/2, high);

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

        int[] arrayA = new int[size];
        int[] arrayB = new int[size];
        int[] arrayC = new int[size];
        int[] arrayD = new int[size];
        int[] arrayE = new int[size];
        int[] arrayF = new int[size];
        int[] arrayG = new int[size];
        int[] aux = new int[size];

        for (int i = 0; i < size; i++)
        {
            temp = (int)(Math.random()*((1000-0)+1))+0;
            arrayA[i] = temp;
            arrayB[i] = temp;
            arrayC[i] = temp;
            arrayD[i] = temp;
            arrayE[i] = temp;
            arrayF[i] = temp;
            arrayG[i] = temp;
        }

        long startTimeSelection = System.nanoTime();
        selectionSort(arrayA);
        long estimatedTimeSelection = System.nanoTime() - startTimeSelection;

        long startTimeInsertion = System.nanoTime();
        insertionSort(arrayB);
        long estimatedTimeInsertion = System.nanoTime() - startTimeInsertion;

        long startTimeStalin = System.nanoTime();
        stalinSort(arrayC);
        long estimatedTimeStalin = System.nanoTime() - startTimeStalin;

        long startTimeMergeSort = System.nanoTime();
        mergeSort(arrayD, arrayD.length);
        long estimatedTimeMergeSort = System.nanoTime() - startTimeMergeSort;

        long startTimeMergeSortEnhanced = System.nanoTime();
        mergeSortEnhanced(arrayE, arrayE.length);
        long estimatedTimeMergeSortEnhanced = System.nanoTime() - startTimeMergeSortEnhanced;

        long startTimeQuickSort = System.nanoTime();
        quickSort(arrayF, 0, arrayF.length-1);
        long estimatedTimeQuickSort = System.nanoTime() - startTimeQuickSort;

        //shuffle(arrayG);
        long startTimeQuickSortEnhanced = System.nanoTime();
        quickSortEnhanced(arrayG, 0, arrayF.length-1);
        long estimatedTimeQuickSortEnhanced = System.nanoTime() - startTimeQuickSortEnhanced;

        System.out.println("Selection Sort: " + Arrays.toString(arrayA) + "took : " + estimatedTimeSelection + " nano seconds.");
        System.out.println("Insertion Sort: " + Arrays.toString(arrayB) + "took : " + estimatedTimeInsertion + " nano seconds.");
        System.out.println("Stalin Sort: " + Arrays.toString(arrayC) + "took : " + estimatedTimeStalin + " nano seconds.");
        System.out.println("Merge Sort: " + Arrays.toString(arrayD) + "took : " + estimatedTimeMergeSort + " nano seconds.");
        System.out.println("Merge Sort Enhanced: " + Arrays.toString(arrayE) + "took : " + estimatedTimeMergeSortEnhanced + " nano seconds.");
        System.out.println("Quick Sort: " + Arrays.toString(arrayF) + "took : " + estimatedTimeQuickSort + " nano seconds.");
        System.out.println("Quick Sort Enhanced: " + Arrays.toString(arrayG) + "took : " + estimatedTimeQuickSortEnhanced + " nano seconds.");
    }
}
