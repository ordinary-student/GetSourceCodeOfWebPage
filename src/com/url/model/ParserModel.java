package com.url.model;

import java.util.HashMap;

import com.url.category.Category;

/**
 * 解析模式
 * 
 * @author ordinary-student
 *
 */
public class ParserModel
{
	// 模式
	public final static int DEFAULT_MODEL = 0;
	public final static int ALL_MODEL = 1;
	public final static int CUSTOM_MODEL = 2;
	// 当前模式
	private int model = DEFAULT_MODEL;

	// 集合
	public HashMap<String, Boolean> map = new HashMap<String, Boolean>();

	/*
	 * 构造方法
	 */
	public ParserModel()
	{

	}

	public ParserModel(int model)
	{
		this.model = model;
	}

	public ParserModel(String key, boolean value)
	{
		this.model = CUSTOM_MODEL;
		put(key, value);
	}

	public ParserModel(String[] keys, boolean[] values)
	{
		this.model = CUSTOM_MODEL;
		try
		{
			put(keys, values);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ParserModel(boolean[] values)
	{
		this.model = CUSTOM_MODEL;
		try
		{
			put(Category.CATEGORY, values);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 添加键值对
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, boolean value)
	{
		// 添加键值对
		map.put(key, value);
	}

	/**
	 * 添加键值对数组
	 * 
	 * @param keys
	 * @param values
	 * @throws Exception
	 */
	public void put(String[] keys, boolean[] values) throws Exception
	{
		// 判断长度
		if (keys.length == values.length)
		{
			for (int i = 0; i < keys.length; i++)
			{
				// 获取键值
				String key = keys[i];
				boolean value = values[i];
				// 添加键值对
				map.put(key, value);
			}
		} else
		{
			throw new Exception("键值对数组长度不相同！");
		}
	}

	/**
	 * 获取类别数组
	 * 
	 * @param parserModel
	 */
	public String[] getCategories()
	{
		// 类别数组
		String[] categories = new String[map.size()];

		int i = 0;
		// 遍历
		for (String key : map.keySet())
		{
			if (map.get(key))
			{
				categories[i] = key;
				i++;
			}
		}

		// 返回
		return categories;
	}

	/**
	 * @return model
	 */
	public int getModel()
	{
		return this.model;
	}

	/**
	 * @param model
	 *            要设置的 model
	 */
	public void setModel(int model)
	{
		this.model = model;
	}
}
