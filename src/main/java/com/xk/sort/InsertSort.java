package com.xk.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author xk
 * @create 2021.07.13 20:25
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {1,0,4,3,7,2,9};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            int insertVal = arr[i];
            int insertIndex = i-1;
            while(insertIndex>=0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex --;
            }
            arr[++insertIndex] = insertVal;
        }
    }
}
