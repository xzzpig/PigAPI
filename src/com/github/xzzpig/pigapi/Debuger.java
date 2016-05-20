package com.github.xzzpig.pigapi;

public class Debuger {
	public static long time;

	private static boolean debug = false;

	public static void print(Object s) {
		if (debug == false)
			return;
		System.err.println(s);
	}

	public static void timeStart() {
		time = System.nanoTime();
	}

	public static void timeStop(String s) {
		Debuger.print(s + (System.nanoTime() - time));
	}
}
