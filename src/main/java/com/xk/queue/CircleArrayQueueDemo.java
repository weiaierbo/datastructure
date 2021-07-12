package com.xk.queue;

/**循环队列演示
 * @author xk
 * @create 2021.07.04 18:24
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

    }
}

/**
 * 循环队列
 */
class CircleArrayQueue{
    //最大容量
    private int maxSize;
    //头指针，指向第一个元素
    private int front;
    //尾指针，指向最后一个元素的下一个元素
    private int rear;

    private int[] array;

    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = 0;
        rear = 0;
    }

    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }
    
    public boolean addQueue(int n){
        if(isFull()){
            throw new RuntimeException("队列已满");
        }

        array[rear] = n;
        rear = (rear+1)%maxSize;
        return true;
    }

    public int removeQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int temp = front++;
        front = front%maxSize;
        return array[temp];
    }

    public void showQueue(){

        for(int i=front;i<front+size();i++){
            System.out.printf("下标%d,%d",i%maxSize,array[i%maxSize]);
        }

    }

    public int size(){
        return (rear+maxSize-front)%maxSize;
    }
}
