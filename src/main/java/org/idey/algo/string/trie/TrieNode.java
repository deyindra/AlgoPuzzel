package org.idey.algo.string.trie;

import java.util.LinkedList;

class TrieNode
{
    char data;
    boolean isEnd;
    int count;
    LinkedList<TrieNode> childList;

    /* Constructor */
    public TrieNode(char c)
    {
        childList = new LinkedList<>();
        isEnd = false;
        data = c;
        count = 0;
    }
    public TrieNode getChild(char c)
    {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.data == c)
                    return eachChild;
        return null;
    }
}