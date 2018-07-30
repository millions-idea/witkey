package com.wanhao.proback;

import java.util.Arrays;

/**
 * Created by LiuLiHao on 2018/7/30 20:53.
 * 描述：
 * 作者： LiuLiHao
 */
public class TestJava {



    public static void main(String[] args) {
        int[] arr = {55,90,32,1,12,44};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr) {
        quickSort(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low>high){
            return;
        }
        int i=low,j=high,key=arr[low];
        //循环
        while (i<j){
            while (i<j && arr[j] > key){
                j--;
            }
            while (i<j && arr[i] <= key){
                i++;
            }
            if (i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //和arr[low]交换
        int temp = arr[i];
        arr[i] = arr[low];
        arr[low] = temp;

        quickSort(arr,low,i-1);
        quickSort(arr,i+1,high);
    }

}
