package org.idey.algo.dynamic;

import java.util.Scanner;

public class NonOverlappingPalinDromic {
    public static int productOfNonOverlappingPalindromicSubSequene(String str){
        if(str==null || str.length()<2 || str.length()>3000){
            throw new IllegalArgumentException("Invalid input");
        }
        final int length = str.length();
        int[][] dp = new int[length][length];
        for(int i=0;i<=length-1;i++){
            dp[i][i]=1;
        }
        for(int le=2;le<length+1;le++){
            for(int i=0;i<length-le+1;i++){
                int j = i + le - 1;
                if(le==2 && str.charAt(i)==str.charAt(j)){
                    dp[i][j] = 2;
                }else if(str.charAt(i)==str.charAt(j)){
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                }else{
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        int ans = 0;
        for(int i=0;i<length-1;i++){
            ans = Math.max(ans, dp[0][i] * dp[i + 1][length - 1]);
        }
        return ans;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str =scan.next();
        System.out.println(productOfNonOverlappingPalindromicSubSequene(str));
    }
}
