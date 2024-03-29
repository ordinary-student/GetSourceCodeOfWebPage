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
import com.url.model.ParserModel;

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
	private JCheckBox css_CheckBox;
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
		// 初始化解析模式
		initChoice();
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
		setSize(350, 330);

		// 定义按钮组
		ButtonGroup buttonGroup = new ButtonGroup();

		// 默认
		default_RadioButton = new JRadioButton("默认(源代码输出)", true);
		default_RadioButton.setBounds(40, 30, 200, 30);
		default_RadioButton.setFont(new Font("楷体", Font.PLAIN, 18));
		default_RadioButton.setBackground(new Color(218, 224, 241));
		default_RadioButton.addActionListener(this);
		getContentPane().add(default_RadioButton);

		// 所有链接
		allLink_RadioButton = new JRadioButton("所有链接");
		allLink_RadioButton.setBounds(40, 70, 200, 30);
		allLink_RadioButton.setFont(new Font("楷体", Font.PLAIN, 18));
		allLink_RadioButton.setBackground(new Color(218, 224, 241));
		allLink_RadioButton.addActionListener(this);
		getContentPane().add(allLink_RadioButton);

		// 自定义
		custom_RadioButton = new JRadioButton("自定义筛选条件");
		custom_RadioButton.setBounds(40, 110, 200, 30);
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
		audio_CheckBox.setBounds(60, 150, 100, 30);
		audio_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		audio_CheckBox.setBackground(new Color(218, 224, 241));
		audio_CheckBox.setFocusPainted(false);
		audio_CheckBox.setVisible(false);
		getContentPane().add(audio_CheckBox);

		// 视频
		video_CheckBox = new JCheckBox("视频");
		video_CheckBox.setBounds(170, 150, 100, 30);
		video_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		video_CheckBox.setBackground(new Color(218, 224, 241));
		video_CheckBox.setFocusPainted(false);
		video_CheckBox.setVisible(false);
		getContentPane().add(video_CheckBox);

		// 图片
		picture_CheckBox = new JCheckBox("图片");
		picture_CheckBox.setBounds(60, 190, 100, 30);
		picture_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		picture_CheckBox.setBackground(new Color(218, 224, 241));
		picture_CheckBox.setFocusPainted(false);
		picture_CheckBox.setVisible(false);
		getContentPane().add(picture_CheckBox);

		// 网页
		html_CheckBox = new JCheckBox("网页");
		html_CheckBox.setBounds(170, 190, 100, 30);
		html_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		html_CheckBox.setBackground(new Color(218, 224, 241));
		html_CheckBox.setFocusPainted(false);
		html_CheckBox.setVisible(false);
		getContentPane().add(html_CheckBox);

		// css和js
		css_CheckBox = new JCheckBox("css、js");
		css_CheckBox.setBounds(60, 230, 100, 30);
		css_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		css_CheckBox.setBackground(new Color(218, 224, 241));
		css_CheckBox.setFocusPainted(false);
		css_CheckBox.setVisible(false);
		getContentPane().add(css_CheckBox);

		// 其它
		other_CheckBox = new JCheckBox("其它链接");
		other_CheckBox.setBounds(170, 230, 100, 30);
		other_CheckBox.setFont(new Font("楷体", Font.PLAIN, 18));
		other_CheckBox.setBackground(new Color(218, 224, 241));
		other_CheckBox.setFocusPainted(false);
		other_CheckBox.setVisible(false);
		getContentPane().add(other_CheckBox);

		// 应用按钮
		apply_Btn = new KButton("应用");
		apply_Btn.setBounds(50, 280, 100, 30);

		apply_Btn.addActionListener(this);
		getContentPane().add(apply_Btn);

		// 关闭按钮
		exit_Btn = new KButton("关闭");
		exit_Btn.setBounds(200, 280, 100, 30);
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

	/**
	 * 初始化解析模式
	 */
	private void initChoice()
	{

		if (MainFrame.parserModel.getModel() == ParserModel.DEFAULT_MODEL)
		{
			default_RadioButton.doClick();

		} else if (MainFrame.parserModel.getModel() == ParserModel.ALL_MODEL)
		{
			allLink_RadioButton.doClick();

		} else if (MainFrame.parserModel.getModel() == ParserModel.CUSTOM_MODEL)
		{
			// 显示解析模式
			showParserModel(MainFrame.parserModel);
			custom_RadioButton.doClick();
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
			MainFrame.parserModel = new ParserModel(ParserModel.DEFAULT_MODEL);

		} else if (allLink_RadioButton.isSelected())
		{
			MainFrame.parserModel = new ParserModel(ParserModel.ALL_MODEL);

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
	private ParserModel getParserModel()
	{
		// 获取值
		boolean[] values = { audio_CheckBox.isSelected(), video_CheckBox.isSelected(), picture_CheckBox.isSelected(),
				html_CheckBox.isSelected(), css_CheckBox.isSelected(), other_CheckBox.isSelected() };

		// 返回
		return new ParserModel(values);
	}

	/*
	 * 获取解析模式
	 */
	private void showParserModel(ParserModel parserModel)
	{
		// 获取值
		boolean[] values = parserModel.getValues();
		// 设置
		audio_CheckBox.setSelected(values[0]);
		video_CheckBox.setSelected(values[1]);
		picture_CheckBox.setSelected(values[2]);
		html_CheckBox.setSelected(values[3]);
		css_CheckBox.setSelected(values[4]);
		other_CheckBox.setSelected(values[5]);
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
		css_CheckBox.setVisible(visible);
		other_CheckBox.setVisible(visible);
	}
}
