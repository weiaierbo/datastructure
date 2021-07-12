package com.xk.stack;

/**
 * @author xk
 * @create 2021.07.07 9:12
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.push(2);
        arrayStack.push(4);
        /*arrayStack.push(1);
        arrayStack.push(5);
        arrayStack.push(3);*/
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.pop();
        arrayStack.show();
    }

}

class ArrayStack{
    // 栈顶，初始为-1
    private int top = -1;

    private int[] stack;

    private int maxSize;//栈容量

    public ArrayStack(int size){
        this.maxSize = size;
        stack = new int[size];
    }

    /**
     * 是否空
     * @return
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 是否满
     * @return
     */
    public boolean isFull(){
        return top == maxSize-1;
    }

    /**
     * 入栈
     * @param n
     * @return
     */
    public boolean push(int n){
        if(isFull()){
            System.out.println("已满");
            return  false;
        }
        stack[++top] = n;
        return true;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if(isEmpty()){
            System.out.println("无数据");
            throw new RuntimeException("空栈");
        }
        int value = stack[top--];
        return value;
    }

    public void show(){
        if(isEmpty()){
            System.out.println("空栈");
            return;
        }

        for(int i=top;i>=0;i--){
            System.out.println(stack[i]);
        }
    }


}
