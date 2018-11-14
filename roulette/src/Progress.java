public class Progress {
	public static void main(String args[]) {
		System.out.println("Hello! World!");

		Table table = new Table();
		table.setVisible(true); // Windowを開く

		try { // 制限時間まで待っている
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}

		table.setLock(true); // 制限時間が来たら，もう選択できない
		System.out.println("setLock(t) = " + table.getLock());

		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
		}

		System.out.println("END");
		System.exit(0);

	}
}