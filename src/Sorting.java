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

    public static void main(String[] args)
    {
        int size = 10;
        int temp;

        int[] arrayA = new int[size];
        int[] arrayB = new int[size];
        int[] arrayC = new int[size];

        for (int i = 0; i < size; i++)
        {
            temp = (int)(Math.random()*((1000-0)+1))+0;
            arrayA[i] = temp;
            arrayB[i] = temp;
            arrayC[i] = temp;
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

        System.out.println("Selection Sort: " + Arrays.toString(arrayA) + "took : " + estimatedTimeSelection + " nano seconds.");
        System.out.println("Insertion Sort: " + Arrays.toString(arrayB) + "took : " + estimatedTimeInsertion + " nano seconds.");
        System.out.println("Stalin Sort: " + Arrays.toString(arrayC) + "took : " + estimatedTimeStalin + " nano seconds.");

    }
}
