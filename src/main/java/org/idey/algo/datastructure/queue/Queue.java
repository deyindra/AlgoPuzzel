package org.idey.algo.datastructure.queue;

public interface Queue<T> {
    void offer(T object);
    T poll();
    int size();
    boolean isEmpty();
}
