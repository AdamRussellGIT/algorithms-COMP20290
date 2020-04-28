/**
 * Implementation of the RunLength encoding methodology algorithm.
 */

public class RunLengthEncoding
{
    /**
     * This method converts an input string into an encoded, compressed output string.
     *
     * @param input string to be encoded
     * @return encoded string
     */
    public static String RLE(String input)
    {
        String output = "";
        int length = input.length();
        int count = 0;

        for (int i = 0; i < length; i++)
        {
            //if first letter just increment count
            if (i == 0)
            {
                count++;
            }

            //increment count if same character
            else if (input.charAt(i) == input.charAt(i-1))
            {
                count++;
            }

            //different character
            else
            {
                //no need to write 1 if there's only 1 of that character
                if (count == 1)
                {
                    output = output.concat(String.valueOf(input.charAt(i-1)));
                    //set count to 1 to account for accound for different character found
                    count = 1;
                }

                else
                {
                    output = output.concat(String.valueOf(input.charAt(i-1)) + String.valueOf(count));
                    count = 1;
                }
            }

            //if at end of string add the last character found and its count
            if (length - i -1 == 0)
            {
                if (count == 1)
                {
                    output = output.concat(String.valueOf(input.charAt(i)));
                    count = 0;
                }

                else
                {
                    output = output.concat(String.valueOf(input.charAt(i)) + String.valueOf(count));
                    count = 0;
                }
            }
        }

        return output;
    }

    public static void main(String[] args)
    {
        String input1 = "aaaabbbbb";
        String input2 = "jjtuuuer";
        String input3 = "1111223ddf";
        String input4 = "Hello      there!";

        System.out.println("Original string: " + input1);
        System.out.println("Compressed string: " + RLE(input1));

        System.out.print("\n");

        System.out.println("Original string: " + input2);
        System.out.println("Compressed string: " + RLE(input2));

        System.out.print("\n");

        System.out.println("Original string: " + input3);
        System.out.println("Compressed string: " + RLE(input3));

        System.out.print("\n");

        System.out.println("Original string: " + input4);
        System.out.println("Compressed string: " + RLE(input4));
    }
}
