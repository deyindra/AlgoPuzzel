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

    public static int findValidParentheses(String s) {
        int maxLen=0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        char[] array = s.toCharArray();
        for(int i=0;i<array.length;i++){
            if(array[i]=='('){
                stack.push(i);
            }else{
                stack.pop();
                if(!stack.isEmpty())
                    maxLen = Math.max(maxLen, i-stack.peek());
                else
                    stack.push(i);
            }
        }
        return maxLen;
    }


    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(10);
        s.push(2);
        s.push(11);
        sort(s);
//        while(!s.empty()){
//            System.out.println(s.pop());
//        }
        System.out.println(checkBalancedParantheSis("{{((daD))asaS}}"));
        System.out.println(checkBalancedParantheSis("{()(}"));

        System.out.println(simplyUnixPath("/../a/../b/"));
        System.out.println(StackUtil.findValidParentheses("()(())))"));
    }


    public static String simplyUnixPath(String str){
        if(str==null){
            throw new IllegalArgumentException("Invalid Path");
        }else{
            int length = str.length();
            if(length==0){
                return str;
            }else{
                String[] splits = str.split("[/]+");
                LinkedList<String> stack = new LinkedList<>();
                for (String s : splits) {
                    if(s.equals("/"))
                        continue;
                    if(s.equals("..")){
                        if(!stack.isEmpty())
                            stack.pop();
                    }else{
                        if(!s.equals(".")  && !s.equals(""))
                            stack.push(s);
                    }
                }

                if(stack.isEmpty()) return "/";
                String ret = "";
                while(!stack.isEmpty()){
                    ret += "/" + stack.removeLast();
                }
                return ret;
            }
        }
    }

    public static int findMaxDepth(String str){
        int currentMax=0;
        int max=0;
        if(str==null || str.length()==0){
            throw new IllegalArgumentException("Invalid string");
        }
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='('){
                currentMax++;
                if(currentMax>max){
                    max=currentMax;
                }
            }else if(str.charAt(i)==')'){
                if(currentMax>0)
                    currentMax--;
                else{
                    throw new IllegalArgumentException("Paranthesis is not balanced");
                }
            }
        }

        if(currentMax!=0){
            throw new IllegalArgumentException("Paranthesis is not balanced");
        }
        return max;
    }

}
