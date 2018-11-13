import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

class Point {
	public double x;
	public double y;
}

// public class Ball extends JFrame {
// public Ball() {
// this.initJFrame();
// }

// private void initJFrame() {

// this.setTitle("Ball");
// this.add(new BallMain());
// this.pack();
// // this.setLayout(new FlowLayout());
// // this.setSize(400, 400);
// this.setResizable(false);
// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// // BallMain ballMain = new BallMain();
// // ballMain.setVisible(true);
// }
// }

public class BallMain extends JPanel implements Runnable {
	private double tmsec = 0.0;
	private double omega = 1.0; // 角速度
	private double r = 350; // 中心からの距離
	private double alpha = 0.0; // 初期位相
	private int cx = 250, cy = 250;

	private volatile Thread thread = null;

	public BallMain() {
		this.initJPanel();
		this.startThread();

	}

	/* Windowの設定 */
	private void initJPanel() {
		// this.setTitle("Ball");
		this.setPreferredSize(new Dimension(800, 800));
		// this.setLayout(new FlowLayout());
		// this.setSize(400, 400);
		// this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void startThread() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	private void stopThread() {
		thread = null;
	}

	/* ボールの現在の場所を計算する */
	private Point equation(double t /* 現在時刻 */ ) {
		Point point = new Point(); // ボールのxy座標
		point.x = this.r * Math.cos(this.omega * t + this.alpha);
		point.y = this.r * Math.sin(this.omega * t + this.alpha);

		return point;
	}

	/* 周期[s]を得る */
	private double getT() {
		return 2 * Math.PI / omega;
	}

	@Override
	public void paintComponent(Graphics g) {
		// System.out.println("paintComponent");
		super.paintComponent(g);
		g.setColor(new Color(100, 70, 140));

		// System.out.println(tmsec + "," + cx + "," + cy);
		// g.drawLine(cx, cy, cx+10, cy+10);
		g.drawString("◎", cx, cy);

	}

	/* ボールのアニメーション */
	// @Override
	public void run() {
		// System.out.println("run");
		for (; tmsec<=this.getT()*1.5 ; tmsec += (double) 0.001) {
			Point xyBall = new Point();
			xyBall = this.equation(tmsec);

			cx = (int) (xyBall.x) + (int) (r);
			cy = (int) (xyBall.y) + (int) (r);

			// System.out.println(tmsec + "," + xyBall.x + "," + xyBall.y + "," + cx + "," +
			// cy);

			repaint();

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

}