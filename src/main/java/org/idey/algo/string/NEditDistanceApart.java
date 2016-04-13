package org.idey.algo.string;

/**
 * Class to check if two String are N Edit Distance apart
 */
public class NEditDistanceApart {

    private static int min(int x, int y, int z)
    {
        return Math.min(Math.min(x, y), z);
    }

    /**
     * Check if both of the the String are N edit distance apart.
     * Assumption : Null String is as good as an empty String and length will be consider in such case 0
     * for example isNEditDistanceApart(null, "cats", 4) will return true
     * isNEditDistanceApart(acts, "cats", 2) will return true
     * @param firstString first Supplid String
     * @param secondString second supplied String
     * @param number number to represent N Edit Distnace apart
     * @return true if both of the String are n edit disctance apart, else return false
     *
     */
    public static boolean isNEditDistanceApart(String firstString, String secondString, int number){
        int m = firstString==null ? 0 : firstString.length();
        int n=  secondString==null ? 0 : secondString.length();

        // Create a table to store results of subproblems
        int dp[][] = new int[m+1][n+1];

        // Fill d[][] in bottom up manner
        for (int i=0; i<=m; i++){
            for (int j=0; j<=n; j++){
                // If first string is empty, only option is to
                // isnert all characters of second string
                if (i==0) {
                    dp[i][j] = j;  // Min. operations = j
                }
                // If second string is empty, only option is to
                // remove all characters of second string
                else if (j==0) {
                    dp[i][j] = i; // Min. operations = i
                }
                // If last characters are same, ignore last char
                // and recur for remaining string
                else if (firstString.charAt(i-1) == secondString.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // If last character are different, consider all
                // possibilities and find minimum
                else {
                    dp[i][j] = 1 + min(dp[i][j - 1],  // Insert
                            dp[i - 1][j],  // Remove
                            dp[i - 1][j - 1]); // Replace
                }
            }
        }
        return dp[m][n]==number;
    }



}
