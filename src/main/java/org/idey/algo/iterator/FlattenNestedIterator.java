package org.idey.algo.iterator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class FlattenNestedIterator<T>  implements Iterator<T>{
    private Stack<Iterator<Nested<T>>> stack = new Stack<>();
    private T current;
    private boolean hasNext;
    public FlattenNestedIterator(Iterator<Nested<T>>  nestedIterator) {
        if(nestedIterator == null){
            throw new IllegalArgumentException("Invalid Iterator");
        }
        stack.push(nestedIterator);
        advance();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    private void advance(){
        while(!stack.isEmpty()){
            Iterator<Nested<T>> top = stack.peek();
            if(top==null || !top.hasNext()){
                stack.pop();
            }else{
                Nested<T> n = top.next();
                if (n.isObject()) {
                    current = n.getObject();
                    hasNext = true;
                    return;
                } else {
                    stack.push(n.getIterator());
                }
            }
        }
        hasNext =false;
    }

    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        T result = current;
        advance();
        return result;
    }


    public static void main(String[] args) {
        List<Nested<Integer>> nesteds = new ArrayList<>();
        nesteds.add(new Nested.NestedBuidler<Integer>().withObject(1));
        nesteds.add(new Nested.NestedBuidler<Integer>().withIterator(null));
        nesteds.add(new Nested.NestedBuidler<Integer>().withIterator(Arrays.asList(new Nested.NestedBuidler<Integer>().withObject(null),
                new Nested.NestedBuidler<Integer>().withObject(2),
                new Nested.NestedBuidler<Integer>().withObject(3)).iterator()));

        FlattenNestedIterator<Integer> it = new FlattenNestedIterator<>(nesteds.iterator());
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}
