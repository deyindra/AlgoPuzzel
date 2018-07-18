package org.idey.algo.iterator;

import java.util.Iterator;

public class NgramIterator implements Iterator<String> {

    private String[] words;
    private int pos = 0, n;

    public NgramIterator(int n, String str) {
        this.n = n;
        words = str.split(" ");
    }

    public boolean hasNext() {
        return pos < words.length - n + 1;
    }

    public String next() {
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < pos + n; i++)
            sb.append(i > pos ? " " : "").append(words[i]);
        pos++;
        return sb.toString();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

}
