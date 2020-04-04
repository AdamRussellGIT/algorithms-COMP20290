import java.lang.*;

public class RussianPeasent
{
    public static long RusPesAlg(int a, int b)
    {
        long res = 0;

        while (b > 0)
        {
            if (b%2 != 0)
            {
                res += a;
                a = a*2;
                b = b/2;
            }

            else
            {
                a = a*2;
                b = b/2;
            }
        }

        return res;
    }

    public static void main(String[] args)
    {
        final long startTime = System.nanoTime();
        System.out.println(RusPesAlg(245, 500));
        final long elapsedTime = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime - startTime));

        final long startTime1 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply 245 x 500: ");
        final long elapsedTime1 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime1 - startTime1));

        final long startTime2 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply 20 x 680: ");
        final long elapsedTime2 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime2 - startTime2));

        final long startTime3 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply -502 x 946: ");
        final long elapsedTime3 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime3 - startTime3));

        final long startTime4 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply 0 x 46: ");
        final long elapsedTime4 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime4 - startTime4));

        final long startTime5 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply 4 x 100000: ");
        final long elapsedTime5 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime5 - startTime5));
    }
}