package com.xk.global;

/**
 * @author xk
 * @create 2021.07.13 9:37
 */
public class GlobalUtil {

    public static void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
