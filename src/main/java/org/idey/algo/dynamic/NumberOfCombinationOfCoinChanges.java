package org.idey.algo.dynamic;

/**
 * Created by i.dey on 8/11/16.
 */
public class NumberOfCombinationOfCoinChanges {

    public static void print(int[] coint, int totalAmout){
        int i, j, x, y;
        int table[][] = new int [totalAmout+1][coint.length];

        for(i=0;i<coint.length;i++){
            table[0][i]=1;
        }

        for (i = 1; i < totalAmout+1; i++)
        {
            for (j = 0; j < coint.length; j++)
            {
                // Count of solutions including S[j]
                x = (i-coint[j] >= 0)? table[i - coint[j]][j]: 0;

                // Count of solutions excluding S[j]
                y = (j >= 1)? table[i][j-1]: 0;

                // total count
                table[i][j] = x + y;
            }
        }

        for(int[] val:table){
            for(int amout:val){
                System.out.print(amout+" ");
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        print(new int[]{1,5,10,25},63);
    }

}
