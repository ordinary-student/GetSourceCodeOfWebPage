package com.ui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 自定义弹窗类
 */
/**
 * @author ordinary-student
 *
 */
public class KDialog extends JDialog
		implements ActionListener, ChangeListener, KeyListener, MouseListener, MouseMotionListener, WindowListener
{
	private static final long serialVersionUID = 4985628449657212965L;
	private Point origin_Point;

	/*
	 * 构造方法
	 */
	public KDialog()
	{
		this(null, "");
	}

	/*
	 * 带参构造方法
	 */
	public KDialog(Component owner, String title)
	{
		// 设置默认大小
		setSize(340, 250);
		// 设置位置
		setLocationRelativeTo(owner);
		// 禁用窗体装饰，不显示标题栏，关闭，最小化等
		setUndecorated(true);
		// 大小不可变
		setResizable(false);
		// 设置背景颜色
		getContentPane().setBackground(new Color(218, 224, 241));
		// 标题边界
		Border lineBorder = BorderFactory.createLineBorder(new Color(0, 128, 0));
		// 设置标题边界
		((JComponent) getContentPane()).setBorder(BorderFactory.createTitledBorder(lineBorder, title,
				TitledBorder.CENTER, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 14), new Color(0, 128, 0)));
		// 设置绝对布局
		getContentPane().setLayout(null);
		// 设置透明度
		setOpacity(0.9f);
		// 设置关闭
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// 设置不可视
		setVisible(false);
		// 添加监听
		addMouseListener(this);
		addMouseMotionListener(this);
		origin_Point = new Point();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// 当鼠标拖动时获取窗口当前位置
		Point p = this.getLocation();
		// 设置窗口的位置
		// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
		this.setLocation(p.x + e.getX() - origin_Point.x, p.y + e.getY() - origin_Point.y);
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
		// 当鼠标按下的时候获得窗口当前的位置
		origin_Point.x = e.getX();
		origin_Point.y = e.getY();
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
	}

	@Override
	public void stateChanged(ChangeEvent e)
	{
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

}
