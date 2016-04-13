package org.idey.algo.iterator.graph;

import org.idey.algo.datastructure.graph.Graph;

import java.util.*;

public class DepthFirstSearchIterator<T>  implements Iterator<T> {
    private Set<T> visited = new HashSet<>();
    private Stack<Iterator<T>> stack = new Stack<>();
    private Graph<T> graph;
    private T next;

    public DepthFirstSearchIterator(Graph g, T startingVertex) {
        if(g.isVertexExist(startingVertex)) {
            this.stack.push(g.getNeighbors(startingVertex).iterator());
            this.graph = g;
            this.next = startingVertex;
        }else{
            throw new IllegalArgumentException("Vertext does not exits");
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return this.next != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            this.visited.add(this.next);
            return this.next;
        } finally {
            this.advance();
        }
    }

    private void advance() {
        Iterator<T> neighbors = this.stack.peek();
        do {
            while (!neighbors.hasNext()) {  // No more nodes -> back out a level
                this.stack.pop();
                if (this.stack.isEmpty()) { // All done!
                    this.next = null;
                    return;
                }
                neighbors = this.stack.peek();
            }

            this.next = neighbors.next();
        } while (this.visited.contains(this.next));
        this.stack.push(this.graph.getNeighbors(this.next).iterator());
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(4,1);
        graph.addEdge(5,null);


        DepthFirstSearchIterator<Integer> dfs = new DepthFirstSearchIterator<>(graph,1);
        while (dfs.hasNext()){
            System.out.println(dfs.next());
        }
    }
}
