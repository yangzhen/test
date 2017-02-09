package com.uc.j8;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by yangzhen on 17/1/4.
 */
public class QuickSortTest {

    @Test
    public void test() {
        int[] arr = {4, 8, 3, 55, 2, 11, 10};
        partition(arr, 0, arr.length - 1);
    }


    public void partition(int[] arr, int start, int end) {
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+ "  ");
        }
        System.out.println("=============,"+start+","+end);

        if (start < end) {
            int left = start;
            int right = end;
            int pos = arr[start];
            while (left < right) {
                while (left < right && arr[right] > pos) {
                    right--;
                }
                arr[left] = arr[right];
                while (left < right && arr[left] < pos) {
                    left++;
                }
                arr[right] = arr[left];
            }
            arr[left] = pos;
            partition(arr, start, left - 1);
            partition(arr, left + 1, end);
        }
    }

    public void swap(int arr[], int k, int j) {
        int temp = arr[k];
        arr[k] = arr[j];
        arr[j] = temp;
    }
}
