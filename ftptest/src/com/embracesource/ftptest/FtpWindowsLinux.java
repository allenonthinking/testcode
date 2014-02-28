package com.embracesource.ftptest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpWindowsLinux {
	public static void main(String[] args) {
		String winurl = "192.168.0.8";
		// 检测IP是否有效
		if(!checkIPValid(winurl)){
			System.out.println("IP不可到达");
		}
		int winport = 21;
		String winusername = "ftp";
		String winpassword = "";
		String winremotePath = "核查";
		String winfileName ="哈哈.txt";
		String winlocalPath = "/tmp";
		downWinFile(winurl, winport, winusername, winpassword, winremotePath, winfileName, winlocalPath);
		
		String linuxurl = "192.168.0.208";
		// 检测IP是否有效
		if(!checkIPValid(linuxurl)){
			System.out.println("IP不可到达");
		}
		int linuxport = 21;
		String linuxusername = "testftp";
		String linuxpassword = "testftp";
		String linuxremotePath = "核查";
		String linuxfileName ="哈哈3.txt";
		String linuxlocalPath = "/tmp";
		downLinuxFile(linuxurl, linuxport, linuxusername, linuxpassword,linuxremotePath, linuxfileName, linuxlocalPath);
	}
	/**
	 * 检测IP是否能ping通
	 * @param ipURL
	 * @return
	 */
	public static boolean checkIPValid(String ipURL){
		boolean falg = false ;
		int timeOut = 3000;
		InetAddress address = null;
		try {
			address = InetAddress.getByName(ipURL);
		} catch (UnknownHostException e) {
			return falg ;
		}
		try {
			return address.isReachable(timeOut);
		} catch (IOException e) {
			return falg ;
		}
	}
	/**
	 * winFTP下载
	 * 
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param remotePath
	 * @param fileName
	 * @param localPath
	 * @return
	 */
	public static boolean downWinFile(String url, int port, String username,
			String password, String remotePath, String fileName,
			String localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}

			// ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			ftp.changeWorkingDirectory(new String(
					remotePath.getBytes("GB2312"), "ISO-8859-1"));// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				String windowsFileName = new String(ff.getName().getBytes(
						"ISO-8859-1"), "GB2312");
				// if (ff.getName().equals(fileName)) {
				if (windowsFileName.equals(fileName)) {
					// File localFile = new File(localPath + "/" +
					// ff.getName());
					File localFile = new File(localPath + "/" + windowsFileName);
					OutputStream is = new FileOutputStream(localFile);
					 ftp.retrieveFile(ff.getName(), is);
//					ftp.retrieveFile(windowsFileName, is);
					is.close();
				}
			}

			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}
	/**
	 *LINUXFTP下载
	 * 
	 * @param url
	 * @param port
	 * @param username
	 * @param password
	 * @param remotePath
	 * @param fileName
	 * @param localPath
	 * @return
	 */
	public static boolean downLinuxFile(String url, int port, String username,
			String password, String remotePath, String fileName,
			String localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}

//			 ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
				ftp.changeWorkingDirectory(new String(
						remotePath.getBytes("UTF-8"), "ISO-8859-1"));// 转移到FTP服务器目录
//			ftp.changeWorkingDirectory(new String(
//					remotePath.getBytes("UTF-8")));// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				String linuxFileName = new String(ff.getName().getBytes(
						"ISO-8859-1"), "UTF-8");
//				 if (ff.getName().equals(fileName)) {
				if (linuxFileName.equals(fileName)) {
					// File localFile = new File(localPath + "/" +
					// ff.getName());
					File localFile = new File(localPath + "/" + fileName);
					OutputStream is = new FileOutputStream(localFile);
					 ftp.retrieveFile(ff.getName(), is);
//					ftp.retrieveFile(linuxFileName, is);
					is.close();
				}
			}

			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}	
}
