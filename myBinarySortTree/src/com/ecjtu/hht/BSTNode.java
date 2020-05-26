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
    private TreeNode rootNode;

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
     * @param newVal 新的值
     */
    public void insertNode(long newVal) {
        rootNode = insertNode(rootNode, newVal);
    }

    private TreeNode insertNode(TreeNode rootNode, long newVal) {

        //如果根结点为空  创建根结点
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
     * 中序遍历
     */
    private int inTraverseTree(TreeNode treeNode, int key, int nums) {
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

    /**
     * 中序遍历
     *
     * @param key 待查找的key
     * @return 查找的次数
     */
    private int inTraverseTree(int key) {
        int nums = 0;
        return inTraverseTree(this.rootNode, key, nums);
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
     * 删除节点
     *
     * @param target 待删除的结点值
     * @return 1代表删除成功  -1代表不存在
     */
    public int removeNode(int target) {
        return inTraverseTreeRemove(rootNode, rootNode, target);
    }

    /**
     * 中序遍历删除某个节点
     *
     * @param rootNode   根节点
     * @param removeNode 待删除节点
     * @param target     待删除节点的值
     * @return 1代表删除成功  -1代表不存在
     */
    private int inTraverseTreeRemove(TreeNode rootNode, TreeNode removeNode, int target) {

        if (rootNode == null) {
            return -1;
        }
        //如果大于就递归右子树
        if (target > removeNode.val) {
            rootNode = removeNode;
            return inTraverseTreeRemove(rootNode, rootNode.rightNode, target);
        } else if (target < removeNode.val) {
            //小于就递归左子树
            rootNode = removeNode;
            return inTraverseTreeRemove(rootNode, rootNode.leftNode, target);
        } else {
            // 找到了 删除该节点
            //1 将根节点的左孩子结点指向 待删除节点的左孩子节点
            if (removeNode.leftNode != null) {
                rootNode.leftNode = removeNode.leftNode;
            }
            //2 将根节点的右孩子节点指向 待删除节点的右孩子节点
            if (removeNode.rightNode != null) {
                rootNode.rightNode = removeNode.rightNode;
            }
            rootNode = null;
            return 1;
        }
    }

    public static void main(String[] args) {
        BSTNode bstNode = new BSTNode();
        bstNode.insertNode(4);
        bstNode.insertNode(2);
        bstNode.insertNode(3);
        bstNode.insertNode(6);
        bstNode.insertNode(9);
        bstNode.insertNode(10);
        bstNode.insertNode(1);

        //中序遍历 就能直接排序了
        bstNode.inTraverseTree();

        bstNode.removeNode(2);

        bstNode.inTraverseTree();
        System.out.println(bstNode.search(2));


    }

}
