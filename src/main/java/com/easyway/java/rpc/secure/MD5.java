package com.easyway.java.rpc.secure;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {

	private final static String[] NOSTR = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e",
		"f" };

	private static String byteToArrayString(byte bByte) {

		if (bByte < 0) {
			bByte += 128;
		}
		int iD1 = bByte / 10;
		int iD2 = bByte % 10;

		return NOSTR[iD1] + NOSTR[iD2];
	}

	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String getMd5(String str) {
		String result = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			result = byteToString(md.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static String getMd5(byte[] bytes) {
		String result = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			result = byteToString(md.digest(bytes));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static String getMd5(File zipFile) {

		String result = null;
		MessageDigest md;
		try {
			byte[] bytes = FileUtils.readFileToByteArray(zipFile);
			md = MessageDigest.getInstance("MD5");
			result = byteToString(md.digest(bytes));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String getUpdateMd5(byte[] bytes) {

		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(bytes);
			byte[] mdbytes = md5.digest();
			for (int i = 0; i < mdbytes.length; ++i) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 10).substring(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String Md5(String plainText) {
		  String result = null;
		  try {
		   MessageDigest md = MessageDigest.getInstance("MD5");
		   md.update(plainText.getBytes());
		   byte b[] = md.digest();
		   int i;
		   StringBuffer buf = new StringBuffer("");
		   for (int offset = 0; offset < b.length; offset++) {
		    i = b[offset];
		    if (i < 0)
		     i += 256;
		    if (i < 10)
		     buf.append("0");
		    buf.append(Integer.toHexString(i));
		   }
		   // result = buf.toString();  //md5 32bit
		   // result = buf.toString().substring(8, 24))); //md5 16bit
		   result = buf.toString().substring(8, 24);
		  } catch (NoSuchAlgorithmException e) {
		   e.printStackTrace();
		  }
		  return result;
		}
	
	/********************************************
	 * @Function  :  Md5To32
	 * @Desc  :  新增时保存Md5加密32位到数据库
	 * @Author  :guo.Gu
	 * @Input   :  String
	 * @OutPut   :  String
	 * @param insertStr	接受传过来的字符串
	 * @return result 加密后的字符串
	 ********************************************/
	public static String Md5To32(String plainText) {
		  String result = null;
		  try {
		   MessageDigest md = MessageDigest.getInstance("MD5");
		   md.update(plainText.getBytes());
		   byte b[] = md.digest();
		   int i;
		   StringBuffer buf = new StringBuffer("");
		   for (int offset = 0; offset < b.length; offset++) {
		    i = b[offset];
		    if (i < 0)
		     i += 256;
		    if (i < 10)
		     buf.append("0");
		    buf.append(Integer.toHexString(i));
		   }
		   result = buf.toString();  //md5 32bit
		  } catch (NoSuchAlgorithmException e) {
		   e.printStackTrace();
		  }
		  return result;
	}
}
