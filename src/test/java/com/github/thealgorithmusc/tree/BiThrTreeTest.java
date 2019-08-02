package com.github.thealgorithmusc.tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BiThrTreeTest {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream ps = new PrintStream(baos);
    private PrintStream defaultps = System.out;

    private BiThrTree<String> biThrTree;

    @Before
    public void setup() {
        System.setOut(ps);
        biThrTree = new BiThrTree<>(new String[]{"-", "+", "a", null, null, "*", "b", null, null, "-", "c", null,
            null, "d", null, null, "/", "e", null, null, "f", null, null});
    }

    @After
    public void reset() {
        baos.reset();
        System.out.flush();
        System.setOut(defaultps);
    }

    @Test
    public void testPreOrderTraverse() {
        biThrTree.preOrderTraverse();
        assertEquals("- + a * b - c d / e f", baos.toString().trim());
    }

    @Test
    public void testInOrderTraverse() {
        biThrTree.inOrderTraverse();
        assertEquals("a + b * c - d - e / f", baos.toString().trim());
    }

    @Test
    public void testInOrderTraverseThr() {
        biThrTree.inOrderTraverseThr();
        assertEquals("a + b * c - d - e / f", baos.toString().trim());
    }

    @Test
    public void testPostOrderTraverse() {
        biThrTree.postOrderTraverse();
        assertEquals("a b c d - * + e f / -", baos.toString().trim());
    }

    @Test
    public void testLevelOrderTraverse() {
        biThrTree.levelOrderTraverse();
        assertEquals("- + / a * e f b - c d", baos.toString().trim());
    }

}
