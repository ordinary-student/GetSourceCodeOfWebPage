package com.ui.dialog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.ui.button.KButton;
import com.url.log.UrlLog;
import com.utils.FileUtil;

/**
 * 解析记录窗口类
 * 
 * @author ordinary-student
 *
 */
public class LoggerDialog extends KDialog
{
	private static final long serialVersionUID = 1843581474698247229L;
	// 表
	public JTable log_table;
	// 模式
	private DefaultTableModel model;
	// 数据集
	private Vector<Vector<String>> vData;
	// 列名
	private Vector<String> col_name;
	// 按钮
	private JButton export_Btn;
	private JButton clear_Btn;
	private JButton exit_Btn;
	private List<UrlLog> urlLogList;
	// 实例对象
	public static LoggerDialog instance;

	/*
	 * 构造方法
	 */
	public LoggerDialog(Component parent, List<UrlLog> urlLogList)
	{
		super(parent, "解析记录");
		this.urlLogList = urlLogList;
		// 初始化面板
		initUI();
		// 初始化日志
		initLog();
	}

	/*
	 * 获取实例对象
	 */
	public static LoggerDialog getInstance(Component parent, List<UrlLog> urlLogList)
	{
		// 为空
		if (instance == null)
		{
			instance = new LoggerDialog(parent, urlLogList);
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
		setSize(520, 380);

		// 数据集
		vData = new Vector<Vector<String>>();

		// 列名
		col_name = new Vector<String>();
		col_name.add("序号");
		col_name.add("时间");
		col_name.add("网址");

		// 默认模式
		model = new DefaultTableModel(vData, col_name);
		// 表
		log_table = new JTable(model);

		// 用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看
		JScrollPane scroll = new JScrollPane(log_table);
		scroll.setBounds(10, 30, 500, 300);
		getContentPane().add(scroll);

		// 导出记录按钮
		export_Btn = new KButton("导出记录");
		export_Btn.setBounds(60, 340, 100, 30);
		export_Btn.addActionListener(this);
		getContentPane().add(export_Btn);

		// 清空记录按钮
		clear_Btn = new KButton("清空记录");
		clear_Btn.setBounds(200, 340, 100, 30);
		clear_Btn.addActionListener(this);
		getContentPane().add(clear_Btn);

		// 关闭按钮
		exit_Btn = new KButton("关闭");
		exit_Btn.setBounds(340, 340, 100, 30);
		exit_Btn.addActionListener(this);
		getContentPane().add(exit_Btn);

		// 排版
		validate();

	}

	/*
	 * 初始化日志
	 */
	private void initLog()
	{
		// 遍历
		for (UrlLog log : urlLogList)
		{
			// 添加
			addLog(log.getTime(), log.getUrl());
		}

	}

	/*
	 * 更新日志
	 */
	public void updateLog(List<UrlLog> logList)
	{
		// 移除日志
		removeLog();
		// 重新导入
		// 遍历
		for (UrlLog log : logList)
		{
			// 添加
			addLog(log.getTime(), log.getUrl());
		}
	}

	/*
	 * 添加日志记录
	 */
	private void addLog(String time, String url)
	{
		// 行向量
		Vector<String> vRow = new Vector<String>();
		// 行数据
		vRow.add(log_table.getRowCount() + 1 + "");
		vRow.add(time);
		vRow.add(url);
		// 添加
		vData.add(vRow);
		model = new DefaultTableModel(vData, col_name);
		log_table.setModel(model);
	}

	/*
	 * 移除日志
	 */
	private void removeLog()
	{
		// 判断
		while (log_table.getRowCount() > 0)
		{
			((DefaultTableModel) log_table.getModel()).removeRow(0);
		}
	}

	/*
	 * 删除日志
	 * 
	 */
	private void deleteLog()
	{
		// 移除
		removeLog();
		// 集合清空
		urlLogList.clear();
	}

	/*
	 * 导出日志
	 * 
	 */
	private void exportLog()
	{
		// 弹出文件选择框
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("请选择文件夹");
		fileChooser.setApproveButtonText("导出");

		// 后缀名过滤器
		FileNameExtensionFilter filter = new FileNameExtensionFilter("日志文件(*.csv)", "csv");
		fileChooser.setFileFilter(filter);

		// 获取绝对路径
		String filePath = "";

		// 选择结果
		int result = fileChooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			// 获取选择的文件夹
			File selectedFile = fileChooser.getSelectedFile();
			// 从文件名输入框中获取文件名
			String fname = fileChooser.getName(selectedFile);

			// 假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
			if (fname.indexOf(".csv") == -1)
			{
				selectedFile = new File(fileChooser.getCurrentDirectory(), fname + ".csv");
			}

			// 获取绝对路径
			filePath = selectedFile.getAbsolutePath();

			// 内容
			StringBuffer content = new StringBuffer();

			// 列数
			int columnCount = log_table.getColumnCount();
			// 行数
			int rowCount = log_table.getRowCount();
			// 遍历
			for (int i = 0; i < rowCount; i++)
			{
				for (int j = 0; j < columnCount; j++)
				{
					// 获取单元格的值
					String cell = (String) log_table.getValueAt(i, j);
					content.append(cell + ",");
				}

				// 换行
				content.append("\r\n");
			}

			// 写入文件
			FileUtil.write(selectedFile, content.toString());

		} else
		{
			return;
		}

		// 选择弹窗
		Object[] options = { "打开文件", "打开文件夹", "取消" };
		// 选择结果
		int choice = JOptionPane.showOptionDialog(this, "导出日志成功！", "请选择", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);

		// 判断
		if (choice == 0)
		{
			// 打开文件
			openFile(new File(filePath));

		} else if (choice == 1)
		{
			// 打开文件存放目录
			openFile(new File(filePath).getParentFile());
		}

	}

	/*
	 * 打开文件
	 */
	private void openFile(File file)
	{
		try
		{
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("cmd /c start explorer " + file.getAbsolutePath());

		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(this, "打开失败！", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// 判断事件发生源
		if (ae.getSource() == clear_Btn)
		{
			// 删除日志
			deleteLog();

		} else if (ae.getSource() == export_Btn)
		{
			// 导出日志
			exportLog();

		} else if (ae.getSource() == exit_Btn)
		{
			this.dispose();
			instance = null;
		}

	}

}
