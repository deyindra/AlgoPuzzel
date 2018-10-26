package org.idey.algo.datastructure.queue;

public class RotatingQueue<T> implements Queue<T>{
    private T[] array;
    //0 based index
    private int start;
    private int end;
    private int size;

    @SuppressWarnings("unchecked")
    public RotatingQueue(int size) {
        if(size<=0){
            throw new IllegalArgumentException("Invalid Size");
        }
        array = (T[])new Object[size];
        start=0;
        end=0;
        this.size = 0;
    }

    @Override
    public void offer(T object) {
        if(size == array.length){
            throw new IllegalArgumentException("Queue is full");
        }
        array[end%array.length] = object;
        end = (end+1)%array.length;
        size++;
    }

    @Override
    public T poll() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }
        T object = array[start%array.length];
        array[start%array.length] = null;
        start = (start+1)%array.length;
        size--;
        if(start==end){
            start=0;
            end = 0;
        }
        return object;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public static void main(String[] args) {
        RotatingQueue<Integer> queue = new RotatingQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.poll());
//        queue.offer(4);
//        queue.offer(5);
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}
