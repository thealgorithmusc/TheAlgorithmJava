package com.github.thealgorithmusc.tree;

public interface IBiSearchTree<T extends Comparable<T>> extends ITree {

    boolean insert(T key);

    boolean find(T key);

}
