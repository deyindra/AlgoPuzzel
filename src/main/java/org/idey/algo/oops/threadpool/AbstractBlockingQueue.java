package org.idey.algo.oops.threadpool;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractBlockingQueue<T> implements BlockingQueue<T> {
    protected ReentrantLock lock;
    protected Condition notEmpty;
    protected Condition notFull;
    protected Queue<T> deque;

    protected AbstractBlockingQueue() {
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    @Override
    public T dequeue() throws InterruptedException {
        lock.lockInterruptibly();
        try{
            while (deque.isEmpty()){
                notEmpty.await();
            }
            T obj = deque.poll();
            notFull.signalAll();
            return obj;
        }finally {
            lock.unlock();
        }
    }
}
