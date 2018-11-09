package roulette;

import roulette.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Table extends JPanel implements ActionListener {
	private int numNumber=36+2; // 賭ける場所の個数
	private int numMoney=4;     // 賭けるお金のパターン
	
	private JButton[] JBtnNumber=new JButton[numNumber]; // 場所のボタン
	private JButton[] JBtnMoney =new JButton[numMoney];  // お金のボタン
	private JButton JBtnExit=new JButton("Exit");        //　強制終了 
	
	private int statusNumber;  // 選択した場所
	private int statusMoney;   // 選択したお金
	
	/* Constructer */
	public Table() {
		System.out.println("Table Class");
		_init_();
	}
	
	/* 初期処理 */
	private void _init_() {
		System.out.println("_init_");
//		setLayout(null); 
		setLayout(new FlowLayout()); // レイアウトの設定
		_init_status();
		
		_setJBtnNumbers();
		_setJBtnExit();
		_setJBtnMoneys();
		

	}
	
	/* ステータスを初期化 */
	private void _init_status() {
		statusNumber=-1;
		statusMoney=-1;
	}
	
	/* 賭ける場所のボタン */
	private void _setJBtnNumbers() {
		System.out.println(Util.getMethodName());
		for(int i=0;i<numNumber;i++) {
			JBtnNumber[i]=new JButton("Num:"+String.valueOf(i+1)); // TODO 画像で置き換える
			JBtnNumber[i].addActionListener(this);
			add(JBtnNumber[i]);
		}
	}
	
	/* 賭けるお金のボタン */
	private void _setJBtnMoneys() {
		System.out.println(Util.getMethodName());
		JBtnMoney[0]=new JButton("Money:"+String.valueOf(1));
		JBtnMoney[1]=new JButton("Money:"+String.valueOf(5));
		JBtnMoney[2]=new JButton("Money:"+String.valueOf(15));
		JBtnMoney[3]=new JButton("Money:"+String.valueOf(50));
		for(int i=0;i<numMoney;i++) {
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
//		 TODO Auto-generated method stub
		System.out.println("actionPerformed");
		String pushBtn = e.getActionCommand(); // 押したボタンの表示名(String)
		System.out.println(pushBtn);
		
		if(pushBtn.equals("Exit")) {
			System.exit(0);
		}
	}
}
