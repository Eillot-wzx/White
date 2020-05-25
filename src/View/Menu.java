package View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Game.GameArcade;
import Game.GameClassic;

public class Menu extends JFrame {

	/*
	 * 菜单类 生成一个菜单
	 */

	// 创建成员变量 窗口 画板 标签 按钮 图标 背景图片
	JFrame jfrm = new JFrame("别踩白块");
	JPanel jpan = new JPanel();
	JLabel jlab = new JLabel();
	JButton arcade = new JButton("街机模式");
	JButton classic = new JButton("经典模式");
	JButton help = new JButton("帮助");
	ImageIcon imgWelcome = new ImageIcon(".//res//Welcome.jpg");
	Image im = (new ImageIcon(".//res//icon.jpg")).getImage();

	// 构造函数 初始化窗口
	public Menu() {

		// 设置JFrame属性 包括大小 居中 关闭设置 不可编辑 布局
		jfrm.setSize(600, 850);
		jfrm.setLocationRelativeTo(null);
		jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jfrm.setResizable(false);
		jfrm.setLayout(null);
		jfrm.setIconImage(im);

		// 设置JLabel属性 包括大小 背景图片 位置定位
		jlab.setSize(600, 850);
		jlab.setIcon(imgWelcome);
		jlab.setLocation(0, 0);

		// 设置JPanel属性 包括大小 位置 布局
		jpan.setSize(600, 850);
		jpan.setLocation(0, 0);
		jpan.setLayout(null);

		// 设置arcade属性 包括位置 字体
		arcade.setBounds(150, 400, 300, 80);
		arcade.setFont(new Font("Dialog", 1, 50));

		// 设置help属性 包括位置 字体
		help.setBounds(150, 500, 300, 80);
		help.setFont(new Font("Dialog", 1, 50));

		// 设置classic属性 包括位置 字体
		classic.setBounds(150, 600, 300, 80);
		classic.setFont(new Font("Dialog", 1, 50));

		// 嵌套关系 help jlab classic arcade >> jpan >> jfrm
		jpan.add(help);
		jpan.add(classic);
		jpan.add(arcade);
		jpan.add(jlab);
		jfrm.add(jpan);

		jfrm.setVisible(true);// 显示窗体

		// 按钮的监听事件 载入新的窗口 并关闭窗口释放资源

		arcade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GameArcade();
				jfrm.dispose();// 关闭窗口 释放资源

			}
		});

		classic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GameClassic();
				jfrm.dispose();// 关闭窗口 释放资源

			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "用鼠标点击黑块\r\n街机模式比较块的数量\r\n经典模式10块比较时间\r\n玩的开心", "游戏帮助",
						JOptionPane.INFORMATION_MESSAGE);// 创建信息消息框

			}
		});

	}
}
