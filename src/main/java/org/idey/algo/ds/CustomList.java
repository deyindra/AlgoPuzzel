package org.idey.algo.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Custom List which will provide add, contains, delete, random in O(1) time
 */
public class CustomList<T> {
    private Map<T, Value<T>> map = new HashMap<T, Value<T>>();
    private List<Value<T>> list = new ArrayList<>();
    private static final Random RANDOM = new Random();

    public boolean add(T object){
        Value<T> value = map.get(object);
        //This mean key does not exists
        if(value==null){
            value = new Value<>(object,list.size());
            //O(1)
            map.put(object,value);
            //Amortize Operarion O(1)
            list.add(value);
            return true;
        }
        return false;
    }

    public boolean contains(T object){
        return map.containsKey(object);
    }

    public void delete(T obj){
        //O(1)
        Value v = map.remove(obj);
        if(v != null){
            int index = v.index;
            Value<T> lastValue = list.get(list.size()-1);
            lastValue.index = index;
            list.set(index,lastValue);
            list.remove(list.size()-1);
        }
    }


    public T getRandom(){
        int index = RANDOM.nextInt(list.size());
        return list.get(index).object;
    }


    private class Value<E>{
        private E object;
        private int index;
        public Value(E object, int index) {
            this.object = object;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "object=" + object +
                    ", index=" + index +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CustomList{" +
                "map=" + map +
                ", list=" + list +
                '}';
    }

    public static void main(String[] args) {
        CustomList<Integer> customList = new CustomList<>();
        customList.add(1);
        customList.add(2);
        customList.add(3);
        customList.add(4);
        customList.delete(2);

        System.out.println(customList);

    }
}
