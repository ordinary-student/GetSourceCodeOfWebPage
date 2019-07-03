package com.main;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.ui.frame.MainFrame;

/*
 * main入口
 */
public class GetSourceCodeOfWebPage
{
	// 版本信息
	// private static String version = "6.2";

	public static void main(String[] args)
	{

		try
		{
			// 设置本地窗口风格样式
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "加载本地系统窗口样式失败！");
		} finally
		{
			new MainFrame().setVisible(true);
		}
	}
}
