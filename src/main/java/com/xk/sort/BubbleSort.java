package com.xk.sort;

import com.xk.global.GlobalUtil;

import java.util.Arrays;

/**
 * @author xk
 * @create 2021.07.13 9:12
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,-1,3,6,20,4,8,21};
        System.out.print("排序前:");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.print("排序后:");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序 O(n^2)
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        int count=0;
        //优化,标识内部循环是否发生过交换
        boolean flag = false;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                count++;//7+6+5+4+3+2+1
                if(arr[j] > arr[j+1]){
                    flag = true;
                    GlobalUtil.swap(arr,j,j+1);
                }
            }

            if(!flag){
                break;
            }else{
                flag = false;
            }
            System.out.println("第"+i+"次排序结果:"+Arrays.toString(arr));
        }
        System.out.println("总次数:"+count);
    }
}
