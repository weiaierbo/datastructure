package com.xk.search;

/** 顺序查找
 * @author xk
 * @create 2021.07.16 19:58
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {1,5,4,7,3};
        int index = seqSearch(arr,4);
        if(index < 0){
            System.out.println("没找到");
        } else {
            System.out.println("找到了，下标："+index);
        }

    }

    public static int seqSearch(int[] arr,int value){

        for(int i=0;i<arr.length;i++){
            if(arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
