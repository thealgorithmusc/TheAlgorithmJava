package com.github.thealgorithmusc.linearlist;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {

    private Random randgen = new Random(42);
    private ArrayLinearList<Integer> ls = new ArrayLinearList<>();

    @Test
    public void testAdd() {
        for (int i = 0; i < 100; i++) {
            ls.add(randgen.nextInt(1000));
        }

        assertEquals(100, ls.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheckForAdd() {
        ls.add(9);
        ls.add(2, 5);
    }

    @Test
    public void testClear() {
        ls.clear();
        assertEquals(0, ls.size());

        for (int i = 0; i < 100; i++) {
            ls.add(randgen.nextInt(1000));
        }
        ls.clear();
        assertEquals(0, ls.size());
    }

    @Test
    public void testContains() {
        assertEquals(false, ls.contains(3));

        ls.add(10);
        ls.add(5);
        ls.add(0);
        assertEquals(true, ls.contains(5));
        assertEquals(false, ls.contains(6));
    }

    @Test
    public void testGet() {
        ls.add(10);
        ls.add(25);
        ls.add(24);
        ls.add(25);
        ls.add(10);
        assertEquals((Integer) 25, ls.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRangeCheck() {
        ls.get(0);
    }

    @Test
    public void testIndexOf() {
        ls.add(10);
        ls.add(25);
        ls.add(24);
        ls.add(25);
        ls.add(10);
        assertEquals(1, ls.indexOf(25));
    }

    @Test
    public void testLastIndexOf() {
        ls.add(10);
        ls.add(25);
        ls.add(24);
        ls.add(25);
        ls.add(10);
        assertEquals(3, ls.lastIndexOf(25));
    }

    @Test
    public void testRemove() {
        ls.add(10);
        ls.add(25);
        ls.add(24);
        ls.add(25);
        ls.add(10);
        assertEquals((Integer) 10, ls.remove(ls.size() - 1));
    }

    @Test
    public void testSet() {
        ls.add(10);
        ls.add(25);
        ls.add(24);
        ls.add(25);
        ls.add(10);
        int oldNum = ls.set(2, 100);
        int newNum = ls.get(2);
        assertEquals(24, oldNum);
        assertEquals(100, newNum);
    }

}
