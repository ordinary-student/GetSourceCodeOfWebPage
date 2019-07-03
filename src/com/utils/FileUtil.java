package com.utils;

import java.awt.Component;
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

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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

	/**
	 * 保存内容入选择的文件
	 * 
	 * @param parent
	 * @param content
	 */
	public static void save(Component parent, String content)
	{
		// 弹出文件选择框
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("保存文件");
		fileChooser.setApproveButtonText("保存");

		// 后缀名过滤器
		FileNameExtensionFilter filter = new FileNameExtensionFilter("网页文件(*.html)", "html");
		fileChooser.setFileFilter(filter);

		// 选择结果
		int result = fileChooser.showSaveDialog(parent);
		// 判断
		if (result == JFileChooser.APPROVE_OPTION)
		{
			// 获取选择的文件夹
			File selectedFile = fileChooser.getSelectedFile();
			// 从文件名输入框中获取文件名
			String fname = fileChooser.getName(selectedFile);

			// 假如用户填写的文件名不带指定的后缀名，那么给它添上后缀
			if (fname.indexOf(".html") == -1)
			{
				selectedFile = new File(fileChooser.getCurrentDirectory(), fname + ".html");
			}

			// 将内容写入文件
			write(selectedFile, content);

			// 保存完成
			JOptionPane.showMessageDialog(parent, "保存成功！");

		} else
		{
			return;
		}
	}
}
