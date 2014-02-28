package com.embracesource.pooltest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class TestThread implements Runnable {
	private int timeout;
    /**
     * 构造函数
     */
    public TestThread(int timeout) {
    	this.timeout=timeout;
    }
	@Override
	public void run() {
		ExecutorService exec = Executors.newCachedThreadPool();
		MyTask task = new MyTask();
		Future<Boolean> future = exec.submit(task);
		Boolean taskResult = null;
		String failReason = null;
		try {
			// 等待计算结果，最长等待timeout秒，timeout秒后中止任务
			taskResult = future.get(timeout, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			failReason = "主线程在等待计算结果时被中断！";
			future.cancel(true);
		} catch (ExecutionException e) {
			failReason = "主线程等待计算结果，但计算抛出异常！";
			future.cancel(true);
		} catch (TimeoutException e) {
			failReason = "主线程等待计算结果超时，因此中断任务线程！";
			future.cancel(true);
			exec.shutdownNow();
		}

		System.out.println("\ntaskResult : " + taskResult);
		System.out.println("failReason : " + failReason);
	}
}
