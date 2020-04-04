public class FibRecursion
{

    public static int fibNumber(int n)
    {
        //base case 1
        if (n == 0)
        {
            return 0;
        }

        //base case 2
        else if (n == 1 || n == 2)
        {
            return 1;
        }

        //double recursive call
        else
        {
            return fibNumber(n-2) + fibNumber(n-1);
        }
    }

    public static void main(String[] args)
    {
        int n;
        long startTime;
        long endTime;

        System.out.print("\n");
        n = 2;
        startTime = System.currentTimeMillis();
        System.out.println("The " + n + "nd fibonacci number is: " + fibNumber(n));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");

        System.out.print("\n");
        n = 5;
        startTime = System.currentTimeMillis();
        System.out.println("The " + n + "th fibonacci number is: " + fibNumber(n));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");

        System.out.print("\n");
        n = 11;
        startTime = System.currentTimeMillis();
        System.out.println("The " + n + "th fibonacci number is: " + fibNumber(n));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");

        System.out.print("\n");
        n = 29;
        startTime = System.currentTimeMillis();
        System.out.println("The " + n + "th fibonacci number is: " + fibNumber(n));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");

        System.out.print("\n");
        n = 44;
        startTime = System.currentTimeMillis();
        System.out.println("The " + n + "th fibonacci number is: " + fibNumber(n));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds.");
    }
}
