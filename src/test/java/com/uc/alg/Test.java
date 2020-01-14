package com.uc.alg;

import java.util.Arrays;

public class Test {

    public static void quickSort(int[] array, int indexStart, int indexEnd) {
        int pivotIndex = (indexStart + indexEnd) / 2;
        // swap
        swap(array, pivotIndex, indexEnd);

        int k = partition(array, indexStart - 1, indexEnd, array[indexEnd]);
        swap(array, k, indexEnd);
        if ((k - indexStart) > 1)
            quickSort(array, indexStart, k - 1);
        if ((indexEnd - k) > 1)
            quickSort(array, k + 1, indexEnd);
    }

    private static int partition(int[] array, int left, int right, int pivot) {
        do {
            while (array[++left] < pivot)
                ;
            while ((right != 0) && array[--right] > pivot)
                ;
            swap(array, left, right);
        } while (left < right);
        swap(array, left, right);
        return left;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {13,16,10,8,9,12,30,21,14};
        quickSort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(t->System.out.print(t + " "));
    }
}
