package org.idey.algo.array;

//ToDO
import java.util.Arrays;
import java.util.Random;

public class KthLargest {
    public static <T extends Comparable<T>> T getPivot(T[] array, int low, int high){
        if(high-low+1 <= 9){
            Arrays.sort(array);
            return array[array.length/2];
        }
        T[] temp;
        T medians[] = (T[])new Comparable[(int)Math.ceil((double)(high-low+1)/5)];
        System.out.println(medians.length);
        int medianIndex = 0;
        while(low <= high){
            temp = (T[])new Comparable[Math.min(5,high-low+1)];
            for(int j=0;j<temp.length && low <= high;j++){
                temp[j] = array[low];
                low++;
            }
            Arrays.sort(temp);
            medians[medianIndex] = temp[temp.length/2];
            medianIndex++;
        }
        return getPivot(medians, 0, medians.length - 1);
    }


    private static <T extends Comparable<T>> int partition(T[] a, int l, int r) {
        int p = l + new Random().nextInt(r - l + 1);
        int storeIndex = l;
        swap(a, r, p); // swap a[pivot] with a[r]
        for (int i = l; i < r; i++) {
            if (a[i].compareTo(a[r])<0) {
                swap(a, i, storeIndex);
                storeIndex++; // a[l] ... a[storeIndex - 1] < pivot < a[storeIndex + 1] ... a[r]
            }
        }
        swap(a, storeIndex, r);
        return storeIndex;
    }

    public static <T extends Comparable<T>> T select(T[] a, int l, int r, int k) {
        int p = partition(a, l, r); // p is an absolute index
        if (p == k) {
            return a[p];
        } else if (p > k) {
            return select(a, l, p - 1, k);
        } else {
            return select(a, p + 1, r, k);
        }
    }





    private static <T extends Comparable<T>>  void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }



    public static void main(String[] args){
        Integer[] a = {4, 2, 6, 1, 7, 3, 5};
        for (int k = 1; k <= a.length; k++) {
            System.out.print(select(a, 0, a.length - 1, k - 1)); // kth smallest
            System.out.println(", " + select(a, 0, a.length - 1, a.length - k)); //kth largest
        }

    }



}
