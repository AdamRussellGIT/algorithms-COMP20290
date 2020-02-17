public class FibRecursion
{
    public static int fibNumber(int n)
    {
        if (n == 0)
        {
            return 0;
        }

        else if (n == 1 || n == 2)
        {
            return 1;
        }

        else
        {
            return fibNumber(n-2) + fibNumber(n-1);
        }
    }

    public static void main(String[] args)
    {
        int n = 25325;
        System.out.println("The " + n + "th fibonacci number is: " + fibNumber(n));
    }
}
