package org.idey.algo.thread;


import java.util.LinkedList;

public class Boundqueue<T> {
    private int size;
    private LinkedList<T> queue;

    public Boundqueue(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(T item)
            throws InterruptedException  {
        while(this.queue.size() == this.size) {
            wait();
        }
        this.queue.add(item);
        notifyAll();
    }


    public synchronized T dequeue()
            throws InterruptedException{
        while(this.queue.size() == 0){
            wait();
        }
        T object = this.queue.remove(0);
        notifyAll();
        return object;
    }

    private static class Producer implements Runnable{
        private Boundqueue<String> boundqueue;

        public Producer(Boundqueue<String> boundqueue) {
            this.boundqueue = boundqueue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Producer...");
                    boundqueue.enqueue("Abc");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class Consumer implements Runnable{
        private Boundqueue<String> boundqueue;

        public Consumer(Boundqueue<String> boundqueue) {
            this.boundqueue = boundqueue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("Consumer...");
                    System.out.println(boundqueue.dequeue());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Boundqueue<String> boundqueue = new Boundqueue<>(5);
        Thread p1 = new Thread(new Producer(boundqueue));
        Thread p2 = new Thread(new Producer(boundqueue));
        Thread c1 = new Thread(new Consumer(boundqueue));
        Thread c2 = new Thread(new Consumer(boundqueue));

        p1.start();

        p2.start();

        c1.start();

        c2.start();

    }

}
