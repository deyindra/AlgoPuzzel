package org.idey.algo.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryNumberGeneratorIterator implements Iterator<String> {
    private final int size;
    private final int bit;
    private int currentBitPostion;

    public BinaryNumberGeneratorIterator(int bit) {
        assert(bit<=31);
        this.bit = bit;
        if(bit<31)
            size =1<<bit;
        else
            size=Integer.MAX_VALUE;
        currentBitPostion=0;
    }

    @Override
    public boolean hasNext() {
        return currentBitPostion<size;
    }

    @Override
    public String next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        StringBuilder sb = new StringBuilder();
        for(int i=bit-1;i>=0;i--){
            if((currentBitPostion & (1<<i))!=0){
                sb.append("1");
            }else{
                sb.append("0");
            }
        }
        currentBitPostion++;
        return sb.toString();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BinaryNumberGeneratorIterator iterator = new BinaryNumberGeneratorIterator(4);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
