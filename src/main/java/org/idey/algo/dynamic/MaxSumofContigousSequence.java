package org.idey.algo.dynamic;

public class MaxSumofContigousSequence {

    public static int maxSum(int[] array){
        int maxSoFar = array[0];
        int currentMax = 0;

        for(int i=0;i<array.length;i++){
            currentMax = currentMax+array[i];
            if(maxSoFar<currentMax){
                maxSoFar =  currentMax;
            }
            if(currentMax<0){
                currentMax=0;
            }
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println(maxSum(new int[]{5, 2, -4, 1}));
    }
}
