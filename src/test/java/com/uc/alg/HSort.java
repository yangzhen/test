package com.uc.alg;

import java.util.Arrays;

public class HSort {

    public static void main(String[] args) {
        int[] arr = {2,3,45,72,8,10,4,11};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void adjustHeap(int[] a, int i, int size) {
        int lChild=2*i+1;
        int rChild=2*i+2;
        int max = i;
        if(i<size/2){
            if(lChild<size&&a[max]<a[lChild]){
                max=lChild;
            }
            if(rChild<size&&a[max]<a[rChild]){
                max=rChild;
            }
            if(max!=i) {
                swap(a,max,i);
                adjustHeap(a,max,size);
            }
        }
    }

    public static void buildHeap(int[] a,int size) {
        for(int i=size/2;i>=0;i--){
            adjustHeap(a,i,size);
        }
    }

    public static void sort(int[] arr){
        int size = arr.length;
        buildHeap(arr, size);
        for(int i=size-1;i>0;i--){
            swap(arr,i,0);
            adjustHeap(arr,0,i);
        }

    }

    public  static void swap(int a[],int i, int j) {
        int temp = a[i];
        a[i]=a[j];
        a[j]=temp;
    }

}
