import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;

class dPoint {
	public double x;
	public double y;
}

class iPoint {
	public int x;
	public int y;
}

/* Castする */
class Cast {
	public static iPoint ToIntFromDbl(dPoint d) {
		iPoint ret = new iPoint();
		ret.x = (int) d.x;
		ret.y = (int) d.y;
		return ret;
	}
}

public class Ball extends JDialog {
	private int stopNum;
	private int xSize = 800;
	private int ySize = 800;

	BallMain ballMain;

	public Ball(int sn) {
		this.stopNum = sn;
		this.initJFrame();
		this.ckDoingBallMain();
	}

	/* JFrameの初期設定 */
	private void initJFrame() {
		System.out.println("initJFrame");

		ballMain = new BallMain(this.stopNum, this.xSize, this.ySize);

		this.setTitle("Ball");
		this.add(ballMain);
		this.setSize(800, 800);
		this.setVisible(true);
		this.setModal(true);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

	/* BallMain内のスレッドが動作中か確認 */
	private void ckDoingBallMain() {
		for (; ballMain.getThreadStatus();) { // 500msecに1回確認する．
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		try { // 値の確認ように10秒待つ
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
		}
		this.setVisible(false); // 自分を閉じる
	}
}

class BallMain extends JPanel implements Runnable {
	// private double tmsec = 0.0; // 時間
	private final double omega = 1.0; // 角速度
	private final double circleR = 350; // 中心からの距離
	private final double alpha = 0.0; // 初期位相
	private final int numNumber = 36 + 1 + 1; // 文字盤の個数 // TODO NumberClass
	private final double angle = 2 * Math.PI / (double) numNumber; // 1つ辺りの角度
	private int stopNum; // どこで止まるか
	private iPoint c = new iPoint(); // Ballの座標
	private int nowBallValue; // 現在，ボールがどの数字の上にいるか
	private iPoint Size = new iPoint(); // Windowサイズ

	private volatile Thread thread = null;

	public BallMain(int sn, int xSize, int ySize) {
		this.stopNum = sn;
		this.Size.x = xSize;
		this.Size.y = ySize;
		this.initJPanel(xSize, ySize);
		this.startThread();
	}

	/* Windowの設定 */
	private void initJPanel(int xSize, int ySize) {
		this.setPreferredSize(new Dimension(xSize, ySize));
	}

	/* スレッドを開始 */
	private void startThread() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	/* スレッドを終了 */
	private void stopThread() {
		thread = null;
	}

	/* スレッドの状態を得る */
	public boolean getThreadStatus() {
		if (thread == null) { // 止まっている
			return false;
		}
		return true;
	}

	/* r-theta座標をxy座標に変換 */
	private dPoint toXYfromRT(double r, double theta) {
		dPoint ret = new dPoint();
		ret.x = r * Math.cos(theta + this.alpha);
		ret.y = r * Math.sin(theta + this.alpha);
		return ret;
	}

	/* r分を足して，整形する */
	private dPoint equation(double r, double theta /* 角度 */ ) {
		dPoint point = new dPoint(); // ボールのxy座標
		point = toXYfromRT(r, theta);
		point.x = point.x + (int) (circleR) + 50;
		point.y = point.y + (int) (circleR) + 25;

		return point;
	}

	/* 周期[s]を得る */
	private double getT() {
		return 2 * Math.PI / omega;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		showBanmen(g);
		g.drawString(Integer.toString(this.nowBallValue), this.Size.x / 2, this.Size.y - 50);

		g.setColor(new Color(255, 0, 0));
		g.drawString("●", c.x, c.y); // ボール
	}

	/* ボールのアニメーション */
	@Override
	public void run() {
		double deltai = 0.004;
		/* 時間で制御 */
		// for (double i = 0.0; i <= this.getT() / 4; i += 0.001) {
		/* 角度で調整 */
		for (double i = Math.random(); i < 4 * Math.PI + this.angle * this.stopNum + this.angle / 2.0; i += deltai) {
			this.nowBallValue = (int) ((this.omega * i) / this.angle % numNumber);
			dPoint xyBall = new dPoint();
			xyBall = this.equation(circleR - 30, this.omega * i);

			c = Cast.ToIntFromDbl(xyBall); // ボールの座標

			repaint();

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}

			/* ボールの速度を調整する */
			if (i >= 4 * Math.PI + this.angle * this.stopNum - Math.random()) {
				deltai = 0.0001;
				continue;
			}

			if (i >= 3 * Math.PI + this.angle * this.stopNum / 2.0 - Math.random()) {
				deltai = 0.001;
				continue;
			}

			if (i >= 3 * Math.PI) {
				deltai = 0.002;
				continue;
			}

			if (i >= 2 * Math.PI) {
				deltai = 0.003;
			}

		}
		stopThread();

		System.out.println("BallMain END");
	}

	private void showBanmen(Graphics g) {
		g.setColor(new Color(0, 0, 0));

		iPoint center = new iPoint(); // 中心座標
		center = Cast.ToIntFromDbl(equation(0.0, 0.0));

		for (int i = 0; i < numNumber; i++) {
			iPoint start = new iPoint(); // 線の始点座標
			start = Cast.ToIntFromDbl(equation(circleR, angle * (i)));
			iPoint next = new iPoint(); // 次の始点
			next = Cast.ToIntFromDbl(equation(circleR, angle * (i + 1)));
			// g.drawLine(start.x, start.y, center.x, center.y); // 線を引く

			int poly_x[] = { start.x, next.x, center.x };
			int poly_y[] = { start.y, next.y, center.y };
			g.drawPolygon(poly_x, poly_y, 3);

			iPoint drawStrNum = new iPoint(); // 文字盤の座標
			drawStrNum = Cast.ToIntFromDbl(equation(circleR - 20, angle * (i + 1) - angle / 2.0));
			g.drawString(Integer.toString(i), drawStrNum.x, drawStrNum.y); // 文字盤を書く

		}
	}
}