package com.ecjtu.hht;

/**
 * 环形队列 不借助已有的类型List
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 *
 * @author hht
 * @date 2019/8/30 17:29
 */
public class MyCircleQueue<T> {
    private T[] data;
    MyCircleQueue(int size){
       // data=new T[1];
    }


}
