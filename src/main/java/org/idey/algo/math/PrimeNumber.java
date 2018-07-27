package org.idey.algo.math;

import java.util.BitSet;

public class PrimeNumber {
    private BitSet bitSet;
    private int upperLimit;

    public PrimeNumber(int upperLimit) {
        this.upperLimit = upperLimit+1;
        this.bitSet = new BitSet(this.upperLimit);
        this.bitSet.set(0,false);
        this.bitSet.set(1,false);
        this.bitSet.set(2,this.upperLimit,true);
        fillSieve();
    }


    private void fillSieve(){
        for(int i=2;i<=upperLimit;i++){
            boolean isPrime = this.bitSet.get(i);
            if(isPrime){
                for (int j=2;i*j<=upperLimit;j++) {
                    bitSet.set(i*j,false);
                }
            }
        }
    }


    public boolean isPrime(int number){
        if(number>=upperLimit){
            throw new IllegalArgumentException();
        }
        return this.bitSet.get(number);
    }

    public static void main(String[] args) {
        PrimeNumber primeNumber=new PrimeNumber(2);
        int count=0;
        for(int i=0;i<=2000;i++){
            if(primeNumber.isPrime(i)){
                count++;
                System.out.print(i+" ");
            }
        }
        System.out.println("\nTotal count "+count);
    }

}
