package com.github.thealgorithmusc.sort;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SortAlgorithmTest {

    private Integer[] nums;

    @Before
    public void setup() {
        nums = new Integer[]{18, 8, 36, 11, 41, 59, 98, 27, 86, 89};
    }

    @Test
    public void testInsertionSort() {
        SortAlgorithm.insertionSort(nums);
        assertArrayEquals(new Integer[]{8, 11, 18, 27, 36, 41, 59, 86, 89, 98}, nums);
    }

    @Test
    public void testSelectionSort() {
        SortAlgorithm.selectionSort(nums);
        assertArrayEquals(new Integer[]{8, 11, 18, 27, 36, 41, 59, 86, 89, 98}, nums);
    }

    @Test
    public void testBubbleSort() {
        SortAlgorithm.bubbleSort(nums);
        assertArrayEquals(new Integer[]{8, 11, 18, 27, 36, 41, 59, 86, 89, 98}, nums);
    }

    @Test
    public void testMergeSort() {
        SortAlgorithm.mergeSort(nums);
        assertArrayEquals(new Integer[]{8, 11, 18, 27, 36, 41, 59, 86, 89, 98}, nums);
    }

    @Test
    public void testQuickSort() {
        SortAlgorithm.quickSort(nums);
        assertArrayEquals(new Integer[]{8, 11, 18, 27, 36, 41, 59, 86, 89, 98}, nums);
    }

    @Test
    public void testHeapSortBySiftUp() {
        SortAlgorithm.heapSortBySiftUp(nums);
        assertArrayEquals(new Integer[]{8, 11, 18, 27, 36, 41, 59, 86, 89, 98}, nums);
    }

    @Test
    public void testHeapSortBySiftDown() {
        SortAlgorithm.heapSortBySiftDown(nums);
        assertArrayEquals(new Integer[]{8, 11, 18, 27, 36, 41, 59, 86, 89, 98}, nums);
    }

}
