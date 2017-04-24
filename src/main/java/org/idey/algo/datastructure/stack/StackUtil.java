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
        boolean isPop = false;
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
                        if(!isPop){
                            isPop= true;
                        }else{
                            System.out.println("Uncessary paranthesis");
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else{
                isPop = false;
            }
        }
        return stack.empty();
    }

    public static int findValidParentheses(String s, int start, int end, int step, char cc) {
        int maxLen = 0, count = 0, len = 0;
        for (int i=start; i!=end; i+=step) {
            if (s.charAt(i)==cc) {
                ++count;
            } else {
                if (count>0) {
                    // exist a matching
                    --count;
                    len += 2;
                    if (count==0) maxLen = Math.max(maxLen, len);
                } else {
                    // no matching
                    len = 0;
                }
            }
        }
        return maxLen;
    }

    public static int longestValidParenthesis2(String str){
        return Math.max(findValidParentheses(str, 0, str.length(), 1, '('),
                findValidParentheses(str, str.length()-1, -1, -1, ')'));
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
//        System.out.println(checkBalancedParantheSis("{((daD))asaS}"));
//        System.out.println(simplyUnixPath("/../a/../b/"));
        System.out.println(StackUtil.longestValidParenthesis2("((())"));
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
