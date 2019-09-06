package com.ecjtu.hht;

import java.lang.reflect.Type;

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

    public static void main(String[] args) {
        MyCircleQueue<Integer> myCircleQueue = new MyCircleQueue<>(3);
        myCircleQueue.enQueue(1);
        myCircleQueue.enQueue(2);
        myCircleQueue.enQueue(3);
        System.out.println(myCircleQueue.front());
        myCircleQueue.enQueue(4);
    }

    private Object[] data;
    private int front;
    private int rear;
    private int size;
    private int count;

    MyCircleQueue(int size) {
        data = new Object[size];
        rear = front = -1;
        this.size = size;
        count = 0;
    }

    /**
     * 向循环队列插入一个元素。如果成功插入则返回真。
     *
     * @param value
     * @return
     */
    public boolean enQueue(T value) {
        if (isFull()) {
            throw new RuntimeException("队列已经满了");
        }
        rear = (rear + 1) % size;
        data[rear] = value;
        count++;
        return true;
    }

    /**
     * 从循环队列中删除一个元素。如果成功删除则返回真。
     *
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front = (front + 1) % size;
        count--;
        return true;
    }

    public T front() {
        return front == -1 ? (T) data[front + 1] : (T) data[front];
    }

    public T rear() {
        return (T) data[rear];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }
}
