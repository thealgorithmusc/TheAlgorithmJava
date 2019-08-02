package com.github.thealgorithmusc.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UnweightedGraphTest {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream ps = new PrintStream(baos);
    private PrintStream defaultps = System.out;

    private IGraph<String> graph1;
    private IGraph<String> graph2;

    @Before
    public void setup() {
        System.setOut(ps);

        String[] vertices = {"Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City",
            "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston"};

        int[][] edges = {
            {0, 1}, {0, 3}, {0, 5},
            {1, 0}, {1, 2}, {1, 3},
            {2, 1}, {2, 3}, {2, 4}, {2, 10},
            {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
            {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
            {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
            {6, 5}, {6, 7},
            {7, 4}, {7, 5}, {7, 6}, {7, 8},
            {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
            {9, 8}, {9, 11},
            {10, 2}, {10, 4}, {10, 8}, {10, 11},
            {11, 8}, {11, 9}, {11, 10}
        };

        graph1 = new UnweightedGraph<>(edges, vertices);

        String[] names = {"Peter", "Jane", "Mark", "Cindy", "Wendy"};
        List<AbstractGraph.Edge> edgelist = new ArrayList<>();
        edgelist.add(new AbstractGraph.Edge(0, 2));
        edgelist.add(new AbstractGraph.Edge(1, 2));
        edgelist.add(new AbstractGraph.Edge(2, 4));
        edgelist.add(new AbstractGraph.Edge(3, 4));

        graph2 = new UnweightedGraph<>(edgelist, Arrays.asList(names));
    }

    @After
    public void reset() {
        System.out.flush();
        System.setOut(defaultps);
    }

    @Test
    public void testGetSize() {
        assertEquals(12, graph1.getSize());
        assertEquals(5, graph2.getSize());
    }

    @Test
    public void testGetVertex() {
        assertEquals("San Francisco", graph1.getVertex(1));
    }

    @Test
    public void testGetIndex() {
        assertEquals(9, graph1.getIndex("Miami"));
    }

    @Test
    public void testPrintEdges() {
        graph1.printEdges();
        String reuslt1 = baos.toString().trim();
        assertEquals(
            "Vertex 0: (0, 1) (0, 3) (0, 5) \n" +
                "Vertex 1: (1, 0) (1, 2) (1, 3) \n" +
                "Vertex 2: (2, 1) (2, 3) (2, 4) (2, 10) \n" +
                "Vertex 3: (3, 0) (3, 1) (3, 2) (3, 4) (3, 5) \n" +
                "Vertex 4: (4, 2) (4, 3) (4, 5) (4, 7) (4, 8) (4, 10) \n" +
                "Vertex 5: (5, 0) (5, 3) (5, 4) (5, 6) (5, 7) \n" +
                "Vertex 6: (6, 5) (6, 7) \n" +
                "Vertex 7: (7, 4) (7, 5) (7, 6) (7, 8) \n" +
                "Vertex 8: (8, 4) (8, 7) (8, 9) (8, 10) (8, 11) \n" +
                "Vertex 9: (9, 8) (9, 11) \n" +
                "Vertex 10: (10, 2) (10, 4) (10, 8) (10, 11) \n" +
                "Vertex 11: (11, 8) (11, 9) (11, 10)",
            reuslt1
        );

        baos.reset();
        graph2.printEdges();
        String reuslt2 = baos.toString().trim();
        assertEquals(
            "Vertex 0: (0, 2) \n" +
                "Vertex 1: (1, 2) \n" +
                "Vertex 2: (2, 4) \n" +
                "Vertex 3: (3, 4) \n" +
                "Vertex 4:",
            reuslt2
        );
    }

    @Test
    public void testGetAdjacencyMatrix() {
        int[][] adjacencyMatrix1 = graph1.getAdjacencyMatrix();
        assertArrayEquals(
            new int[][]{
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0},
                {1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0}
            },
            adjacencyMatrix1
        );

        int[][] adjacencyMatrix2 = graph2.getAdjacencyMatrix();
        assertArrayEquals(
            new int[][]{
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0}
            },
            adjacencyMatrix2
        );
    }

    @Test
    public void testDfs() {
        AbstractGraph.SpanningTree bfs = graph1.dfs(5);     // 5 is Chicago
        List<Integer> searchOrders = bfs.getSearchOrders();
        int numberOfVertices = bfs.getNumberOfVerticesFound();

        assertEquals(12, numberOfVertices);
        assertEquals(new ArrayList<Integer>() {{
            add(5);
            add(0);
            add(1);
            add(2);
            add(3);
            add(4);
            add(7);
            add(6);
            add(8);
            add(9);
            add(11);
            add(10);
        }}, searchOrders);
    }

    @Test
    public void testBfs() {
        AbstractGraph.SpanningTree bfs = graph1.bfs(5);     // 5 is Chicago
        List<Integer> searchOrders = bfs.getSearchOrders();
        int numberOfVertices = bfs.getNumberOfVerticesFound();

        assertEquals(12, numberOfVertices);
        assertEquals(new ArrayList<Integer>() {{
            add(5);
            add(0);
            add(3);
            add(4);
            add(6);
            add(7);
            add(1);
            add(2);
            add(8);
            add(10);
            add(9);
            add(11);
        }}, searchOrders);
    }

}
