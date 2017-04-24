package org.idey.algo.thread.semaphore;

public class CountingSemaphore implements Semaphore {
    private int signals = 0;

    public synchronized void take() {
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
