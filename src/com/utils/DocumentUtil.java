package com.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.url.model.ParserModel;

/**
 * 解析网页源代码工具类
 * 
 * @author ordinary-student
 *
 */
public class DocumentUtil
{
	// 代理头
	public static String os = "5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)";
	public static String chrome = "70.0.3538.25";
	public static String safari = "537.36";
	public static String core = "1.70.3695.400";
	public static String qqbrowser = "10.4.3562.400";

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

	/**
	 * 根据链接和解析模式，返回解析结果
	 * 
	 * @param url
	 * @param parserModel
	 * @return
	 */
	public static List<String> parser(String url, ParserModel parserModel)
	{
		// 获取文档对象
		Document document = getDocument(url);
		// 创建集合
		List<String> list = new ArrayList<String>();

		// 判断解析模式
		if (parserModel.getModel() == ParserModel.DEFAULT_MODEL)
		{
			// 默认模式
			list.add(document.toString());

		} else if (parserModel.getModel() == ParserModel.ALL_MODEL)
		{
			// 获取所有链接
			list = getAllUrls(document);
		} else
		{
			// 筛选符合的链接
			list = ParserModelUtil.filter(getAllUrls(document), parserModel);
		}

		// 返回
		return list;
	}

	/**
	 * 获取所有链接
	 * 
	 * @param document
	 * @return
	 */
	private static List<String> getAllUrls(Document document)
	{
		// 获取src链接
		List<String> srcList = getSrcUrls(document);
		// 获取href链接
		List<String> hrefList = getHrefUrls(document);
		// 遍历添加
		for (String url : hrefList)
		{
			srcList.add(url);
		}

		// 返回
		return srcList;
	}

	/**
	 * 获取href链接
	 * 
	 * @param document
	 * @return
	 */
	private static List<String> getHrefUrls(Document document)
	{
		// 创建集合
		List<String> list = new ArrayList<String>();
		// 获取元素
		Elements elements = document.select("[href]");
		// 遍历
		for (Element element : elements)
		{
			// 获取单条链接
			String href = element.attr("abs:href");
			list.add(href);
		}

		// 返回
		return list;
	}

	/**
	 * 获取src链接
	 * 
	 * @param document
	 * @return
	 */
	private static List<String> getSrcUrls(Document document)
	{
		// 创建集合
		List<String> list = new ArrayList<String>();
		// 获取元素
		Elements elements = document.select("[src]");
		// 遍历
		for (Element element : elements)
		{
			// 获取单条链接
			String src = element.attr("abs:src");
			list.add(src);
		}

		// 返回
		return list;
	}

}
