package com.embracesource.pooltest;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestPool {
	public static void main(String[] args) {
		System.out.println("Start ...");

		ExecutorService exec = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			
			testTask(exec, 15); // 任务成功结束后等待计算结果，不需要等到15秒
			testTask(exec, 5); // 只等待5秒，任务还没结束，所以将任务中止
			
			testTask(exec, 10); // 只等待5秒，任务还没结束，所以将任务中止
		}

//		exec.shutdown();
		System.out.println("End!");
	}

	public static void testTask(ExecutorService exec, int timeout) {
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
//			exec.shutdownNow();
			future.cancel(true);
		}

		System.out.println("\ntaskResult : " + taskResult);
		System.out.println("failReason : " + failReason);
	}
}
