public class Progress {		

	// この辺の値は要調整
	private static final int playerCache = 100000;
	private static final int dealerCache = 100000;

	public static void main(String args[]) {
		System.out.println("Hello! World!");
		Player player = new Player(new Wallet(playerCache));
		Dealer dealer = new Dealer(new Wallet(dealerCache));
						
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
		
		Roulette roulette = new Roulette();

		for (int i = 0; i < 38; i++) { // TODO Numberクラス
			System.out.println(roulette.getStrRouletteValue());

		}
    Ball ball = new Ball(28);

		System.out.println("END");
		System.exit(0);
		
	}
}