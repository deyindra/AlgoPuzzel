package org.idey.algo.dynamic;

public class BuySell {
    public static void displayBuySell(int[] stocks){
        int min = 0;
        int maxDiff = 0;
        int buy=0,sell = 0;
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] < stocks[min])
                min = i;
            int diff = stocks[i] - stocks[min];
            if (diff > maxDiff) {
                buy = min;
                sell = i;
                maxDiff = diff;
            }
        }
        System.out.println(stocks[buy]);
        System.out.println(stocks[sell]);
        System.out.println(maxDiff);
    }

    public static void main(String[] args) {
        displayBuySell(new int[]{2,3,1,7,10,1,12});
    }
}
