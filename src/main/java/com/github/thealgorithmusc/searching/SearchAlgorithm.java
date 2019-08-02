package com.github.thealgorithmusc.searching;

import com.github.thealgorithmusc.tree.BiSortTree;

public class SearchAlgorithm {

    // region Sequential search
    public static <T extends Comparable<T>> int sequentialSearch(T[] list, T key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].compareTo(key) == 0) {
                return i;
            }
        }

        return -1;
    }
    // endregion

    // region Binary search
    public static <T extends Comparable<T>> int binarySearch(T[] orderedList, T key) {
        return binarySearch(orderedList, 0, orderedList.length - 1, key);
    }

    private static <T extends Comparable<T>> int binarySearch(T[] orderedList, int first, int last, T key) {
        if (first > last) {
            return -1;
        }

        int mid = first + (last - first) / 2;
        if (orderedList[mid].compareTo(key) > 0) {
            return binarySearch(orderedList, first, mid - 1, key);
        } else if (orderedList[mid].compareTo(key) < 0) {
            return binarySearch(orderedList, mid + 1, last, key);
        } else {
            return mid;
        }
    }
    // endregion

    // region Binary sort tree search
    public static <T extends Comparable<T>> boolean bstSearch(BiSortTree<T> biSortTree, T key) {
        return biSortTree.find(key);
    }
    // endregion

}
