package org.idey.algo.string.trie;


public class Trie {
    private TrieNode root;

    /* Constructor */
    public Trie()
    {
        root = new TrieNode(' ');
    }
    /* This function is used to insert a word in trie*/
    public void insert(String word)
    {
        if (search(word))
            return;
        TrieNode current = root;
        for (char ch : word.toCharArray() )
        {
            TrieNode child = current.getChild(ch);
            if (child != null)
                current = child;
            else
            {
                // If child not present, adding it io the list
                current.childList.add(new TrieNode(ch));
                current = current.getChild(ch);
            }
            current.count++;
        }
        System.out.println(current.count);
        current.isEnd = true;
    }
    /* This function is used to search a word in trie*/
    public boolean search(String word)
    {
        TrieNode current = root;
        for (char ch : word.toCharArray() )
        {
            if (current.getChild(ch) == null)
                return false;
            else
                current = current.getChild(ch);
        }
        if (current.isEnd)
            return true;
        return false;
    }
    /* This function is used to remove function from trie*/
    public void remove(String word)
    {
        if (!search(word))
        {
            System.out.println(word +" does not present in trien");
            return;
        }
        TrieNode current = root;
        for (char ch : word.toCharArray())
        {
            TrieNode child = current.getChild(ch);
            if (child.count == 1)
            {
                current.childList.remove(child);
                return;
            }
            else
            {
                child.count--;
                current = child;
            }
        }
        current.isEnd = false;
    }



}