package com.github.thealgorithmusc.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WeightedGraphTest {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream ps = new PrintStream(baos);
    private PrintStream defaultps = System.out;

    private WeightedGraph<String> graph;

    @Before
    public void setup() {
        System.setOut(ps);

        String[] vertices = {"Seattle", "San Francisco", "Los Angeles", "Denver", "Kansas City",
            "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston"};

        int[][] edges = {
            {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
            {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
            {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
            {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
            {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260}, {4, 8, 864}, {4, 10, 496},
            {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983}, {5, 7, 787},
            {6, 5, 983}, {6, 7, 214},
            {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
            {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, {8, 10, 781}, {8, 11, 810},
            {9, 8, 661}, {9, 11, 1187},
            {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
            {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
        };

        graph = new WeightedGraph<>(edges, vertices);
    }

    @After
    public void reset() {
        System.out.flush();
        System.setOut(defaultps);
    }

    @Test
    public void testMinSpanningTree() {
        WeightedGraph.MinSpanningTree tree = graph.getMinSpanningTree();
        tree.printTree();

        assertEquals(6513, tree.getTotalWeight());
        assertEquals(
            "Root: Seattle\n" +
                "Edges: (Seattle, San Francisco) (San Francisco, Los Angeles) (Los Angeles, Denver)" +
                " (Denver, Kansas City) (Kansas City, Chicago) (New York, Boston) (Chicago, New York)" +
                " (Dallas, Atlanta) (Atlanta, Miami) (Kansas City, Dallas) (Dallas, Houston)",
            baos.toString().trim()
        );
    }

    @Test
    public void testShortestPath() {
        WeightedGraph<String>.ShortestPathTree tree = graph.getShortestPath(5);
        tree.printAllPaths();
        assertEquals(
            "All shortests paths from Chicagoare:\n" +
                "A path from Chicago to Seattle: Chicago Seattle (cost: 2097)\n" +
                "A path from Chicago to San Francisco: Chicago Denver San Francisco (cost: 2270)\n" +
                "A path from Chicago to Los Angeles: Chicago Denver Los Angeles (cost: 2018)\n" +
                "A path from Chicago to Denver: Chicago Denver (cost: 1003)\n" +
                "A path from Chicago to Kansas City: Chicago Kansas City (cost: 533)\n" +
                "A path from Chicago to Chicago: Chicago (cost: 0)\n" +
                "A path from Chicago to Boston: Chicago Boston (cost: 983)\n" +
                "A path from Chicago to New York: Chicago New York (cost: 787)\n" +
                "A path from Chicago to Atlanta: Chicago Kansas City Atlanta (cost: 1397)\n" +
                "A path from Chicago to Miami: Chicago Kansas City Atlanta Miami (cost: 2058)\n" +
                "A path from Chicago to Dallas: Chicago Kansas City Dallas (cost: 1029)\n" +
                "A path from Chicago to Houston: Chicago Kansas City Dallas Houston (cost: 1268)",
            baos.toString().trim()
        );

        baos.reset();
        List<String> path = tree.getPath(11);
        for (String s : path) {
            System.out.print(s + " ");
        }
        assertEquals("Houston Dallas Kansas City Chicago", baos.toString().trim());
    }

}
