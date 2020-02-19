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
        System.out.println(RusPesAlg(300, 382));
        final long elapsedTime = System.nanoTime();
        System.out.println("The time take was " + (elapsedTime - startTime));
    }
}