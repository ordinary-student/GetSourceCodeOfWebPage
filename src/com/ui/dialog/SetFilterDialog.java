package com.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import com.ui.button.KButton;
import com.ui.frame.MainFrame;

/**
 * 设置筛选条件窗口类
 * 
 * @author ordinary-student
 *
 */
public class SetFilterDialog extends KDialog
{
	private static final long serialVersionUID = -5950315543969756936L;
	public static SetFilterDialog instance;
	private JRadioButton default_RadioButton;
	private JRadioButton allLink_RadioButton;
	private JRadioButton custom_RadioButton;
	private JCheckBox audio_CheckBox;
	private JCheckBox video_CheckBox;
	private JCheckBox picture_CheckBox;
	private JCheckBox html_CheckBox;
	private JCheckBox other_CheckBox;
	private JButton apply_Btn;
	private JButton exit_Btn;

	/*
	 * 构造方法
	 */
	public SetFilterDialog(Component parent)
	{
		super(parent, "设置筛选条件");
		// 初始化面板
		initUI();
	}

	/*
	 * 获取实例对象
	 */
	public static SetFilterDialog getInstance(Component parent)
	{
		// 为空
		if (instance == null)
		{
			instance = new SetFilterDialog(parent);
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
		setSize(340, 250);

		// 定义按钮组
		ButtonGroup buttonGroup = new ButtonGroup();

		// 默认
		default_RadioButton = new JRadioButton("默认(源代码输出)", true);
		default_RadioButton.setBounds(40, 26, 150, 23);
		default_RadioButton.setFont(new Font("楷体", Font.PLAIN, 18));
		default_RadioButton.setBackground(new Color(218, 224, 241));
		default_RadioButton.addActionListener(this);
		getContentPane().add(default_RadioButton);

		// 所有链接
		allLink_RadioButton = new JRadioButton("所有链接");
		allLink_RadioButton.setBounds(40, 50, 150, 23);
		allLink_RadioButton.setFont(new Font("楷体", Font.PLAIN, 18));
		allLink_RadioButton.setBackground(new Color(218, 224, 241));
		allLink_RadioButton.addActionListener(this);
		getContentPane().add(allLink_RadioButton);

		// 自定义
		custom_RadioButton = new JRadioButton("自定义筛选条件");
		custom_RadioButton.setBounds(40, 74, 150, 23);
		custom_RadioButton.setFont(new Font("楷体", Font.PLAIN, 18));
		custom_RadioButton.setBackground(new Color(218, 224, 241));
		custom_RadioButton.addActionListener(this);
		getContentPane().add(custom_RadioButton);

		// 添加进按钮组
		buttonGroup.add(default_RadioButton);
		buttonGroup.add(allLink_RadioButton);
		buttonGroup.add(custom_RadioButton);

		// 音频
		audio_CheckBox = new JCheckBox("音频");
		audio_CheckBox.setBounds(60, 98, 100, 23);
		audio_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		audio_CheckBox.setBackground(new Color(218, 224, 241));
		audio_CheckBox.setFocusPainted(false);
		audio_CheckBox.setVisible(false);
		getContentPane().add(audio_CheckBox);

		// 视频
		video_CheckBox = new JCheckBox("视频");
		video_CheckBox.setBounds(60, 122, 100, 23);
		video_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		video_CheckBox.setBackground(new Color(218, 224, 241));
		video_CheckBox.setFocusPainted(false);
		video_CheckBox.setVisible(false);
		getContentPane().add(video_CheckBox);

		// 图片
		picture_CheckBox = new JCheckBox("图片");
		picture_CheckBox.setBounds(60, 146, 100, 23);
		picture_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		picture_CheckBox.setBackground(new Color(218, 224, 241));
		picture_CheckBox.setFocusPainted(false);
		picture_CheckBox.setVisible(false);
		getContentPane().add(picture_CheckBox);

		// 网页
		html_CheckBox = new JCheckBox("网页");
		html_CheckBox.setBounds(60, 170, 100, 23);
		html_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		html_CheckBox.setBackground(new Color(218, 224, 241));
		html_CheckBox.setFocusPainted(false);
		html_CheckBox.setVisible(false);
		getContentPane().add(html_CheckBox);

		// 其它
		other_CheckBox = new JCheckBox("其它链接");
		other_CheckBox.setBounds(162, 171, 100, 23);
		other_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		other_CheckBox.setBackground(new Color(218, 224, 241));
		other_CheckBox.setFocusPainted(false);
		other_CheckBox.setVisible(false);
		getContentPane().add(other_CheckBox);

		// 应用按钮
		apply_Btn = new KButton("应用");
		apply_Btn.setBounds(50, 210, 100, 30);

		apply_Btn.addActionListener(this);
		getContentPane().add(apply_Btn);

		// 关闭按钮
		exit_Btn = new KButton("关闭");
		exit_Btn.setBounds(200, 210, 100, 30);
		exit_Btn.addActionListener(this);
		getContentPane().add(exit_Btn);

		// 排版
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == default_RadioButton)
		{
			// 选中默认
			jCheckBoxSetVisible(false);

		} else if (e.getSource() == allLink_RadioButton)
		{
			// 选中所有链接
			jCheckBoxSetVisible(false);

		} else if (e.getSource() == custom_RadioButton)
		{
			// 选中自定义筛选条件
			jCheckBoxSetVisible(true);

		} else if (e.getSource() == apply_Btn)
		{
			// 应用
			apply();

		} else if (e.getSource() == exit_Btn)
		{
			// 关闭
			this.dispose();
			instance = null;
		}
	}

	/*
	 * 应用
	 */
	private void apply()
	{
		// 应用
		if (default_RadioButton.isSelected())
		{
			MainFrame.parserModel = 0;
		} else if (allLink_RadioButton.isSelected())
		{
			MainFrame.parserModel = 1;
		} else if (custom_RadioButton.isSelected())
		{
			// 获取解析模式
			MainFrame.parserModel = getParserModel();
		}

		// 应用成功
		JOptionPane.showMessageDialog(this, "应用成功！");

	}

	/*
	 * 获取解析模式
	 */
	private int getParserModel()
	{
		//
		int[] model = { 1, 1, 1, 1, 1 };
		// 判断
		if (audio_CheckBox.isSelected())
		{
			model[0] = 2;
		}
		if (video_CheckBox.isSelected())
		{
			model[1] = 2;
		}
		if (picture_CheckBox.isSelected())
		{
			model[2] = 2;
		}
		if (html_CheckBox.isSelected())
		{
			model[3] = 2;
		}
		if (other_CheckBox.isSelected())
		{
			model[4] = 2;
		}

		String model_str = "";
		for (int i = 0; i < model.length; i++)
		{
			model_str = model_str + model[i];
		}

		// 返回
		return Integer.parseInt(model_str);

	}

	/*
	 * 设置可视与不可视
	 */
	private void jCheckBoxSetVisible(boolean visible)
	{
		audio_CheckBox.setVisible(visible);
		video_CheckBox.setVisible(visible);
		picture_CheckBox.setVisible(visible);
		html_CheckBox.setVisible(visible);
		other_CheckBox.setVisible(visible);
	}
}
