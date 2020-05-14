package com.ecjtu.hht;

import java.util.Comparator;

/**
 * 二叉查找树的创建
 *
 * @author hht
 * @date 2020/5/11 11:08
 */
public class BSTNode<T> {

    /**
     * 数据域
     */
    private T value;

    /**
     * 左孩子结点
     */
    private BSTNode leftChildNode;

    /**
     * 右孩子节点
     */
    private BSTNode rightChildNode;


    /**
     * 比较器
     */
    private final Comparator<? super T> comparator;


    /**
     * 有参构造 添加一个根结点
     */
    public BSTNode(T value) {
        this.value = value;
        comparator = null;
    }

    /**
     * 比较器添加
     */
    public BSTNode(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /**
     * 无参构造
     */
    public BSTNode() {
        this.comparator = null;
    }

    /**
     * 添加结点
     *
     * @return
     */
    public void insertNode(T newValue) {

        //如果插入当前结点值为null  将新的值作为根结点
        if (this.value == null) {
            this.value = newValue;
            return;
        }
        BSTNode newNode = new BSTNode(this.comparator);
        //否则将当前值与新的值比较  > 查找  右子树  < 查找左子树
        if (compare(this.value, newValue) > 0) {
            this.rightChildNode = newNode;
            this.rightChildNode.insertNode(newValue);
        } else {
            this.leftChildNode = newNode;
            this.leftChildNode.insertNode(newValue);
        }
    }

    /**
     * 比较值的大小
     *
     * @param k1
     * @param k2
     * @return
     */
    final int compare(Object k1, Object k2) {
        return comparator == null ? ((Comparable<T>) k1).compareTo((T) k2)
                : comparator.compare((T) k1, (T) k2);
    }

}
