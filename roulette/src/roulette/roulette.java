package roulette;

import roulette.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class roulette{
	public static void main(String args[]) {
		System.out.println("Hello! World!");
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Table");
			frame.add(new Table());
			frame.pack();
			frame.setVisible(true);
			frame.setSize(600,400);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		});

		
	}
	
}