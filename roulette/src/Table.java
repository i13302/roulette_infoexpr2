import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;

class Util {
	/**
	 * 実行中のメソッド名を取得します。
	 * 
	 * @return メソッド名
	 */
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
}

public class Table extends JFrame implements ActionListener {
	JFrame jframe;
	private int numNumber = 36 + 2; // 賭ける場所の個数 // TODO Numberクラス
	private int numMoney = 4; // 賭けるお金のパターン

	private JButton[] JBtnNumber = new JButton[numNumber]; // 場所のボタン
	private String labelNumber = "Num"; // 場所のラベル
	private JButton[] JBtnMoney = new JButton[numMoney]; // お金のボタン
	private String labelMoney = "Mon"; // お金のラベル
	private JButton JBtnMoneyClear = new JButton("Clear"); // 選択したお金を0に戻す．

	private int setBtnYLine; // y軸のどこまでボタンが設置されているのか

	private JButton JBtnExit = new JButton("Exit"); // 強制終了

	private int statusNumber; // 選択した場所
	private JLabel JLblStatusNumber = new JLabel(); // 選択した場所を表示
	private int statusMoney; // 選択したお金
	private JLabel JLblStatusMoney = new JLabel(); // 選択したお金を表示

	private boolean statusLock = false; // 選択をロック true...Locked,False...Open

	/* Constructer */
	public Table() {
		System.out.println("Table Class");
		_init_();

	}

	/* 初期処理 */
	private void _init_() {
		System.out.println("_init_");
		// setLayout(new FlowLayout()); // レイアウトの設定
		this.setLayout(null);

		_init_JFrame();
		_init_status();

		_setJBtnNumbers();
		_setJBtnMoneys();
		_setJBtnExit();
		_setJLbl();

	}

	/* Windowの設定 */
	private void _init_JFrame() {
		this.setTitle("Table");
		this.setSize(600, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/* ステータスを初期化 */
	private void _init_status() {
		statusNumber = -1;
		statusMoney = 0;
	}

	/* 賭ける場所のボタン */
	private void _setJBtnNumbers() {
		System.out.println(Util.getMethodName());

		int width = 90, height = 25;
		for (int i = 0, x = 10, y = 10; i < numNumber; i++, x += (width + 10)) {
			JBtnNumber[i] = new JButton(labelNumber + ":" + String.valueOf(i + 1)); // TODO 画像で置き換える
			JBtnNumber[i].addActionListener(this);
			JBtnNumber[i].setBounds(x, y, width, height);
			add(JBtnNumber[i]);
			if ((i + 1) % 5 == 0) {
				x = 10 - (width + 10); // x座標をリセット

				y += (height + 5); // y座標を更新
			}
			setBtnYLine = y + height; // 現在，どこまでボタンが置いているのか保存
		}

	}

	/* 賭けるお金のボタン */
	private void _setJBtnMoneys() {
		System.out.println(Util.getMethodName());

		JBtnMoney[0] = new JButton(labelMoney + ":" + String.valueOf(1));
		JBtnMoney[1] = new JButton(labelMoney + ":" + String.valueOf(5));
		JBtnMoney[2] = new JButton(labelMoney + ":" + String.valueOf(15));
		JBtnMoney[3] = new JButton(labelMoney + ":" + String.valueOf(50));

		int width = 90, height = 25;
		int x = 10;
		for (int i = 0; i < numMoney; i++, x += (width + 10)) {
			JBtnMoney[i].addActionListener(this);
			JBtnMoney[i].setBounds(x, setBtnYLine + 10, width, height);
			add(JBtnMoney[i]);
		}

		JBtnMoneyClear.addActionListener(this);
		JBtnMoneyClear.setBounds(x, setBtnYLine + 10, width, height);
		add(JBtnMoneyClear);

		setBtnYLine += (5 + height);
	}

	/* 強制終了ボタン */
	private void _setJBtnExit() {
		System.out.println(Util.getMethodName());

		int width = 100, height = 30;
		JBtnExit.addActionListener(this);
		JBtnExit.setBounds(200, setBtnYLine + 10, width, height);
		add(JBtnExit);

		setBtnYLine += (5 + height);
	}

	/* ラベルの設定 */
	private void _setJLbl() {
		JLblStatusNumber.setText("no Select");
		JLblStatusMoney.setText("no Select");

		int width = 150, height = 15;
		JLblStatusNumber.setBounds(20, setBtnYLine + 10, width, height);
		JLblStatusMoney.setBounds(20 + 10 + width, setBtnYLine + 10, width, height);
		setBtnYLine += (5 + height);

		add(JLblStatusNumber);
		add(JLblStatusMoney);
	}

	/* イベント入力時 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(Util.getMethodName());
		String pushBtn = e.getActionCommand(); // 押したボタンの表示名(String)
		System.out.printf("(statusLock,pushBtn,statusNumber,statusMoney) = (%b,%s,%d,%d) \n", statusLock, pushBtn,
				statusNumber, statusMoney);

		if (e.getSource() == JBtnExit) { // Exitボタンでプログラムごと終了
			System.exit(-1);
		}

		if (statusLock) { // ロックされていたら，入力しない
			return;
		}

		if (e.getSource() == JBtnMoneyClear) { // 賭金を0にする．
			statusMoney = 0;
			JLblStatusMoney.setText("Select Money is " + Integer.toString(statusMoney));
			return;
		}

		/* START 雑なつくり getSourceを使うべき */
		String label = pushBtn.substring(0, 3); // ラベル
		int value = Integer.parseInt(pushBtn.substring(4)); // 値

		if (label.equals("Num")) { // Num:%dの場合
			statusNumber = value;
			JLblStatusNumber.setText("Select Num is " + Integer.toString(statusNumber));
		} else if (label.equals("Mon")) { // Mon:%dの場合
			statusMoney += value;
			JLblStatusMoney.setText("Select Money is " + Integer.toString(statusMoney));
		}
		/* END */
	}

	/* 賭けにロックをする */
	public void setLock(boolean _t) {
		statusLock = _t;
	}

	/* 賭けのロック状態を取得する */
	public boolean getLock() {
		return statusLock;
	}

	/* 現在の賭けている番号を返す */
	public int getNumber() {
		return statusNumber;
	}

	/* 現在の賭けている金額を返す */
	public int getMoney() {
		return statusMoney;
	}
}
