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
     * 根结点
     */
    TreeNode rootNode;

    static class TreeNode {
        long val;
        TreeNode leftNode;
        TreeNode rightNode;

        public TreeNode(long val) {
            this.val = val;
        }
    }


    /**
     * 插入节点
     *
     * @param newVal
     */
    public void insertNode(long newVal) {
        rootNode = insertNode(rootNode, newVal);
    }

    public TreeNode insertNode(TreeNode rootNode, long newVal) {
        /**
         * 如果根结点为空  创建根结点
         */
        if (rootNode == null) {
            return new TreeNode(newVal);
        } else if (newVal > rootNode.val) {
            //如果大于新的值
            rootNode.rightNode = insertNode(rootNode.rightNode, newVal);
        } else {
            rootNode.leftNode = insertNode(rootNode.leftNode, newVal);
        }

        return rootNode;
    }


    /**
     * 中序遍历
     */
    public void inTraverseTree(BSTNode rootNode) {
    }


    public static void main(String[] args) {
        BSTNode bstNode = new BSTNode();
        bstNode.insertNode(4);
        bstNode.insertNode(2);
        bstNode.insertNode(3);
        bstNode.insertNode(6);
    }

}
