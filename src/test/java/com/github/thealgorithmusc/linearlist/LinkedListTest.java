package com.github.thealgorithmusc.linearlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkedListTest {

    private LinkedLinearList<Integer> ls1;
    private LinkedLinearList<Integer> ls2;

    @Before
    public void setup() {
        ls1 = new LinkedLinearList<>();

        ls2 = new LinkedLinearList<>();
        ls2.add(12);
        ls2.add(3);
        ls2.add(23);
        ls2.add(50);
        ls2.add(3);
        ls2.add(10);
    }

    @Test
    public void testIndexOf() {
        assertEquals(1, ls2.indexOf(3));
    }

    @Test
    public void testLastIndexOf() {
        assertEquals(4, ls2.lastIndexOf(3));
    }

    // TODO: Need for implementation of other methods in
    //  class com.github.thealgorithmusc.linearlist.LinkedLinearList

}
