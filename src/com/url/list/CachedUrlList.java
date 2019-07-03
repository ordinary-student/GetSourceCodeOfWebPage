package com.url.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存网址集合类
 * 
 * @author ordinary-student
 *
 */
public class CachedUrlList
{
	private List<String> urlList;
	private int currentIndex;

	public CachedUrlList()
	{
		urlList = new ArrayList<String>();
		currentIndex = urlList.size();
	}

	/*
	 * 添加网址
	 */
	public void add(String url)
	{
		urlList.add(url);
		currentIndex = urlList.size();
	}

	/*
	 * 获取上一条网址
	 */
	public String getLastUrl()
	{
		String result = "";
		// 如果集合没记录
		if (urlList.size() <= 0)
		{

		} else
		{
			// 索引减一
			currentIndex--;
			// 如果所有小于0
			if (currentIndex < 0)
			{
				// 尽头为最开始第一个网址
				currentIndex = 0;
			}
			result = urlList.get(currentIndex);
		}

		// 返回
		return result;
	}

	/*
	 * 获取下一条网址
	 */
	public String getNextUrl()
	{
		String result = "";
		// 如果集合没记录
		if (urlList.size() <= 0)
		{

		} else
		{
			// 索引加一
			currentIndex++;
			// 如果所有小于0
			if (currentIndex > urlList.size() - 1)
			{
				// 尽头为最新的那个网址
				currentIndex = urlList.size() - 1;
			}
			result = urlList.get(currentIndex);
		}

		// 返回
		return result;
	}
}
