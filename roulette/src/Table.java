
/*
 *
 * BetするためのTableを作成する．
 * 
 */

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.*;

/* Debug用 いずれ消したい */
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
	private JButton[] JBtnNumber = new JButton[NumbersTable.numbers.size()]; // 場所のボタン
	private String[] btnNumSPPattern = { "SMALL", "MIDDLE", "LARGE", "LOW", "HIGH", "PARILLINEN", "PARITON", "RED",
			"BALCK" }; // 特殊な場所
	private JButton[] JBtnNumSP = new JButton[btnNumSPPattern.length]; // SMALL，MIDDLE...のボタン

	private int[] btnMoneyPattern = { 1, 5, 15, 50 }; // 賭けるパターン
	private JButton[] JBtnMoney = new JButton[btnMoneyPattern.length]; // お金のボタン

	private JLabel JLblStatusNumber = new JLabel(); // 選択した場所を表示
	private JLabel JLblStatusMoney = new JLabel(); // 選択したお金を表示

	private int statusMax = 5; // 賭けれる個数
	private AnyNumMoney[] statusNumMoney; // 選択した場所とお金．多点賭け対応．

	private boolean statusLock = false; // 選択をロック true...Locked,False...Open

	private int setBtnNumberYLine = 0; // y軸のどこまでボタンが設置されているのか場所
	private int setBtnMoneyYLine = 0; // 同賭金

	private JButton JBtnMoneyClear = new JButton("Clear"); // 選択したお金を0に戻す
	private JButton JBtnExit = new JButton("Exit"); // 強制終了

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

		initJFrame();
		initStatus();

		setJBtnNumbers();
		setJBtnMoneys();
		setJBtnExit();
		setJLbl();

	}

	/* Windowの設定 */
	private void initJFrame() {
		this.setTitle("Table");
		this.setSize(500, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/* ステータスを初期化 */
	private void initStatus() {
		statusNumMoney = new AnyNumMoney[statusMax];
		for (int i = 0; i < statusNumMoney.length; i++) {
			statusNumMoney[i] = new AnyNumMoney();
		}
		statusNumMoney[0].num = 0;
		System.out.println(statusNumMoney[0].num + " , " + AnyNumMoney.num_len);
	}

	/* 賭ける場所のボタン */
	private void setJBtnNumbers() {
		System.out.println(Util.getMethodName());
		System.out.println("nnsize" + NumbersTable.numbers.size());
		int btn_x = 200;
		int width = 90, height = 25;
		for (int i = 0, x = btn_x, y = 10, most_y = 0; i < NumbersTable.numbers.size(); i++, x += (width + 10)) {
			// TODO 画像で置き換える
			JBtnNumber[i] = new JButton(NumbersTable.numbers.get((i) % NumbersTable.numbers.size()).getStrNum());
			JBtnNumber[i].addActionListener(this);
			JBtnNumber[i].setBounds(x, y, width, height);
			add(JBtnNumber[i]);
			if ((i + 1) == 37) { // 00だけ特殊に上
				x = btn_x;
				y = 10;
			} else if (i % 3 == 0) {
				x = btn_x - (width + 10); // x座標をリセット
				y += (height + 5); // y座標を更新
				most_y = y; // 最下層のyを保存
			}
			setBtnNumberYLine = most_y + height; // 現在，どこまでボタンが置いているのか保存ボタン

		}
		setBtnNumberYLine += 20;

		for (int i = 0, x = btn_x, y = setBtnNumberYLine; i < btnNumSPPattern.length; i++, x += (width + 10)) {
			JBtnNumSP[i] = new JButton(btnNumSPPattern[i]);
			JBtnNumSP[i].addActionListener(this);
			if (i == 3) {
				width += 50;
			}
			if (i < 3) {
				JBtnNumSP[i].setBounds(x, y, width, height);
			} else {
				JBtnNumSP[i].setBounds(x, y, width, height);
			}
			add(JBtnNumSP[i]);
			if ((i < 3 && (i + 1) % 3 == 0) || (i >= 3 && (i) % 2 == 0)) {
				x = btn_x - (width + 10); // x座標をリセット
				y += (height + 5); // y座標を更新
			}
			setBtnNumberYLine = y + height; // 現在，どこまでボタンが置いているのか保存ボタンS
		}

	}

	/* 賭けるお金のボタン */
	private void setJBtnMoneys() {
		System.out.println(Util.getMethodName());

		for (int i = 0; i < btnMoneyPattern.length; i++) {
			JBtnMoney[i] = new JButton(String.valueOf(btnMoneyPattern[i]));
		}

		int width = 90, height = 25;
		int x = 20, y = 10;
		for (int i = 0; i < btnMoneyPattern.length; i++, y += (height + 10)) {
			JBtnMoney[i].addActionListener(this);
			JBtnMoney[i].setBounds(x, y + 10, width, height);

			add(JBtnMoney[i]);
		}

		JBtnMoneyClear.addActionListener(this);
		JBtnMoneyClear.setBounds(x, y + 10, width, height);

		add(JBtnMoneyClear);

		setBtnMoneyYLine = (y + height);

	}

	/* 強制終了ボタン */
	private void setJBtnExit() {
		System.out.println(Util.getMethodName());

		int width = 100, height = 30;
		JBtnExit.addActionListener(this);
		JBtnExit.setBounds(20, setBtnMoneyYLine + 10, width, height);

		add(JBtnExit);

		setBtnMoneyYLine += (5 + height);

	}

	/* ラベルの設定 */
	private void setJLbl() {
		JLblStatusNumber.setText("no");
		JLblStatusMoney.setText("no");

		int width = 35, height = 100;
		JLblStatusNumber.setBounds(70, setBtnMoneyYLine + 40, width + 20, height);

		JLblStatusMoney.setBounds(20, setBtnMoneyYLine + 40, width, height);

		setBtnMoneyYLine += (5 + height * 2);

		add(JLblStatusNumber);
		add(JLblStatusMoney);
	}

	private String convertNumToString(int x) {
		if (x == AnyNumMoney.init) {
			return "no";
		}
		if (x < NumbersTable.numbers.size() - 1) {
			return Integer.toString(x);
		}
		if (x == NumbersTable.numbers.size() - 1) {
			return "00";
		}
		return btnNumSPPattern[x % NumbersTable.numbers.size()];
	}

	private String convertMoneyToString(int x) {
		if (x == AnyNumMoney.init) {
			return "no";
		}
		return Integer.toString(x);
	}

	/* イベント入力時 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(Util.getMethodName());
		String pushBtn = e.getActionCommand(); // 押したボタンの表示名(String)
		System.out.printf("(statusLock,pushBtn) = (%b,%s) \n", statusLock, pushBtn);

		StringBuilder jLblStr = new StringBuilder("<html>"); // JLabelにて表示する内容．

		if (e.getSource() == JBtnExit) { // Exitボタンでプログラムごと終了
			System.exit(-1);
		}

		if (statusLock) { // ロックされていたら，入力しない
			return;
		}

		if (e.getSource() == JBtnMoneyClear) { // 賭金を0にする
			initStatus();

			JLblStatusNumber.setText("no");
			JLblStatusMoney.setText("no");
			return;
		}

		for (int i = 0; i < NumbersTable.numbers.size(); i++) { // 賭けるNumberを設定する
			if (e.getSource() == JBtnNumber[i]) {
				AnyNumMoney.num_len = AnyNumMoney.num_len % statusNumMoney.length;
				statusNumMoney[AnyNumMoney.num_len++].num = i;
				for (int a = 0; a < statusNumMoney.length; a++) {
					String str = convertNumToString(statusNumMoney[a].num);
					jLblStr.append(str + "<br>");
				}
				jLblStr.append("</html>");
				JLblStatusNumber.setText(jLblStr.toString());
				return;
			}
		}

		for (int i = 0; i < btnNumSPPattern.length; i++) { // 賭ける特殊な場所を設定する
			if (e.getSource() == JBtnNumSP[i]) {
				AnyNumMoney.num_len = AnyNumMoney.num_len % statusNumMoney.length;
				statusNumMoney[AnyNumMoney.num_len++].num = NumbersTable.numbers.size() + i;
				for (int a = 0; a < statusNumMoney.length; a++) {
					String str = convertNumToString(statusNumMoney[a].num);
					jLblStr.append(str + "<br>");
				}
				jLblStr.append("</html>");
				JLblStatusNumber.setText(jLblStr.toString());
				return;
			}
		}

		for (int i = 0; i < btnMoneyPattern.length; i++) {
			if (e.getSource() == JBtnMoney[i]) { // 賭けるお金を足す
				AnyNumMoney.money_len = AnyNumMoney.money_len % statusNumMoney.length;
				statusNumMoney[AnyNumMoney.money_len++].money = btnMoneyPattern[i];
				for (int a = 0; a < statusNumMoney.length; a++) {
					String str = convertMoneyToString(statusNumMoney[a].money);
					jLblStr.append(str + "<br>");
				}
				jLblStr.append("</html>");
				JLblStatusMoney.setText(jLblStr.toString());
				return;
			}
		}
	}

	/* 賭けにロックをする */
	public void setLock(boolean _t) {
		statusLock = _t;
	}

	/* 賭けのロック状態を取得する */
	public boolean getLock() {
		return statusLock;
	}

	/* 現在の賭けている場所と金額 */
	public AnyNumMoney[] getNumMoney() {
		return statusNumMoney;
	}

}
