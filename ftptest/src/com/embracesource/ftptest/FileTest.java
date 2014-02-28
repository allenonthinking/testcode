package com.embracesource.ftptest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

public class FileTest {
	  public static void main(String[] args) {
//		 String read = readTxtFile("/tmp/t1.txt","ISO8859-1");
		  String read =readTxtFile("/tmp/s2.txt","UTF-8");
//		 try {
//			 writeAndcode("/tmp/t2.txt",read);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		  
//		 readTxtFile("/tmp/t2.txt","UTF-8");
	}
	  public static void writeAndcode(String filePath ,String readStr){
		  FileOutputStream fos = null ; 
		  OutputStreamWriter osw  = null ;
		  try  {
//		   fos = new FileOutputStream( filePath , true ); 
		   fos = new FileOutputStream( filePath ); 
		         osw  =   new  OutputStreamWriter(fos,  "UTF-8" );
//		         readStr.getBytes("GB2312");
		        osw.write(new String(readStr.getBytes("ISO8859-1"),"UTF-8"));
		        osw.flush();
		    }  catch  (Exception e) {
		        e.printStackTrace();
		    }finally{
		    	try {
					osw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	  }
	  public static void write(String filePath ,String readStr) throws IOException {
		    // 先读取原有文件内容，然后进行写入操作
//		    String filein = newStr + "\r\n" + readStr;
		    RandomAccessFile randomFile = null;
		    File file = new File(filePath);
		    file.delete();
		    try {
		      randomFile = new RandomAccessFile(file, "rw");
//		      if (isAppened) {
//		        long fileLength = randomFile.length();
//		        // 将写文件指针移到文件尾
//		        randomFile.seek(fileLength);
//		      }
//		      randomFile.write(new String(readStr.getBytes(),"UTF-8").getBytes());
		      randomFile.write(readStr.getBytes("gb2312"));
		      System.out.println("write success");
		    } catch (Exception e1) {
		      e1.printStackTrace();
		    } finally {
		      if (randomFile != null) {
		        try {
		          randomFile.close();
		        } catch (Exception e2) {
		          e2.printStackTrace();
		        }
		      }
		    }
		  }
	  /**
	   * 读取文本文件.
	   */
	  public static String readTxtFile(String filePath,String codetype) {
		  StringBuilder readStr = new StringBuilder();
	    File file = new File(filePath);
	    String read;
	    FileReader fileread;
	    
	    try {
	      fileread = new FileReader(file);
	      // bufread = new BufferedReader(fileread); //中文乱码
	      BufferedReader bufread = new BufferedReader(new InputStreamReader(new FileInputStream(file),
	    		  codetype));
	      try {
	        while ((read = bufread.readLine()) != null) {
//	        	System.out.println(read.getBytes("GB2312").toString());
	        	 String[] lines = read.split(",");
	        	System.out.println(lines.length);
	        	for (int i = 0; i < lines.length; i++) {
	        		System.out.println(lines[i]);
				}
//	        	System.out.println("end");
	        	System.out.println(read);
	        	System.out.println(read.length());
	            String gbk = new String(read.getBytes("ISO8859-1"),"GBK");   
	            
	            System.out.println(gbk);  
	            System.out.println(gbk.length());  
	            String utf81 = getUTF8StringFromGBKString(gbk);  
	            System.out.println(utf81);  
	            System.out.println(utf81.length());  
//	            String utf8 = new String(read.getBytes("ISO-8859-1"),"UTF-8");  
//	            System.out.println(utf8);  
	        	return read ;
////	        	System.out.println(read);
//	        	  String s1 = read.substring(0, 100);
//	        	  String s2 = read.substring(101, 200);
//	        	  String s3 = read.substring(201, 300);
//	        	  String s4 = read.substring(301, 400);
//	        	  String s5 = read.substring(401);
//	        	  readStr = readStr.append(s1);
//	        	  readStr = readStr.append(",");
//	        	  readStr = readStr.append(s2);
//	        	  readStr = readStr.append(",");
//	        	  readStr = readStr.append(s3);
//	        	  readStr = readStr.append(",");
//	        	  readStr = readStr.append(s4);
//	        	  readStr = readStr.append(",");
//	        	  readStr = readStr.append(s5);
//	        	  readStr = readStr.append("\r\n");
//	          String one = read.substring(0, 21);
//	          System.out.println(one);
//	          	 System.out.println("abc".substring(1, 2));
//	        	  String line = System.getProperty("line.separator");
	        }
	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
//	    System.out.println("文件内容是:" + "\r\n" + readStr);
	    return readStr.toString();
	  }
	  /**
	   * 写文件.
	   * 
	   * @param newStr
	   *          写入内容
	   * @param isAppened
	   *          true表示追加，false表示覆盖
	   * @throws IOException
	   */
	  public static void writeTxtFile(String filePath, String newStr, boolean isAppened,String readStr) throws IOException {
	    // 先读取原有文件内容，然后进行写入操作
	    String filein = newStr + "\r\n" + readStr;
	    RandomAccessFile randomFile = null;
	    File file = new File(filePath);
	    try {
	      randomFile = new RandomAccessFile(file, "rw");
	      if (isAppened) {
	        long fileLength = randomFile.length();
	        // 将写文件指针移到文件尾
	        randomFile.seek(fileLength);
	      }
	      randomFile.write(new String(filein.getBytes("ISO8859-1").toString()).getBytes());
//	      randomFile.write(new String(filein.getByte("ISO8859-1").toString()).getBytes());
	      System.out.println("write success");
	    } catch (Exception e1) {
	      e1.printStackTrace();
	    } finally {
	      if (randomFile != null) {
	        try {
	          randomFile.close();
	        } catch (Exception e2) {
	          e2.printStackTrace();
	        }
	      }
	    }
	  }
	  /**
	   * 创建文本文件.
	   */
	  public static void creatTxtFile(String filePath) throws IOException {
	    File file = new File(filePath);
	    if (!file.exists()) {
	      file.createNewFile();
	      System.out.println(file + "已创建！");
	    }
	  }
	    public static String getUTF8StringFromGBKString(String gbkStr) {  
	        try {  
	            return new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8");  
	        } catch (UnsupportedEncodingException e) {  
	            throw new InternalError();  
	        }  
	    }  
	      
	    public static byte[] getUTF8BytesFromGBKString(String gbkStr) {  
	        int n = gbkStr.length();  
	        byte[] utfBytes = new byte[3 * n];  
	        int k = 0;  
	        for (int i = 0; i < n; i++) {  
	            int m = gbkStr.charAt(i);  
	            if (m < 128 && m >= 0) {  
	                utfBytes[k++] = (byte) m;  
	                continue;  
	            }  
	            utfBytes[k++] = (byte) (0xe0 | (m >> 12));  
	            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));  
	            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));  
	        }  
	        if (k < utfBytes.length) {  
	            byte[] tmp = new byte[k];  
	            System.arraycopy(utfBytes, 0, tmp, 0, k);  
	            return tmp;  
	        }  
	        return utfBytes;  
	    }  
}
