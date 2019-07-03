package com.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ui.button.KButton;
import com.utils.DocumentUtil;

/**
 * 设置代理头窗口类
 * 
 * @author ordinary-student
 *
 */
public class SetAgentDialog extends KDialog
{
	private static final long serialVersionUID = 1066121268273989938L;
	private JTextField chrome_TextField;
	private JTextField safari_TextField;
	private JTextField core_TextField;
	private JTextField qqbrowser_TextField;
	private JButton default_Btn;
	private JButton apply_Btn;
	private JButton cancel_Btn;

	/*
	 * 构造方法
	 */
	public SetAgentDialog(Component parent)
	{
		super(parent, "设置代理头");
		// 初始化面板
		initUI();
	}

	/*
	 * 初始化面板
	 */
	private void initUI()
	{
		// 设置大小
		setSize(340, 250);
		// 阻塞父窗体
		setModal(true);

		// 面板
		JPanel panel = new JPanel();
		panel.setBounds(10, 30, 320, 150);
		panel.setBackground(new Color(218, 224, 241));
		panel.setLayout(new GridLayout(5, 2, 10, 10));

		// 操作系统标签
		JLabel os_Label = new JLabel("操作系统：");
		os_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(os_Label);

		// 复选框
		JComboBox<String> os_ComboBox = new JComboBox<String>();
		os_ComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
		os_ComboBox.setBackground(new Color(218, 224, 241));
		os_ComboBox.setFocusable(false);
		os_ComboBox.addItem("Windows 64位");
		panel.add(os_ComboBox);

		// chrome标签
		JLabel chrome_Label = new JLabel("Chrome版本：");
		chrome_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(chrome_Label);

		// chrome版本号填写
		chrome_TextField = new JTextField();
		chrome_TextField.setFont(new Font("楷体", Font.PLAIN, 18));
		chrome_TextField.setBackground(new Color(218, 224, 241));
		chrome_TextField.addKeyListener(this);
		panel.add(chrome_TextField);

		// safari标签
		JLabel safari_Label = new JLabel("Safari版本：");
		safari_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(safari_Label);

		// safari版本号填写
		safari_TextField = new JTextField();
		safari_TextField.setFont(new Font("楷体", Font.PLAIN, 18));
		safari_TextField.setBackground(new Color(218, 224, 241));
		safari_TextField.addKeyListener(this);
		panel.add(safari_TextField);

		// core标签
		JLabel core_Label = new JLabel("Core版本：");
		core_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(core_Label);

		// core版本号填写
		core_TextField = new JTextField();
		core_TextField.setFont(new Font("楷体", Font.PLAIN, 18));
		core_TextField.setBackground(new Color(218, 224, 241));
		core_TextField.addKeyListener(this);
		panel.add(core_TextField);

		// QQ浏览器标签
		JLabel qqbrowser_Label = new JLabel("QQ浏览器版本：");
		qqbrowser_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		panel.add(qqbrowser_Label);

		// qqbrowser版本号填写
		qqbrowser_TextField = new JTextField();
		qqbrowser_TextField.setFont(new Font("楷体", Font.PLAIN, 18));
		qqbrowser_TextField.setBackground(new Color(218, 224, 241));
		qqbrowser_TextField.addKeyListener(this);
		panel.add(qqbrowser_TextField);
		getContentPane().add(panel);

		// 恢复默认按钮
		default_Btn = new KButton("恢复默认值");
		default_Btn.setBounds(10, 210, 100, 30);
		default_Btn.addActionListener(this);
		getContentPane().add(default_Btn);

		// 应用按钮
		apply_Btn = new KButton("应用");
		apply_Btn.setBounds(120, 210, 100, 30);
		apply_Btn.addActionListener(this);
		getContentPane().add(apply_Btn);

		// 关闭按钮
		cancel_Btn = new KButton("关闭");
		cancel_Btn.setBounds(230, 210, 100, 30);
		cancel_Btn.addActionListener(this);
		getContentPane().add(cancel_Btn);

		// 设置浏览器版本号
		chrome_TextField.setText(DocumentUtil.chrome);
		safari_TextField.setText(DocumentUtil.safari);
		core_TextField.setText(DocumentUtil.core);
		qqbrowser_TextField.setText(DocumentUtil.qqbrowser);

		// 排版
		validate();
	}

	// 按钮事件
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// 判断事件发生源
		if (e.getSource() == default_Btn)
		{
			// 恢复默认值
			restoreDefault();

		} else if (e.getSource() == apply_Btn)
		{
			// 使修改值生效
			apply();

		} else if (e.getSource() == cancel_Btn)
		{
			this.dispose();
		}

	}

	/*
	 * 使修改值生效
	 */
	private void apply()
	{
		if (!isBlank(chrome_TextField))
		{
			DocumentUtil.chrome = chrome_TextField.getText().replaceAll(" ", "");
		} else
		{
			return;
		}

		if (!isBlank(safari_TextField))
		{
			DocumentUtil.safari = safari_TextField.getText().replaceAll(" ", "");
		} else
		{
			return;
		}

		if (!isBlank(core_TextField))
		{
			DocumentUtil.core = core_TextField.getText().replaceAll(" ", "");
		} else
		{
			return;
		}

		if (!isBlank(qqbrowser_TextField))
		{
			DocumentUtil.qqbrowser = qqbrowser_TextField.getText().replaceAll(" ", "");
		} else
		{
			return;
		}

		// 应用成功
		JOptionPane.showMessageDialog(this, "应用成功！");
	}

	/*
	 * 恢复默认值
	 */
	private void restoreDefault()
	{
		chrome_TextField.setText("70.0.3538.25");
		safari_TextField.setText("537.36");
		core_TextField.setText("1.70.3695.400");
		qqbrowser_TextField.setText("10.4.3562.400");
	}

	/*
	 * 检查是否留空
	 */
	private boolean isBlank(JTextField jTextField)
	{
		if ((jTextField.getText() == null) || (jTextField.getText().equals(""))
				|| (jTextField.getText().replaceAll(" ", "").equals("")))
		{
			JOptionPane.showMessageDialog(this, "不能留空！", "填写错误！", JOptionPane.ERROR_MESSAGE);
			return true;
		} else
		{
			return false;
		}
	}

	// 按键释放
	@Override
	public void keyReleased(KeyEvent ke)
	{
		// 判断键符
		int key = ke.getKeyCode();
		if (key == KeyEvent.VK_ENTER)
		{
			// 判断来源
			if (ke.getSource() == chrome_TextField)
			{
				safari_TextField.requestFocus();
			} else if (ke.getSource() == safari_TextField)
			{
				core_TextField.requestFocus();
			} else if (ke.getSource() == core_TextField)
			{
				qqbrowser_TextField.requestFocus();
			} else if (ke.getSource() == qqbrowser_TextField)
			{
				apply();
			}
		}
	}

}
