package com.github.thealgorithmusc.tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class BiTreeTest {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream ps = new PrintStream(baos);
    private PrintStream defaultps = System.out;

    private BiTree<String> biTree;

    @Before
    public void setup() {
        System.setOut(ps);
        biTree = new BiTree<>(new String[]{"-", "+", "a", null, null, "*", "b", null, null, "-", "c", null,
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
        biTree.preOrderTraverse();
        assertEquals("- + a * b - c d / e f", baos.toString().trim());
    }

    @Test
    public void testInOrderTraverse() {
        biTree.inOrderTraverse();
        assertEquals("a + b * c - d - e / f", baos.toString().trim());
    }

    @Test
    public void testPostOrderTraverse() {
        biTree.postOrderTraverse();
        assertEquals("a b c d - * + e f / -", baos.toString().trim());
    }

    @Test
    public void testLevelOrderTraverse() {
        biTree.levelOrderTraverse();
        assertEquals("- + / a * e f b - c d", baos.toString().trim());
    }

}
