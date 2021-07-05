package com.xk.queue;

/**
 * @author xk
 * @create 2021.07.04 18:24
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {

    }
}

class ArrayQueue{
    //最大容量
    private int maxSize;
    //头指针，指向第一个元素的前一个位置
    private int front;
    //尾指针，指向最后一个元素
    private int rear;

    private int[] array;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize-1;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public boolean addQueue(int n){
        if(isFull()){
            throw new RuntimeException("队列已满");
        }
        rear++;
        array[rear] = n;
        return true;
    }

    public int removeQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return array[front];
    }

}
