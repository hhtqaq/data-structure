package com.ecjtu.hht;

import jdk.nashorn.internal.ir.BinaryNode;
import org.jetbrains.annotations.Contract;

/**
 * 二叉查找树的创建
 *
 * @author hht
 * @date 2020/5/11 11:08
 */
public class BSTree {


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

        /**
         * 找到某个结点的父节点
         *
         * @param val
         * @return
         */
        public TreeNode findParent(long val) {
            if (val < this.val) {
                if (this.leftNode == null) {
                    return null;
                } else if (this.leftNode.val == val) {
                    return this;
                } else {
                    return this.leftNode.findParent(val);
                }
            } else {
                if (this.rightNode == null) {
                    return null;
                } else if (this.rightNode.val == val) {
                    return this;
                } else {
                    return this.rightNode.findParent(val);
                }
            }
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

    /**
     * 根据值找到节点
     *
     * @param val 值
     * @return 返回该节点
     */
    private TreeNode findNode(long val) {
        return recursiveSearchTree(this.rootNode, val);
    }

    /**
     * 递归查找
     *
     * @param rootNode 根节点
     * @param val      值
     * @return 找到的节点
     */
    @Contract("null, _ -> null")
    private TreeNode recursiveSearchTree(TreeNode rootNode, long val) {
        if (rootNode == null) {
            return null;
        }
        if (val > rootNode.val) {
            return recursiveSearchTree(rootNode.rightNode, val);
        } else if (val < rootNode.val) {
            return recursiveSearchTree(rootNode.leftNode, val);
        } else {
            //等于就返回
            return rootNode;
        }
    }

    /**
     * 找到父节点
     *
     * @param val 值
     * @return 父节点
     */
    public TreeNode findParent(long val) {
        //如果根结点为空 返回null
        if (rootNode == null) {
            return null;
        } else if (val == rootNode.val) {
            //如果找到的是根节点 根结点的父节点还是null
            return null;
        } else if (val < rootNode.val) {
            //小于就找左子树
            if (rootNode.leftNode == null) {
                //左子树为null 返回null
                return null;
            } else if (val == rootNode.leftNode.val) {
                //如果左子树的值等于val 根节点
                return rootNode;
            } else {
                //如果不等于的话   继续查找
                return rootNode.leftNode.findParent(val);
            }
        } else {
            //如果大于 就找右子树
            if (rootNode.rightNode == null) {
                return null;
            } else if (val == rootNode.rightNode.val) {
                return rootNode;
            } else {
                return rootNode.rightNode.findParent(val);
            }

        }

    }


    /**
     * 删除节点
     * <p>
     * 功能实现了  但是思路有点绕  应该先判断待删除结点是左孩子还是有孩子
     * 就不用每个都判断一次
     *
     * @param val 待删除结点的值
     * @return 成功=true   失败=false
     */
    public boolean removeNode(long val) {

        //空树
        if (rootNode == null) {
            throw new NullPointerException("empty  tree");
        }
        //找到待删除结点
        TreeNode targetNode = findNode(val);
        //todo 待删除结点为根节点
        TreeNode parent = findParent(val);
        if (targetNode == null) {
            return false;
        }
        //如果待删除结点为叶子结点   左右子树都为空
        if (targetNode.leftNode == null && targetNode.rightNode == null) {
            //将双亲节点指向为null
            if (targetNode.val == parent.leftNode.val) {
                parent.leftNode = null;
                return true;
            }
            if (targetNode.val == parent.rightNode.val) {
                parent.rightNode = null;
                return true;
            }
            //如果待删除结点的左孩子不为空 右孩子为空  直接修改双亲结点的 孩子结点的指向
        } else if (targetNode.leftNode != null && targetNode.rightNode == null) {

            //如果待删除结点是右子树  修改双亲节点右孩子的引用为待删除结点的左孩子结点
            if (targetNode.val == parent.rightNode.val) {
                parent.rightNode = targetNode.leftNode;
                return true;
            }

            //如果待删除结点是左子树 修改双亲节点的左孩子引用为待删除结点的左孩子结点
            if (targetNode.val == parent.leftNode.val) {
                parent.leftNode = targetNode.leftNode;
                return true;
            }
            //如果待删除结点的右孩子不为空 左孩子为空 直接修改双亲节点的孩子结点的引用
        } else if (targetNode.leftNode == null) {

            //如果待删除结点是右子树 修改双亲节点的右孩子为待删除结点的右孩子结点
            if (targetNode.val == parent.rightNode.val) {
                parent.rightNode = targetNode.rightNode;
                return true;
            }

            //如果待删除结点是左子树 修改双亲结点的左孩子为待删除结点的右孩子结点
            if (targetNode.val == parent.leftNode.val) {
                parent.leftNode = targetNode.rightNode;
                return true;
            }
            //如果待删除结点的左孩子结点和右孩子结点都不为空
        } else {

            //遍历待删除结点的左子树 找到最大值 也就是最右节点
            TreeNode maxNode = targetNode.leftNode;
            while (maxNode.rightNode != null) {
                maxNode = maxNode.rightNode;
            }
            //如果有最右节点
            if (targetNode.leftNode != maxNode) {
                targetNode.val = maxNode.val;
                maxNode = null;
                return true;
            } else {

                //重接右子树
                targetNode.leftNode.rightNode = targetNode.rightNode;
                //如果待删除结点为左子树
                if (parent.leftNode.val == targetNode.val) {
                    parent.leftNode = targetNode.leftNode;
                    return true;
                }
                //待删除结点为右子树
                if (parent.rightNode.val == targetNode.val) {
                    parent.rightNode = targetNode.leftNode;
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        BSTree bsTree = new BSTree();
        bsTree.insertNode(50);
        bsTree.insertNode(35);
        bsTree.insertNode(70);
        bsTree.insertNode(60);
        bsTree.insertNode(80);
        bsTree.insertNode(30);
        bsTree.insertNode(40);
        bsTree.insertNode(37);
        bsTree.insertNode(15);
        bsTree.insertNode(32);
        bsTree.insertNode(7);
        bsTree.insertNode(18);
        bsTree.insertNode(44);
        bsTree.insertNode(55);
        // bsTree.insertNode(63);
        //中序遍历 就能直接排序了
        bsTree.inTraverseTree();


        System.out.println(bsTree.removeNode(100));

        bsTree.inTraverseTree();

        // System.out.println(bsTree.search(2));


    }

}
