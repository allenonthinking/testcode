package com.embracesource.ftptest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


public class TestFTP {
	public static void main(String[] args) {
		String url = "192.168.0.202";
//		String url = "192.168.56.15";
		// 检测IP是否有效
		if(!checkIPValid(url)){
			System.out.println("IP不可到达");
		}
		int port = 21;
		String username = "ftp";
		String password = null;
		String remotePath = "pub/test";
//		String remotePath = "test";
		String fileName ="t.txt";
		String localPath = "/tmp";
		int downFlag = checkDownFtpService(url, port, username, password, remotePath, fileName);
		if(downFlag!=1){
			switch (downFlag) {
			case 2:
				System.out.println("FTP服务器无法登陆");
				break;
			case 3:
				System.out.println("FTP服务器目录不存在");
				break;
			case 4:
				System.out.println("FTP服务器目录下无文件");
				break;	
			case 5:
				System.out.println("文件不存在");
				break;	
			default:
				break;
			}
		}
		downFile(url, port, username, password, remotePath, fileName, localPath);
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
	 * Description: 检测FTP服务器DOWNLOAD服务 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @return 
	 */  
	public static int checkDownFtpService(String url, int port,String username, String password, String remotePath,String fileName) {  
	    int flag = 1;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            	// ftp服务器不能登陆
	            return 2;  
	        }  
	        boolean validPath =ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录  
	        	if(!validPath){
	        		 return 3;  
	        	}
	        FTPFile[] fs = ftp.listFiles();  
	         if(fs.length==0){
	        	 return 4;  
	          }
	           boolean validFile = false ;
	        for(FTPFile ff:fs){  
	            if(ff.getName().equals(fileName)){
	            	validFile= true ;
	            	}  
	        	}  
	        if(!validFile){
	        	 return 5;  
	          }
	        ftp.logout();  
	    } catch (IOException e) {  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return flag;  
	}	
	/** 
	 * Description: 向FTP服务器上传文件检测 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 */ 
	public static int checkUpLoadFtpService(String url, int port,String username, String password, String remotePath) {  
	    int flag = 1;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            	// ftp服务器不能登陆
	            return 2;  
	        }  
	        boolean validPath =ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录  
	        	if(!validPath){
	        		 return 3;  
	        	}
	        ftp.logout();  
	    } catch (IOException e) {  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return flag;  
	}
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public static boolean downFile(String url, int port,String username, String password, String remotePath,String fileName,String localPath) {  
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	        boolean f = ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录  
	           System.out.println(f);
	        FTPFile[] fs = ftp.listFiles();  
	        for(FTPFile ff:fs){  
	            if(ff.getName().equals(fileName)){  
//	                File localFile = new File(localPath+"/"+ff.getName());  
//	                OutputStream is = new FileOutputStream(localFile);   
//	                ftp.retrieveFile(ff.getName(), is);  
//	                is.close();  
	            	String result = "";
	            	InputStream ins = null;
	            	ins = ftp.retrieveFileStream(ff.getName());
	            	BufferedReader reader=new BufferedReader(new InputStreamReader(ins)); 
	                 String inLine = reader.readLine();
	                 while (inLine != null) {
	                	 System.out.println(inLine);
	                 result += (inLine + System.getProperty("line.separator"));
	                 inLine = reader.readLine();
	                 	   }
	                 reader.close();
	                 if(ins != null){
	                 ins.close(); 
	                 }
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
	 * Description: 向FTP服务器上传文件 
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */  
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {  
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);//连接FTP服务器  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	        ftp.changeWorkingDirectory(path);  
	        boolean fs = ftp.storeFile(filename, input);           
	          System.out.println(fs);
	        input.close();  
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
	//将本地文件上传到FTP服务器上
	public void testUpLoadFromDisk(){  
	    try {  
	        FileInputStream in=new FileInputStream(new File("/tmp/test.xls"));  
	        boolean flag = uploadFile("192.168.0.208", 21, "testftp", "testftp", "testftp", "test.xls", in);  
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	}
	//在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
	public void testUpLoadFromString(){  
	    try {  
	        InputStream input = new ByteArrayInputStream("test ftp".getBytes("utf-8"));  
	        boolean flag = uploadFile("127.0.0.1", 21, "test", "test", "D:/ftp", "test.txt", input);  
	        System.out.println(flag);  
	    } catch (UnsupportedEncodingException e) {  
	        e.printStackTrace();  
	    }  
	}
}
