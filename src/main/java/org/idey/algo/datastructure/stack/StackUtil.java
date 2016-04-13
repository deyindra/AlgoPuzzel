package org.idey.algo.datastructure.stack;

import java.util.*;
import java.util.Stack;

public class StackUtil<T> {
    public static <T extends Comparable<T>> void sort(java.util.Stack<T> stack){
        T object = stack.pop();
        if(stack.size()!=1){
            sort(stack);
        }
        insert(stack,object);
    }

    public static <T extends Comparable<T>>  void insert(java.util.Stack<T> stack, T object){
        if(stack.size()==0){
            stack.push(object);
        }else{
            T topObject = stack.peek();
            if(object.compareTo(topObject)>=0){
                stack.push(object);
            }else{
                topObject = stack.pop();
                insert(stack,object);
                stack.push(topObject);
            }

        }
    }

    //O(n) time with O(1) constant space
    public static boolean checkBalancedParantheSis(String str){
        Map<Character, Character> mapping = new HashMap<>();
        mapping.put(')','(');
        mapping.put('}','{');
        mapping.put(']','[');

        Set<Character> values = new HashSet<>(mapping.values());

        char[] array = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char ch:array){
            if(values.contains(ch)){
                stack.push(ch);
            }else if(mapping.containsKey(ch)){
                if(!stack.isEmpty()) {
                    char value = mapping.get(ch);
                    char stackValue = stack.peek();
                    if (value == stackValue) {
                        stack.pop();
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        return stack.empty();
    }


    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(10);
        s.push(2);
        s.push(11);
        sort(s);
        while(!s.empty()){
            System.out.println(s.pop());
        }
        System.out.println(checkBalancedParantheSis("[[()]]["));
    }


}
