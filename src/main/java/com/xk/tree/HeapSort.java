package com.xk.tree;

import java.util.Arrays;

/** 堆排序
 * @author xk
 * @create 2021.07.18 17:02
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr={7,-1,2,10,4,6,8,5,9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr){
        //将无需序列构建成一个堆，根据升序或降序需求选择大顶堆或小顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        int temp;
        for(int j= arr.length-1;j>0;j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
    }

    //将一个数组（二叉树）调整成大顶堆

    /**
     * 完成将以i对应的非叶子结点的树调整成大顶堆
     * @param arr
     * @param i 表示非叶子结点在数组中索引
     * @param length 对多少个元素进行调整
     */
    public static void adjustHeap(int[] arr,int i,int length){

        int temp = arr[i];

        for(int k=i*2+1;k<length;k=k*2+1){

            if(k+1<length && arr[k] <arr[k+1]){
                k++;
            }
            // 将元素上移
            if(arr[k]>temp){
                arr[i] = arr[k];
                i=k;
            } else {
                break;
            }
        }
        // 当for循环结束后，我们应将i为父节点的树的最大值，放到了最顶部(局部)
        arr[i] = temp;
    }
}
