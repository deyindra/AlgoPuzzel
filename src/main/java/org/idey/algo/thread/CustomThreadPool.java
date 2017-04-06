package org.idey.algo.thread;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomThreadPool {
    private ConcurrentLinkedQueue<Runnable> runnables;
    private AtomicBoolean execute;
    private List<SimpleThreadpoolThread> workerThreads;

    private class ThreadpoolException extends RuntimeException {
        public ThreadpoolException(Throwable cause) {
            super(cause);
        }
    }


    private class SimpleThreadpoolThread extends Thread {
        private AtomicBoolean execute;
        private ConcurrentLinkedQueue<Runnable> runnables;

        private SimpleThreadpoolThread(String name,
                                       AtomicBoolean execute, ConcurrentLinkedQueue<Runnable> runnables) {
            super(name);
            this.execute = execute;
            this.runnables = runnables;
        }



        @Override
        public void run() {
            try {
                // Continue to execute when the execute flag is true, or when there are runnables in the queue
                while (execute.get() || !runnables.isEmpty()) {
                    Runnable runnable;
                    // Poll a runnable from the queue and execute it
                    while ((runnable = runnables.poll()) != null) {
                        runnable.run();
                    }
                    // Sleep in case there wasn't any runnable in the queue. This helps to avoid hogging the CPU.
                    Thread.sleep(1);
                }
            } catch (RuntimeException | InterruptedException e) {
                throw new ThreadpoolException(e);
            }
        }
    }

    private CustomThreadPool(int threadCount) {
        // Increment pool count
        this.runnables = new ConcurrentLinkedQueue<>();
        this.execute = new AtomicBoolean(true);
        this.workerThreads = new ArrayList<>();
        for (int threadIndex = 0; threadIndex < threadCount; threadIndex++) {
            SimpleThreadpoolThread thread = new SimpleThreadpoolThread("SimpleThreadpool" + threadIndex + "Thread" + threadIndex, this.execute, this.runnables);
            thread.start();
            this.workerThreads.add(thread);
        }
    }

    public static CustomThreadPool getInstance() {
        return getInstance(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Gets a new threadpool instance with the number of threads specified
     *
     * @param threadCount Threads to add to the pool
     * @return new SimpleThreadpool
     */
    public static CustomThreadPool getInstance(int threadCount) {
        return new CustomThreadPool(threadCount);
    }

    public void execute(Runnable runnable) {
        if (this.execute.get()) {
            runnables.add(runnable);
        } else {
            throw new IllegalStateException("Threadpool terminating, unable to execute runnable");
        }
    }

    public void awaitTermination(long timeout) throws TimeoutException {
        if (this.execute.get()) {
            throw new IllegalStateException("Threadpool not terminated before awaiting termination");
        }
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime <= timeout) {
            boolean flag = true;
            for (Thread thread : workerThreads) {
                if (thread.isAlive()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new ThreadpoolException(e);
            }
        }
        throw new TimeoutException("Unable to terminate threadpool within the specified timeout (" + timeout + "ms)");
    }


    public void awaitTermination() throws TimeoutException {
        if (this.execute.get()) {
            throw new IllegalStateException("Threadpool not terminated before awaiting termination");
        }
        while (true) {
            boolean flag = true;
            for (Thread thread : workerThreads) {
                if (thread.isAlive()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return;
            }
        }
    }


    public void terminate() {
        runnables.clear();
        stop(false);
    }


    public void stop(boolean isInterrupted) {
        execute.set(false);
        if(isInterrupted){
            for(Thread w : workerThreads){
               w.interrupt();
            }
        }
    }


}
