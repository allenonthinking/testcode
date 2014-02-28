package com.embracesource.pooltest;

import java.util.concurrent.Callable;

class MyTask implements Callable<Boolean> {

	@Override
	public Boolean call() throws Exception {
		// 总计耗时约10秒
		for (int i = 0; i < 100L; i++) {
			Thread.sleep(50); // 睡眠0.1秒
			System.out.print('-');
		}
		return Boolean.TRUE;
	}
}
