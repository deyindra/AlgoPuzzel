package org.idey.algo.datastructure.cache;


import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    private int capacity;
    private Map<K, Node<K,V>> map = new HashMap<>();
    private DoubleLinkedList<K,V> doubleLinkedList=new DoubleLinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }


    public V get(K key) {
        if(map.containsKey(key)){
            Node<K,V> n = map.get(key);
            doubleLinkedList.remove(n);
            doubleLinkedList.setHead(n);
            return n.value;
        }
        return null;
    }

    public V remove(K key){
        if(map.containsKey(key)){
            Node<K,V> node = map.remove(key);
            doubleLinkedList.remove(node);
            return node.value;
        }
        return null;
    }


    public void set(K key, V value) {
        if(map.containsKey(key)){
            Node<K,V> old = map.get(key);
            old.value = value;
            doubleLinkedList.remove(old);
            doubleLinkedList.setHead(old);
        }else{
            Node<K,V> created = new Node<>(key, value);
            if(map.size()>=capacity){
                map.remove(doubleLinkedList.end.key);
                doubleLinkedList.remove(doubleLinkedList.end);
                doubleLinkedList.setHead(created);

            }else{
                doubleLinkedList.setHead(created);
            }
            map.put(key, created);
        }
    }


    private  class Node<K1,V1>{
        private K1 key;
        private V1 value;
        private Node<K1,V1> pre;
        private Node<K1,V1> next;

        private Node(K1 key, V1 value){
            this.key = key;
            this.value = value;
        }

    }

    private  class DoubleLinkedList<K2,V2>{
        private Node<K2,V2> head=null;
        private Node<K2,V2> end=null;

        public void remove(Node<K2,V2> n){
            if(n.pre!=null){
                n.pre.next = n.next;
            }else{
                head = n.next;
            }

            if(n.next!=null){
                n.next.pre = n.pre;
            }else{
                end = n.pre;
            }
        }


        public void setHead(Node<K2,V2> n){
            n.next = head;
            n.pre = null;

            if(head!=null)
                head.pre = n;
            head = n;
            if(end ==null)
                end = head;
        }

    }



}
