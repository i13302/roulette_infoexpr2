import javax.swing.JFrame;

class SSample5_1 {
	public static void main(String args[]) {
		MyFrame frame = new MyFrame("タイトル");
		frame.setVisible(true);
	}
}

class MyFrame extends JFrame {
	MyFrame(String title) {
		setTitle(title);
		init();
	}

	private void init() {
		setBounds(100, 100, 200, 160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}