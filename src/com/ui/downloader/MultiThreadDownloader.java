package com.ui.downloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.ui.downloader.thread.SingleDownloadThread;

/**
 * 多线程下载类
 * 
 * @author ordinary-student
 *
 */
public class MultiThreadDownloader
{
	// 信息输出区
	private JTextArea outputTextArea;
	// 下载目录
	private File downloadDir;
	// 临时文件夹
	private File tempDir;

	/*
	 * 构造方法
	 */
	public MultiThreadDownloader(JTextArea outputTextArea, File downloadDir, File tempDir)
	{
		this.outputTextArea = outputTextArea;
		this.downloadDir = downloadDir;
		this.tempDir = tempDir;

		// 若下载目录不存在，则创建
		if (!this.downloadDir.exists())
		{
			this.downloadDir.mkdirs();
		}
		// 若临时文件夹不存在，则创建
		if (!this.tempDir.exists())
		{
			this.tempDir.mkdirs();
		}
	}

	/**
	 * 下载
	 * 
	 * @param url_str
	 * @throws Exception
	 */
	public void download(String url_str) throws Exception
	{
		// 记录开始下载的时间
		long begin_time = new Date().getTime();

		// 创建一个URL链接
		URL url = new URL(url_str);

		// 获取连接
		URLConnection connection = url.openConnection();

		// 获取文件全路径
		String fileName = url.getFile();

		// 获取文件名
		fileName = fileName.substring(fileName.lastIndexOf("/"));

		// 开始下载
		outputTextArea.append("[---开始下载 " + getCurrentTime() + "---]\r\n");
		outputTextArea.append("链接：" + url_str + "\r\n");
		outputTextArea.append("主机：" + url.getHost() + "\r\n");
		outputTextArea.append("端口：" + url.getPort() + "\r\n");
		outputTextArea.append("协议：" + url.getProtocol() + "\r\n");

		// 获取文件大小
		int fileSize = connection.getContentLength();
		outputTextArea.append("文件总共大小：" + fileSize + "字节\r\n");

		// 获取分块大小属性值
		// TODO
		int fileBlockSize = 1;
		// 设置分块大小
		int blockSize = fileBlockSize * 1024 * 1024;
		// 文件分块的数量
		int blockNum = fileSize / blockSize;
		// 若不能整分，则分块数加一
		if ((fileSize % blockSize) != 0)
		{
			blockNum = blockNum + 1;
		}

		// 获取最大线程属性值
		// TODO
		int maxThreadNum = 20;
		// 判断
		if (blockNum > maxThreadNum)
		{
			JOptionPane.showMessageDialog(outputTextArea, "超出最大线程数量！请把文件分块大小调大一点！", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 分块数/线程数
		outputTextArea.append("分块数/线程数：" + blockNum + "\r\n");

		// 创建线程组
		Thread[] threads = new Thread[blockNum];
		// 循环创建线程
		for (int i = 0; i < blockNum; i++)
		{
			// 创建一个下载线程
			threads[i] = new SingleDownloadThread(outputTextArea, url, i, blockSize, blockNum, fileSize, fileName,
					tempDir);
			// 启动下载线程
			threads[i].start();
		}

		// 当所有线程都结束时才开始文件的合并
		for (Thread t : threads)
		{
			t.join();
		}

		// 合并文件
		mergeFile(blockNum, fileName);
		// 合并完成
		outputTextArea.append("合并完成！\r\n");

		// 计算用时
		long end_time = new Date().getTime();
		long seconds = (end_time - begin_time) / 1000;
		long minutes = seconds / 60;
		long second = seconds % 60;

		// 下载完成
		outputTextArea.append("下载完成！用时：" + minutes + "分" + second + "秒\r\n");
		outputTextArea.append("\r\n");

	}

	/**
	 * 合并文件
	 * 
	 * @param blockNum
	 * @param fileName
	 */
	public void mergeFile(int blockNum, String fileName)
	{
		try
		{
			// 输出合并文件信息
			outputTextArea.append("正在合并文件...\r\n");
			// 定义文件输出流
			FileOutputStream fos = new FileOutputStream(tempDir + fileName);
			// 遍历读分块文件
			for (int i = 0; i < blockNum; i++)
			{
				// 读分块文件
				FileInputStream fis = new FileInputStream(tempDir + "temp/" + fileName + "_" + (i + 1));
				// 缓冲区
				byte[] buffer = new byte[1024];
				int count;
				while ((count = fis.read(buffer)) > 0)
				{
					// 写入一个文件
					fos.write(buffer, 0, count);
				}

				// 关闭流
				fis.close();
			}

			// 关闭流
			fos.close();

		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(outputTextArea, "合并文件出错！");
			return;
		}
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间
	 */
	private String getCurrentTime()
	{
		// 格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 返回
		return df.format(System.currentTimeMillis());
	}
}