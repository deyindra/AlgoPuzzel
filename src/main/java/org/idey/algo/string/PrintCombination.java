package org.idey.algo.string;

import java.util.Stack;

public class PrintCombination {
    public static void populateSubset(int[] data, int fromIndex, int endIndex, int sumInStack,
                               final int targetSum, Stack<Integer> stack) {

        if (sumInStack >= targetSum) {
            if (sumInStack == targetSum) {
                print(stack, targetSum);
            }
            // there is no need to continue when we have an answer
            // because nothing we add from here on in will make it
            // add to anything less than what we have...
            return;
        }
        for (int currentIndex = fromIndex; currentIndex < endIndex; currentIndex++) {

            if (sumInStack + data[currentIndex] <= targetSum) {
                stack.push(data[currentIndex]);
                sumInStack += data[currentIndex];

                /*
                * Make the currentIndex +1, and then use recursion to proceed
                * further.
                */
                populateSubset(data, currentIndex + 1, endIndex, sumInStack, targetSum, stack);
                sumInStack -= stack.pop();
            }
        }
    }

    /**
     * Print satisfied result. i.e. 15 = 4+6+5
     */

    private static void print(Stack<Integer> stack, final int targetSum) {
        StringBuilder sb = new StringBuilder();
        sb.append(targetSum).append(" = ");
        String separator="";
        for (Integer i : stack) {
            sb.append(separator).append(i);
            separator="+";
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        int[] array = new int[] { 1,5,10,8,7,15,25};
        populateSubset(array,0,array.length,0,16,new Stack<>());

    }
}
