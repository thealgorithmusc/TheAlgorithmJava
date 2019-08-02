package com.github.thealgorithmusc.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BiTree<T> implements ITree {

    protected BiTNode<T> rootNode;

    public BiTree() {
    }

    public BiTree(T[] preOrderSeq) {
        Queue<T> preOrderQueue = new LinkedList<>(Arrays.asList(preOrderSeq));
        this.rootNode = build(preOrderQueue);
    }

    public BiTree(List<T> preOrderSeq) {
        Queue<T> preOrderQueue = new LinkedList<>();
        preOrderQueue.addAll(preOrderSeq);
        this.rootNode = build(preOrderQueue);
    }

    protected BiTNode<T> build(Queue<T> preOrderQueue) {
        T key = preOrderQueue.poll();

        if (key == null) {
            return null;
        } else {
            BiTNode<T> entryNode = new BiTNode<>(key);
            entryNode.setlChild(this.build(preOrderQueue));
            entryNode.setrChild(this.build(preOrderQueue));
            return entryNode;
        }
    }

    /**
     * Preorder traverse of the tree
     * (also known as the depth-first traversing method)
     */
    @Override
    public void preOrderTraverse() {
        this.preOrderTraverse(this.rootNode);
    }

    /**
     * Recursive preorder traversing method
     *
     * @param entryNode
     */
    protected void preOrderTraverse(BiTNode<T> entryNode) {
        if (entryNode != null) {
            entryNode.visit();

            BiTNode<T> lchild = entryNode.getlChild();
            if (lchild != null) {
                this.preOrderTraverse(lchild);
            }

            BiTNode<T> rchild = entryNode.getrChild();
            if (rchild != null) {
                this.preOrderTraverse(rchild);
            }
        }
    }

    /**
     * Inorder traverse of the tree
     */
    @Override
    public void inOrderTraverse() {
        this.inOrderTraverse(this.rootNode);
    }


    /**
     * Recursive inorder traversing method
     *
     * @param entryNode
     */
    protected void inOrderTraverse(BiTNode<T> entryNode) {
        if (entryNode != null) {
            BiTNode<T> lchild = entryNode.getlChild();
            if (lchild != null) {
                this.inOrderTraverse(lchild);
            }

            entryNode.visit();

            BiTNode<T> rchild = entryNode.getrChild();
            if (rchild != null) {
                this.inOrderTraverse(rchild);
            }
        }
    }

    /**
     * Postorder traverse of the tree
     */
    @Override
    public void postOrderTraverse() {
        this.postOrderTraverse(this.rootNode);
    }


    /**
     * Recursive postorder traversing method
     *
     * @param entryNode
     */
    protected void postOrderTraverse(BiTNode<T> entryNode) {
        if (entryNode != null) {
            BiTNode<T> lchild = entryNode.getlChild();
            if (lchild != null) {
                this.postOrderTraverse(lchild);
            }

            BiTNode<T> rchild = entryNode.getrChild();
            if (rchild != null) {
                this.postOrderTraverse(rchild);
            }

            entryNode.visit();
        }
    }

    /**
     * Levelorder traverse of the tree
     * (also known as the breadth-first traversing method)
     */
    @Override
    public void levelOrderTraverse() {
        Queue<BiTNode<T>> queue = new LinkedList<>();
        queue.add(this.rootNode);
        while (!queue.isEmpty()) {
            BiTNode<T> node = queue.poll();
            node.visit();

            BiTNode<T> lchild = node.getlChild();
            if (lchild != null) {
                queue.add(lchild);
            }
            BiTNode<T> rchild = node.getrChild();
            if (rchild != null) {
                queue.add(rchild);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return this.rootNode == null ? true : false;
    }

    public BiTNode getRootNode() {
        return rootNode;
    }

}
