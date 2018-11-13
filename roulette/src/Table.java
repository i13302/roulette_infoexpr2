import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class Table extends JFrame implements ActionListener {
	JFrame jframe;
	private int numNumber = 36 + 2; // 賭ける場所の個数
	private int numMoney = 4; // 賭けるお金のパターン

	private JButton[] JBtnNumber = new JButton[numNumber]; // 場所のボタン
	private String labelNumber = "Num"; // 場所のラベル
	private JButton[] JBtnMoney = new JButton[numMoney]; // お金のボタン
	private String labelMoney = "Mon"; // お金のラベル

	private JButton JBtnExit = new JButton("Exit"); // 強制終了

	private int statusNumber; // 選択した場所
	private int statusMoney; // 選択したお金

	private boolean statusLock = false; // 選択をロック true...Locked,False...Open

	/* Constructer */
	public Table() {
		System.out.println("Table Class");
		_init_();

	}

	/* 初期処理 */
	private void _init_() {
		System.out.println("_init_");
		setLayout(new FlowLayout()); // レイアウトの設定

		_init_JFrame();
		_init_status();

		_setJBtnNumbers();
		_setJBtnExit();
		_setJBtnMoneys();

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
		statusMoney = -1;
	}

	/* 賭ける場所のボタン */
	private void _setJBtnNumbers() {
		System.out.println(Util.getMethodName());
		for (int i = 0; i < numNumber; i++) {
			JBtnNumber[i] = new JButton(labelNumber + ":" + String.valueOf(i + 1)); // TODO 画像で置き換える
			JBtnNumber[i].addActionListener(this);
			add(JBtnNumber[i]);
		}
	}

	/* 賭けるお金のボタン */
	private void _setJBtnMoneys() {
		System.out.println(Util.getMethodName());
		JBtnMoney[0] = new JButton(labelMoney + ":" + String.valueOf(1));
		JBtnMoney[1] = new JButton(labelMoney + ":" + String.valueOf(5));
		JBtnMoney[2] = new JButton(labelMoney + ":" + String.valueOf(15));
		JBtnMoney[3] = new JButton(labelMoney + ":" + String.valueOf(50));
		for (int i = 0; i < numMoney; i++) {
			JBtnMoney[i].addActionListener(this);
			add(JBtnMoney[i]);
		}
	}

	/* 強制終了ボタン */
	private void _setJBtnExit() {
		System.out.println(Util.getMethodName());
		JBtnExit.addActionListener(this);
		add(JBtnExit);
	}

	/* イベント入力時 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(Util.getMethodName());
		String pushBtn = e.getActionCommand(); // 押したボタンの表示名(String)
		System.out.printf("(statusLock,pushBtn,statusNumber,statusMoney) = (%b,%s,%d,%d) \n", statusLock, pushBtn,
				statusNumber, statusMoney);

		if (pushBtn.equals("Exit")) { // Exitボタンでプログラムごと終了
			System.exit(-1);
		}

		if (statusLock) { // ロックされていたら，入力しない
			return;
		}
		/* START 雑なつくり getSourceを使うべき */
		String label = pushBtn.substring(0, 3); // ラベル
		int value = Integer.parseInt(pushBtn.substring(4)); // 値

		if (label.equals("Num")) { // Num:%dの場合 
			statusNumber = value;
		} else if (label.equals("Mon")) { // Mon:%dの場合
			statusMoney = value;
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
