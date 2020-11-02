package com.uc.alg;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] arr,int left, int right) {
        if(left>right) {
            return;
        }
        int pivot = arr[left];
        int i=left;
        int j=right;

        while(i<j) {
            while(pivot<=arr[j]&&i<j) {
                j--;
            }
            while(pivot>=arr[i]&&i<j) {
                i++;
            }
            if(i<j) {
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        System.out.print(" ");
        arr[left]=arr[i];
        arr[i]=pivot;
        quickSort(arr,left,i-1);
        quickSort(arr,i+1,right);
    }

    public static void main(String[] args) {
        int arr[] = {13,16,10,8,9,12,30,21,12,14};
        quickSort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(t->System.out.print(t + " "));
    }




}
