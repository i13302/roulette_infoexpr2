import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class Point {
	public double x;
	public double y;
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
		ballMain = new BallMain(this.xSize, this.ySize);
//		JDialog dialog = new JDialog(ballMain,"theTitle", Dialog.ModalityType.APPLICATION_MODAL);

		this.setTitle("Ball");
		this.add(ballMain);
//		this.pack();
//		this.setLayout(new FlowLayout());
		this.setSize(800, 800);
		this.setVisible(true);
		System.out.println("initJFrame");
//		this.setResizable(false);
		this.setModal(true);
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
//        WindowListener winListener = new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//            	System.out.println("BallEND");
//            }
//        };
//        this.addWindowListener(winListener);
//        this.setVisible(false);
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
// 
//		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

//  BallMain ballMain = new BallMain();
//  ballMain.setVisible(true);
	}
}

class BallMain extends JPanel implements Runnable {
	private double tmsec = 0.0; // 時間
	private double omega = 1.0; // 角速度
	private double r = 350; // 中心からの距離
	private double alpha = 0.0; // 初期位相
	private int cx = 250, cy = 250;

	private volatile Thread thread = null;

	public BallMain(int xSize, int ySize) {
		this.initJPanel(xSize, ySize);
		this.startThread();

	}

	/* Windowの設定 */
	private void initJPanel(int xSize, int ySize) {
		// this.setTitle("Ball");
		this.setPreferredSize(new Dimension(xSize, ySize));
		// this.setLayout(new FlowLayout());
		// this.setSize(400, 400);
		// this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		for (; tmsec <= this.getT() / 2; tmsec += (double) 0.001) {
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
		stopThread();

//		this.setVisible(false);

		System.out.println("BallMain END");

	}

}