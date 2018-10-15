package com.uc.j8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

import com.google.common.collect.Lists;

public class Hhhh {
	/**
	 * 偶数房前，指数放后
	 */
	@Test
	public void test() {
		int[] a = { 1, 3, 4, 8, 5, 7 };
		for (int i = 0, j = a.length - 1; i < a.length && j >= 0 && i <= j;) {
			int h = a[i];
			if (h % 2 == 0) {
				i++;
			} else {
				int k = a[j];
				if (k % 2 == 0) {
					swap(a, i, j);
				} else {
					j--;
				}
			}
		}
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	public void swap(int[] a, int i, int j) {
		System.out.println(i + "," + j);
		int temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}

//	public static void main(String[] args) {
//		//System.out.println(get("a","b"));
//		//System.out.println(5%2);
//		File file = new File("/Users/yangzhen/logs");
//		File[] fl = file.listFiles();
//		for (int i = 0; i < 50; i++) {
//			for (File f : fl) {
//				try {
//					System.out.println(f.getPath());
//					Files.lines(Paths.get(f.getPath()));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public static int func() {
		try {
			return 1;
		} catch (Exception e) {
			return 2;
		} finally {
			return 3;
		}
	}

	public static String get(String hh, String b) {
		String a = "a";
		try {
			a = a + "b";
			Integer.parseInt(hh);
			return a;
		} catch (Exception e) {
			a += "c";
			return a;
		} finally {
			a += "d";
			return a;
		}
	}

	public static void main(String[] args) {

			int array[ ][ ]=new int[3][ ];
			array[0]=new int[4];
			array[1]=new int[2];
			array[2]=new int[3];
			System.out.println(array.length+"\t"+array[1].length);


		Integer a = 345;
		Integer b = 777;
		Integer c = 999;
		a = b = c;
		System.out.println(a+","+b+","+c);
		System.out.println(get("1","b"));
		System.out.println(get("1a1","b"));
		System.out.println(func());
	}
}
