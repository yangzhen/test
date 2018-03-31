package com.uc.alg;

public class ArrayOne {

    public int singleNumber(int[] A) {   //待求数组A
        int temp = 0;
        for(int i = 0 ; i < A.length; i++){
            temp ^= A[i];            //异或运算
        }
        return temp;
    }

    public static   int singleNumber3(int[] A) {
        int bits[] = new int[32]; //定义一个32位数组
        for (int i = 0; i < bits.length; i++) {
            bits[i]=0;			//初始化  数组中所有值为0
        }
        for (int i = 0; i < A.length; i++) {    	//对数组中每一个数字
            for (int j = 0; j < bits.length; j++) {	//这个数字的每一位
                bits[j] += ((A[i] >> j) & 1);		//记录这个位上是否为1，为1的话 bits数组就加1
            }
        }
        int result = 0 ;
        for (int j = 0; j < 32; j++)
            if (bits[j] % 3 != 0)  		//对3取余，是3的倍数的取余后都为0，剩下的就是我们要的
                result += (1 << j);       //把记录的二进制他转化成整形数字
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,5,5,5,7};
        System.out.println(singleNumber3(arr));
    }

}
