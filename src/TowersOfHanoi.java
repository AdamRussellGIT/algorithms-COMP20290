public class TowersOfHanoi
{
    public static void moveTowers(int disk, String source, String dest, String auxiliary)
    {

        //base case
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
        long startTime = System.currentTimeMillis();
        moveTowers(3, "A", "C", "B");
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to move 3 disks: " + (endTime - startTime) + " milliseconds.");

        System.out.print("\n");

        long startTime1 = System.currentTimeMillis();
        moveTowers(4, "A", "C", "B");
        long endTime1 = System.currentTimeMillis();
        System.out.println("Time taken to move 4 disks: " + (endTime1 - startTime1) + " milliseconds.");

        System.out.print("\n");

        long startTime2 = System.currentTimeMillis();
        moveTowers(8, "A", "C", "B");
        long endTime2 = System.currentTimeMillis();
        System.out.println("Time taken to move 8 disks: " + (endTime2 - startTime2) + " milliseconds.");

        System.out.print("\n");

        long startTime3 = System.currentTimeMillis();
        moveTowers(13, "A", "C", "B");
        long endTime3 = System.currentTimeMillis();
        System.out.println("Time taken to move 13 disks: " + (endTime3 - startTime3) + " milliseconds.");
    }
}