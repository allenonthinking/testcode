package com.embracesource.pooltest;


public class TestMain {
public static void main(String[] args) {
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(4));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(6));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(7));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(8));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(1));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(2));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(9));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(10));
		FtpTaskPool.getinstancePool().ftppool.execute(new TestThread(12));
}
}
