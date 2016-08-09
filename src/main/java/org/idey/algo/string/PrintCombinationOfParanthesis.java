package org.idey.algo.string;

import java.util.ArrayList;
import java.util.List;

public class PrintCombinationOfParanthesis {
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(result, "", n, n);
        return result;
    }

    public static void dfs(List<String> result, String s, int left, int right){
        if(left > right)
            return;

        if(left==0&&right==0){
            result.add(s);
            return;
        }

        if(left>0){
            dfs(result, s+"(", left-1, right);
        }

        if(right>0){
            dfs(result, s+")", left, right-1);
        }
    }

    public static void main(String[] args) {
        List<String> list = generateParenthesis(2);
        System.out.println(list);
    }
}
