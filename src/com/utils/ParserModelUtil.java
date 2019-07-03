package com.utils;

/**
 * 解析模式工具类
 * 
 * @author ordinary-student
 *
 */
public class ParserModelUtil
{
	// 音频
	private static final String[] AUDIO = { ".mp3", ".wav", ".midi" };
	// 视频
	private static final String[] VIDEO = { ".mp4", ".flv", ".rmvb", ".avi", ".wmv" };
	// 图片
	private static final String[] PICTURE = { ".bmp", ".png", ".jpg", ".jpeg", ".gif", ".tif", ".svg" };
	// 网页
	private static final String[] HTML = { ".html", ".htm", ".shtml" };
	// css和js
	private static final String[] CSS = { ".css", ".js" };

	/*
	 * 方法：判断字符串是否属于指定类别
	 */
	public static boolean belongsTo(String string, String... categories)
	{
		boolean result = false;
		// 遍历
		for (String category : categories)
		{
			// 判断类别
			switch (category)
				{
				// 音频
				case "audio":
					{
						result = endsWith(string, AUDIO);
					}
					break;
				// 视频
				case "video":
					{
						result = endsWith(string, VIDEO);
					}
					break;
				// 图片
				case "picture":
					{
						result = endsWith(string, PICTURE);
					}
					break;
				// 网页
				case "html":
					{
						result = endsWith(string, HTML);
					}
					break;
				// css&js
				case "css":
					{
						result = endsWith(string, CSS);
					}
					break;
				default:
					break;
				}

			// 如果是属于,跳出循环
			if (result)
			{
				break;
			}
		}
		// 返回结果
		return result;
	}

	/**
	 * 方法：判断是否以指定字符串后缀结尾
	 * 
	 * @param string
	 * @param suffixes
	 * @return
	 */
	public static boolean endsWith(String string, String[] suffixes)
	{
		boolean result = false;
		// 遍历
		for (String suffix : suffixes)
		{
			if (string.endsWith(suffix))
			{
				result = true;
				break;
			}
		}

		// 返回结果
		return result;
	}
}
