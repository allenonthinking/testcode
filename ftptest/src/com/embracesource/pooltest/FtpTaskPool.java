package com.embracesource.pooltest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例FTP线程池
 * 
 * @author allen
 * 
 */
public class FtpTaskPool {
	public static ExecutorService ftppool = null;
	private static FtpTaskPool instancePool;
	private FtpTaskPool() {
		// 限定ftp线程池 同时只运行三个task
		ftppool = Executors.newFixedThreadPool(3);
	}

	public static FtpTaskPool getinstancePool() {
		if (instancePool == null) {
			synchronized (FtpTaskPool.class) {
				if (instancePool == null) {
					System.out.println("Pool instance");
					instancePool = new FtpTaskPool();
				}
			}
		}
		return instancePool;
	}

	public static synchronized void restart() {
		ftppool.shutdown();
		ftppool = null ;
		ftppool = Executors.newFixedThreadPool(3);
	}

}
