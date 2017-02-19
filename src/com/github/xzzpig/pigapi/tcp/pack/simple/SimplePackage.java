package com.github.xzzpig.pigapi.tcp.pack.simple;

import com.github.xzzpig.pigapi.tcp.pack.Package;

public class SimplePackage implements Package{
	private byte[] data;
	
	public SimplePackage(byte[] data) {
		this.data = data;
	}
	
	@Override
	public int size() {
		return data.length;
	}
	
	@Override
	public byte[] getData() {
		return data;
	}
}
