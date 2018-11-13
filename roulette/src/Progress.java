import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Progress {
	public static void main(String args[]) {
		System.out.println("Hello! World!");
		
		Table table = new Table();
		table.setVisible(true); // Windowを開く
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}
		
		table.setLock(true);
		System.out.println("setLock(t) = " + table.getLock());
		
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}
		
		System.out.println("END");
		System.exit(0);
		
	}
}