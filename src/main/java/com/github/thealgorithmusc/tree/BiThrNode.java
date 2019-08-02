package com.github.thealgorithmusc.tree;

public class BiThrNode<T> {

    private T key;
    private BiTNode parent;
    private BiThrNode lChild;
    private BiThrNode rChild;
    private PointerTag lTag;
    private PointerTag rTag;

    public BiThrNode(T key) {
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

    public BiThrNode getlChild() {
        return lChild;
    }

    public void setlChild(BiThrNode lChild) {
        this.lChild = lChild;
    }

    public BiThrNode getrChild() {
        return rChild;
    }

    public void setrChild(BiThrNode rChild) {
        this.rChild = rChild;
    }

    public PointerTag getlTag() {
        return lTag;
    }

    public void setlTag(PointerTag lTag) {
        this.lTag = lTag;
    }

    public PointerTag getrTag() {
        return rTag;
    }

    public void setrTag(PointerTag rTag) {
        this.rTag = rTag;
    }

    @Override
    public String toString() {
        return "BiThrNode{" +
            "key=" + key +
            ", parent=" + parent +
            ", lChild=" + lChild +
            ", rChild=" + rChild +
            ", lTag=" + lTag +
            ", rTag=" + rTag +
            '}';
    }

    public enum PointerTag {
        Link, Thread;
    }

}
