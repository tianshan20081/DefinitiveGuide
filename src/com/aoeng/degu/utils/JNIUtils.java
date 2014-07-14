package com.aoeng.degu.utils;

public class JNIUtils {
	static {

		System.loadLibrary("hello-jni");
	}

	public native static String msgFromJNI();

	public native static int getSum(int i, int j);

	public native static int getFactorial(int i);

	public native static int[] getArraySort(int[] its);

}
