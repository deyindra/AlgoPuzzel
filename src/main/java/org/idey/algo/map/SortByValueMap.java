package org.idey.algo.map;

import java.util.*;

public class SortByValueMap<K extends Comparable<K>, V extends Comparable<V>> {
    private Map<K,V> map;

    public SortByValueMap(Map<K, V> map) {
        this.map = map;
    }

    public Map<K, V> getMap() {
        SortedMap<K,V> map = new TreeMap<>(new ValueComparator<K,V>(this.map));
        map.putAll(this.map);
        return map;
    }

    public static class ValueComparator<K,V extends Comparable<V>> implements Comparator<K>{
        private Map<K,V> map;

        private ValueComparator(Map<K, V> map) {
            this.map = map;
        }

        @Override
        public int compare(K o1, K o2) {
            V v1 = map.get(o1);
            V v2 = map.get(o2);
            if(v1 == null && v2 == null){
                return 0;
            }else if(v1==v2){
                return 0;
            }else if(v1==null){
                return 1;
            }else{
                return -v1.compareTo(v2);
            }
        }
    }


    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A",5);
        map.put("B",1);
        map.put("C",2);
        System.out.println(new SortByValueMap<>(map).getMap());
    }


}
