package com.utils;

import java.util.Iterator;
import java.util.List;

import com.url.category.Category;
import com.url.model.ParserModel;

/**
 * 解析模式工具类
 * 
 * @author ordinary-student
 *
 */
public class ParserModelUtil
{

	/*
	 * 方法：判断字符串是否属于指定类别
	 */
	public static boolean belongsTo(String string, String[] categories)
	{
		boolean result = false;
		// 遍历
		for (String category : categories)
		{
			if (category != null)
			{
				// 判断类别
				switch (category)
					{
					// 音频
					case Category.AUDIO:
						{
							result = endsWith(string, Category.AUDIOS);
						}
						break;
					// 视频
					case Category.VIDEO:
						{
							result = endsWith(string, Category.VIDEOS);
						}
						break;
					// 图片
					case Category.PICTURE:
						{
							result = endsWith(string, Category.PICTURES);
						}
						break;
					// 网页
					case Category.HTML:
						{
							result = endsWith(string, Category.HTMLS);
						}
						break;
					// css&js
					case Category.CSS:
						{
							result = endsWith(string, Category.CSSES);
						}
						break;
					case Category.OTHER:
						{
							result = endsWith(string, Category.OTHERS);
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

	/**
	 * 根据模式筛选符合的链接
	 * 
	 * @param list
	 * @param parserModel
	 * @return
	 */
	public static List<String> filter(List<String> list, ParserModel parserModel)
	{
		return filter(list, parserModel.getCategories());
	}

	/**
	 * 筛选符合类别的链接
	 * 
	 * @param list
	 * @param categories
	 * @return
	 */
	public static List<String> filter(List<String> list, String[] categories)
	{
		// 迭代器
		Iterator<String> iterator = list.iterator();
		// 判断筛选
		while (iterator.hasNext())
		{
			String url = iterator.next();
			// 不属于就移除
			if (!belongsTo(url, categories))
			{
				iterator.remove();
			}
		}

		// 返回筛选后的集合
		return list;
	}
}
