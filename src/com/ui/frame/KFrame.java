package com.ui.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

/**
 * 自定义窗口类
 * 
 * @author ordinary-student
 *
 */
public class KFrame extends JFrame
		implements ActionListener, KeyListener, MouseListener, MouseMotionListener, WindowListener
{
	private static final long serialVersionUID = -6536900275981354816L;

	/*
	 * 构造方法
	 */
	public KFrame()
	{
	}

	/*
	 * 设置菜单项外观
	 */
	public void setAppearance(JMenuItem jMenuItem, String iconName)
	{
		// 背景颜色
		jMenuItem.setBackground(new Color(218, 224, 241));
		// 字体
		jMenuItem.setFont(new Font("楷体", Font.PLAIN, 18));
		// 光标
		jMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		// 焦点框
		jMenuItem.setFocusPainted(false);
		// 图标
		if (!iconName.equals(""))
		{
			jMenuItem.setIcon(new ImageIcon(KFrame.class.getResource("/res/" + iconName)));
		}
	}

	@Override
	public void windowOpened(WindowEvent e)
	{

	}

	@Override
	public void windowClosing(WindowEvent e)
	{

	}

	@Override
	public void windowClosed(WindowEvent e)
	{

	}

	@Override
	public void windowIconified(WindowEvent e)
	{

	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{

	}

	@Override
	public void windowActivated(WindowEvent e)
	{

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{

	}

	@Override
	public void mouseDragged(MouseEvent e)
	{

	}

	@Override
	public void mouseMoved(MouseEvent e)
	{

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO 自动生成的方法存根

	}

}
