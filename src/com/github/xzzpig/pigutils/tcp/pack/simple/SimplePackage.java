package com.github.xzzpig.pigutils.tcp.pack.simple;

import com.github.xzzpig.pigutils.tcp.pack.Package;

public class SimplePackage implements Package {
	private byte[] data;

	public SimplePackage(byte[] data) {
		this.data = data;
	}

	@Override
	public byte[] getData() {
		return data;
	}

	@Override
	public int size() {
		return data.length;
	}
}
