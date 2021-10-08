package com.xk.sort;

/**
 * @author xk
 * @since 2021.10.01 12:41
 */
public class MergeSort2 {
    public static void main(String[] args) {
        int[] arr = new int[]{4,1,3,9,6,8};
        sort(arr,0,arr.length-1);
        System.out.println("最终排序后的数组:"+print(arr));
    }

    public static void sort(int[] data,int left,int right){
        if(left>=right){
            return;
        }
        int center = (left+right)/2;
        sort(data,left,center);
        sort(data,center+1,right);

        merge(data,left,center,right);

    }

    private static void merge(int[] data, int left, int center, int right) {
        int[] tempArr = new int[data.length];
        //记录临时数据当前遍历的下标位置
        int third = left;
        //记录临时数组真正有意义的第一个元素的下标，初始化和left相同
        int tmp = left;
        //当前所访问的data数组的据不部分的右部分的第一个元素
        int mid = center+1;

        while(left<=center && mid<=right){
            if(data[left] <= data[mid]){
                tempArr[third++] = data[left++];
            } else {
                tempArr[third++] = data[mid++];
            }
        }
        //将当前data数组的逻辑上的（左右部分）剩下部分合并到tempArr
        while(left<=center){
            tempArr[third++] = data[left++];
        }
        while(mid<=right){
            tempArr[third++] = data[mid++];
        }
        //将临时数组下标left~right部分的有序元素复制回data数组的left~right位置
        while(tmp<=right){
            data[tmp] = tempArr[tmp];
            tmp++;
        }
        System.out.println("归并后的数组:"+print(data));
    }
    static String print(int[] arr){
        String str = "";
        for(int i=0;i<arr.length;i++){
            str+=arr[i]+" ";
        }
        //System.out.println(str);
        return str;
    }
}
