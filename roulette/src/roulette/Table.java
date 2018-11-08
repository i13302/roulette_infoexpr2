package roulette;

import roulette.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Table extends JPanel implements ActionListener {
	private int numNumber=36+2;
	
	private Point mouse=new Point();
	
	private JButton[] JBtnNumber=new JButton[numNumber];
	private JButton JBtnExit=new JButton("Exit");
	
	public Table() {
		System.out.println("Table Class");
		_init_();
	}
	
	private void _init_() {
		System.out.println("_init_");
		_setJBtnNumbers();
		_setJBtnExit();
		
		setLayout(new FlowLayout());
	}
	
	private void _setJBtnNumbers() {
		System.out.println(Util.getMethodName());
		for(int i=0;i<numNumber;i++) {
			JBtnNumber[i]=new JButton(String.valueOf(i+1));
			JBtnNumber[i].addActionListener(this);
			add(JBtnNumber[i]);
		}
	}
	
	private void _setJBtnExit() {
		System.out.println(Util.getMethodName());
		JBtnExit.addActionListener(this);
		add(JBtnExit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		 TODO Auto-generated method stub
		System.out.println("actionPerformed");
		String pushBtn = e.getActionCommand();
		if(pushBtn=="Exit") {
			System.out.println("Bye");
			System.exit(0);
		}
		System.out.println(pushBtn);
		
	}
}
