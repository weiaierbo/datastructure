package com.xk.search;

/** 二分查找
 * @author xk
 * @create 2021.07.16 20:01
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,3,4,6,7,8};
        int search = binarySearch(arr, 300);
        if(search < 0){
            System.out.println("未找到");
        } else {
            System.out.println("找到了，下标:"+search);
        }
    }

    /**
     * 二分查找
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearch(int[] arr,int value){
        //对于插值查找，必须需要，否则下面的mid可能越界
        if(value < arr[0] || value > arr[arr.length-1]){
            System.out.println("不存在");
            return -1;
        }
        int left = 0;
        int right = arr.length-1;
        while(left <= right){
            // 普通查找
            //int mid = (left+right)/2;
            // 插值查找，适合关键字分布均匀的情况
            int mid = left+((value-arr[left])/(arr[right]-arr[left]))*(right-left);
            if(arr[mid] == value){
                return mid;
            }
            if(arr[mid] > value){
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return -1;
    }

}
