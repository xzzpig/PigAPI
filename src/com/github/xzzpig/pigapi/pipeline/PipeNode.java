package com.github.xzzpig.pigapi.pipeline;

/**
 * @author xzzpig 
 * @param <T> 被处理对象
 * 流水线的处理节点
 */
public interface PipeNode<T> {

	/**
	 * 处理对象
	 * 
	 * @param solver
	 * @return 对象被处理的结果，用于下个处理
	 */
	<R> PipeNode<R> next(PipeLineSolver<T, R> solver);

	/**
	 * @param stayobj
	 *            保留在堆中，可作为累积器使用
	 * @param c
	 * @return stayobj
	 */
	<S> S finish(S stayobj, PipeLineConsumer<S, T> c);
}