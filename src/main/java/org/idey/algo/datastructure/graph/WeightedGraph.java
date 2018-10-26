package org.idey.algo.datastructure.graph;



import java.util.*;

@SuppressWarnings("ALL")
public class WeightedGraph<T, E extends Number & Comparable<E>> {
    private Map<T, Set<Edge<T, E>>> map = new HashMap<>();
    private boolean isDirected;


    public WeightedGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public WeightedGraph<T,E> addNode(T node){
        if(!map.containsKey(node)){
            map.put(node,new HashSet<>());
        }else{
            throw new IllegalArgumentException("Node already exists");
        }
        return this;
    }

    public WeightedGraph<T,E> addEdge(T src, T dst, E weight, Optional<E> reverseWeight){
        Set<Edge<T,E>> edges = map.computeIfAbsent(src, k->new HashSet<>());
        edges.add(new Edge<>(src,dst,weight));

        if(!isDirected){
            assert(reverseWeight.isPresent());
            Set<Edge<T,E>> reverseEdge = map.computeIfAbsent(dst, k->new HashSet<>());
            reverseEdge.add(new Edge<>(dst,src,reverseWeight.get()));
        }
        return this;
    }






    public Iterable<Edge<T,E>> getNeighbours(T src){
        if(map.containsKey(src)){
            return map.get(src);
        }else{
            throw new IllegalArgumentException("Invalid Source");
        }
    }




    public Map<T, NodeWrapper<T,E>> getAllShortestPathBetweenFromStartNode(final T src,WeightInterface<E> weightCalculator){
        if(!map.containsKey(src)){
            throw new IllegalArgumentException("Invalid Source and distanation node");
        }
        Map<T, NodeWrapper<T,E>> resultMap = new LinkedHashMap<>();
        NodeWrapper<T,E> startNodeWrapper = new NodeWrapper<>(src,weightCalculator.initialValue());
        resultMap.put(startNodeWrapper.node,startNodeWrapper);

        PriorityQueue<NodeWrapper<T,E>> pq = new PriorityQueue<>();
        Set<T> visited = new HashSet<>();
        pq.offer(startNodeWrapper);
        visited.add(startNodeWrapper.node);

        while (!pq.isEmpty()){
            NodeWrapper<T,E> actualNode = pq.poll();
            for(Edge<T,E> edge:getNeighbours(actualNode.node)){
                T destination = edge.dst;
                if(!visited.contains(destination)){
                    NodeWrapper<T,E> childNodeWrapper = new NodeWrapper<>(destination,weightCalculator.minValue());
                    E distance = weightCalculator.newValue(actualNode.getDistance(),edge.weight);
                    if(distance.compareTo(childNodeWrapper.distance)<0){
                        pq.remove(childNodeWrapper);
                        childNodeWrapper.setDistance(distance);
                        childNodeWrapper.setPredecessors(actualNode);
                        resultMap.put(childNodeWrapper.node,childNodeWrapper);
                        pq.offer(childNodeWrapper);
                    }
                }
            }
            visited.add(actualNode.node);
        }
        return resultMap;
    }

    public NodeWrapper<T,E> getShortestPathBetweenTwoNodes(final T src,final T dst, WeightInterface<E> weightCalculator){
        if(!map.containsKey(src)){
            throw new IllegalArgumentException("Invalid Source and distanation node");
        }
        NodeWrapper<T,E> result = null;
        NodeWrapper<T,E> startNodeWrapper = new NodeWrapper<>(src,weightCalculator.initialValue());

        PriorityQueue<NodeWrapper<T,E>> pq = new PriorityQueue<>();
        Set<T> visited = new HashSet<>();
        pq.offer(startNodeWrapper);
        visited.add(startNodeWrapper.node);

        while (!pq.isEmpty()){
            NodeWrapper<T,E> actualNode = pq.poll();
            for(Edge<T,E> edge:getNeighbours(actualNode.node)){
                T destination = edge.dst;
                if(!visited.contains(destination)){
                    NodeWrapper<T,E> childNodeWrapper = new NodeWrapper<>(destination,weightCalculator.minValue());
                    E distance = weightCalculator.newValue(actualNode.getDistance(),edge.weight);
                    if(distance.compareTo(childNodeWrapper.distance)<0){
                        pq.remove(childNodeWrapper);
                        childNodeWrapper.setDistance(distance);
                        childNodeWrapper.setPredecessors(actualNode);
                        pq.offer(childNodeWrapper);
                        if(childNodeWrapper.node.equals(dst)){
                            result = childNodeWrapper;
                        }
                    }
                }
            }
            visited.add(actualNode.node);
        }
        return result;
    }



    public static interface WeightInterface<E extends Number>{
        E minValue();
        E initialValue();
        E newValue(E e1, E e2);
    }

    private static class Edge<T, E extends Number & Comparable<E>>{
        private final T src;
        private final T dst;
        private final E weight;


        private Edge(final T src, final T dst, final E weight) {
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(src, edge.src) &&
                    Objects.equals(dst, edge.dst);
        }

        @Override
        public int hashCode() {
            return Objects.hash(src, dst);
        }
    }

    public static class NodeWrapper<T, E extends Number & Comparable<E>> implements Comparable<NodeWrapper<T,E>> {
        private T node;
        private E distance;
        private NodeWrapper<T,E> predecessors;


        public NodeWrapper(T node, E distance) {
            this.node = node;
            this.distance = distance;
        }

        public E getDistance() {
            return distance;
        }

        public void setDistance(E distance) {
            assert(distance!=null);
            this.distance = distance;
        }

        public NodeWrapper<T, E> getPredecessors() {
            return predecessors;
        }

        public void setPredecessors(NodeWrapper<T, E> predecessors) {
            this.predecessors = predecessors;
        }

        public Deque<T> getAllPredecessors(){
            Deque<T> list = new LinkedList<>();
            for(NodeWrapper<T,E> wrapper=this;wrapper!=null;wrapper=wrapper.predecessors){
                list.addFirst(wrapper.node);
            }
            return list;
        }

        @Override
        public int compareTo(NodeWrapper<T, E> o) {
            if(o==null){
                return 1;
            }else if(this==o){
                return 0;
            }else{
                return this.distance.compareTo(o.distance);
            }
        }
    }

    public static void main(String[] args) {
        WeightedGraph<String, Double> g = new WeightedGraph<>(false);
        g.addNode("A").addNode("B").addNode("C").addNode("D").addNode("E");
        g.addEdge("A","B",10d,Optional.of(1/10d));
        g.addEdge("B","C",20d,Optional.of(1/20d));
        g.addEdge("C","D",10d,Optional.of(1/10d));
        g.addEdge("A","D",30d,Optional.of(1/30d));
        g.addEdge("D","E",40d,Optional.of(1/40d));
        g.addEdge("A","E",10d,Optional.of(1/10d));

        WeightInterface<Double> weightInterface = new WeightInterface<Double>() {
            @Override
            public Double minValue() {
                return Double.MAX_VALUE;
            }

            @Override
            public Double initialValue() {
                return 0d;
            }

            @Override
            public Double newValue(Double e1, Double e2) {
                return e1+e2;
            }
        };

        Map<String, NodeWrapper<String, Double>> map = g.getAllShortestPathBetweenFromStartNode("A",weightInterface);

        NodeWrapper<String, Double> D = map.get("D");
        System.out.println(D.distance);
        System.out.println(D.getAllPredecessors());

        NodeWrapper<String, Double> wrapper = g.getShortestPathBetweenTwoNodes("A","D", weightInterface);
        System.out.println(wrapper.distance);
        System.out.println(wrapper.getAllPredecessors());
    }



}
