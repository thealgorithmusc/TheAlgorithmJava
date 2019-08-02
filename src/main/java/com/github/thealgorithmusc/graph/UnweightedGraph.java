package com.github.thealgorithmusc.graph;

import java.util.List;

public class UnweightedGraph<V> extends AbstractGraph<V> {

    public UnweightedGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
    }

    public UnweightedGraph(List<Edge> edges, List<V> vertices) {
        super(edges, vertices);
    }

    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

}
