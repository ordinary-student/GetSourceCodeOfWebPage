package com.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.downloader.MultiThreadDownloader;
import com.ui.button.KButton;

/**
 * 下载器窗口类
 * 
 * @author ordinary-student
 *
 */
public class DownloaderDialog extends KDialog
{
	private static final long serialVersionUID = 3155235844291910787L;
	private JButton exit_Btn;
	private JButton download_Btn;
	private MultiThreadDownloader multiThreadDownloader;
	private JTextArea url_textArea;
	private JTextArea outputMess_textArea;
	private JButton paste_Btn;
	private JButton clearUrl_Btn;
	private JButton clearMess_Btn;
	private JButton openDir_Btn;
	private JTextArea outputTextArea;
	// 文件存放目录
	private File downloadDir;
	// 临时目录
	private File tempDir;
	// 实例对象
	public static DownloaderDialog instance;

	/*
	 * 构造方法
	 */
	private DownloaderDialog(Component parent, JTextArea outputTextArea)
	{
		super(parent, "下载器");
		this.outputTextArea = outputTextArea;

		// 初始化面板
		initUI();

		// 文件存放目录
		downloadDir = new File("E:/temp/download/");
		// 临时目录
		tempDir = new File("E:/temp/download/temp/");

		// 若下载目录不存在，则创建
		if (!downloadDir.exists())
		{
			downloadDir.mkdirs();
		}
		// 若临时文件夹不存在，则创建
		if (!tempDir.exists())
		{
			tempDir.mkdirs();
		}

		// 多线程下载器
		multiThreadDownloader = new MultiThreadDownloader(outputTextArea, downloadDir, tempDir);
	}

	/*
	 * 获取实例对象
	 */
	public static DownloaderDialog getInstance(Component parent, JTextArea outputTextArea)
	{
		// 为空
		if (instance == null)
		{
			instance = new DownloaderDialog(parent, outputTextArea);
		}

		// 返回实例对象
		return instance;
	}

	/*
	 * 初始化面板
	 */
	private void initUI()
	{
		// 设置大小
		setSize(600, 600);

		// 下载地址区
		url_textArea = new JTextArea();
		url_textArea.setBackground(new Color(218, 224, 241));
		url_textArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));

		JScrollPane urlScrollPane = new JScrollPane(url_textArea);
		urlScrollPane.setBounds(10, 30, 580, 150);
		urlScrollPane.setBackground(new Color(218, 224, 241));
		// 标题边界
		Border lineBorder = BorderFactory.createLineBorder(new Color(0, 128, 0));
		// 设置标题边界
		urlScrollPane.setBorder(BorderFactory.createTitledBorder(lineBorder, "下载链接", TitledBorder.CENTER,
				TitledBorder.TOP, new Font("楷体", Font.PLAIN, 16), new Color(0, 128, 0)));
		getContentPane().add(urlScrollPane);

		// 粘贴按钮
		paste_Btn = new KButton("粘贴解析结果");
		paste_Btn.setBounds(50, 200, 150, 30);
		paste_Btn.addActionListener(this);
		getContentPane().add(paste_Btn);

		// 清空按钮
		clearUrl_Btn = new KButton("清空下载链接");
		clearUrl_Btn.setBounds(250, 200, 150, 30);
		clearUrl_Btn.addActionListener(this);
		getContentPane().add(clearUrl_Btn);

		// 下载按钮
		download_Btn = new KButton("下载");
		download_Btn.setBounds(450, 200, 100, 30);
		download_Btn.addActionListener(this);
		getContentPane().add(download_Btn);

		// 输出信息区
		outputMess_textArea = new JTextArea();
		outputMess_textArea.setBackground(new Color(218, 224, 241));
		outputMess_textArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		outputMess_textArea.setEditable(false);

		JScrollPane outputScrollPane = new JScrollPane(outputMess_textArea);
		outputScrollPane.setBounds(10, 250, 580, 290);
		outputScrollPane.setBackground(new Color(218, 224, 241));
		// 标题边界
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(0, 128, 0));
		// 设置标题边界
		outputScrollPane.setBorder(BorderFactory.createTitledBorder(lineBorder2, "输出信息", TitledBorder.CENTER,
				TitledBorder.TOP, new Font("楷体", Font.PLAIN, 16), new Color(0, 128, 0)));
		getContentPane().add(outputScrollPane);

		// 清空输出信息按钮
		clearMess_Btn = new KButton("清空输出信息");
		clearMess_Btn.setBounds(50, 560, 150, 30);
		clearMess_Btn.addActionListener(this);
		getContentPane().add(clearMess_Btn);

		// 打开下载文件夹
		openDir_Btn = new KButton("打开下载目录");
		openDir_Btn.setBounds(250, 560, 150, 30);
		openDir_Btn.addActionListener(this);
		getContentPane().add(openDir_Btn);

		// 关闭按钮
		exit_Btn = new KButton("关闭");
		exit_Btn.setBounds(450, 560, 100, 30);
		exit_Btn.addActionListener(this);
		getContentPane().add(exit_Btn);

		validate();
	}

	// 事件监听
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// 判断来源
		if (e.getSource() == paste_Btn)
		{
			// 粘贴
			url_textArea.setText(outputTextArea.getText());

		} else if (e.getSource() == clearUrl_Btn)
		{
			// 清除链接
			url_textArea.setText("");

		} else if (e.getSource() == download_Btn)
		{
			// 下载
			download();

		} else if (e.getSource() == clearMess_Btn)
		{
			// 清空输出信息
			outputMess_textArea.setText("");

		} else if (e.getSource() == openDir_Btn)
		{
			// 打开目录
			openDownloadDir();

		} else if (e.getSource() == exit_Btn)
		{
			// 关闭
			this.dispose();
			instance = null;
		}
	}

	/*
	 * 打开下载目录
	 */
	private void openDownloadDir()
	{
		// 打开文件存放目录
		try
		{
			Desktop.getDesktop().open(downloadDir);
		} catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "打开下载目录出错！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	/*
	 * 下载
	 */
	private void download()
	{
		try
		{
			// 获取多个链接
			String urls_str = url_textArea.getText();
			// 判断
			if ((urls_str.equals("")) || (urls_str == null))
			{
				JOptionPane.showMessageDialog(this, "请填写下载链接！");
				return;
			}

			// 按行分割
			String[] urls = urls_str.split("\r\n");
			// 遍历
			for (String url_str : urls)
			{
				// 去掉空格
				String url = url_str.replaceAll(" ", "");

				// 判断
				if ((url.equals("")) || (url == null))
				{

				} else
				{
					// 下载
					multiThreadDownloader.download(url);
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "下载文件出错！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

}
