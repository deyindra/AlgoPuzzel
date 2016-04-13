package org.idey.algo.iterator.graph;

import org.idey.algo.datastructure.graph.Graph;

import java.util.*;

public class BreadthFirstIterator<T> implements Iterator<T> {
    private Set<T> visited = new HashSet<>();
    private Queue<T> queue = new LinkedList<>();
    private Graph<T> graph;

    public BreadthFirstIterator(Graph<T> g, T startingVertex) {
        if(g.isVertexExist(startingVertex)) {
            this.graph = g;
            this.queue.add(startingVertex);
            this.visited.add(startingVertex);
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
        return !this.queue.isEmpty();
    }

    @Override
    public T next() {
        if(!hasNext())
            throw new NoSuchElementException();
        //removes from front of queue
        T next = queue.remove();
        for (T neighbor : this.graph.getNeighbors(next)) {
            if (!this.visited.contains(neighbor)) {
                this.queue.add(neighbor);
                this.visited.add(neighbor);
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(4,1);
        graph.addEdge(5,null);


       BreadthFirstIterator<Integer> bfs = new BreadthFirstIterator<>(graph,5);
        while (bfs.hasNext()){
            System.out.println(bfs.next());
        }
    }
}

