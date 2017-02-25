package org.idey.algo.bitops;

public class BitSet {
    private int[] numbers;
    private int size;
    public BitSet(int k){
        this.size = k;
        numbers = new int[(k >> 5) +1];
    }

    public BitSet(){
        this(Integer.MAX_VALUE);
    }

    public void set(int k)
    {
        if(k>size)
            throw new IllegalArgumentException("Invalid position");
        int remender = k & 0x1F;
        int devide = k >> 5;
        numbers[devide] = numbers[devide] | (1<<remender);
    }

    public void clear(int k)
    {
        if(k>size)
            throw new IllegalArgumentException("Invalid position");
        int remender = k & 0x1F;
        int devide = k >> 5;
        numbers[devide] = numbers[devide] & (~(1<<remender));
    }

    public boolean get(int k)
    {
        if(k>size)
            throw new IllegalArgumentException("Invalid position");
        int remender = k & 0x1F;
        int devide = k >> 5;
        return (numbers[devide] & (1<<remender))!=0;
    }

    public void toggle(int k)
    {
        if(k>=size)
            throw new IllegalArgumentException("Invalid position");
        int remender = k & 0x1F;
        int devide = k >> 5;
        numbers[devide] = numbers[devide] ^ (1 << remender);
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        BitSet set = new BitSet(Integer.MAX_VALUE);
        System.out.println(set.numbers.length);
        set.set(Integer.MAX_VALUE);
        System.out.println(set.get(Integer.MAX_VALUE));
    }

}
