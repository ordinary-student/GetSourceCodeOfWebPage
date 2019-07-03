package com.urllog;

import java.util.HashMap;

/**
 * 解析模式
 * 
 * @author ordinary-student
 *
 */
public class ParserModel
{
	public HashMap<String, Boolean> map = new HashMap<String, Boolean>();

	/*
	 * 构造方法
	 */
	public ParserModel(String key, boolean value)
	{
		put(key, value);
	}

	public ParserModel(String[] keys, boolean[] values)
	{
		put(keys, values);
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
	 */
	public void put(String[] keys, boolean[] values)
	{
		for (int i = 0; i < keys.length; i++)
		{
			// 获取键值
			String key = keys[i];
			boolean value = values[i];
			// 添加键值对
			map.put(key, value);
		}
	}
}
