package com.ecjtu.hht;

import java.util.ArrayList;

/**
 * 队列
 *
 * @author hht
 * @date 2019/8/29 20:15
 */
public class MyQueue<T> {
    private ArrayList<T> list;

    MyQueue() {
        list = new ArrayList<>();
    }

    /**
     * 入队
     *
     * @param value
     */
    public void enqueue(T value) {
        list.add(value);
    }

    /**
     * 出队
     *
     * @return
     */
    public T dequeue() {
        if (isEmpty())
            throw new RuntimeException("队列为空");
        return list.remove(0);
    }

    //判断是否为空
    public boolean isEmpty() {
        return list.size() == 0;
    }

    public static void main(String[] args) {
        MyQueue<String> queues = new MyQueue<>();
        queues.enqueue("nihao");
        queues.enqueue("woshishui");
        System.out.println(queues.dequeue());
        System.out.println(queues.dequeue());
        System.out.println(queues.dequeue());
    }
}
