public class TowersOfHanoi
{
    public static void moveTowers(int disk, String source, String dest, String auxiliary)
    {
        if (disk == 1)
        {
            System.out.println("Move " + source + " -> " + dest);
        }

        else
        {
            moveTowers(disk - 1, source, auxiliary, dest);
            System.out.println("Move " + source + " -> " + dest);
            moveTowers(disk - 1, auxiliary, dest, source);
        }
    }

    public static void main(String[] args)
    {
        moveTowers(3, "A", "C", "B");
    }
}