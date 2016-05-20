package com.github.xzzpig.pigapi;

import java.util.Arrays;
import java.util.List;

public class Debuger {
	public static long time;

	public static boolean debug = false;

	public static void print(Object s) {
		if (debug == false)
			return;
		if(s instanceof Exception){
			((Exception) s).printStackTrace();
			return;
		}
		else if (s instanceof List<?>) {
			System.err.println(Arrays.toString(((List<?>) s).toArray()));
			return;
		}
		else if (s.getClass().isArray()) {
			System.err.println(Arrays.toString((Object[]) s));
			return;
		}
		System.err.println(s);
	}

	public static void timeStart() {
		time = System.nanoTime();
	}

	public static void timeStop(String s) {
		Debuger.print(s + (System.nanoTime() - time));
	}
}
