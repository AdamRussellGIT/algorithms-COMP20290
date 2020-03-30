public class Trie
{
    static final int ALPHABET_SIZE = 26;

    static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        boolean isEndOfWord;

        TrieNode()
        {
            isEndOfWord = false;

            for (int i = 0; i < ALPHABET_SIZE; i++)
            {
                children[i] = null;
            }
        }
    }

    static TrieNode root;

    static void insert(String key)
    {
        TrieNode walk = root;

        for (int i = 0; i < key.length(); i++)
        {
            //check if there is a letter at current node
            if (walk.children[key.charAt(i) - 'a'] == null)
            {
                walk.children[key.charAt(i)-'a'] = new TrieNode();
            }

            walk = walk.children[key.charAt(i) - 'a'];
        }

        walk.isEndOfWord = true;
    }

    static boolean search(String key)
    {
        TrieNode walk = root;

        for (int i = 0; i < key.length(); i++)
        {
            if (walk.children[key.charAt(i) - 'a'] == null)
            {
                return false;
            }

            walk = walk.children[key.charAt(i) - 'a'];
        }

        return (walk != null && walk.isEndOfWord);
    }

    public static void main(String[] args)
    {
        //input
        String[] keys = {"bank", "book", "bar", "bring", "film", "filter", "simple", "silt", "silver"};


        String[] output = {"Not present in Trie!", "Present in Trie!"};

        root = new TrieNode();

        //construct a Trie
        for (int i = 0; i < keys.length; i++)
        {
            insert(keys[i]);
        }

        if (search("bar"))
        {
            System.out.println("bar: " + output[1]);
        }

        else
        {
            System.out.println("bar: " + output[0]);
        }

        if (search("film"))
        {
            System.out.println("film: " + output[1]);
        }

        else
        {
            System.out.println("film: " + output[0]);
        }

        if (search("boo"))
        {
            System.out.println("boo: " + output[1]);
        }

        else
        {
            System.out.println("boo: " + output[0]);
        }

        if (search("silt"))
        {
            System.out.println("silt: " + output[1]);
        }

        else
        {
            System.out.println("silt: " + output[0]);
        }

        if (search("blank"))
        {
            System.out.println("blank: " + output[1]);
        }

        else
        {
            System.out.println("blank: " + output[0]);
        }

        if (search("bring"))
        {
            System.out.println("bring: " + output[1]);
        }

        else
        {
            System.out.println("bring: " + output[0]);
        }

        if (search("hip"))
        {
            System.out.println("hip: " + output[1]);
        }

        else
        {
            System.out.println("hip: " + output[0]);
        }
    }
}
