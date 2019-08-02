package com.github.thealgorithmusc.graph;

import java.util.*;

public class AbstractGraph<V> implements IGraph<V> {

    // Vertices list
    protected List<V> vertices;

    // Adjacency lists (邻接线性表表示边，即neighbors[i]记录了所有与i相邻的顶点的下标)
    protected List<List<Integer>> neighbors;

    /**
     * Construct a graph from edges and vertices stored in arrays
     *
     * @param edges
     * @param vertices
     */
    protected AbstractGraph(int[][] edges, V[] vertices) {
        this.vertices = Arrays.asList(vertices);
        createAdjacencyLists(edges, vertices.length);
    }

    /**
     * Construct a graph from edges and vertices stored in List
     *
     * @param edges
     * @param vertices
     */
    protected AbstractGraph(List<Edge> edges, List<V> vertices) {
        this.vertices = vertices;
        createAdjacencyLists(edges, vertices.size());
    }

    /**
     * Construct a graph from integer vertices 0, 1, ... and edge array
     *
     * @param edges
     * @param numberOfVertices
     */
    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        this.vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) new Integer(i));   // vertices is {0, 1, ...}
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * Construct a graph from integer vertices 0, 1, ... and edge list
     *
     * @param edges
     * @param numberOfVertices
     */
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        this.vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) new Integer(i));   // vertices is {0, 1, ...}
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * Create adjacency lists for each vertex
     *
     * @param edges
     * @param numberOfVertices
     */
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        this.neighbors = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            neighbors.get(u).add(v);
        }
    }

    /**
     * Create adjacency lists for each vertex
     *
     * @param edges
     * @param numberOfVertices
     */
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        this.neighbors = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            neighbors.add(new ArrayList<Integer>());
        }

        for (Edge edge : edges) {
            neighbors.get(edge.u).add(edge.v);
        }
    }

    /**
     * @return The number of vertices in the graph
     */
    @Override
    public int getSize() {
        return vertices.size();
    }

    /**
     * @return The vertices in the graph
     */
    @Override
    public List<V> getVertices() {
        return vertices;
    }

    /**
     * Get the object for the specified vertex
     *
     * @param index
     * @return
     */
    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    /**
     * Get the index for the specified vertex object
     *
     * @param v
     * @return
     */
    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    /**
     * Get the neighbors of vertex with the specified index
     *
     * @param index
     * @return
     */
    @Override
    public List<Integer> getNeighbors(int index) {
        return neighbors.get(index);
    }

    /**
     * Get the degree for vertex with the speicifed index
     *
     * @param v
     * @return
     */
    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    /**
     * @return The adjacency matrix
     */
    @Override
    public int[][] getAdjacencyMatrix() {
        int[][] adjacencyMatrix = new int[getSize()][getSize()];

        for (int i = 0; i < neighbors.size(); i++) {
            for (int j = 0; j < neighbors.get(i).size(); j++) {
                int v = neighbors.get(i).get(j);
                adjacencyMatrix[i][v] = 1;
            }
        }

        return adjacencyMatrix;
    }

    /**
     * Print the adjacency matrix
     */
    @Override
    public void printAdjacencyMatrix() {
        int[][] adjacencyMatrix = getAdjacencyMatrix();
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Print the edges
     */
    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print("Vertex " + u + ": ");
            for (int v = 0; v < neighbors.get(u).size(); v++) {
                System.out.print("(" + u + ", " + neighbors.get(u).get(v) + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Edge inner class inside the AbstractGraph class
     */
    public static class Edge {

        public int u;   // Starting vertex of the edge
        public int v;   // Ending vertex of the edge

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

    }

    /**
     * Obtain a DFS tree starting from vertex v
     *
     * @param v
     * @return
     */
    @Override
    public SpanningTree dfs(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1; // Initialize parent[i] to -1
        }

        boolean[] isVisited = new boolean[vertices.size()];

        dfs(v, parent, searchOrders, isVisited);

        return new SpanningTree(v, parent, searchOrders);
    }

    /**
     * Recursive method for DFS search
     *
     * @param v
     * @param parent
     * @param searchOrders
     * @param isVisited
     */
    private void dfs(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited) {
        // Store the visited vertex
        searchOrders.add(v);

        // Mark the visited vertex
        isVisited[v] = true;

        // Recursively search
        for (int i : neighbors.get(v)) {
            if (!isVisited[i]) {
                parent[i] = v;
                dfs(i, parent, searchOrders, isVisited);
            }
        }
    }

    /**
     * Obtain a bfs tree starting from vertex v
     *
     * @param v
     * @return
     */
    @Override
    public SpanningTree bfs(int v) {
        List<Integer> searchOrders = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1; // Initialize parent[i] to -1
        }

        boolean[] isVisited = new boolean[vertices.size()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        isVisited[v] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrders.add(u);

            for (int w : neighbors.get(u)) {
                if (!isVisited[w]) {
                    parent[w] = u;
                    queue.add(w);
                    isVisited[w] = true;
                }
            }
        }

        return new SpanningTree(v, parent, searchOrders);
    }

    /**
     * SpanningTree inner class inside the AbstractGraph class
     */
    public class SpanningTree {

        private int root;                       // The root of the tree
        private int[] parent;                   // Store the parent of each vertex
        private List<Integer> searchOrders;     // Store the search order

        public SpanningTree(int root, int[] parent, List<Integer> searchOrders) {
            this.root = root;
            this.parent = parent;
            this.searchOrders = searchOrders;
        }

        public SpanningTree(int root, int[] parent) {
            this.root = root;
            this.parent = parent;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }

        public List<Integer> getSearchOrders() {
            return searchOrders;
        }

        /**
         * @return The number of vertices found
         */
        public int getNumberOfVerticesFound() {
            return searchOrders.size();
        }

        /**
         * Get the path of vertices from a vertex index to the root
         *
         * @param index
         * @return
         */
        public List<V> getPath(int index) {
            List<V> path = new ArrayList<>();

            do {
                path.add(vertices.get(index));
                index = parent[index];
            } while (index != -1);

            return path;
        }

        /**
         * Print a path from the root to vertex v
         *
         * @param index
         */
        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
        }

        /**
         * Print the whole spanning tree
         */
        public void printTree() {
            System.out.println("Root: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }

    }

}
