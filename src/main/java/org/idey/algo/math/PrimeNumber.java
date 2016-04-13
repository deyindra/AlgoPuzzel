package org.idey.algo.math;

import java.util.BitSet;

public class PrimeNumber {
    private BitSet bitSet;
    private int upperLimit;

    public PrimeNumber(int upperLimit) {
        this.bitSet = new BitSet(upperLimit);
        this.bitSet.set(0,false);
        this.bitSet.set(1,false);
        this.bitSet.set(2,upperLimit,true);
        fillSieve(upperLimit);
    }


    private void fillSieve(int upperLimit){
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
        assert (number<=upperLimit);
        return this.bitSet.get(number);
    }

    public static void main(String[] args) {
        PrimeNumber primeNumber=new PrimeNumber(2000);
        int count=0;
        for(int i=0;i<=2000;i++){
            if(primeNumber.isPrime(i)){
                count++;
                System.out.print(i+" ");
            }
        }
        System.out.println("\n"+count);
    }

}
