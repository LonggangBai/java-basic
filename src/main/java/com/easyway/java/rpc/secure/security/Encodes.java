/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.easyway.java.rpc.secure.security;

import com.easyway.java.rpc.secure.MD5;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * 封装各种格式的编码解码工具类.
 * 
 * 1.Commons-Codec的 hex/base64 编码 2.自制的base62 编码 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * 
 * @author calvin
 */
public class Encodes {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	private static int offset = 2;
	private static ArrayList<Character> list;
	static {
		list = new ArrayList<Character>();
		for (char c : BASE62) {
			list.add(c);
		}
	}

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}

	/**
	 * Html 转码.
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * Html 解码.
	 */
	public static String unescapeHtml(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String unescapeXml(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 */
	public static String urlEncode(String part) {
		try {
			return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 */
	public static String urlDecode(String part) {

		try {
			return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.unchecked(e);
		}
	}

	// encode rongxin.li
	public static String encodeXX(String str) {
		// Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_'
		str = Encodes.encodeUrlSafeBase64(str.getBytes());
		StringBuffer buff1 = new StringBuffer();
		// 获取加密密文字符长度
		int length = list.size();
		// 将字符串转换为字符数组 进行遍历 并进行重排
		for (char c : str.toCharArray()) {
			// 获取对应的 字符 所对应的下标索引
			int i = list.indexOf(c);
			// 如果当前字符在我们 定义的list 里面不存在的话 则不进行重排直接拼接到字符串里
			if (i < 0) {
				buff1.append(c);
				continue;
			}
			// 进行替换
			i = i + offset < length ? i + offset : i + offset - length;
			buff1.append(list.get(i));
		}
		return buff1.toString();
	}

	// decode rongxin.li
	public static String decodeXX(String str) {
		StringBuffer buff2 = new StringBuffer();
		// 获取加密密文字符长度
		int length = list.size();
		// 将字符串转换为字符数组 进行遍历 并进行重排
		for (char c : str.toString().toCharArray()) {
			// 获取对应的 字符 所对应的下标索引
			int i = list.indexOf(c);
			// 如果当前字符在我们 定义的list 里面不存在的话 则不进行重排直接拼接到字符串里
			if (i < 0) {
				buff2.append(c);
				continue;
			}
			// 进行逆向的排序
			i = i - offset >= 0 ? i - offset : i - offset + length;
			// 进行拼接
			buff2.append(list.get(i));
		}
		String deStr = buff2.toString();
		byte[] base64de = Encodes.decodeBase64(deStr);
		try {
			return new String(base64de, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	// encode rongxin.li
	public static String encode(String str) {
		// Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_'
		try {
			str = new String(Base64.encodeBase64(str.getBytes("GBK"), false, true));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer buff1 = new StringBuffer();
		// 获取加密密文字符长度
		int length = list.size();
		// 将字符串转换为字符数组 进行遍历 并进行重排
		for (char c : str.toCharArray()) {
			// 获取对应的 字符 所对应的下标索引
			int i = list.indexOf(c);
			// 如果当前字符在我们 定义的list 里面不存在的话 则不进行重排直接拼接到字符串里
			if (i < 0) {
				if (c == '-') {
					buff1.append("+");
				} else if (c == '_') {
					buff1.append("/");
				} else {
					buff1.append(c);
				}
				continue;
			}

			// 进行替换
			i = i + offset < length ? i + offset : i + offset - length;
			buff1.append(list.get(i));
		}
		return buff1.toString();
	}

	// decode rongxin.li
	public static String decode(String str) {
		StringBuffer buff2 = new StringBuffer();
		// 获取加密密文字符长度
		int length = list.size();
		// 将字符串转换为字符数组 进行遍历 并进行重排
		for (char c : str.toString().toCharArray()) {
			// 获取对应的 字符 所对应的下标索引
			int i = list.indexOf(c);
			// 如果当前字符在我们 定义的list 里面不存在的话 则不进行重排直接拼接到字符串里
			if (i < 0) {
				if (c == '+') {
					buff2.append("-");
				} else if(c=='/'){
					buff2.append("_");
				}else{
					buff2.append(c);
				}
				continue;
			}
			// 进行逆向的排序
			i = i - offset >= 0 ? i - offset : i - offset + length;
			// 进行拼接
			buff2.append(list.get(i));
		}
		String deStr = buff2.toString();
		try {
			// byte[] deStrbyte=deStr.getBytes("ISO-8859-1");
			byte[] base64de = Base64.decodeBase64(deStr);
			return new String(base64de, "GBK");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	/**
	 * 
	 * @Function : aesEncode
	 * @Desc : AES 加密 通过 指定KEY 对 input进行加密 key先进行MD5加密 变成长度为16为的密文 达到符合 AES 加密标准
	 * @Author : YunLei.Huo
	 * @param input
	 * @param key
	 * @return
	 */
	public static String aesEncode(String input, String key) {
		String keyStr = MD5.Md5(key);
		byte[] keyByte = keyStr.getBytes();
		byte[] encryptResult = Cryptos.aesEncrypt(input.getBytes(), keyByte);
		return encodeHex(encryptResult);
	}

	/**
	 * 
	 * @Function : aesDecode
	 * @Desc : AES 解密 进行HEX解码并解密AES密文
	 * @Author : YunLei.Huo
	 * @param d5key
	 * @return
	 */
	public static String aesDecode(String input, String d5key) {
		byte[] inputByte = decodeHex(input);
		return Cryptos.aesDecrypt(inputByte, d5key.getBytes());
	}

	// SHA-1 加密 不可逆 cuidongxue
	public static String operationAlgorithm(String secureData)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");

		md.update(secureData.getBytes("utf-8"), 0, secureData.length());
		byte[] sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	/**
	 * Hex转码
	 * 
	 * @param data
	 * @return
	 */
	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
}
