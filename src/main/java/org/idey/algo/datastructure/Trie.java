package org.idey.algo.datastructure;

import java.util.*;

public class Trie<T> {
    private TrieNode<T> root;

    public Trie() {
        this.root = new TrieNode<>();
    }

    public void insert(String word, T object){
        TrieNode<T> node = root;
        char[] array = word.toCharArray();
        for(char ch:array){
            if(!node.containsKey(ch)){
                node.put(ch, new TrieNode<>());
            }
            node = node.get(ch);
        }
        node.setEnd();
        node.setValue(object);
    }
    // Returns if the word is in the trie.
    public T search(String word) {
        TrieNode<T> node = searchPrefix(word);
        if(node != null && node.isEnd()){
            return node.getValue();
        }else{
            return null;
        }
    }

    public Map<String, T> words(){
        Map<String, T> map = new LinkedHashMap<>();
        populateList(root,map,"");
        return map;
    }

    public Map<String, T>  words(String prefix){
        TrieNode<T> node = searchPrefix(prefix);
        Map<String, T>  map = new LinkedHashMap<>();
        populateList(node,map,prefix);
        return map;
    }

    private TrieNode<T> searchPrefix(String word) {
        TrieNode<T> node = root;
        char[] array = word.toCharArray();
        for (char curLetter:array) {
            if (node.containsKey(curLetter)) {
                node = node.get(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    private void populateList(TrieNode<T> node, Map<String, T> map, String prefix){
        if(node.isEnd()){
            map.put(prefix, node.getValue());
        }
        TrieNode<T>[] array = node.getArray();
        for(int i=0;i<array.length;i++){
            if(array[i]!=null){
                populateList(array[i],map,prefix+(char)(i));
            }
        }
    }

    private static class TrieNode<T>{
        private boolean isEnd;
        private Optional<T> value;
        private final TrieNode<T>[] array;

        @SuppressWarnings("unchecked")
        public TrieNode() {
            this.array = new TrieNode[256];
            value = Optional.empty();
        }

        public TrieNode<T>[] getArray() {
            return array;
        }

        public boolean containsKey(char ch) {
            return array[ch] != null;
        }
        public TrieNode<T> get(char ch) {
            return array[ch];
        }
        public void put(char ch, TrieNode<T> node) {
            array[ch] = node;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }

        public T getValue() {
            return (value.orElse(null));
        }

        public void setValue(T value) {
            this.value = Optional.of(value);
        }


    }

    public static void main(String[] args) {
        Trie<String> trie = new Trie<>();
        trie.insert("John", "666-191-222");
        trie.insert("Jones","555-666-789");
        trie.insert("Joe","123-456-789");
        trie.insert("Josh","555-555-1345");
        trie.insert("Joshua","555-555-1345");
        trie.insert("Bob","123-456-7899");

        System.out.println(trie.search("Jo"));
        System.out.println(trie.words());
        System.out.println(trie.words("Josh"));
    }
}
