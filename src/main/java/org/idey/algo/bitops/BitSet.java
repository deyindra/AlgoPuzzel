package org.idey.algo.bitops;

public class BitSet {
    int[] numbers;
    public BitSet(int k){
        numbers = new int[(k >> 5) + 1];
    }
    public void set(int k)
    {
        int remender = k & 0x1F;
        int devide = k >> 5;
        numbers[devide] = numbers[devide] | (1<<remender);
    }

    public void clear(int k)
    {
        int remender = k & 0x1F;
        int devide = k >> 5;
        numbers[devide] = numbers[devide] & (~(1<<remender));
    }

    public boolean get(int k)
    {
        int remender = k & 0x1F;
        int devide = k >> 5;
        return (numbers[devide] & (1<<remender))!=0;
    }

    public void toggle(int k)
    {
        int remender = k & 0x1F;
        int devide = k >> 5;
        numbers[devide] = numbers[devide] ^ (1 << remender);
    }

}
