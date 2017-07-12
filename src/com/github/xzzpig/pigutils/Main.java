package com.github.xzzpig.pigutils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.github.xzzpig.pigutils.io.GroupInputStream;
import com.github.xzzpig.pigutils.io.IOBinder;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fin = new FileInputStream("D:\\putty.exe");
		GroupInputStream gin = new GroupInputStream(fin);
		FileOutputStream fout = new FileOutputStream("D:\\putty2.exe");
		new IOBinder<>(gin.getSub(), fout).start();
		gin.getSub();
		new IOBinder<>(gin.getSub(), System.out).start();
		gin.start();
	}

}
