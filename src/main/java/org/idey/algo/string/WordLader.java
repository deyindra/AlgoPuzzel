package org.idey.algo.string;

import java.util.*;

public class WordLader {

    public static int countMinSteps(String start, String end, Set<String> dict){
        LinkedList<WordCount> queue = new LinkedList<>();
        queue.offer(new WordCount(start,1));

        dict.add(end);

        while (!queue.isEmpty()){
            WordCount top = queue.poll();
            String word = top.word;
            if(word.equals(end)){
                return top.steps;
            }
            char[] array = word.toCharArray();
            for(int i=0;i<array.length;i++){
                for(char ch='a';ch<='z';ch++){
                    char temp = array[i];
                    if(array[i]!=ch){
                        array[i] = ch;
                    }
                    String newWord = new String(array);
                    if(dict.contains(newWord)){
                        queue.add(new WordCount(newWord, top.steps+1));
                        dict.remove(newWord);
                    }

                    array[i]=temp;
                }
            }
        }
        return 0;
    }


    private static class WordCount {
        private String word;
        private int steps;

        public WordCount(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hot","dot","dog","lot","log");
        Set<String> dict = new HashSet<>();
        dict.addAll(list);
        System.out.println(WordLader.countMinSteps("hit", "cog", dict));
    }
}
