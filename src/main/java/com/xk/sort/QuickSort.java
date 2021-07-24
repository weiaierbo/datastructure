package com.xk.sort;

import java.util.Arrays;

/**快速排序是对冒泡排序的改进
 * @author xk
 * @create 2021.07.14 20:10
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1,2,5,1,0,4,0,6};
        //quickSort(arr,0,arr.length-1);
        quickSort2(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 教程写法
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int[] arr,int left,int right){
        int l = left;
        int r = right;

        int pivot=arr[(l+r)/2];

        while(l<r){
            while (arr[l] < pivot){
                l++;
            }
            while(arr[r] > pivot){
                r--;
            }

            if(l>=r){
                break;
            }

            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if(arr[l] == pivot){
                r--;
            }
            if(arr[r] == pivot){
                l++;
            }
        }
        if(l==r){
            l++;
            r--;
        }
        if(left<r){
            quickSort(arr,left,r);
        }
        if(right>l){
            quickSort(arr,l,right);
        }

    }

    /**
     * 方案
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort2(int[] arr,int left,int right){

        if(left >= right){
            return;
        }

        int l = left;
        int r = right;

        int pivot=arr[left];

        while(l<r){
            while(l<r&&arr[r] >= pivot){
                r--;
            }
            arr[l] = arr[r];

            while (l<r&&arr[l] <= pivot){
                l++;
            }
            arr[r] = arr[l];

        }
        arr[l] = pivot;

        quickSort2(arr,left,l-1);
        quickSort2(arr,l+1,right);

    }
}
