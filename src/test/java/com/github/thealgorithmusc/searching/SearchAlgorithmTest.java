package com.github.thealgorithmusc.searching;

import com.github.thealgorithmusc.tree.BiSortTree;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SearchAlgorithmTest {

    @Test
    public void testSequentialSearch() {
        Integer[] nums = new Integer[]{18, 8, 36, 11, 41, 59, 98, 27, 86, 89};
        assertEquals(7, SearchAlgorithm.sequentialSearch(nums, 27));
        assertEquals(0, SearchAlgorithm.sequentialSearch(nums, 18));
        assertEquals(nums.length - 1, SearchAlgorithm.sequentialSearch(nums, 89));
        assertEquals(-1, SearchAlgorithm.sequentialSearch(nums, 5));
    }

    @Test
    public void testBinarySearch() {
        Integer[] nums = new Integer[]{18, 8, 36, 11, 41, 59, 98, 27, 86, 89};
        Arrays.sort(nums);
        assertEquals(3, SearchAlgorithm.binarySearch(nums, 27));
        assertEquals(0, SearchAlgorithm.binarySearch(nums, 8));
        assertEquals(nums.length - 1, SearchAlgorithm.binarySearch(nums, 98));
        assertEquals(-1, SearchAlgorithm.binarySearch(nums, 5));
    }

    @Test
    public void testBstSearch() {
        Integer[] nums = new Integer[]{18, 8, 36, 11, 41, 59, 98, 27, 86, 89};
        BiSortTree<Integer> biSortTree = new BiSortTree<>();
        for (Integer i : nums) {
            biSortTree.insert(i);
        }

        assertEquals(true, SearchAlgorithm.bstSearch(biSortTree, 27));
        assertEquals(true, SearchAlgorithm.bstSearch(biSortTree, 18));
        assertEquals(true, SearchAlgorithm.bstSearch(biSortTree, 89));
        assertEquals(false, SearchAlgorithm.bstSearch(biSortTree, 5));
    }

}
