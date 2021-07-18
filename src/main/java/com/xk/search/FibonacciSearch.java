package com.xk.search;

/**
 * @author xk
 * @create 2021.07.16 20:39
 */
public class FibonacciSearch {

    public static void main(String[] args) {
        int[] arr={1,4,6,3,7};
    }

    public static int[] fibo(){
        int[] arr = new int[20];
        arr[0]=1;
        arr[1]=1;
        for(int i=2;i<20;i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return arr;
    }

}
