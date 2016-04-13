package org.idey.algo.string;

import java.util.Arrays;

/**
 * Created by indranildey on 1/30/16.
 */
public class PermutationEnhanced {
    public static void main(String[] args) {
        combine("AAB".toCharArray(), new char[2], 3,2);
    }

    public static void combine(char[] A, char[] T, int n, int r) {
        if (r == 0) {
            permute(T, 0);
        } else if (n >= r) {
            T[r - 1] = A[n - 1];
            combine(A, T, n - 1, r - 1);
            combine(A, T, n - 1, r);
        }
    }

    /**
     * This method permutes the r elements in array T, T is prepared from the
     * combine method. T[] contains the combinations of r elements from N
     * elements
     *
     * @param T
     * @param swapingPosition
     */
    public static void permute(char[] T, int swapingPosition) {
        if (swapingPosition == T.length) {
            System.out.println(Arrays.toString(T));
        } else {
            for (int i = swapingPosition; i < T.length; i++) {
                swap(T, swapingPosition, i);
                permute(T, swapingPosition + 1);
                swap(T, swapingPosition, i);
            }
        }
    }

    /**
     * This is simple utility method to swap two elements of the array A at
     * given two indices, index1 and index2
     *
     * @param A
     * @param index1
     * @param index2
     */
    public static void swap(char[] A, int index1, int index2) {
        char temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }
}
