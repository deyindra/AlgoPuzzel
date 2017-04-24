package org.idey.algo.thread.semaphore;

public class BoundedSemaphore implements Semaphore {
    private int signals = 0;
    private final int bound;

    public BoundedSemaphore(int upperBound){
        this.bound = upperBound;
    }

    public synchronized void take(){
        while(this.signals == bound){
           try {
               wait();
           }catch (InterruptedException ex){
               throw new RuntimeException(ex);
           }
        }
        this.signals++;
        this.notifyAll();
    }

    public synchronized void release(){
        while(this.signals == 0){
            try{
                wait();
            }catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        }
        this.signals--;
    }
}
