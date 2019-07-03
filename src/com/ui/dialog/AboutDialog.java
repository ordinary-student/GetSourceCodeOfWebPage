package com.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.ui.button.KButton;

/**
 * 关于-窗口类
 * 
 * @author ordinary-student
 *
 */
public class AboutDialog extends KDialog
{
	private static final long serialVersionUID = -1508832244630515665L;
	private JLabel authorLogo_Label;
	private JLabel qrcode_Label;
	private JButton update_Btn;
	private JButton exit_Btn;

	/*
	 * 构造方法
	 */
	public AboutDialog(Component parent)
	{
		super(parent, "关于本软件");
		// 初始化面板
		initUI();
	}

	/*
	 * 初始化面板
	 */
	private void initUI()
	{
		// 设置大小
		setSize(400, 250);
		// 阻塞父窗体
		setModal(true);

		// 程序名字标签
		JLabel name_Label = new JLabel("获取网页源代码");
		name_Label.setBounds(10, 30, 150, 30);
		name_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		getContentPane().add(name_Label);

		// 版本标签
		JLabel version_Label = new JLabel("版本：v1.0");
		version_Label.setBounds(10, 70, 150, 30);
		version_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		getContentPane().add(version_Label);

		// 作者标签
		JLabel author_Label = new JLabel("作者：");
		author_Label.setBounds(10, 110, 70, 30);
		author_Label.setFont(new Font("楷体", Font.PLAIN, 18));
		getContentPane().add(author_Label);

		// 作者LOGO标签
		authorLogo_Label = new JLabel();
		authorLogo_Label.setBounds(90, 110, 92, 30);
		authorLogo_Label.setIcon(new ImageIcon(AboutDialog.class.getResource("/res/author.png")));
		authorLogo_Label.setToolTipText("咸鱼学社");
		authorLogo_Label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		authorLogo_Label.addMouseListener(this);
		getContentPane().add(authorLogo_Label);

		// 二维码图片标签
		qrcode_Label = new JLabel();
		qrcode_Label.setBounds(230, 30, 130, 130);
		qrcode_Label.setIcon(new ImageIcon(AboutDialog.class.getResource("/res/qrcode.jpg")));
		qrcode_Label.setToolTipText("咸鱼学社");
		qrcode_Label.setVisible(false);
		getContentPane().add(qrcode_Label, BorderLayout.CENTER);

		// 更新软件按钮
		update_Btn = new KButton("更新软件");
		update_Btn.setBounds(30, 200, 120, 35);
		update_Btn.addActionListener(this);
		getContentPane().add(update_Btn, BorderLayout.WEST);

		// 关闭按钮
		exit_Btn = new KButton("关闭");
		exit_Btn.setBounds(220, 200, 120, 35);
		exit_Btn.addActionListener(this);
		getContentPane().add(exit_Btn, BorderLayout.EAST);

		// 排版
		validate();

	}

	// 按钮按下
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == update_Btn)
		{
			// 更新
			update();

		} else if (e.getSource() == exit_Btn)
		{
			// 关闭
			this.dispose();
		}
	}

	/*
	 * 更新
	 */
	private void update()
	{
		try
		{
			// 创建URI对象
			URI uri = new URI("https://github.com/ordinary-student/GetSourceCodeOfWebPage");
			// 调用默认浏览器打开指定URL
			Desktop.getDesktop().browse(uri);

		} catch (Exception exception)
		{
			exception.printStackTrace();
			JOptionPane.showMessageDialog(this, "链接打开失败！");
			return;
		}
	}

	// 鼠标点击
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if ((e.getSource() == authorLogo_Label) && (e.getButton() == MouseEvent.BUTTON1))
		{
			qrcode_Label.setVisible(!qrcode_Label.isVisible());
		}
	}

}
