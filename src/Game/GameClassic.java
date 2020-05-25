package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Date;
import java.util.Random;

public class GameClassic extends GameObject {

	/*
	 * 此类为经典模式
	 */

	long timenow = 0l;

	// 构造函数
	public GameClassic() {
		super();
		pattern = 1;// 模式选择
		initializationame();// 初始化数据
	}

	// 初始化数据 全部初始化
	@Override
	public void initializationame() {

		Random r = new Random();
		for (int i = 0; i < block.length; i++) {
			block[i][r.nextInt(4)] = 1;
		}

	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);

		if (isAlive) {
			drawWhite(g);
			// 数组值的改变
			// 如果最后一排 全是0 则交换数据
			boolean Kong = false;
			for (int i = 0; i < block[5].length; i++) {
				if (block[5][i] == 1) {
					Kong = false;
					break;
				} else {
					Kong = true;

				}
			}
			if (Kong) {
				dataProcessing();
			}

			// 绘制时间戳
			// System.out.println(time);
			if (time == 0l) {
				g.setColor(Color.RED);
				g.setFont(new Font("TimesRoman", Font.BOLD, 50));
				g.drawString(Long.toString(time), 285, 50);
			} else {
				Date b = new Date();
				timenow = b.getTime();
				g.setColor(Color.RED);
				g.setFont(new Font("TimesRoman", Font.BOLD, 50));
				g.drawString((timenow - time) / 1000 + "s" + (timenow - time) % 1000, 250, 50);
				g.setColor(Color.BLACK);

			}
			if (fraction == 10) {
				isAlive = false;
			}
			repaint();
			if (!isAlive) {
				gameOver(timenow - time);
			}

		}

	}

}
