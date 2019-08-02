package com.github.thealgorithmusc.tree;

public class BiTNode<T> {

    private T key;
    private BiTNode parent;
    private BiTNode lChild;
    private BiTNode rChild;

    public BiTNode(T key) {
        this.key = key;
    }

    public void visit() {
        System.out.print(key + " ");
    }

    public T getKey() {
        return key;
    }

    public BiTNode getParent() {
        return parent;
    }

    public void setParent(BiTNode parent) {
        this.parent = parent;
    }

    public BiTNode getlChild() {
        return lChild;
    }

    public void setlChild(BiTNode lChild) {
        this.lChild = lChild;
    }

    public BiTNode getrChild() {
        return rChild;
    }

    public void setrChild(BiTNode rChild) {
        this.rChild = rChild;
    }

    @Override
    public String toString() {
        return "BiTNode{" +
            "key=" + key +
            ", parent=" + parent +
            ", lChild=" + lChild +
            ", rChild=" + rChild +
            '}';
    }

}
