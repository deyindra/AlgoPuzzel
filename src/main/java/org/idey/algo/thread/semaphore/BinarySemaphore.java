package org.idey.algo.thread.semaphore;

public class BinarySemaphore implements Semaphore {
    private boolean signal = false;

    public synchronized void take() {
        this.signal = true;
        this.notifyAll();
    }

    public synchronized void release(){
        while(!this.signal){
            try{
                wait();
            }catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        }
        this.signal = false;
    }
}
