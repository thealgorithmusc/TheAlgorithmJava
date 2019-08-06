package com.github.thealgorithmusc.tree;

public class BiSearchTree<T extends Comparable<T>> extends BiTree<T>
    implements IBiSearchTree<T> {

    @Override
    public boolean insert(T key) {
        boolean isSuccess = false;

        if (isEmpty()) {
            BiTNode<T> newNode = new BiTNode<>(key);
            newNode.setParent(null);
            rootNode = newNode;
            isSuccess = true;
        } else {
            BiTNode<T> curNode = rootNode;
            while (true) {
                if (curNode.getKey().compareTo(key) == 0) {
                    isSuccess = false;
                    break;
                } else if (curNode.getKey().compareTo(key) > 0) {
                    if (curNode.getlChild() == null) {
                        BiTNode newNode = new BiTNode(key);
                        newNode.setParent(curNode);
                        curNode.setlChild(newNode);
                        isSuccess = true;
                        break;
                    } else {
                        curNode = curNode.getlChild();
                    }
                } else {
                    if (curNode.getrChild() == null) {
                        BiTNode newNode = new BiTNode(key);
                        newNode.setParent(curNode);
                        curNode.setrChild(newNode);
                        isSuccess = true;
                        break;
                    } else {
                        curNode = curNode.getrChild();
                    }
                }
            }
        }

        return isSuccess;
    }

    public boolean delete(T key) {
        boolean isSuccess = false;

        BiTNode<T> curNode = rootNode;
        while (curNode != null) {
            if (curNode.getKey().compareTo(key) == 0) {
                BiTNode<T> parent = curNode.getParent();
                if (curNode.getlChild() == null && curNode.getrChild() == null) {
                    if (parent.getlChild() == curNode) {
                        parent.setlChild(null);
                    } else {
                        parent.setrChild(null);
                    }
                } else if (curNode.getrChild() == null) {
                    if (parent.getlChild() == curNode) {
                        parent.setlChild(curNode.getlChild());
                    } else {
                        parent.setrChild(curNode.getlChild());
                    }
                } else if (curNode.getlChild() == null) {
                    if (parent.getlChild() == curNode) {
                        parent.setlChild(curNode.getrChild());
                    } else {
                        parent.setrChild(curNode.getrChild());
                    }
                } else {
                    if (parent.getlChild() == curNode) {
                        parent.setlChild(curNode.getlChild());
                    } else {
                        parent.setrChild(curNode.getlChild());
                    }

                    BiTNode<T> subtreeEntry = curNode.getlChild();
                    while (subtreeEntry.getrChild() != null) {
                        subtreeEntry = subtreeEntry.getrChild();
                    }
                    subtreeEntry.setrChild(curNode.getrChild());
                }
                isSuccess = true;
                break;
            } else if (curNode.getKey().compareTo(key) > 0) {
                curNode = curNode.getlChild();
            } else {
                curNode = curNode.getrChild();
            }
        }

        return isSuccess;
    }

    @Override
    public boolean find(T key) {
        boolean isSuccess = false;

        BiTNode<T> curNode = rootNode;
        while (curNode != null) {
            if (curNode.getKey().compareTo(key) == 0) {
                isSuccess = true;
                break;
            } else if (curNode.getKey().compareTo(key) > 0) {
                curNode = curNode.getlChild();
            } else {
                curNode = curNode.getrChild();
            }
        }

        return isSuccess;
    }

}
