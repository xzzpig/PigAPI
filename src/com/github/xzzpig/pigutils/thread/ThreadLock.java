package com.github.xzzpig.pigutils.thread;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadLock {

	private Vector<Thread> threads;

	private AtomicBoolean locked;

	public ThreadLock() {
		locked = new AtomicBoolean(false);
		threads = new Vector<>();
	}

	public void lock() {
		threads.clear();
		locked.set(true);
	}

	public void unlock() {
		locked.set(false);
		while (threads.size() != 0)
			;
	}

	public void waitUnlock() {
		while (locked.get())
			;
	}

	public void waitLock() {
		while (!locked.get())
			;
	}

	public boolean isLocked() {
		return locked.get();
	}

	public void prepare() {
		waitUnlock();
		waitLock();
		threads.add(Thread.currentThread());
		waitUnlock();
	}

	public void prepared() {
		threads.remove(Thread.currentThread());
	}

	public boolean isPrepared() {
		return threads.contains(Thread.currentThread());
	}
}
