package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.JOptionPane;

/**
 * 读写文件工具类
 * 
 * @author ordinary-student
 *
 */
public class FileUtil
{
	/*
	 * 构造方法
	 */
	public FileUtil()
	{

	}

	/**
	 * 方法：按行读取文件内容
	 * 
	 * @param file
	 * @return
	 */
	public static String read(File file)
	{
		// 创建StringBuffer对象
		StringBuffer result = new StringBuffer();
		// 输入流
		InputStream inputStream = null;
		// 读取流
		Reader reader = null;
		// 缓冲流
		BufferedReader bufferedReader = null;

		try
		{
			// 创建流
			inputStream = new FileInputStream(file);
			reader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(reader);

			// 行内容
			String line = null;
			// 读取
			while ((line = bufferedReader.readLine()) != null)
			{
				// 追加
				result.append(line);
				result.append("\r\n");
			}

			// 关闭流
			if (bufferedReader != null)
			{
				bufferedReader.close();
			}
			if (reader != null)
			{
				reader.close();
			}
			if (inputStream != null)
			{
				inputStream.close();
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "找不到文件！", "错误", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "读取文件失败！", "错误", JOptionPane.ERROR_MESSAGE);
		}

		// 返回内容
		return result.toString();
	}

	/**
	 * 方法：将内容写入文件
	 * 
	 * @param file
	 * @param content
	 */
	public static void write(File file, String content)
	{
		// 输出流
		BufferedWriter bufferedWriter = null;

		try
		{
			// 封装输出流
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			// 写入
			bufferedWriter.write(content);
			// 刷新
			bufferedWriter.flush();

			// 关闭流
			if (bufferedWriter != null)
			{
				bufferedWriter.close();
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "找不到文件！", "错误", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "写入文件失败！", "错误", JOptionPane.ERROR_MESSAGE);
		}

	}
}
