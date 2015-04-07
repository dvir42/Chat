package com.github.dvir42.screenshare.p2p.utils;

import java.util.ArrayList;

public class ArrayUtils {

	public static String arrayToString(String[] arr) {
		ArrayList<String> al = new ArrayList<String>();
		for (String s : arr)
			al.add(s);
		return al.toString();
	}

	public static String arrayToString(String[] arr, int startIndex) {
		ArrayList<String> al = new ArrayList<String>();
		for (int i = startIndex; i < arr.length; i++)
			al.add(arr[i]);
		return al.toString();
	}

	public static String arrayToString(String[] arr, int startIndex,
			int endIndex) {
		ArrayList<String> al = new ArrayList<String>();
		for (int i = startIndex; i < endIndex; i++)
			al.add(arr[i]);
		return al.toString();
	}

	public static String[] stringToArray(String str) {
		ArrayList<String> al = new ArrayList<String>();
		str = str.substring(1, str.length() - 1);
		for (String s : str.split(", ")) {
			al.add(s);
		}
		String[] arr = new String[al.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = al.get(i);
		}
		return arr;
	}

}
