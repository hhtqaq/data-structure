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
         * 结点高度  初始化为1
         */
        private int height = 1;
        /**
         * 左孩子节点
         */
        private TreeNode leftNode;

        /**
         * 值
         */
        private long val;
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
            this.val = val;
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
     * 返回结点的高度
     *
     * @return
     */
    public int height() {
        return height(rootNode);
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
     * @param treeNode 结点
     * @return 平衡因子
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


    private TreeNode insert(TreeNode rootNode, long val) {

        //如果根节点为null
        if (rootNode == null) {
            return new TreeNode(val);
        } else if (rootNode.val > val) {
            //插入左子树
            rootNode.leftNode = insert(rootNode.leftNode, val);
        } else if (rootNode.val < val) {
            //插入右子树
            rootNode.rightNode = insert(rootNode.rightNode, val);
        } else {
            return rootNode;
        }

        //这里已经是插入完的结点了   需要判断是否需要旋转满足平衡

        // 每次递归一次 更新结点高度+1
        rootNode.height = 1 + max(height(rootNode.leftNode), height(rootNode.rightNode));

        //获取平衡因子
        int balance = getBalance(rootNode);

        //根据平衡因子判断旋转情况（不存在=3的情况 ）

        // >1说明是插入左子树，rootNode.leftNode.val > val代表是插入左孩子结点  符合LL型 右旋
        if (balance > 1 && rootNode.leftNode.val > val) {
            //右旋
            return rightRotate(rootNode);
        }

        // >1说明是插入左子树，rootNode.leftNode.val < val代表是插入右孩子结点   符合LR型   先左旋变成LL型再右旋
        if (balance > 1 && rootNode.leftNode.val < val) {
            //先将左子树左旋转为LL型  再右旋
            rootNode.leftNode = leftRotate(rootNode.leftNode);
            return rightRotate(rootNode);
        }

        // <-1说明是插入右子树，rootNode.rightNode.val < val代表是插入右孩子结点   符合RR型   左旋
        if (balance < -1 && rootNode.rightNode.val < val) {
            return leftRotate(rootNode);
        }

        // <-1说明是插入右子树，rootNode.rightNode.val > val代表是插入左孩子结点   符合RL型   右旋变成RR型在左旋
        if (balance < -1 && rootNode.rightNode.val > val) {
            rootNode.rightNode = rightRotate(rootNode.rightNode);
            return leftRotate(rootNode);
        }
        return rootNode;
    }

    /**
     * RR型  左旋
     *
     * @param y 带旋转的结点
     * @return 旋转后结点
     */
    private TreeNode leftRotate(TreeNode y) {
        System.out.println("左旋");
        TreeNode x = y.rightNode;
        TreeNode t = x.leftNode;
        x.leftNode = y;
        y.rightNode = t;
        //修改高度
        y.height = max(height(y.leftNode), height(y.rightNode)) + 1;
        x.height = max(height(x.leftNode), height(x.rightNode)) + 1;
        return x;
    }

    /**
     * LR 左右旋转
     *
     * @param y 待旋转结点
     * @return 旋转后的结点
     */
    private TreeNode leftRightRotate(TreeNode y) {
        return y;
    }

    /**
     * LL 右旋
     *
     * @param y 待旋转结点
     * @return 旋转后的节点
     */
    private TreeNode rightRotate(TreeNode y) {
        System.out.println("右旋");
        TreeNode x = y.leftNode;
        TreeNode t = x.rightNode;
        x.rightNode = y;
        y.leftNode = t;
        //更新高度
        y.height = max(height(y.leftNode), height(y.rightNode)) + 1;
        x.height = max(height(x.leftNode), height(x.rightNode)) + 1;
        return x;
    }

    /**
     * 中序遍历
     */
    public void inTraverseTree() {
        inTraverseTree(this.rootNode);
    }

    /**
     * 中序遍历
     */
    private void inTraverseTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        //遍历左子树
        inTraverseTree(treeNode.leftNode);
        //遍历根节点 这里打印一下
        System.out.println(treeNode.val);
        //遍历右子树
        inTraverseTree(treeNode.rightNode);
    }


    /**
     * 查找key是否在该颗二叉查找树中
     *
     * @param key 待搜索的key
     * @return 返回遍历的次数
     */
    public int search(int key) {
        return inTraverseTree(key);
    }

    /**
     * 中序遍历
     *
     * @param key 待查找的key
     * @return 查找的次数
     */
    private int inTraverseTree(long key) {
        int nums = 0;
        return inTraverseTree(this.rootNode, key, nums);
    }

    /**
     * 中序遍历
     */
    private int inTraverseTree(TreeNode treeNode, long key, int nums) {
        ++nums;
        if (treeNode == null) {
            return -1;
        }
        if (key > treeNode.val) {
            //遍历右子树
            return inTraverseTree(treeNode.rightNode, key, nums);
        } else if (key < treeNode.val) {
            //遍历左子树
            return inTraverseTree(treeNode.leftNode, key, nums);
        } else {
            return nums;
        }
    }

    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        avlTree.insertNode(31);
        avlTree.insertNode(25);
        avlTree.insertNode(47);
        avlTree.insertNode(16);
        avlTree.insertNode(28);
        avlTree.insertNode(26);
        System.out.println(avlTree.height());
        System.out.println(avlTree.search(47));
    }

}
