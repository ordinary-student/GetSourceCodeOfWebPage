package com.ui.frame;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.jsoup.nodes.Document;

import com.ui.button.KButton;
import com.ui.dialog.AboutDialog;
import com.ui.dialog.DownloaderDialog;
import com.ui.dialog.LoggerDialog;
import com.ui.dialog.SetAgentDialog;
import com.ui.dialog.SetFilterDialog;
import com.urllog.CachedUrlList;
import com.urllog.UrlLog;
import com.utils.DocumentUtil;
import com.utils.FileUtil;
import com.utils.WindowUtil;

/**
 * 主窗体类
 * 
 * @author ordinary-student
 *
 */
public class MainFrame extends KFrame
{
	private static final long serialVersionUID = -4418352844524869701L;

	// 输入框
	private JTextField address_TextField;
	// 按钮
	private JButton start_Btn;
	// 菜单项
	private JMenuItem save_MenuItem;
	private JMenuItem exit_MenuItem;
	private JMenuItem clearUrl_MenuItem;
	private JMenuItem clearCode_MenuItem;
	private JMenuItem start_MenuItem;
	private JMenuItem downloader_MenuItem;
	private JMenuItem setAgent_MenuItem;
	private JMenuItem setFilter_MenuItem;
	private JMenuItem setProperties_MenuItem;
	private JMenuItem changeSkin_MenuItem;
	private JMenuItem record_MenuItem;
	private JMenuItem about_MenuItem;
	// 右键菜单
	private JPopupMenu popupMenu;
	private JMenuItem clearCode_PopupMenuItem;
	private JMenuItem downloader_PopupMenuItem;
	private MenuItem display_PopupMenuItem;
	private MenuItem exit_PopupMenuItem;
	// 文本框
	private JTextArea output_TextArea;

	// 挂起状态
	private boolean trayStatus = false;
	// 托盘图标
	private TrayIcon trayIcon;
	// 系统托盘
	private SystemTray systemTray;

	// 解析模式
	public static int parserModel = 00000;
	// 解析记录集合
	public static List<UrlLog> urlLogList;
	// 缓存的网址集合
	private CachedUrlList cachedUrlList;

	// 日志窗口
	private LoggerDialog logDialog;

	/*
	 * 构造方法
	 */
	public MainFrame()
	{
		super();
		// 初始化面板
		initUI();
		// 解析记录集合
		urlLogList = new ArrayList<UrlLog>();
		// 缓存的网址集合
		cachedUrlList = new CachedUrlList();
	}

	/*
	 * 初始化面板
	 */
	private void initUI()
	{
		// 设置标题
		setTitle("获取网页源代码");
		// 设置图标
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/res/fish.png")));
		// 设置大小
		setSize(600, 500);
		setMinimumSize(new Dimension(500, 300));
		// 设置居中
		WindowUtil.center(this);
		// 设置边框
		((JComponent) getContentPane()).setBorder(BorderFactory.createTitledBorder(""));
		// 设置背景颜色
		getContentPane().setBackground(new Color(218, 224, 241));
		// 设置布局
		getContentPane().setLayout(new BorderLayout(10, 10));
		// 添加监听
		addWindowListener(this);
		// 设置不可视
		setVisible(false);

		// 添加菜单栏
		addMenuBar();

		// 创建面板
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 224, 241));
		panel.setLayout(new BorderLayout(10, 10));
		// 标题边界
		Border lineBorder = BorderFactory.createLineBorder(new Color(218, 224, 241));
		// 设置标题边界
		panel.setBorder(BorderFactory.createTitledBorder(lineBorder, "填写网页链接地址", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("楷体", Font.PLAIN, 18), new Color(0, 128, 0)));

		// 网页链接填写框
		address_TextField = new JTextField();
		address_TextField.setPreferredSize(new Dimension(350, 35));
		address_TextField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		address_TextField.setBackground(new Color(220, 225, 225));
		address_TextField.addKeyListener(this);
		panel.add(address_TextField, BorderLayout.CENTER);

		// 解析按钮
		start_Btn = new KButton("开始解析");
		start_Btn.setPreferredSize(new Dimension(150, 35));
		start_Btn.setIcon(new ImageIcon(MainFrame.class.getResource("/res/start.png")));
		start_Btn.setFont(new Font("楷体", Font.PLAIN, 18));
		start_Btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		start_Btn.setFocusPainted(false);
		start_Btn.addActionListener(this);
		panel.add(start_Btn, BorderLayout.EAST);
		getContentPane().add(panel, BorderLayout.NORTH);

		// 输出文本域
		output_TextArea = new JTextArea();
		output_TextArea.setBackground(new Color(218, 224, 241));
		output_TextArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		output_TextArea.setEditable(false);
		// 添加鼠标监听
		output_TextArea.addMouseListener(this);

		// 创建带滚动条的面板
		JScrollPane scrollPane = new JScrollPane(output_TextArea);
		scrollPane.setBackground(new Color(218, 224, 241));
		// 标题边界
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(0, 128, 0));
		// 设置标题边界
		scrollPane.setBorder(BorderFactory.createTitledBorder(lineBorder2, "解析结果", TitledBorder.CENTER,
				TitledBorder.TOP, new Font("楷体", Font.PLAIN, 18), new Color(0, 128, 0)));

		// 向窗口添加信息输出区
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// 右键弹出菜单
		popupMenu = new JPopupMenu();

		// 显示主界面菜单项
		clearCode_PopupMenuItem = new JMenuItem("清空代码");
		setAppearance(clearCode_PopupMenuItem, "clear.png");
		clearCode_PopupMenuItem.addActionListener(this);
		popupMenu.add(clearCode_PopupMenuItem);

		// 分割线
		popupMenu.addSeparator();

		// 关闭菜单项
		downloader_PopupMenuItem = new JMenuItem("打开下载器");
		setAppearance(downloader_PopupMenuItem, "download.png");
		downloader_PopupMenuItem.addActionListener(this);
		popupMenu.add(downloader_PopupMenuItem);

		validate();
	}

	/*
	 * 添加菜单栏
	 */
	private void addMenuBar()
	{
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();

		/*** 文件菜单 ***/
		JMenu file_Menu = new JMenu("文件(F)");
		file_Menu.setFont(new Font("楷体", Font.PLAIN, 18));
		// 设置快捷键ALT+F
		file_Menu.setMnemonic('F');

		// 保存菜单项
		save_MenuItem = new JMenuItem("保存");
		setAppearance(save_MenuItem, "save.png");
		save_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		save_MenuItem.addActionListener(this);
		file_Menu.add(save_MenuItem);

		// 分割线
		file_Menu.addSeparator();

		// 退出菜单项
		exit_MenuItem = new JMenuItem("退出");
		setAppearance(exit_MenuItem, "exit.png");
		exit_MenuItem.addActionListener(this);
		file_Menu.add(exit_MenuItem);
		menuBar.add(file_Menu);

		/*** 编辑菜单 ***/
		JMenu edit_Menu = new JMenu("编辑(E)");
		edit_Menu.setFont(new Font("楷体", Font.PLAIN, 18));
		edit_Menu.setMnemonic('E');

		// 清除网址菜单项
		clearUrl_MenuItem = new JMenuItem("清除网址");
		setAppearance(clearUrl_MenuItem, "delete.png");
		clearUrl_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		clearUrl_MenuItem.addActionListener(this);
		edit_Menu.add(clearUrl_MenuItem);

		// 清空代码菜单项
		clearCode_MenuItem = new JMenuItem("清空代码");
		setAppearance(clearCode_MenuItem, "clear.png");
		clearCode_MenuItem.addActionListener(this);
		edit_Menu.add(clearCode_MenuItem);
		menuBar.add(edit_Menu);

		/*** 运行菜单 ***/
		JMenu run_Menu = new JMenu("运行(R)");
		run_Menu.setFont(new Font("楷体", Font.PLAIN, 18));
		run_Menu.setMnemonic('R');

		// 开始解析菜单项
		start_MenuItem = new JMenuItem("开始解析");
		setAppearance(start_MenuItem, "start.png");
		start_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		start_MenuItem.addActionListener(this);
		run_Menu.add(start_MenuItem);

		// 分割线
		run_Menu.addSeparator();

		// 下载器菜单项
		downloader_MenuItem = new JMenuItem("打开下载器");
		setAppearance(downloader_MenuItem, "download.png");
		downloader_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		downloader_MenuItem.addActionListener(this);
		run_Menu.add(downloader_MenuItem);
		menuBar.add(run_Menu);

		/*** 设置菜单 ***/
		JMenu set_Menu = new JMenu("设置(S)");
		set_Menu.setFont(new Font("楷体", Font.PLAIN, 18));
		set_Menu.setMnemonic('S');
		menuBar.add(set_Menu);

		// 设置代理头菜单项
		setAgent_MenuItem = new JMenuItem("设置代理头");
		setAppearance(setAgent_MenuItem, "set.png");
		setAgent_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		setAgent_MenuItem.addActionListener(this);
		set_Menu.add(setAgent_MenuItem);

		// 设置筛选条件
		setFilter_MenuItem = new JMenuItem("设置筛选条件");
		setAppearance(setFilter_MenuItem, "filter.png");
		setFilter_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		setFilter_MenuItem.addActionListener(this);
		set_Menu.add(setFilter_MenuItem);

		// 设置属性
		setProperties_MenuItem = new JMenuItem("设置属性");
		setAppearance(setProperties_MenuItem, "properties.png");
		setProperties_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		setProperties_MenuItem.addActionListener(this);
		set_Menu.add(setProperties_MenuItem);

		// 分割线
		set_Menu.addSeparator();

		// 设置皮肤菜单项
		changeSkin_MenuItem = new JMenuItem("设置皮肤");
		setAppearance(changeSkin_MenuItem, "skin.png");
		changeSkin_MenuItem.addActionListener(this);
		set_Menu.add(changeSkin_MenuItem);

		/*** 浏览菜单 ***/
		JMenu view_Menu = new JMenu("浏览(N)");
		view_Menu.setFont(new Font("楷体", Font.PLAIN, 18));
		view_Menu.setMnemonic('N');
		menuBar.add(view_Menu);

		// 解析记录菜单项
		record_MenuItem = new JMenuItem("解析记录");
		setAppearance(record_MenuItem, "log.png");
		record_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		record_MenuItem.addActionListener(this);
		view_Menu.add(record_MenuItem);

		/*** 帮助菜单 ***/
		JMenu help_Menu = new JMenu("帮助(H)");
		help_Menu.setFont(new Font("楷体", Font.PLAIN, 18));
		set_Menu.setMnemonic('H');
		menuBar.add(help_Menu);

		// 关于菜单项
		about_MenuItem = new JMenuItem("关于");
		setAppearance(about_MenuItem, "about.png");
		about_MenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		about_MenuItem.addActionListener(this);
		help_Menu.add(about_MenuItem);

		// 设置菜单栏
		this.setJMenuBar(menuBar);
	}

	/*
	 * 按钮点击事件
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		// 判断事件发生源
		if (ae.getSource() == save_MenuItem)
		{
			// 保存
			FileUtil.save(this, output_TextArea.getText());

		} else if ((ae.getSource() == exit_MenuItem) || (ae.getSource() == exit_PopupMenuItem))
		{
			// 退出
			System.exit(0);

		} else if (ae.getSource() == clearUrl_MenuItem)
		{
			// 清除网址
			address_TextField.setText("");
			address_TextField.requestFocus();

		} else if ((ae.getSource() == clearCode_MenuItem) || (ae.getSource() == clearCode_PopupMenuItem))
		{
			// 清空代码
			output_TextArea.setText("");

		} else if ((ae.getSource() == start_Btn) || (ae.getSource() == start_MenuItem))
		{
			// 开始解析
			// getSourceCode();

		} else if (ae.getSource() == downloader_MenuItem)
		{
			// 打开下载器
			DownloaderDialog.getInstance(this, output_TextArea).setVisible(true);

		} else if (ae.getSource() == setAgent_MenuItem)
		{
			// 设置代理头
			new SetAgentDialog(output_TextArea).setVisible(true);

		} else if (ae.getSource() == setFilter_MenuItem)
		{
			// 设置筛选条件
			SetFilterDialog.getInstance(output_TextArea).setVisible(true);

		} else if (ae.getSource() == setProperties_MenuItem)
		{
			// 设置属性
			// new SetPropertiesDialog(output_TextArea).setVisible(true);

		} else if (ae.getSource() == changeSkin_MenuItem)
		{
			// 更换皮肤
			// new ChangeSkinDialog(output_TextArea).setVisible(true);

		} else if (ae.getSource() == record_MenuItem)
		{
			// 解析记录窗口
			LoggerDialog.getInstance(output_TextArea, urlLogList).setVisible(true);

		} else if (ae.getSource() == about_MenuItem)
		{
			// 关于
			new AboutDialog(output_TextArea).setVisible(true);

		}
		// 显示主界面右键菜单项
		else if (ae.getSource() == display_PopupMenuItem)
		{
			// 显示主界面
			this.setVisible(true);
			// 还原窗口
			this.setExtendedState(Frame.NORMAL);
			// 窗口最前
			this.toFront();
		}
	}

	/*
	 * 获取源代码
	 */
	private void getSourceCode()
	{
		try
		{
			if ((address_TextField.getText() == null) || (address_TextField.getText().equals("")))
			{
				JOptionPane.showMessageDialog(this, "网址不能为空！", "网址填写错误！", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// 去除空格
			String url = address_TextField.getText().replaceAll(" ", "");
			address_TextField.setText(url);

			// 缓存记录
			cachedUrlList.add(url);

			// 获取解析工具
			// 获取Document对象
			Document document = DocumentUtil.getDocument(url);
			// 判断解析模式
			if (parserModel == 0)// 默认
			{
				// 输出
				output_TextArea.setText(document.toString());

			} else if (parserModel == 11111)// 全不勾
			{
				output_TextArea.setText("");
			} else
			{
				output_TextArea.setText("");
				// 根据解析模式去解析
				// List<String> list = getDocument.parser(document, parserModel);
				// for (String result : list)
				// {
				// // 输出
				// output_TextArea.append(result);
				// output_TextArea.append("\r\n");
				// }
			}

			// 记录日志
			UrlLog urlLog = new UrlLog(getCurrentTime(), url);
			urlLogList.add(urlLog);
			// 刷新解析记录窗口
			updateLog();

		} catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(output_TextArea, "解析出错！", "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	/*
	 * 刷新日志记录窗口
	 */
	private void updateLog()
	{
		if (logDialog != null)
		{
			logDialog.updateLog(urlLogList);
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

	// 释放按键
	@Override
	public void keyReleased(KeyEvent ke)
	{
		// 获取键码
		int key = ke.getKeyCode();
		// 判断
		if (key == KeyEvent.VK_ENTER)
		{
			// 解析
			getSourceCode();

		} else if (key == KeyEvent.VK_UP)
		{
			// 上一条链接
			address_TextField.setText(cachedUrlList.getLastUrl());

		} else if (key == KeyEvent.VK_DOWN)
		{
			// 下一条链接
			address_TextField.setText(cachedUrlList.getNextUrl());
		}
	}

	/*
	 * 缩小到托盘
	 */
	private void setToTray()
	{
		// 获取系统托盘
		systemTray = SystemTray.getSystemTray();
		// 右键弹出菜单
		PopupMenu popupMenu = new PopupMenu();

		// 显示主界面菜单项
		display_PopupMenuItem = new MenuItem("View");
		display_PopupMenuItem.addActionListener(this);

		// 关闭菜单项
		exit_PopupMenuItem = new MenuItem("Exit");
		exit_PopupMenuItem.addActionListener(this);

		// 添加菜单项
		popupMenu.add(display_PopupMenuItem);
		// 分割线
		popupMenu.addSeparator();
		popupMenu.add(exit_PopupMenuItem);

		// 判断当前平台是否支持系统托盘
		if (SystemTray.isSupported())
		{
			try
			{
				// 托盘图标
				// 光标停留显示文字
				trayIcon = new TrayIcon(new ImageIcon(MainFrame.class.getResource("/res/about.png")).getImage(),
						"获取网页源代码", popupMenu);
				// 托盘图标添加鼠标监听
				trayIcon.addMouseListener(this);

				// 托盘添加图标
				systemTray.add(trayIcon);
				// 任务栏图标隐藏

				// 窗体隐藏
				this.setVisible(false);

			} catch (AWTException ex)
			{
				JOptionPane.showMessageDialog(null, "缩小到系统托盘出错！", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		// 挂起状态
		trayStatus = true;
	}

	// 窗口关闭
	@Override
	public void windowClosing(WindowEvent e)
	{
		// 判断是否已经挂起
		if (trayStatus)
		{
			// 窗体隐藏
			this.setVisible(false);
		} else
		{
			// 缩小到托盘
			setToTray();
		}
	}

	// 鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// 判断事件源
		// 来源为托盘图标且是鼠标左键
		if ((e.getSource() == trayIcon) && (e.getButton() == MouseEvent.BUTTON1))
		{
			// 可视
			this.setVisible(true);
			// 还原窗口
			this.setExtendedState(Frame.NORMAL);
			// 窗口最前
			this.toFront();
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getSource() != trayIcon)
		{
			// 判断是否触发弹出菜单事件
			if (e.isPopupTrigger())
			{
				// 显示弹出菜单
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (e.getSource() != trayIcon)
		{
			// 判断是否触发弹出菜单事件
			if (e.isPopupTrigger())
			{
				// 显示弹出菜单
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}
