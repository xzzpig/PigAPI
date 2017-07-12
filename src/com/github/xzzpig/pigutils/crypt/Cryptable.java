package com.github.xzzpig.pigutils.crypt;

public interface Cryptable {
	String crypt();

	boolean match(Object obj);
}
