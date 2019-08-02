package com.github.thealgorithmusc.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class WeightedGraph<V> extends AbstractGraph<V> {

    private List<PriorityQueue<WeightedEdge>> queues;

    /**
     * Construct a WeightedGraph from edges and vertices in arrays
     *
     * @param edges
     * @param vertices
     */
    public WeightedGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
        createQueues(edges, vertices.length);
    }

    /**
     * Construct a WeightedGraph from edges and vertices in List
     *
     * @param edges
     * @param vertices
     */
    public WeightedGraph(List<WeightedEdge> edges, List<V> vertices) {
        super((List) edges, vertices);
        createQueues(edges, vertices.size());
    }

    /**
     * Construct a WeightedGraph from vertices 0, 1, 2 ... and edge array
     *
     * @param edges
     * @param numberOfVertices
     */
    public WeightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
        createQueues(edges, numberOfVertices);
    }

    /**
     * Construct a WeightedGraph from vertices 0, 1, 2 ... and edge list
     *
     * @param edges
     * @param numberOfVertices
     */
    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        super((List) edges, numberOfVertices);
        createQueues(edges, numberOfVertices);
    }

    /**
     * Create priority adjacency lists from edge arrays
     *
     * @param edges
     * @param numberOfVertices
     */
    private void createQueues(int[][] edges, int numberOfVertices) {
        this.queues = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new PriorityQueue<WeightedEdge>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int weight = edges[i][2];
            queues.get(u).add(new WeightedEdge(u, v, weight));
        }
    }

    /**
     * Create priority adjacency lists from edge list
     *
     * @param edges
     * @param numberOfVertices
     */
    private void createQueues(List<WeightedEdge> edges, int numberOfVertices) {
        this.queues = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            queues.add(new PriorityQueue<WeightedEdge>());
        }

        for (WeightedEdge edge : edges) {
            queues.get(edge.u).add(edge);
        }
    }

    /**
     * Display edges with weight
     */
    public void printWeightedEdges() {
        for (int i = 0; i < queues.size(); i++) {
            System.out.print("Vertex: " + i + ": ");
            for (WeightedEdge edge : queues.get(i)) {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Get a minium spanning tree rooted at vertex 0
     *
     * @return
     */
    public MinSpanningTree getMinSpanningTree() {
        return getMinSpanningTree(0);
    }

    /**
     * Get a minium spanning tree rooted at a specified vertex
     *
     * @param startIndex
     * @return
     */
    public MinSpanningTree getMinSpanningTree(int startIndex) {
        List<Integer> T = new ArrayList<>();
        // searchOrder initally contains the startIndex
        T.add(startIndex);

        int numberOfVertices = vertices.size();     // Number of vertices
        int[] parent = new int[numberOfVertices];   // Parent of a vertex
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;     // Initailize parent[i] to -1
        }
        int totalWeight = 0;    // Total weight of the tree thus far

        // Clone the queue, so as to keep the original queue intact
        List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

        // All vertices are found?
        while (T.size() < numberOfVertices) {
            // Search for the vertex with the smallest edge adjacent to a vertex in searchOrder
            int v = -1;
            double minWeight = Double.MAX_VALUE;
            for (int u : T) {
                while (!queues.get(u).isEmpty() && T.contains(queues.get(u).peek().v)) {
                    // Remove the edge from queues.get(u) if the adjacent vertex of u is already in T
                    queues.get(u).remove();
                }

                if (queues.get(u).isEmpty()) {
                    continue;   // Consider the next vertex in T
                }

                // Current samllest weight on an edge adjacent to u
                WeightedEdge edge = queues.get(u).peek();
                if (edge.weight < minWeight) {
                    v = edge.v;
                    minWeight = edge.weight;
                    parent[v] = u;
                }
            }

            T.add(v);
            totalWeight += minWeight;
        }

        return new MinSpanningTree(startIndex, parent, T, totalWeight);
    }

    /**
     * Find single-source shortest paths
     *
     * @param startIndex
     * @return
     */
    public ShortestPathTree getShortestPath(int startIndex) {
        List<Integer> T = new ArrayList<>();    // T stores the vertices whose path to start vertex found so far
        T.add(startIndex);      // T initially contains the startIndex

        int numberOfVertices = vertices.size();

        // parent[v] stores the previous vertex of v in the path
        int[] parent = new int[numberOfVertices];
        parent[startIndex] = -1;    // The parent of the root is set to -1

        // costs[v] stores the cost of the path from v to the root
        int[] costs = new int[numberOfVertices];
        for (int i = 0; i < costs.length; i++) {
            costs[i] = Integer.MAX_VALUE;   // Initial cost is set to infinity
        }
        costs[startIndex] = 0;  // Cost of root is 0

        // Get a copy of queues
        List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

        // Expand verticesFound
        while (T.size() < numberOfVertices) {
            int v = -1;
            int minCost = Integer.MAX_VALUE;

            for (int u : T) {
                while (!queues.get(u).isEmpty() && T.contains(queues.get(u).peek().v)) {
                    queues.get(u).remove(); // Remove the vertex in verticesFound
                }

                if (queues.get(u).isEmpty()) {
                    continue;   // All vertices adjacent to u are in verticesFound
                }

                WeightedEdge edge = queues.get(u).peek();

                if (costs[u] + edge.weight < minCost) {
                    v = edge.v;
                    minCost = costs[u] + edge.weight;
                    parent[v] = u;
                }
            }

            T.add(v);
            costs[v] = minCost;
        }

        return new ShortestPathTree(startIndex, parent, T, costs);
    }

    /**
     * Clone an array of queues
     *
     * @param queues
     * @return
     */
    private List<PriorityQueue<WeightedEdge>> deepClone(List<PriorityQueue<WeightedEdge>> queues) {
        List<PriorityQueue<WeightedEdge>> copiedQueues = new ArrayList<>();
        for (PriorityQueue<WeightedEdge> queue : queues) {
            PriorityQueue<WeightedEdge> copiedQueue = new PriorityQueue<>();
            copiedQueue.addAll(queue);
            copiedQueues.add(copiedQueue);
        }

        return copiedQueues;
    }

    /**
     * @return The weighted edge list
     */
    public List<PriorityQueue<WeightedEdge>> getWeightedEdges() {
        return queues;
    }

    /**
     * WeightedEdge inner class inside the WeightedGraph class
     */
    public static class WeightedEdge extends AbstractGraph.Edge implements Comparable<WeightedEdge> {

        public int weight;

        public WeightedEdge(int u, int v, int weight) {
            super(u, v);
            this.weight = weight;
        }

        @Override
        public int compareTo(WeightedEdge edge) {
            if (weight > edge.weight) {
                return 1;
            } else if (weight == edge.weight) {
                return 0;
            } else {
                return -1;
            }
        }

    }

    /**
     * MinSpanningTree inner class inside the WeightedGraph class
     */
    public class MinSpanningTree extends AbstractGraph.SpanningTree {

        private int totalWeight;

        public MinSpanningTree(int root, int[] parent, List<Integer> searchOrder, int totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public int getTotalWeight() {
            return totalWeight;
        }

    }

    /**
     * ShortestPathTree inner class inside the WeightedGraph class
     */
    public class ShortestPathTree extends AbstractGraph.SpanningTree {

        private int[] costs;

        /**
         * Construct a ShortestPathTree
         *
         * @param root
         * @param parent
         * @param searchOrder
         * @param costs
         */
        public ShortestPathTree(int root, int[] parent, List<Integer> searchOrder, int[] costs) {
            super(root, parent, searchOrder);
            this.costs = costs;
        }

        /**
         * Get the cost for a path from the root to vertex v
         *
         * @param v
         * @return
         */
        public int getCost(int v) {
            return costs[v];
        }

        public void printAllPaths() {
            System.out.println("All shortests paths from " + vertices.get(getRoot()) + "are:");
            for (int i = 0; i < costs.length; i++) {
                printPath(i);   // Print a path from i to the root
                System.out.println("(cost: " + costs[i] + ")");     // Path cost
            }
        }

    }

}
