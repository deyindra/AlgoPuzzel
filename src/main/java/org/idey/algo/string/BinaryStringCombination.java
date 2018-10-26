package org.idey.algo.string;

import java.util.ArrayList;
import java.util.List;


public class BinaryStringCombination {
    private List<String> stringList;

    public BinaryStringCombination(int bits) {
        int size = 1<< bits;
        stringList = new ArrayList<String>(size);
        for (int val = 0; val < size; val++) {
            StringBuilder builder= new StringBuilder("");
            for (int b = bits-1; b >=0; b--) {
                if ((val & (1<<b) ) != 0) {
                    builder.append("1");
                }else{
                    builder.append("0");
                }
            }
            stringList.add(builder.toString());
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BinaryStringCombination{");
        sb.append("stringList=").append(stringList);
        sb.append('}');
        return sb.toString();
    }


    public static void main(String[] args){
        System.out.println(new BinaryStringCombination(10));
    }
}
