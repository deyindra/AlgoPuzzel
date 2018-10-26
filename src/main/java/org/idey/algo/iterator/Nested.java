package org.idey.algo.iterator;

import java.util.Iterator;

public class Nested<T> {
    private T object;
    private Iterator<Nested<T>> iterator;
    private boolean isObject;

    private Nested(T object, Iterator<Nested<T>> iterator, boolean isObject) {
        this.object = object;
        this.iterator = iterator;
        this.isObject = isObject;
    }

    public T getObject() {
        return object;
    }

    public Iterator<Nested<T>> getIterator() {
        return iterator;
    }

    public boolean isObject() {
        return isObject;
    }

    public static class NestedBuidler<T>{
        public Nested<T> withObject(T object){
            return new Nested<>(object, null, true);
        }

        public Nested<T> withIterator(Iterator<Nested<T>> iterator){
            return new Nested<>(null, iterator, false);
        }
    }
}
