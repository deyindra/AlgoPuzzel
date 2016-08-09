package org.idey.algo.string;

public class Permutation {
    public static void permute(String str){
        permute(str,"");
    }

    public static void permutation2(String prefix, String s, int k)
    {
        int N = prefix.length();
        int M = s.length();
        if(N == k)
            System.out.println(prefix);
        else
        {
            for(int i = 0; i < M; i++) {
                char ch = s.charAt(i);
                if(s.indexOf(ch, i+1)!=-1){
                    continue;
                }
                permutation2(prefix + ch, s.substring(0, i) + s.substring(i + 1, M), k);
            }
        }
    }




    private static void permute(String input, String sofar){
        if(input.equals("")){
            System.out.println(sofar);
        }
        for(int i=0;i<input.length();i++){
            char ch = input.charAt(i);
            if(input.indexOf(ch, i+1)!=-1){
                continue;
            }
            permute(input.substring(0,i)+input.substring(i+1), sofar+ch);
        }
    }

    public static void main(String[] args) {
        String abc ="CBA";
        permutation2("",abc,3);
    }

}
