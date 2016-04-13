package org.idey.algo.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseGrammer {
    public static String[] split(String str, char delimeter){
        char[] array = str.toCharArray();
        int index=0;
        List<String> list = new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        boolean mapIndexFound=false;
        boolean arrayIndexFound=false;
        while(index<array.length){
            char ch=array[index++];
            if(ch!=delimeter){
                switch (ch){
                    case '{' :mapIndexFound=true;
                        break;
                    case '}' :mapIndexFound=false;
                        break;
                    case '[' :arrayIndexFound=true;
                        break;
                    case ']' :arrayIndexFound=false;
                        break;
                    default:
                        sb.append(ch);
                        if(index==array.length){
                            list.add(sb.toString());
                        }
                        break;

                }
            }else{
                if(!mapIndexFound && !arrayIndexFound) {
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }else{
                    sb.append(ch);
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(split("abc.efg.xyz{a.abc.xyz}.mcc", '.')));
    }

}
