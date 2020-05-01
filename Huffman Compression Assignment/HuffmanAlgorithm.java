/******************************************************************************
 *  Compress Text Files/Binary Files to Binary Files
 *  Decompress Binary Files to Text Files/ Binary Files
 *
 *  Compilation:  javac HuffmanAlgorithm.java
 *
 *  Execution: java HuffmanAlgorithm [compress/decompress] 'inputFileName' 'outputFileName'
 *           e.g. java HuffmanAlgorithm compress mobydick.txt mobycompressed.bin
 *                java HuffmanAlgorithm compress smile.bmp smileCompressed.bin
 *
 *
 *  Dependencies: MinPQ.java (Supplied from master repo)
 *                  * StdIn.java (Needed for MinPQ.java. Supplied from master repo)
 *                  * StdOut.java (Needed for MinPQ.java. Supplied from master repo)
 *                BinaryIn.java (Supplied from master repo)
 *                BinaryOut.java (Supplied from master repo)
 ******************************************************************************/


/**
 *  This {@code HuffmanAlgorithm} class provides static methods for compressing
 *  and decompressing text/binary files using the 8 bit Ascii alphabet.
 *
 *  There are some helper functions that allow codes to be built {@code buildTrie}, and allows our 
 *  compression trie to be written to and read from the compressed file {@code writeTrie} and {@code readTrie}.
 *
 *  The algorithm takes advantage of the fact that all text files and all other files are all a multiple of 8 bits in size.
 *  When reading in, it convert each 8 bit sequence into an ascii character which it uses for encoding.
 *
 *  The only issue with this approach is that depending on the binary file (non-text) file, there may be occurances
 *      of my pseudo EOF character ('\0') within the file (sequence of 8 0's). To get around this, I simply increase the frequency of '\0' in the text artificially
 *      by 1 and encode the frequency of the character in my compressed file.
 *      When decompressing, I decrement a counter (that is initilisaed to the frequency of '\0' in the text) if I find '\0' and if the counter is > 1,  and write '\0' to the file.
 *      Otherwise, I stop writing to the file, as we have reached the legitimate end of the encoded file, and the rest of the file
 *      (if there is any) will be padding.
 *
 *  @author Adam Russell - 18328861
 */

import java.io.*;
import java.util.*;

public class HuffmanAlgorithm {

    //alphabet size of extended ASCII
    private static final int ALPHABET = 256;

    //dealing with 8 bit ascii
    private static final int WR = 8;

    private HuffmanAlgorithm() {}

    /**
     * Private node class used inorder to build the trie used for compression
     */
    private static class Node implements Comparable<Node> 
    {
        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) 
        {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf()
        {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that)
        {
            return this.freq - that.freq;
        }
    }



    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to a compressed file.
     *
     * @param inFile file name of file we are compressing
     * @param outFile file name for compressed outfile file
     */
    public static void compress(String inFile, String outFile) throws Exception {
        //set up of timing of compress() method
        long startTimeCompress = System.currentTimeMillis();



        //map to store characters and their frequencies
        Map<Character, Integer> charFreq = new HashMap<>();

        //used to calculate size of uncompressed file later
        long numChars = 0;
        //used to count how many unique characters there are in the text
        int uniqueChars = 0;

        /* READ THE INPUT */
        try 
        {
            BinaryIn input = new BinaryIn(inFile);

            while (!input.isEmpty())
            {
                char c = input.readChar();
                Integer count = charFreq.get(c);

                //if map doesn't already have this character, make count = 0
                if (count == null)
                {
                    //found new character
                    uniqueChars++;
                    count = 0;
                }                
                
                //put this character in the map
                charFreq.put(c, count + 1);

                //added another character
                numChars++;
            }

        } catch (Throwable e)
        {
            e.printStackTrace();
        }

        /* TABULATE FREQUENCY COUNTS */
        //int array to hold the frequencies
        int[] freq = new int[ALPHABET];

        for (Character c : charFreq.keySet())
        {
            //make c's position in freq = number of occurances of c
            freq[c] = charFreq.get(c);
        }

        /**
         * We always set the frequency of freq[0] (the null character '\0') to +1 of what it actually is.
         *
         * We need this as a psuedo EOF character. 0's may be padded onto the end of our compressed file
         * if the codes used don't work out to be a multiple of 8, and we will need this character to signify
         * the end of the file which will be seen later in this method.
         *
         * The issue with this is the way I deal with binary file's which again will be seen later.
         */
        freq['\0']++;


        /* BUILD HUFFMAN TRIE */
        Node root = buildTrie(freq);


        /* BUILD CODE TABLE */
        //array to hold the spcific compression code for each character
        String[] codes = new String[ALPHABET];
        buildCode(codes, root, "");


        /* USING HUFFMAN ENCODING TO OUTPUT TO NEW COMPRESSED BINARY FILE */
        try 
        {
            BinaryIn input2 = new BinaryIn(inFile);
            BinaryOut output = new BinaryOut(outFile);

            /**
             * We need to write our compression trie to the file, as we will need it to decompress the file.
             * 
             * It can be possible to create different trie's for the same file, so we need to use the same trie
             * we created originally, as otherwise we may create a different trie when trying to decompress,
             * which would give inocrrect decompression.
             */
            writeTrie(root, output);

            //write the frequency of the null character to the file
            output.write(freq['\0'], 32);

            //reread input file
            //write the codes for each character in original file to output file
            while (!input2.isEmpty())
            {
                char c = input2.readChar();

                //get corresponding code
                String code = codes[c];

                //write code to file
                for (int j = 0; j < code.length(); j++) 
                {
                    //write either 0 or 1
                    if (code.charAt(j) == '0') 
                    {
                        output.write(false);
                    }
                
                    else
                    {
                        output.write(true);
                    }
                }
            }

            /**
             * Write the code associated with '\0'.
             *
             * When we decode this character in decompression, we will know we've reached the end of the original text (given certain criteria seen in decompression),
             * and can ignore the remaining 0's in the file (if there are any) as these belong to padding,
             * and not the original file.
             */
            String code = codes['\0'];
            for (int j = 0; j < code.length(); j++) 
            {
                //write either 0 or 1
                if (code.charAt(j) == '0') 
                {
                    output.write(false);
                }
                
                else
                {
                    output.write(true);
                }
            }

            //flush output buffer
            output.close();

        } catch (Throwable e)
        {
            e.printStackTrace();
        }

        long endTimeCompress = System.currentTimeMillis();

        long timeToCompress = endTimeCompress - startTimeCompress;

        System.out.println("Time taken to compress: " + timeToCompress + " milliseconds.");
    }



    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; decompresses them; and writes the results to a file.
     *
     * @param inFile name of compressed file
     * @param outFile name of output file of decompressed data
     */
    public static void decompress(String inFile, String outFile) throws Exception {
        //set up for timing of decompress()
        long startTimeDecompress = System.currentTimeMillis();


        /* READ IN THE COMPRESSED FILE */
        try
        {    
            BinaryIn input = new BinaryIn(inFile);
            BinaryOut output = new BinaryOut(outFile);

            Node root = readTrie(input);

            //read in the frequency of the null character
            int nullOccurances = input.readInt(32);

            //move through rest of file
            while (!input.isEmpty())
            {
                //used to find code trough Huffman trie
                Node code = root;

                while(!code.isLeaf())
                {
                    //which way to go through trie
                    boolean direction = input.readBoolean();

                    //if 1, go right otherwise go left
                    if (direction)
                    {
                        code = code.right;
                    }

                    else
                    {
                        code = code.left;
                    }
                }

                /** 
                 * Found our EOF character, check if it's the actual EOF character
                 * 
                 * If it is the acutal end of file, break out of loop and print nothing
                 * Otherwise decrement counter and print the null character
                 */
                if (code.ch == '\0')
                {
                    if (nullOccurances <= 1)
                    {
                        break;
                    }

                    else
                    {
                        nullOccurances--;
                    }
                }

                //reach a leaf, found a character, write it to decompressed file
                output.write(code.ch, WR);
            }

            //flush output buffer
            output.close();

        } catch (Throwable e)
        {
            e.printStackTrace();
        }

        long endTimeDecompress = System.currentTimeMillis();

        long timeToDecompress = endTimeDecompress - startTimeDecompress;

        System.out.println("Time to decompress: " + timeToDecompress + " milliseconds.");
    }



    /**
     * Build's a trie based on the frequency of characters in the text.
     *
     * @param freq array of character frequencies
     */
    private static Node buildTrie(int[] freq) {
        //initialze priority queue with singleton trees
        MinPQ<Node> pq = new MinPQ<Node>();

        for (char i = 0; i < ALPHABET; i++)
        {
            //only add characters that actually exist in the text
            if (freq[i] > 0)
            {
                pq.insert(new Node(i, freq[i], null, null));
            }
        }

        //special case in case there is only one character with a nonzero frequency
        if (pq.size() == 1) 
        {
            if (freq['\0'] == 0) 
            {
                pq.insert(new Node('\0', 0, null, null));
            }

            else
            {
                pq.insert(new Node('\1', 0, null, null));
            }
        }

        //merge two smallest trees until we get to root
        while (pq.size() > 1) 
        {
            Node left  = pq.delMin();
            Node right = pq.delMin();

            //create parent node which contains combined frequencies of
            //  children nodes
            Node parent = new Node('\0', left.freq + right.freq, left, right);

            pq.insert(parent);
        }

        //return root node
        return pq.delMin();
    }



    /**
     * Recursive method that moves through the trie created and build's codes that are used 
     * in the compressed file to represent the different characters.
     *
     * @param codes the array of codes for each unqiue character
     * @param node the root of the trie
     * @param specCode code for the unique character that is added to the codes array
     */
    private static void buildCode(String[] codes, Node node, String specCode) {
        //build code recursively, 0 for left, 1, for right
        if (!node.isLeaf()) 
        {
            buildCode(codes, node.left,  specCode + '0');
            buildCode(codes, node.right, specCode + '1');
        }
        //when we reach a leaf node, add the current code to the codes array at the right
        //  location based on character
        else 
        {
            codes[node.ch] = specCode;
        }
    }


    /**
     * Recursive method that writes a trie to the compressed file in the 
     * form of an encoded bitstream.
     * 
     * Writes 0's to the file until we reach a leaf, where we write a 1 followed by the character
     *
     * @param currNode current node we are at in the trie
     * @param out binaryOut object used to write the the file
     */
    private static void writeTrie(Node currNode, BinaryOut out) {
        if (currNode.isLeaf()) {
            out.write(true);
            out.write(currNode.ch, WR);
            return;
        }
        out.write(false);
        writeTrie(currNode.left, out);
        writeTrie(currNode.right, out);
    }


    /**
     * Recursive method called at the start of decompress(), which reads in a trie from the file.
     * The frequency of each node is set to -1 as we don't care about frequency at this stage.
     * We already have all our codes created, we just need to readh them in and create a trie out of them.
     *
     * @param in binaryIn object used to read from the file
     */
    private static Node readTrie(BinaryIn in) {
        boolean isLeaf = in.readBoolean();
        //if we reach a 1, next birs constitute a character (and a leaf node of our trie)
        if (isLeaf) 
        {
            return new Node(in.readChar(), -1, null, null);
        }

        //read in internal nodes make their children (eventual) leaf nodes
        else {
            return new Node('\0', -1, readTrie(in), readTrie(in));
        }
    }



    /**
     * Sample client that calls {@code compress()} if the command-line
     * argument is "compress" an {@code decompress()} if it is "decompress".
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws Exception{
        //make sure given number of arguments is correct
        if (args.length != 3)
        {
            System.out.println("Invalid number of arguments!");
        }

        else
        {
            if (args[0].equals("compress"))
            {
                //compress the given file, and print compressed string to output file
                compress(args[1], args[2]);
            }

            else if (args[0].equals("decompress"))
            {
                //decompress the given file, and print decompressed string to output file
                decompress(args[1], args[2]);
            }

            else
            {
                System.out.println("Argument must be either 'compress' or 'decompress'!");
            }
        }
    }

}
