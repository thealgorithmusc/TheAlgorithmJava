package com.github.thealgorithmusc.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BiThrTree<T> implements ITree {

    private BiThrNode<T> rootNode;

    private BiThrNode<T> preNode;

    public BiThrTree() {
    }

    public BiThrTree(T[] preOrderSeq) {
        Queue<T> preOrderQueue = new LinkedList<>(Arrays.asList(preOrderSeq));
        this.rootNode = build(preOrderQueue);
        this.inOrderThreading();
    }

    public BiThrTree(List<T> preOrderSeq) {
        Queue<T> preOrderQueue = new LinkedList<>();
        preOrderQueue.addAll(preOrderSeq);
        this.rootNode = build(preOrderQueue);
        this.inOrderThreading();
    }

    protected BiThrNode<T> build(Queue<T> preOrderQueue) {
        T key = preOrderQueue.poll();

        if (key == null) {
            return null;
        } else {
            BiThrNode<T> entryNode = new BiThrNode<>(key);

            entryNode.setlChild(this.build(preOrderQueue));
            entryNode.setlTag(BiThrNode.PointerTag.Link);

            entryNode.setrChild(this.build(preOrderQueue));
            entryNode.setrTag(BiThrNode.PointerTag.Link);

            return entryNode;
        }
    }

    @Override
    public void preOrderTraverse() {
        preOrderTraverse(this.rootNode);
    }

    protected void preOrderTraverse(BiThrNode<T> entryNode) {
        if (entryNode != null) {
            entryNode.visit();

            BiThrNode<T> lchild = entryNode.getlChild();
            if (lchild != null && entryNode.getlTag() == BiThrNode.PointerTag.Link) {
                this.preOrderTraverse(lchild);
            }

            BiThrNode<T> rchild = entryNode.getrChild();
            if (rchild != null && entryNode.getrTag() == BiThrNode.PointerTag.Link) {
                this.preOrderTraverse(rchild);
            }
        }
    }

    @Override
    public void inOrderTraverse() {
        this.inOrderTraverse(this.rootNode);
    }

    protected void inOrderTraverse(BiThrNode<T> entryNode) {
        if (entryNode != null) {
            BiThrNode<T> lchild = entryNode.getlChild();
            if (lchild != null && entryNode.getlTag() == BiThrNode.PointerTag.Link) {
                this.inOrderTraverse(lchild);
            }

            entryNode.visit();

            BiThrNode<T> rchild = entryNode.getrChild();
            if (rchild != null && entryNode.getrTag() == BiThrNode.PointerTag.Link) {
                this.inOrderTraverse(rchild);
            }
        }
    }

    /**
     * 中序遍历线索二叉树，按照后继方式遍历（思路：找到最左子节点开始）
     */
    public void inOrderTraverseThr() {
        BiThrNode<T> curNode = this.rootNode;

        // 找中序遍历方式开始的节点
        while (curNode != null && curNode.getlTag() == BiThrNode.PointerTag.Link) {
            curNode = curNode.getlChild();
        }

        // 开始从前往后根据节点后继信息遍历二叉树
        while (curNode != null) {
            curNode.visit();

            // 如果右指针是线索，则当前节点的后继即为右指针所指节点
            if (curNode.getrTag() == BiThrNode.PointerTag.Thread) {
                curNode = curNode.getrChild();

            }
            // 如果右指针不是线索，则当前节点的后继为右子树的最靠左下方的叶子节点
            else {
                curNode = curNode.getrChild();
                while (curNode != null && curNode.getlTag() == BiThrNode.PointerTag.Link) {
                    curNode = curNode.getlChild();
                }
            }
        }
    }

    /**
     * 线索化二叉树
     */
    protected void inOrderThreading() {
        this.preNode = null;
        inOrderThreading(this.rootNode);
    }

    protected void inOrderThreading(BiThrNode<T> entryNode) {
        if (entryNode == null) {
            return;
        }

        // 处理左子树
        inOrderThreading(entryNode.getlChild());

        // 处理当前节点：若左指针为空，则将左指针指向前驱节点
        if (entryNode.getlChild() == null) {
            entryNode.setlChild(this.preNode);
            entryNode.setlTag(BiThrNode.PointerTag.Thread);
        }

        // 处理前驱节点：若前驱节点非空，则将前驱节点的后继指向当前节点
        if (this.preNode != null && this.preNode.getrChild() == null) {
            this.preNode.setrChild(entryNode);
            this.preNode.setrTag(BiThrNode.PointerTag.Thread);
        }

        // 更新preNode
        this.preNode = entryNode;

        // 处理右子树
        inOrderThreading(entryNode.getrChild());
    }

    @Override
    public void postOrderTraverse() {
        this.postOrderTraverse(this.rootNode);
    }

    protected void postOrderTraverse(BiThrNode<T> entryNode) {
        if (entryNode != null) {
            BiThrNode<T> lchild = entryNode.getlChild();
            if (lchild != null && entryNode.getlTag() == BiThrNode.PointerTag.Link) {
                this.postOrderTraverse(lchild);
            }

            BiThrNode<T> rchild = entryNode.getrChild();
            if (rchild != null && entryNode.getrTag() == BiThrNode.PointerTag.Link) {
                this.postOrderTraverse(rchild);
            }

            entryNode.visit();
        }
    }

    @Override
    public void levelOrderTraverse() {
        Queue<BiThrNode<T>> queue = new LinkedList<>();
        queue.add(this.rootNode);
        while (!queue.isEmpty()) {
            BiThrNode<T> node = queue.poll();
            node.visit();

            BiThrNode<T> lchild = node.getlChild();
            if (lchild != null && node.getlTag() == BiThrNode.PointerTag.Link) {
                queue.add(lchild);
            }
            BiThrNode<T> rchild = node.getrChild();
            if (rchild != null && node.getrTag() == BiThrNode.PointerTag.Link) {
                queue.add(rchild);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
