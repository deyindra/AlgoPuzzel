package org.idey.algo.datastructure.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class LFUCache<K,V> {
    private Map<K, Node<K,V>> map = new HashMap<>();
    private Map<K, Integer> counts = new HashMap<>();
    private SortedMap<Integer, DLL<K,V>> frequencies = new TreeMap<>();
    private int capacity;

    public LFUCache(int capacity) {
        assert(capacity>=1);
        this.capacity = capacity;
    }

    public V get(K key){
        Node<K,V> node = map.get(key);
        if(node!=null){
            int frequency = counts.get(key);
            frequencies.get(frequency).remove(node);
            removeIfListEmpty(frequency);
            frequencies.computeIfAbsent(frequency + 1, k -> new DLL<>()).setHead(node);
            counts.put(key, frequency + 1);
            return map.get(key).value;
        }
        return null;
    }


    public V remove(K key){
        Node<K,V> node = map.remove(key);
        if(node!=null){
            int frequency = counts.remove(key);
            frequencies.get(frequency).remove(node);
            removeIfListEmpty(frequency);
            return node.value;
        }
        return null;
    }

    public void put(K key, V value){
        Node<K,V> oldNode = map.get(key);
        if(oldNode!=null){
            oldNode.value = value;
            int frequency = counts.get(key);
            frequencies.get(frequency).remove(oldNode);
            removeIfListEmpty(frequency);
            frequencies.computeIfAbsent(frequency + 1, k -> new DLL<>()).setHead(oldNode);
            counts.put(key, frequency + 1);
        }else{
            int size = map.size();

            if(size==capacity) {
                int lowestFrequency = frequencies.firstKey();
                Node<K,V> lastNode = frequencies.get(lowestFrequency).end;
                counts.remove(lastNode.key);
                map.remove(lastNode.key);
                frequencies.get(lowestFrequency).remove(lastNode);
                removeIfListEmpty(lowestFrequency);
            }
            Node<K,V> newNode = new Node<>(key,value);
            map.put(key, newNode);
            counts.put(key,1);
            frequencies.computeIfAbsent(1, k->new DLL<>()).setHead(newNode);
        }
    }



    private void removeIfListEmpty(int frequency) {
        if (frequencies.get(frequency).getSize() == 0) {
            frequencies.remove(frequency);  // remove from map if list is empty
        }
    }


    private static class Node<K,V>{
        private K key;
        private V value;
        private Node<K,V> pre;
        private Node<K,V> next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static class DLL<K,V>{
        private Node<K,V> head;
        private Node<K,V> end;
        private int size;

        private void remove(Node<K,V> n){
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
            size--;
        }


        private void setHead(Node<K,V> n){
            n.next = head;
            n.pre = null;

            if(head!=null)
                head.pre = n;
            head = n;
            if(end ==null)
                end = head;
            size++;
        }

        private int getSize() {
            return size;
        }
    }


}
