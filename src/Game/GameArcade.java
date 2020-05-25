package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class GameArcade extends GameObject {

	/*
	 * 此类为街机模式
	 */

	int speed = 5;

	// 构造函数
	public GameArcade() {
		super();
		pattern = 0;// 模式选择
		initializationame();// 初始化数据

	}

	// 初始化数据 最后两行不初始化
	@Override
	public void initializationame() {

		Random r = new Random();
		for (int i = 0; i < block.length - 2; i++) {
			block[i][r.nextInt(4)] = 1;
		}

	}

	public void paint(Graphics g) {

		super.paint(g);
		g.setColor(Color.BLACK);
		if (isAlive) {
			drawWhite(g);

			// 调整速度
			if (fraction > 100) {
				speed = 20;
			} else if (fraction > 70) {
				speed = 15;
			} else if (fraction > 50) {
				speed = 10;
			} else if (fraction > 30) {
				speed = 8;
			}

			height += speed;

			if (height >= 850) {
				for (int i = 0; i < block[5].length; i++) {
					if (block[5][i] == 1) {
						isAlive = false;
					}
				}
				if (isAlive) {
					height = 680;
					// 当heght=850时 就是改变数组的数据的时候
					dataProcessing();
				} else {
					isAlive = false;

				}

			}

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 绘制成绩
			g.setColor(Color.RED);
			g.setFont(new Font("TimesRoman", Font.BOLD, 50));
			g.drawString(Integer.toString(fraction), 285, 50);
			g.setColor(Color.BLACK);
			repaint();

			if (!isAlive) {
				gameOver(fraction);
				isAlive = true;
			}

		}
	}

}
