package com.github.thealgorithmusc.tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BiSearchTreeTest {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream ps = new PrintStream(baos);
    private PrintStream defaultps = System.out;

    @Before
    public void setup() {
        System.setOut(ps);
    }

    @After
    public void reset() {
        System.out.flush();
        System.setOut(defaultps);
    }

    @Test
    public void testInsert() {
        BiSearchTree<Integer> biSearchTree = new BiSearchTree<>();
        int[] seq = new int[]{45, 24, 53, 45, 12, 24, 90, 67, 32, 5, 98, 10, 0, 8, 135, 25};
        for (int i : seq) {
            biSearchTree.insert(i);
        }

        biSearchTree.preOrderTraverse();
        assertEquals("45 24 12 5 0 10 8 32 25 53 90 67 98 135", baos.toString().trim());

        baos.reset();
        biSearchTree.inOrderTraverse();
        assertEquals("0 5 8 10 12 24 25 32 45 53 67 90 98 135", baos.toString().trim());

        baos.reset();
        biSearchTree.postOrderTraverse();
        assertEquals("0 8 10 5 12 25 32 24 67 135 98 90 53 45", baos.toString().trim());

        baos.reset();
        biSearchTree.levelOrderTraverse();
        assertEquals("45 24 53 12 32 90 5 25 67 98 0 10 135 8", baos.toString().trim());
    }

    @Test
    public void testDelete() {
        BiSearchTree<Integer> biSearchTree = new BiSearchTree<>();
        int[] seq = new int[]{45, 24, 53, 45, 12, 24, 90, 67, 32, 5, 98, 10, 0, 8, 135, 25};
        for (int i : seq) {
            biSearchTree.insert(i);
        }
        biSearchTree.delete(24);
        biSearchTree.delete(98);

        biSearchTree.preOrderTraverse();
        assertEquals("45 12 5 0 10 8 32 25 53 90 67 135", baos.toString().trim());

        baos.reset();
        biSearchTree.inOrderTraverse();
        assertEquals("0 5 8 10 12 25 32 45 53 67 90 135", baos.toString().trim());

        baos.reset();
        biSearchTree.postOrderTraverse();
        assertEquals("0 8 10 5 25 32 12 67 135 90 53 45", baos.toString().trim());

        baos.reset();
        biSearchTree.levelOrderTraverse();
        assertEquals("45 12 53 5 32 90 0 10 25 67 135 8", baos.toString().trim());
    }

}
