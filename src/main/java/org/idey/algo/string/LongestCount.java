package org.idey.algo.string;

import java.util.PriorityQueue;
import java.util.Stack;

public class LongestCount {

    public static int maxConsecutiveCharCount(String str){
        int maxCount=0;
        int consecutiveCharCount = 1;
        char[] array = str.toCharArray();
        char lastChar = array[0];

        for(int i=1;i<array.length;i++){
            if(lastChar==array[i]){
                consecutiveCharCount++;
            }
            if(lastChar != array[i] || i==array.length-1){
                if(maxCount<consecutiveCharCount){
                    maxCount = consecutiveCharCount;
                }
                consecutiveCharCount=1;
                lastChar = array[i];
            }
        }
        return maxCount;
    }


    public static Stack<CharCount> getNCharCount(String str, boolean isMax, int limit){
        PriorityQueue<CharCount> priorityQueue = new PriorityQueue<>((o1, o2) ->
                isMax ? (o1.count-o2.count) : -(o1.count-o2.count));

        char[] array = str.toCharArray();
        char lastChar = array[0];
        int consecutiveCharCount = 1;


        for(int i=1;i<array.length;i++){
            if(lastChar==array[i]){
                consecutiveCharCount++;
            }
            if(lastChar != array[i] || i==array.length-1){
                CharCount charCount = new CharCount(lastChar,consecutiveCharCount);
                addCharCount(charCount,priorityQueue,isMax,limit);
                consecutiveCharCount=1;
                lastChar = array[i];
            }
        }

        Stack<CharCount> charCounts = new Stack<>();
        while (!priorityQueue.isEmpty()){
            charCounts.push(priorityQueue.poll());
        }
        return charCounts;
    }

    private static class CharCount{
        private char ch;
        private int count;

        public CharCount(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CharCount charCount = (CharCount) o;

            if (ch != charCount.ch) return false;
            return count == charCount.count;
        }

        @Override
        public int hashCode() {
            int result = (int) ch;
            result = 31 * result + count;
            return result;
        }

        @Override
        public String toString() {
            return "CharCount{" +
                    "ch=" + ch +
                    ", count=" + count +
                    '}';
        }
    }

    public static void addCharCount(CharCount charCount,PriorityQueue<CharCount> priorityQueue,
                                                       boolean isMax, int limit){
        if(priorityQueue.contains(charCount)){
            priorityQueue.remove(charCount);
            priorityQueue.offer(charCount);
        }else{
            int size = priorityQueue.size();
            if(size<limit){
                priorityQueue.offer(charCount);
            }else{
                CharCount head = priorityQueue.peek();
                if(isMax? (head.count<charCount.count) : (head.count>charCount.count)){
                    priorityQueue.poll();
                    priorityQueue.offer(charCount);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(maxConsecutiveCharCount("CCCCCBBBCC"));
        System.out.println(getNCharCount("CCCCCBBBCCAAAAAA",true,3));
    }

}
