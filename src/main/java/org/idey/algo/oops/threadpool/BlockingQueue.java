package org.idey.algo.oops.threadpool;

public interface BlockingQueue<T> {
    void enqueue(T obj);
    T dequeue() throws InterruptedException;
}
