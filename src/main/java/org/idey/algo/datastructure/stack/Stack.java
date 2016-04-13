package org.idey.algo.datastructure.stack;

public interface Stack<T> {
    void push(T object);
    T pop();
    int size();
    boolean isEmpty();
}
