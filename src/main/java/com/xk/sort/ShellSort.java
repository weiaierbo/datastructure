package com.xk.sort;

import java.util.Arrays;

/**希尔排序，是插入排序的升级版
 * @author xk
 * @create 2021.07.14 9:07
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr={3,2,5,1,6,8,9,7,0};
        /*shellSort(arr);*/
        shellSort2(arr);
    }

    public static void shellSort(int[] arr) {
        for(int gap=arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                for(int j=i-gap;j>=0;j-=gap){
                    if(arr[j]>arr[j+gap]){
                        int temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
        }

    }

    /**
     * 希尔排序优化版，采用移位法
     * @param arr
     */
    public static void shellSort2(int[] arr){
        System.out.println("-------------");
        for(int gap = arr.length/2;gap>0;gap/=2){
            for(int i=gap;i<arr.length;i++){
                int j=i;
                int temp = arr[j];
                while(j-gap>=0 && temp<arr[j-gap]){
                    arr[j] = arr[j-gap];
                    j-=gap;
                }
                arr[j] = temp;
                System.out.println(Arrays.toString(arr));
            }
        }
    }
}
