package org.idey.algo.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LongestCount {

    public static PriorityQueue<CharCount> returnLongest(String string, int run){
        if(string==null || string.length()==0){
            return null;
        }else{
            PriorityQueue<CharCount> priorityQueue = new PriorityQueue<>(run);
            Map<Character, CharCount> map = new HashMap<>();
            char[] array = string.toCharArray();
            char repeatedChar = array[0];
            int count = 1;
            for(int i=1;i<array.length;i++){
                if(array[i]==repeatedChar){
                    count++;
                }else{
                    addToPriorityQueue(map,priorityQueue,run,new CharCount(repeatedChar,count));
                    repeatedChar = array[i];
                    count=1;
                }

            }
            addToPriorityQueue(map,priorityQueue,run,new CharCount(repeatedChar,count));
            return priorityQueue;
        }
    }

    public static CharCount returnLongest(String str){
        if(str==null || str.length()==0){
            return null;
        }else{
            char[] array = str.toCharArray();
            CharCount charCount = new CharCount();
            char maxChar = array[0];
            int count = 1;
            for(int i=1;i<array.length;i++){
                if(array[i]==maxChar){
                    count++;
                }else{
                    if(charCount.count<count){
                        charCount.character=maxChar;
                        charCount.count=count;
                        maxChar=array[i];
                        count=1;
                    }
                }
            }
            if(charCount.count<count){
                charCount.character=maxChar;
                charCount.count=count;
            }
            return charCount;
        }
    }

    private static void addToPriorityQueue(Map<Character, CharCount> map, PriorityQueue<CharCount> priorityQueue, int run, CharCount charCount){
        CharCount oldCharCount = map.get(charCount.character);
        if(oldCharCount==null){
            map.put(charCount.character, charCount);
        }else{
            if(oldCharCount.compareTo(charCount)<0){
                priorityQueue.remove(oldCharCount);
                map.put(charCount.character, charCount);
            }else{
                return;
            }
        }

        int size = priorityQueue.size();
        if(size<run){
            priorityQueue.add(charCount);
        }else{
            CharCount head = priorityQueue.peek();
            if(head.compareTo(charCount)<0){
                priorityQueue.poll();
                map.remove(head.character);
                priorityQueue.add(charCount);
            }
        }
    }




        private static class CharCount implements Comparable<CharCount>{
        private Character character;
        private int count;

            private CharCount() {
            }

            public CharCount(Character character, int count) {
            if(character==null){
                throw new IllegalArgumentException("Invalid char");
            }
            this.character = character;
            if(count<=0){
                throw new IllegalArgumentException("Invalid Count");
            }
            this.count = count;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("CharCount{");
            sb.append("character=").append(character);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public int compareTo(CharCount o) {
            if(o==null){
                return 1;
            }else if(this==o){
                return 0;
            }else{
                int comp = this.count - o.count;
                if(comp==0){
                    return this.character.compareTo(o.character);
                }
                return comp;
            }
        }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                CharCount charCount = (CharCount) o;

                if (character != null ? !character.equals(charCount.character) : charCount.character != null)
                    return false;

                return true;
            }

            @Override
            public int hashCode() {
                return character != null ? character.hashCode() : 0;
            }
        }

    public static void main(String[] args) {
        System.out.println(returnLongest("ab",2));
        System.out.println(returnLongest("aaabbbbaaaaa"));

    }

}
