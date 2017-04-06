package org.idey.algo.thread;

import java.util.concurrent.TimeoutException;

public class OrderThreadExecuter {
    private CustomThreadPool threadPool = CustomThreadPool.getInstance();


    public <T> void execute(ThreadPrinter<T>... array){
        if(array==null || array.length==0){
            throw new IllegalArgumentException("Invalid argument");
        }

        if(array.length>1){
            array[0].parent = array[array.length-1];
            for(int i=1;i<array.length;i++){
                array[i].parent = array[i-1];
            }
        }
        for(ThreadPrinter<T> t:array){
            threadPool.execute(t);
        }
    }

    public void shutdown() throws TimeoutException {
        threadPool.stop(true); // Disable new tasks from being submitted
        threadPool.awaitTermination(1000);

    }


    public static class ThreadPrinter<T> implements Runnable{
        private T object;
        private ThreadPrinter<T> parent;


        public ThreadPrinter(T object) {
            this.object = object;
        }


        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(object);
                    synchronized (this) {
                        notify();
                    }
                    if(parent !=null) {
                        synchronized (parent) {
                            parent.wait();
                        }
                    }
                } catch (InterruptedException | IllegalMonitorStateException e) {
                  System.out.println("Thread Interruppted");
                }
            }
        }
    }

    public static void main(String[] args) throws TimeoutException {
        OrderThreadExecuter executer = new OrderThreadExecuter();
        executer.execute(new ThreadPrinter<>(1), new ThreadPrinter<>(2));
        executer.shutdown();
    }

}
