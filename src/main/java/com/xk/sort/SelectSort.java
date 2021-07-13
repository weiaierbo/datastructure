package com.xk.sort;

import com.xk.global.GlobalUtil;

import java.util.Arrays;

/**选择
 * @author xk
 * @create 2021.07.13 12:46
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr={1,4,3,6,2,7};
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr){

        int count=0;
        for(int i=0;i<arr.length-1;i++){
            int mindex = i;
            for(int j=i+1;j<arr.length-1;j++){
                if(arr[mindex]>arr[j]){
                    mindex = j;
                }
            }
            if(mindex != i){
                count++;
                GlobalUtil.swap(arr,i,mindex);
            }
            //System.out.println(Arrays.toString(arr));
        }
        System.out.println("交换总次数:"+count);
    }


}
