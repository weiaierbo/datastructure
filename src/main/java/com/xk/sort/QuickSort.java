package com.xk.sort;

import java.util.Arrays;

/**
 * @author xk
 * @create 2021.07.14 20:10
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1,2,5,4,0,6};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

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
}
