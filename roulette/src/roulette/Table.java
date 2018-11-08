package roulette;

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
		for(int i=0;i<numNumber;i++) {
			JBtnNumber[i]=new JButton(String.valueOf(i+1));
			JBtnNumber[i].addActionListener(this);
			add(JBtnNumber[i]);
		}
		JBtnExit.addActionListener(this);
		add(JBtnExit);
		
		setLayout(new FlowLayout());
	}
	
	public void mouseClicked(MouseEvent e) { 
		mouse.x = e.getX();
		mouse.y = e.getY();
		repaint();
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
