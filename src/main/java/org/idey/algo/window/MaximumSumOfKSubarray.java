package org.idey.algo.window;

public class MaximumSumOfKSubarray {
    public static int maxSumOfKSubArray(int[] array, int k){
        int maxSum;
        int sum=0;
        for(int i=0;i<k;i++){
            sum = sum+array[i];
        }
        maxSum=sum;
        for(int i=k;i<array.length;i++){
            sum=sum+array[i]-array[i-k];
            if(maxSum<sum){
                maxSum=sum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        //5,4,6
        int maxSum = maxSumOfKSubArray(new int[]{-1,1,5,-2,3},3);
        System.out.println(maxSum);
    }
}
