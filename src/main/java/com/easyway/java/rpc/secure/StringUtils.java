package com.easyway.java.rpc.secure;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils{

	
	//判断是否是中文
		public static boolean isChinese(String string) {  
			boolean isChinese=false;
			if(StringUtils.isNotBlank(string)){
				for (int i = 0; i < string.length(); i++) {// for循环
					char c = string.charAt(i); // 获得字符串中的每个字符
					if (StringUtils.isChinese(c)) {// 调用方法进行判断是否是汉字
						isChinese=true; // 等同于chineseCount=chineseCount+1
						break;
					}
				}
			}
	        return isChinese;  
	    }
	
	
	//判断是否是中文
	public static boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }  
	
	// 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
	public static long ipToLong(String strIp) {
		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}
	
	
	
	/**
	 * 
	 * @Function: getCredentialNum
	 * @Desc    :  替换字母(字符串中只有字母和数字时使用)
	 * @Author  : YunLei.Huo
	 * @param str
	 * @return
	 */
	public static  String replaceLetter(String str) {
		if(StringUtils.isNotBlank(str)){
		char[] credentialNumChar =str.toCharArray();
		    char beginChar = 'A';
		    char endChar = 'z';
		    StringBuilder newName = new StringBuilder();
		    for (int i = 0; i < str.length(); i++)
		    {
		        if (credentialNumChar[i] >= beginChar && credentialNumChar[i] <= endChar)
		        {
		            continue;
		        }
		        else
		        {
		        	newName.append(credentialNumChar[i]);
		        }
		    }
		return newName.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @Function: getCredentialNum
	 * @Desc    :  替换字母(字符串中只有字母和数字时使用)
	 * @Author  : YunLei.Huo
	 * @param str,falg
	 * @return
	 */
	public static String replaceLetter(String str,String falg) {
		if(StringUtils.isNotBlank(str)){
		char[] credentialNumChar =str.toCharArray();
		    char beginChar = 'A';
		    char endChar = 'z';
		    StringBuilder newName = new StringBuilder();
		    for (int i = 0; i < str.length(); i++)
		    {
		        if (credentialNumChar[i] >= beginChar && credentialNumChar[i] <= endChar)
		        {
		        	newName.append(falg);
		        }
		        else
		        {
		        	newName.append(credentialNumChar[i]);
		        }
		    }
		return newName.toString();
		}else{
			return "";
		}
	}
	 /**
     * 字符串格式化方法
     * @param str  原始字符串 
     * @param strLength  长度
     * @param isBefore  是否在前面
     * @return
     */
    public static String format(String str, int strLength,boolean isBefore) {
      return StringUtils.format(str, strLength, isBefore, "0");
    }
    /**
     * 
     * @param str
     * @param strLength
     * @param isBefore
     * @param addchar
     * @return
     */
    public static String format(String str, int strLength,boolean isBefore,String addchar) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                if(isBefore){
                    sb.append(addchar).append(str);// 左补0
                }else{
                    sb.append(str).append(addchar);//右补0
                }
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }
    
    // Empty checks
    //-----------------------------------------------------------------------
    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = false
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0 || "".equals(str.trim());
    }

    
    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmptynbsp(String str) {
        return str == null || str.length() == 0 || "".equals(str);
    }
    /**
     * <p>Checks if a String is not empty ("") and not null.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = true
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isBlank(str);
    }
    

	/**
	 * 
	 * @param src
	 * @param sFnd
	 * @param sRep
	 */
	public static String replaceStr(String src, String sFnd, String sRep) {
		String sTemp = "";
		int endIndex = 0;
		int beginIndex = 0;
		do {
			endIndex = src.indexOf(sFnd, beginIndex);
			// logger.debug(endIndex + ":" + beginIndex);
			if (endIndex >= 0) {// mean found it.
				// logger.debug( src.substring(beginIndex,endIndex));
				sTemp += src.substring(beginIndex, endIndex) + sRep;
				beginIndex = endIndex + sFnd.length();
			} else if (beginIndex >= 0) {
				sTemp += src.substring(beginIndex);
				break;
			}
		} while (endIndex >= 0);
		// logger.debug(sTemp);
		return sTemp;
	}

	/**
	 * 判断是否为字母（包括大小字母）和数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEntNum(String str) {
		String all = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int i;
		for (i = 0; i < str.length(); i++) {
			if (all.indexOf(str.substring(i, i + 1)) < 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否包含字母（包括大小字母）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isConletter(String str) {
		String all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int i;
		for (i = 0; i < str.length(); i++) {
			if (all.indexOf(str.substring(i, i + 1)) > 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 空转化为空字串
	 */
	public static String nvl(String sIn) {
		return (sIn == null) ? "" : sIn;
	}

	/**
	 * 把空值转换为指定的字符串
	 * 
	 * @param sIn 待转换的字符串
	 * @param sDef 如果为空，转换后的字符串
	 * @return 转换后的字符串
	 */
	public static String nvl(String sIn, String sDef) {
		return (sIn == null || "".equals(sIn)) ? sDef : sIn;
	}

	/**
	 * 检查字符是否为合法的邮件地址
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean chkMail(String mail) {
		int i;
		int len = mail.length();
		int aPos = mail.indexOf("@");
		int dPos = mail.indexOf(".");
		int aaPos = mail.indexOf("@@");
		int adPos = mail.indexOf("@.");
		int ddPos = mail.indexOf("..");
		int daPos = mail.indexOf(".@");
		String lastChar = mail.substring(len - 1, len);
		String chkStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-_@.";
		if ((aPos <= 0) || (aPos == len - 1) || (dPos <= 0)
				|| (dPos == len - 1) || (adPos > 0) || (daPos > 0)
				|| (lastChar.equals("@")) || (lastChar.equals("."))
				|| (aaPos > 0) || (ddPos > 0)) {
			return false;
		}
		if (mail.indexOf("@", aPos + 1) > 0) {
			return false;
		}
		while (aPos > dPos) {
			dPos = mail.indexOf(".", dPos + 1);
			if (dPos < 0) {
				return false;
			}
		}
		for (i = 0; i < len; i++) {
			if (chkStr.indexOf(mail.charAt(i)) < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * remove char from the input string
	 * 
	 * @param str input string rc removed char
	 * @return 
	 */
	public static String removeChar(String str, String rc) {
		if (str == null) {
			return null;
		}
		int i = str.indexOf(rc);
		while (i >= 0) {
			str = str.substring(0, i) + str.substring(i + 1, str.length());
			i = str.indexOf(rc);
		}
		return str;
	}

	/**
	 * convert input value to html out string
	 */
	public static String toHTMLOutStr(String sIn) {
		if (sIn == null) {
			return sIn;
		}
		String sOut = sIn;
		sOut = replaceStr(sOut, "<", "&lt;");
		sOut = replaceStr(sOut, ">", "&gt;");
		return sOut;
	}

	/**
	 * convert input value to html return string
	 */
	public static String toHTMLRtnStr(String sIn) {
		if (sIn == null) {
			return sIn;
		}
		String sOut = sIn;
		sOut = replaceStr(sOut, "\r\n", "<br>");
		return sOut;
	}

	public static boolean isSpace(char c) {
		return (c == ' ' || c == '\t');
	}

	public static boolean isSpace(String s) {
		if (s == null)
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (!isSpace(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static String nullToSpace(String s) {
		return (s == null) ? "" : s;
	}

	public String safeTrim(String src) {
		if (src != null) {
			return src.trim();
		} else {
			return null;
		}
	}

	public static String trim(String s) {
		if (s == null)
			return null;
		int begin, end;
		for (begin = 0; (begin < s.length()) && isSpace(s.charAt(begin)); begin++)
			;
		for (end = s.length() - 1; (end >= 0) && isSpace(s.charAt(end)); end--)
			;
		if (end < begin) {
			return "";
		}
		return s.substring(begin, end + 1);
	}

	/*
	 * Convert a string like "243,434,343" to long like 243434343
	 */
	public static java.math.BigDecimal unFormatNum(String input) {
		if (input == null || input.equals("")) {
			return new java.math.BigDecimal(0);
		}
		try {
			return new java.math.BigDecimal(removeChar(input, ","));
		} catch (Exception e) {
			return new java.math.BigDecimal(-1);
		}
	}

	// End of update
	/**
	 * 9999999 -> 9,999,999
	 */
	public static String formatNum(String input0) {
		if (input0 == null)
			return null;

		if (input0.trim().length() == 0)
			return "";
		String input = input0;
		boolean neg = false;
		if (input.startsWith("-")) {
			neg = true;
			input = input0.substring(1);
		}
		int point = (input.indexOf(".") > 0) ? input.indexOf(".") : input
				.length();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < point - 1; i++)
			if ((point - i - 1) % 3 == 0)
				result.append(input.charAt(i)).append(",");
			else
				result.append(input.charAt(i));
		result.append(input.substring(point - 1));

		return neg ? "-" + result.toString() : result.toString();

	}

	/**
	 * 9999999 -> 9,999,999
	 */
	public static String formatNum(java.math.BigDecimal src) {
		return formatNum(src.toString());
	}

	/**
	 * added by morris 2000/10/20 Filter the specified string for characters
	 * that are senstive to HTML interpreters, returning the string with these
	 * characters replaced by the corresponding character entities.
	 */
	public static String filterForHtml(String value) {
		if (value == null) {
			return (null);
		}
		StringBuffer result = new StringBuffer();
		value = value.trim();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			if (ch == '<')
				result.append("&lt;");
			else if (ch == '>')
				result.append("&gt;");
			else if (ch == '&')
				result.append("&amp;");
			else if (ch == '"')
				result.append("&quot;");
			else
				result.append(ch);
		}
		return (result.toString());
	}

	/**
	 * Filter the specified string for characters that are senstive to
	 * JavaScript interpreters, returning the string with these characters
	 * replaced by the corresponding character entities.
	 */
	public static String filterForJS(String value) {
		if (value == null) {
			return (null);
		}
		StringBuffer result = new StringBuffer();
		value = value.trim();
		for (int i = 0; i < value.length(); i++) {
			char ch = value.charAt(i);
			if (ch == '\'')
				result.append("\\'");
			else if (ch == '"')
				result.append("\\\"");
			else
				result.append(ch);
		}
		return (result.toString());
	}

	/**
	 * convert a string value to BigDecimal value
	 */
	public static java.math.BigDecimal toBigDecimal(String src) {
		if (src == null)
			return new java.math.BigDecimal(0);
		else
			return new java.math.BigDecimal(src);
	}

	/**
	 * 比较两个字符串是否值相等
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean isEqual(Object o1, Object o2) {
		if (o1 == null) {
			return o2 == null;
		} else {
			return o1.equals(o2);
		}
	}

	/**
	 * 判断字符串可否转化成数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (str == null) {
			return false;
		}
		return str.length() > 0 && str.matches("\\d*\\.{0,1}\\d*")
				&& !".".equals(str);
	}

	/**
	 * 判断字符串可否转化成整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		//return str.length() > 0 && str.matches("\\d+");
		if(str.length()>10){
			return false;
		}
		for(int i=0;i<str.length();i++){
			if(!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 组合两个字符串
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String combinationString(String str1, String str2) {
		StringBuffer sb = new StringBuffer();
		sb.append(str1);
		sb.append(str2);
		return sb.toString();
	}
	
	/**
	 * 首字母大写
	 * @param str String 要修改的字符串
	 * @return String 首字母大写的字符串
	 */
	public static  String capUpper(String str)
	{
		if(str == null ||"".equals(str))
			return "";
		if(str.length()==1)
			return str.toUpperCase();
		
		String temp = str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
		
		return temp;			
		
	}
	
	
	/**
	 * 首字母小写,后续字符串保持原样
	 * @param str String 要修改的字符串
	 * @return String 首字母小写的字符串
	 */
	public static  String capLower(String str)
	{
		if(str == null ||"".equals(str))
			return "";
		if(str.length()==1)
			return str.toLowerCase();
		
		String temp = str.substring(0,1).toLowerCase().concat(str.substring(1));
		
		return temp;			
		
	}
	/**
	 * 将对象转换为字符串
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object){
	    if(object == null){
	        return null;
	    }
	    return object.toString();
	}
	
	
	 public static String replaceBlank(String str) {
        String dest = "";
 
 	        if (str!=null) {
 
 	            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
 
 	            Matcher m = p.matcher(str);
 
 	            dest = m.replaceAll("");
 
 	        }
 
 	        return dest;
 
 	    }
	 
	 
	/**
	 * 将list转换成String格式。
	 * 票种[2B]+张数[2B，不足2位左补0]，多票种时为变长字符串。
	 * @param list
	 * @return
	 */
	public static String listToString(List<Map<String,Object>> list){
		if(list.isEmpty()){
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for(Map<String,Object> map:list){
			String obj1 = "00";
			if(map.containsKey("ticket_type")){
				obj1 = map.get("ticket_type").toString();
			}else if(map.containsKey("bed_level")) {
				obj1 = map.get("bed_level").toString();
			}
			String count = map.get("count").toString();
			if(isEmpty(obj1)){
				buffer.append("00");
			}else{
				buffer.append(format(obj1, 2, true));
			}
			if(isEmpty(count)){
				buffer.append("00");
			}else{
				buffer.append(format(count, 2, true));
			}
		}
		
		return buffer.toString();
		
	}
	
	/**
	 * String格式转换为数字格式
	 * @param str
	 * @return
	 */
	public static String formatInt(String str){
		if(StringUtils.isBlank(str)){
			return str;
		}else{
			return StringUtils.objectToString(Integer.valueOf(str));
		}
	}
}
