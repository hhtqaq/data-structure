package com.ecjtu.hht;

import java.util.Comparator;

/**
 * 二叉查找树的创建
 *
 * @author hht
 * @date 2020/5/11 11:08
 */
public class BSTNode {

    /**
     * 数据域
     */
    private long value;

    /**
     * 左孩子结点
     */
    private BSTNode leftChildNode;

    /**
     * 右孩子节点
     */
    private BSTNode rightChildNode;


    /**
     * 有参构造 添加一个根结点
     */
    public BSTNode(long value) {
        this.value = value;
    }

    /**
     * 有参构造 添加一个根结点
     */
    public BSTNode() {
    }

    /**
     * 添加结点
     */
    public static void insertNode(BSTNode bstNode, long newValue) {
        if (bstNode == null) {
            System.out.println(bstNode);
            bstNode = new BSTNode(newValue);
            bstNode.leftChildNode = bstNode.rightChildNode = null;
        } else if (bstNode.value > newValue) {
            insertNode(bstNode.rightChildNode, newValue);
        } else {
            insertNode(bstNode.leftChildNode, newValue);
        }
    }


    /**
     * 中序遍历
     */
    public void inTraverseTree(BSTNode rootNode) {
        if (rootNode != null) {
            inTraverseTree(rootNode.leftChildNode);
            System.out.println(rootNode.value);
            inTraverseTree(rightChildNode.rightChildNode);
        }
    }


    public static void main(String[] args) {
        BSTNode bstNode = new BSTNode(4);
        insertNode(bstNode, 2);
        insertNode(bstNode, 3);
        //insertNode(bstNode, 6);
        // bstNode.insertNode(4L).insertNode(2L).insertNode(3L).insertNode(6L);
        //bstNode.inTraverseTree();
    }

}
