import javax.swing.JFrame;

class Point {
	public double x;
	public double y;
}

public class Ball extends JFrame {
	private double omega = 1.0; // 角速度
	private double r = 3.5; // 中心からの距離
	private double alpha = 0.0; // 初期位相

	public Ball() {
		for (double tmsec = 0.0; tmsec <= this.getT(); tmsec+=(double)1/1000) {
			
			Point xyBall = new Point();
			xyBall = this.equation(tmsec);

			System.out.println(tmsec + "," + xyBall.x + "," + xyBall.y);

		}
	}

	private Point equation(double t /* 現在時刻 */ ) // ボールの現在の場所を計算する
	{
		Point point = new Point(); // ボールのxy座標
		point.x = this.r * Math.cos(this.omega * t + this.alpha);
		point.y = this.r * Math.sin(this.omega * t + this.alpha);

		return point;
	}

	private double getT() { // 周期[s]
		return 2 * Math.PI / omega;
	}

}