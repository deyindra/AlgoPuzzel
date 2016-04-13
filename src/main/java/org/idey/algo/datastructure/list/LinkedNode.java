package org.idey.algo.datastructure.list;

public class LinkedNode<T extends Comparable<T>> implements Comparable<LinkedNode<T>>{
    private T object;
    private LinkedNode<T> next;
    private LinkedNode<T> random;

    public LinkedNode(T object) {
        this.object = object;
        next=null;
        random=null;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    public LinkedNode<T> getRandom() {
        return random;
    }

    public void setRandom(LinkedNode<T> random) {
        this.random = random;
    }

    public T getObject() {
        return object;
    }

    @Override
    public String toString() {
        return object!=null ? object.toString() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedNode<?> that = (LinkedNode<?>) o;

        if (object != null ? !object.equals(that.object) : that.object != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return object != null ? object.hashCode() : 0;
    }

    public static class LinkedNodeWrapper<T extends Comparable<T>>{
        private LinkedNode<T> linkedNode;

        public LinkedNode<T> getLinkedNode() {
            return linkedNode;
        }

        public void setLinkedNode(LinkedNode<T> linkedNode) {
            this.linkedNode = linkedNode;
        }
    }

    @Override
    public int compareTo(LinkedNode<T> o) {
        if(o==null){
            return 1;
        }else{
            T data1 = this.object;
            T data2 = o.object;
            if(data1==null && data2==null){
                return 0;
            }else{
                if(data1!=null){
                    return data1.compareTo(data2);
                }else{
                    return -1;
                }
            }
        }
    }

    @Override
    protected LinkedNode<T> clone() throws CloneNotSupportedException {
        return new LinkedNode<>(this.object);
    }
}
