import java.lang.*;

/**
 * Implementation and testing of the RussianPeasant multiplication algorithm.
 */
public class RussianPeasent
{
    /**
     * Method to multiply two numbers using the Russian Peasnt algorithm.
     *
     * @param a first number
     * @param b second number
     * @return a multiplied by b
     */
    public static long RusPesAlg(int a, int b)
    {
        //result variable
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
        System.out.println("Multiply 2 x 4: ");
        System.out.println(RusPesAlg(2, 4));
        final long elapsedTime = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime - startTime));

        final long startTime1 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply 25 x 51: ");
        System.out.println(RusPesAlg(25, 51));
        final long elapsedTime1 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime1 - startTime1));

        final long startTime2 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply 202 x 680: ");
        System.out.println(RusPesAlg(202, 680));
        final long elapsedTime2 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime2 - startTime2));

        final long startTime3 = System.nanoTime();
        System.out.print("\n");
        System.out.println("Multiply -5025 x 9426: ");
        System.out.println(RusPesAlg(-5025, 9426));
        final long elapsedTime3 = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime3 - startTime3));
    }
}