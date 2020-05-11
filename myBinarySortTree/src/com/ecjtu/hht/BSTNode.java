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
    private Comparator<T> comparator;


    /**
     * 有参构造 添加一个根结点
     */
    public BSTNode(T value) {
        this.value = value;
    }

    /**
     * 比较器添加
     */
    public BSTNode(Comparator<T> comparator) {
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

        if (this.value == null) this.value = newValue;

        if (compare(value,this.value)>0) {

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
