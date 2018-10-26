package org.idey.algo.window;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaximumOfKSubarray {
    public static <E extends Comparable<E>> List<E> getMaxSubArray(E[] array, int k){
        if (k <= 0 && k>array.length) {
            throw new IllegalArgumentException("The value of k should atleast be 1.");
        }

        final Deque<Integer> deque = new LinkedList<>();
        final List<E> result = new ArrayList<>();

        int i;
        for (i = 0; i < k; i++) {
            while (!deque.isEmpty() && array[deque.peek()].compareTo(array[i]) <= 0) {
                deque.removeFirst();
            }
            deque.addLast(i);
        }


        for (; i < array.length; i++) {
            result.add(array[deque.peek()]);

            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && array[deque.peek()].compareTo(array[i]) <= 0) {
                deque.removeFirst();
            }
            deque.addLast(i);
        }

        result.add(array[deque.peek()]);
        return result;
    }




    public static void main(String[] args) {
        List<Integer> integers = getMaxSubArray(new Integer[]{1,-1,3,1,2,7},5);
        System.out.println(integers);
    }
}
