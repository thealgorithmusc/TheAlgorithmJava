package com.github.thealgorithmusc.graph;

import java.util.List;

public interface IGraph<V> {

    /**
     * @return The number of vertices in the graph
     */
    int getSize();

    /**
     * @return The vertices in the graph
     */
    List<V> getVertices();

    /**
     * @return The object for the specified vertex index
     */
    V getVertex(int index);

    /**
     * @return The index for the specified vertex object
     */
    int getIndex(V v);

    /**
     * @return The neighbors of vertex with the specified index
     */
    List<Integer> getNeighbors(int index);

    /**
     * @return The degree of a specified vertex
     */
    int getDegree(int v);

    /**
     * @return The Adjacency matrix
     */
    int[][] getAdjacencyMatrix();

    /**
     * Print the adjacency matrix
     */
    void printAdjacencyMatrix();

    /**
     * Print the edges
     */
    void printEdges();

    /**
     * Obtain a depth-first search tree
     *
     * @param v
     * @return
     */
    AbstractGraph<V>.SpanningTree dfs(int v);

    /**
     * Obtain a breadth-first search tree
     *
     * @param v
     * @return
     */
    AbstractGraph<V>.SpanningTree bfs(int v);

}
