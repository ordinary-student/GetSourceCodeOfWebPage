package com.ui.downloader.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * 单个下载线程
 * 
 * @author ordinary-student
 *
 */
public class SingleDownloadThread extends Thread
{
	// 链接
	private URL url;
	// 索引-第几块
	private int index;
	// 块的大小
	private int blockSize;
	// 块数量
	private int blockNum;
	// 文件大小
	private int fileSize;
	// 文件名
	private String fileName;
	// 信息输出区
	private JTextArea outputTextArea;
	// 文件存放目录
	private File tempDir;

	/*
	 * 构造方法
	 */
	public SingleDownloadThread(JTextArea outputTextArea, URL url, int index, int blockSize, int blockNum, int fileSize,
			String fileName, File tempDir)
	{
		this.outputTextArea = outputTextArea;
		this.url = url;
		this.index = index;
		this.blockSize = blockSize;
		this.blockNum = blockNum;
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.tempDir = tempDir;
	}

	@Override
	public void run()
	{
		try
		{
			// 重新获取连接
			URLConnection conn = url.openConnection();
			// 重新获取流
			InputStream in = conn.getInputStream();
			// 定义起始和结束点
			int beginPoint = 0, endPoint = 0;

			// 输出信息
			outputTextArea.append(">正在下载第" + (index + 1) + "块文件\r\n");
			// 起点
			beginPoint = index * blockSize;

			// 判断结束点
			if (index < (blockNum - 1))
			{
				endPoint = beginPoint + blockSize;
			} else
			{
				endPoint = fileSize;
			}

			// 将下载的文件存储到一个文件夹中
			// 当临时文件夹不存在时，则新建
			if (!tempDir.exists())
			{
				tempDir.mkdirs();
			}

			// 文件输出流
			FileOutputStream fos = new FileOutputStream(new File(tempDir, fileName + "_" + (index + 1)));

			// 跳过 beginPoint个字节进行读取
			in.skip(beginPoint);
			// 缓冲大小
			byte[] buffer = new byte[1024];

			// 读取到的内容
			int content;
			// 定义当前下载进度
			int process = beginPoint;
			// 当进度未到达结束字节数时，继续
			while (process < endPoint)
			{
				// 读取数据
				content = in.read(buffer);
				// 判断是否读到最后一块
				if (process + content >= endPoint)
				{
					content = endPoint - process;
					process = endPoint;
				} else
				{
					// 计算当前进度
					process = process + content;
				}

				// 保存文件流
				fos.write(buffer, 0, content);

			}

			// 关闭流
			fos.close();
			in.close();

		} catch (Exception e)
		{
			e.printStackTrace();
			// 输出信息
			outputTextArea.append("*第" + (index + 1) + "块文件下载出错！\r\n");
			JOptionPane.showMessageDialog(outputTextArea, "第" + (index + 1) + "块文件下载出错！", "错误",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

}
