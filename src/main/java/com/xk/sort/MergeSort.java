package com.xk.sort;

import java.util.Arrays;

/**归并排序
 * @author xk
 * @create 2021.07.16 10:04
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] arr = {2,5,7,1,8,3,0,9,6};
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 分治排序
     * @param arr
     * @param start
     * @param end
     */
    public static void mergeSort(int[] arr,int start,int end){

        if(start < end){
            int mid = (start+end)/2;
            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            int[] temp = new int[end-start+1];
            merge(arr,start,mid,end,temp);
        }

    }
    /**
     *
     * @param arr
     * @param left 左边界
     * @param mid 中间元素
     * @param right 右边界
     * @param temp
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i=left;
        int j = mid+1;
        int tempIndex = 0;
        while(i<=mid && j<=right){
            if(arr[i] > arr[j]){
                temp[tempIndex] = arr[j];
                tempIndex++;
                j++;
            } else {
                temp[tempIndex] = arr[i];
                tempIndex++;
                i++;
            }
        }
        // 合并剩余的元素
        while(i<=mid){
            temp[tempIndex]=arr[i];
            tempIndex++;
            i++;
        }
        while(j<=right){
            temp[tempIndex]=arr[j];
            tempIndex++;
            j++;
        }
        tempIndex=0;
        //复制回原数组
        while(tempIndex<temp.length){
            arr[left++] = temp[tempIndex++];
        }

    }
}

