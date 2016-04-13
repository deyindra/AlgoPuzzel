package org.idey.algo.string;

import java.util.HashMap;
import java.util.Map;

public class AnagramClass {
    private Map<Character, Integer> map = new HashMap<>();

    public AnagramClass(String str) {
        this.map = new HashMap<>();
        char[] array = str.toCharArray();
        for(char ch:array){
            Integer count = map.get(ch);
            if(count==null){
                count=1;
            }else{
                count=count+1;
            }
            map.put(ch, count);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnagramClass that = (AnagramClass) o;

        if (map != null ? !map.equals(that.map) : that.map != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return map != null ? map.hashCode() : 0;
    }
}
