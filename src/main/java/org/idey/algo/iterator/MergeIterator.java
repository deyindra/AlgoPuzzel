package org.idey.algo.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/**Purpose of this class is to merge multiple sorted collection in to a single sorted collection. This used k way merge
 * algorithm. Over all complexity is O(nk*logk) where n is number of element in each array and k is number of array
 * @author indranil dey
 * @param <T> Any generic type which extends {@link java.util.Comparator} interface
 * @see java.util.Iterator
 * @see java.lang.Iterable
 * @see java.util.Queue
 * @see java.util.PriorityQueue
 */
public class MergeIterator<T extends Comparable<T>> implements Iterator<T>,Iterable<T> {
    private Queue<Node<T>> q;
    private Iterator<T>[] arrayIterators;

    @SafeVarargs
    public MergeIterator(Iterator<T>... iterators) {
        assert(iterators!=null && iterators.length>0);
        this.arrayIterators = iterators;
        q = new PriorityQueue<>(iterators.length);
        int count=0;
        for(Iterator<T> it:iterators){
            if(it!=null && it.hasNext()){
                q.offer(new Node<>(it.next(),count));
            }
            count++;
        }
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }

    @Override
    public T next() {
        if(q.isEmpty()){
            throw new NoSuchElementException("No more element...");
        }
        Node<T> n = q.poll();
        T data = n.getData();
        int index = n.getIndex();
        Iterator<T> it = arrayIterators[index];
        if(it!=null && it.hasNext()){
            q.offer(new Node<>(it.next(),index));
        }
        return data;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    @Override
    public Iterator<T> iterator() {
        return this;
    }


    private class Node<E extends Comparable<E>> implements Comparable<Node<E>>{
        private E data;
        private int index;

        private Node(E data, int index) {
            this.data = data;
            this.index = index;
        }

        public E getData() {
            return data;
        }

        public int getIndex() {
            return index;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (data != null ? !data.equals(node.data) : node.data != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return data != null ? data.hashCode() : 0;
        }

        @SuppressWarnings({"ConstantConditions", "NullableProblems"})
        @Override
        public int compareTo(Node<E> tNode) {
            if(tNode==null){
                return 1;
            }else {
                if(this==tNode){
                    return 0;
                }else{
                    if(tNode.data==null && this.data==null){
                        return 0;
                    }else{
                        return (this.data!=null) ? this.data.compareTo(tNode.data) : tNode.data.compareTo(this.data);
                    }
                }
            }
        }
    }
}