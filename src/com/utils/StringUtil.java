package com.utils;

/**
 * 字符串工具类
 * 
 * @author ordinary-student
 *
 */
public class StringUtil
{

	/*
	 * String转Unicode
	 */
	public static String string2Unicode(String string)
	{
		// 创建StringBuffer对象
		StringBuffer unicode = new StringBuffer();
		// 遍历
		for (int i = 0; i < string.length(); i++)
		{
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		// 返回
		return unicode.toString();
	}

	/*
	 * Unicode转String
	 */
	public static String unicode2String(String unicode)
	{
		// 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格
		String[] strs = unicode.split("\\\\u");
		// 结果
		String result = "";
		// 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""
		for (int i = 1; i < strs.length; i++)
		{
			result = result + (char) Integer.valueOf(strs[i], 16).intValue();
		}

		// 返回
		return result;
	}
}