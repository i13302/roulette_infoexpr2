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

public class Ball extends JDialog {
	private int xSize = 800;
	private int ySize = 800;

	BallMain ballMain;

	public Ball() {
		this.initJFrame();
		this.ckDoingBallMain();
	}

	/* JFrameの初期設定 */
	private void initJFrame() {
		System.out.println("initJFrame");

		ballMain = new BallMain(this.xSize, this.ySize);

		this.setTitle("Ball");
		this.add(ballMain);
		this.setSize(800, 800);
		this.setVisible(true);
		this.setModal(true);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

	/* BallMain内のスレッドが動作中か確認 */
	private void ckDoingBallMain() {
		for (;;) { // 500msecに1回確認する．
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}

			boolean t = ballMain.getThreadStatus();
			if (!t) { // 動作が終わっていたら，
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				this.setVisible(false); // 自分を閉じる
				break;
			}
		}
	}
}

class BallMain extends JPanel implements Runnable {
	private double tmsec = 0.0; // 時間
	private double omega = 1.0; // 角度
	private double r = 350; // 中心からの距離
	private double alpha = 0.0; // 初期位相
	private iPoint c = new iPoint(); // Ballの座標
	private int numNumber = 36 + 1 + 1;

	private volatile Thread thread = null;

	public BallMain(int xSize, int ySize) {
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
	private dPoint toXYfromRT(double r,double theta) {
		dPoint ret=new dPoint();
		ret.x=r*Math.cos(theta+this.alpha);
		ret.y=r*Math.sin(theta+this.alpha);
		return ret;
	}
	
	/* r分を足して，整形する */
	private dPoint equation(double theta /* 角速度 */ ) {
		dPoint point = new dPoint(); // ボールのxy座標
		point=toXYfromRT(this.r, theta);
		point.x = point.x + (int) (this.r);
		point.y = point.y + (int) (this.r);

		return point;
	}

	/* 周期[s]を得る */
	private double getT() {
		return 2 * Math.PI / omega;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(100, 70, 140));

		// System.out.println(tmsec + "," + cx + "," + cy);
		g.drawString("◎", c.x, c.y);
		showBanmen(g);

	}

	/* ボールのアニメーション */
	@Override
	public void run() {
		for (; tmsec <= this.getT() / 2; tmsec += (double) 0.001) {
			dPoint xyBall = new dPoint();
			xyBall = this.equation(omega * tmsec);

			c.x = (int) (xyBall.x);
			c.y = (int) (xyBall.y);

			// System.out.println(tmsec + "," + xyBall.x + "," + xyBall.y + "," + cx + "," +
			// cy);

			repaint();

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		stopThread();

		System.out.println("BallMain END");
	}

	private void showBanmen(Graphics g) {
		double angle = 2 * Math.PI / (double) numNumber;
		g.setColor(new Color(0, 0, 0));

		dPoint tmPoint = new dPoint();
		tmPoint = toXYfromRT(this.r, 0.0);

		iPoint center = new iPoint();
	
		center.x =(int)r;
		center.y =(int)r;

		for (int i = 0; i < numNumber; i++) {
			tmPoint = equation(angle * (i + 1));
			
			iPoint start = new iPoint();
			start.x=(int)tmPoint.x;
			start.y=(int)tmPoint.y;
			
			g.drawLine(start.x, start.y, center.x, center.y);
//			g.drawLine(center.x, center.y, center.x, center.y);
			System.out.println(start.x + start.y + center.x + center.y);
		}
	}
}