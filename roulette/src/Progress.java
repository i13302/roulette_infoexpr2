import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Progress {
	public static void main(String args[]) {
		System.out.println("Hello! World!");
		// JFrame jframe;
		// SwingUtilities.invokeLater(() -> {
		JFrame jframe = new JFrame("Table");
		jframe.add(new Table());
		jframe.pack();
		jframe.setVisible(true);
		jframe.setSize(600, 400);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// });
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}

		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}
		jframe.setVisible(false);
		System.out.println("END");
		System.exit(0);
		// SwingUtilities.getAncestorOfClass(JFrame.class, getLock);
	}
}