package org.idey.algo.bitops;

public class BitSet {
    private int[] numbers;
    private int size;
    public BitSet(int k){
        this.size = k;
        int arrayLength = this.size >> 5;
        if((this.size & 31) != 0){
            arrayLength++;
        }
        numbers = new int[arrayLength];
    }

    public BitSet(){
        this(Integer.MAX_VALUE);
    }

    public void set(int k)
    {
        if(k>size)
            throw new IllegalArgumentException("Invalid position");
        int reminder = k & 0x1F;
        int divide = k >> 5;
        numbers[divide] = numbers[divide] | (1<<reminder);
    }

    public void clear(int k)
    {
        if(k>size)
            throw new IllegalArgumentException("Invalid position");
        int reminder = k & 0x1F;
        int divide = k >> 5;
        numbers[divide] = numbers[divide] & (~(1<<reminder));
    }

    public boolean get(int k)
    {
        if(k>size)
            throw new IllegalArgumentException("Invalid position");
        int reminder = k & 0x1F;
        int divide = k >> 5;
        return (numbers[divide] & (1<<reminder))!=0;
    }

    public void toggle(int k)
    {
        if(k>=size)
            throw new IllegalArgumentException("Invalid position");
        int reminder = k & 0x1F;
        int divide = k >> 5;
        numbers[divide] = numbers[divide] ^ (1 << reminder);
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        BitSet set = new BitSet(Integer.MAX_VALUE);
        System.out.println(set.numbers.length);
        set.set(32);
        System.out.println(set.get(32));
    }

}
