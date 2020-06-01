package com.ecjtu.hht;

/**
 * 实现一颗平衡二叉树
 *
 * @author hht
 * @date 2020/5/11 10:36
 */
public class AvlTree {


    /**
     * 根结点
     */
    private TreeNode rootNode;


    private class TreeNode {
        /**
         * 结点高度
         */
        private int height = 0;
        /**
         * 左孩子节点
         */
        private TreeNode leftNode;

        /**
         * 值
         */
        private long value;
        /**
         * 右孩子节点
         */
        private TreeNode rightNode;

        /**
         * 构造方法
         *
         * @param val 值
         */
        public TreeNode(long val) {
            this.value = val;
        }

    }

    /**
     * 结点的高度
     *
     * @param treeNode 结点
     * @return 返回结点的高度 0代表
     */
    private int height(TreeNode treeNode) {
        return treeNode == null ? 0 : treeNode.height;
    }

    /**
     * 计算最大值
     *
     * @param a a
     * @param b b
     * @return a、b中最大的值
     */
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    /**
     * 获取平衡因子   该节点的左右子树的深度之差
     *
     * @param treeNode
     * @return
     */
    private int getBalance(TreeNode treeNode) {
        return treeNode == null ? 0 : height(treeNode.leftNode) - height(treeNode.rightNode);
    }

    /**
     * 插入结点
     *
     * @param val 结点值
     */
    public void insertNode(long val) {
        rootNode = insert(rootNode, val);
    }


    public TreeNode insert(TreeNode rootNode, long val) {

        //如果根节点为null
        if (rootNode == null) {
            return new TreeNode(val);
        } else if (rootNode.value < val) {
            //插入左子树
            rootNode.leftNode = insert(rootNode.leftNode, val);
        } else if (rootNode.value > val) {
            //插入右子树
            rootNode.rightNode = insert(rootNode.rightNode, val);
        } else {
            return rootNode;
        }
        // 每次递归一次 更新结点高度+1
        rootNode.height = 1 + max(height(rootNode.leftNode), height(rootNode.rightNode));

        //这里已经是插入完的

        //获取平衡因子
        int balance = getBalance(rootNode);

        return rootNode;
    }

}
