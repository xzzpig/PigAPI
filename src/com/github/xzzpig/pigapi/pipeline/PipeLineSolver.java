package com.github.xzzpig.pigapi.pipeline;

public interface PipeLineSolver<T, R> {
	public R solve(T t);
}
