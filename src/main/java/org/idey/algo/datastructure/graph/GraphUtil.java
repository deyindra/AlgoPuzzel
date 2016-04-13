package org.idey.algo.datastructure.graph;

import org.idey.algo.iterator.graph.DepthFirstSearchIterator;

import java.util.*;

public class GraphUtil {
    public static <T> List<List<T>> findAllPossiblePath(Graph<T> g, T source, T destination){
        List<List<T>> paths = new ArrayList<List<T>>();
        Set<T> visited = new LinkedHashSet<>();
        findAllPossiblePath(g, source, destination, paths, new LinkedList<T>(), visited);
        return paths;
    }


    private static <T> void findAllPossiblePath (Graph<T> g, T current, final T destination, List<List<T>> paths,
                                                 LinkedList<T> path, Set<T> visited) {

        if(current==null){
            return;
        }
        path.add(current);
        visited.add(current);
        if (current.equals(destination)) {
            paths.add(new ArrayList<T>(path));
        }else{
            final Iterable<T> edges  = g.getNeighbors(current);

            for (T t : edges) {
                if (!visited.contains(t)) {
                    findAllPossiblePath(g, t, destination, paths, path,visited);
                }
            }
        }
        path.removeLast();
        visited.remove(current);
    }


   public static <T> boolean isPathExists(Graph<T> g, T current, final T destination){
       Iterator<T> depthFirstIterator = new DepthFirstSearchIterator<>(g,current);
       while (depthFirstIterator.hasNext()){
           T vertiex = depthFirstIterator.next();
           if(vertiex.equals(destination)){
               return true;
           }
       }
       return false;
   }




    public interface Cloneable<T>{
        T newObject(T object);
    }



    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(1,4);
        graph.addEdge(4,1);
        graph.addEdge(3,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(5,1);
        graph.addEdge(6,7);



        graph.addEdge(5,null);

        List<List<Integer>> lists = findAllPossiblePath(graph,3,5);
        System.out.println(lists);

        System.out.println(isPathExists(graph,3,5));
    }
}
