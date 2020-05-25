package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public abstract class GameObject extends JPanel {

	/*
	 * 这个类是所有游戏模式的父类 包括所有游戏模式的共有成员 共有方法体
	 */

	// 两个游戏模式拥有的共同成员
	JFrame jfrm = new JFrame("别踩白块");
	Image im = (new ImageIcon(".//res//icon.jpg")).getImage();
	boolean isAlive = true;// 存活判断
	int height = 600;
	int fraction = 0;// 计数器
	int[][] block = new int[6][4];// 块的容器

	public int pattern = 0;// 模式选择器 0为街机模式 1为经典模式

	// 关于时间计时的变量

	Date b = new Date();
	boolean dateFirst = true;
	long time = 0l;

	// 初始化 游戏数据 由于模式的不同 初始化的数据不同 故此方法为抽象方法
	public abstract void initializationame();

	// 构造函数 初始化窗体
	public GameObject() {

		// 窗口初始化 设置大小 居中 关闭设置 不可编辑 布局
		jfrm.setSize(600, 800);
		jfrm.setLocationRelativeTo(null);
		jfrm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jfrm.setResizable(false);
		jfrm.setLayout(null);
		jfrm.setIconImage(im);
		jfrm.setVisible(true);

		// 添加鼠标监听事件
		// 监听事件为鼠标弹起 经过测试 鼠标点击事件会造成灵敏问题
		this.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				judge(getIndexX(e.getX()), getIndexY(e.getY()));

			}
		});

		// 犹豫此类继承了JPanel 对JPaenl进行设置
		this.setSize(650, 850);
		this.setLocation(0, 0);
		this.setLayout(null);

		jfrm.add(this);

	}

	/*
	 * 此方法将获得到的索引值进行判断 为成员变量赋值
	 */
	public void judge(int indexX, int indexY) {
		if (block[indexY][indexX] == 0) {
			isAlive = false;
			if (pattern == 0) {
				gameOver(fraction);
			} else {
				if (fraction == 0) {
					gameOver(0);
				} else {
					gameOver(b.getTime() - time);
				}

			}
			//
		} else {
			// 在此处追加一个判断 用于隔行判断
			boolean geHang = false;
			for (int i = indexY + 1; i < block.length; i++) {
				for (int j = 0; j < block[indexY].length; j++) {
					if (block[i][j] == 1) {
						geHang = true;
						break;
					}
				}
			}
			if (!geHang) {
				block[indexY][indexX] = 0;
				fraction++;
			}

		}
		// 首次点击 初始化一个时间
		if (dateFirst) {
			Date a = new Date();
			time = a.getTime();
			dateFirst = false;
		}
	}

	/*
	 * 此方法通过鼠标的横坐标获取到块容器的横坐标
	 */
	public int getIndexX(int MouseX) {
		return MouseX / 150;
	}

	/*
	 * 此方法通过鼠标的纵坐标获取到块容器的纵坐标
	 */
	public int getIndexY(int MouseY) {
		if (height < MouseY) {
			return 5;
		} else {
			return 4 - ((height - MouseY) / 170);
		}
	}

	/*
	 * 游戏结束方法 当IsAlive变量为False值时 会调用此方法
	 */
	public void gameOver(long m) {

		int n = JOptionPane.showOptionDialog(null, "你的分数是" + m, "游戏结束", JOptionPane.DEFAULT_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, new Object[] { "退出游戏", "返回主菜单", "再来一局" }, "再来一局");
		if (n == 2) {
			// 再来一局的处理 根据模式的不同 要创建不同的对象
			isAlive = true;
			jfrm.dispose();
			if (pattern == 0) {
				new GameArcade();
			} else {
				new GameClassic();
			}

		} else if (n == 1) {
			isAlive = true;
			jfrm.dispose();
			new View.Menu();
		} else {
			isAlive = true;
			System.exit(0);
		}

	}

	/*
	 * 数据的处理
	 */
	public void dataProcessing() {
		// 以后的数据进行 下移
		for (int k = 5; k > 0; k--) {
			for (int i = 0; i < block[1].length; i++) {
				block[k][i] = block[k - 1][i];
			}
		}

		// 第一行数据 进行随机初始化
		for (int i = 0; i < block[0].length; i++) {
			block[0][i] = 0;
		}
		Random r = new Random();
		block[0][r.nextInt(4)] = 1;
	}

	/*
	 * 绘制横线 竖线 块元素
	 */
	public void drawWhite(Graphics g) {

		g.setColor(Color.BLACK);

		// 绘制竖线
		for (int i = 150; i <= 600; i += 150) {
			g.drawLine(i, 0, i, 850);
		}

		// 绘制横线
		for (int i = 0; i < 5; i++) {
			g.drawLine(0, height - 170 * i, 600, height - 170 * i);
		}

		// 绘制块
		int blockX = 0;
		int blockY = 0;
		for (int i = 0; i < block.length; i++) {
			for (int j = 0; j < block[i].length; j++) {
				if (block[i][j] == 1) {
					switch (j) {
					case 0:
						blockX = 0;
						blockY = height - 170 * (block.length - i - 1);
						break;
					case 1:
						blockX = 150;
						blockY = height - 170 * (block.length - i - 1);
						break;
					case 2:
						blockX = 300;
						blockY = height - 170 * (block.length - i - 1);
						break;
					case 3:
						blockX = 450;
						blockY = height - 170 * (block.length - i - 1);
						break;

					}
					g.fillRect(blockX, blockY, 150, 170);
				}
			}
		}
	}
}
