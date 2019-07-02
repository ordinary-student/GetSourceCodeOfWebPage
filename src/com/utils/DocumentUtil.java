package com.utils;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 解析网页源代码工具类
 * 
 * @author ordinary-student
 *
 */
public class DocumentUtil
{
	// 代理头
	private static String os = "5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)";
	private static String chrome = "70.0.3538.25";
	private static String safari = "537.36";
	private static String core = "1.70.3695.400";
	private static String qqbrowser = "10.4.3562.400";

	/*
	 * 构造方法
	 */
	public DocumentUtil()
	{
	}

	/*
	 * 获取文档对象
	 */
	public static Document getDocument(String url)
	{
		// 代理头
		String agent = os + " " + "Chrome/" + chrome + " " + "Safari/" + safari + " " + "Core/" + core + " "
				+ "QQBrowser/" + qqbrowser;

		// 失败次数
		int errorCount = 0;
		// 继续运行标志
		boolean flag = false;
		// 文档对象
		Document document = null;

		// 循环
		do
		{
			try
			{
				// 请求代理头
				// 获得document对象
				document = Jsoup.connect(url).userAgent(agent).timeout(5000).get();
				// 获取成功，退出
				flag = false;

			} catch (IOException e)
			{
				// 获取失败，失败次数+1
				errorCount++;
				// 判断失败次数是否大于3次
				if (errorCount > 3)
				{
					JOptionPane.showMessageDialog(null, "网络请求超时！");
					// 失败4次，就退出
					flag = false;
				} else
				{
					// 否则继续请求
					flag = true;
				}
			}
		} while (flag);

		// 返回document对象
		return document;
	}
}
