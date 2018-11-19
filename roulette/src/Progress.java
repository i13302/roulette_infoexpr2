public class Progress {
	// この辺の値は要調整
	private static final int playerCache = 1000;
	private static final int dealerCache = 1000;

	public static void main(String args[]) {
		Player player = new Player(new Wallet(playerCache));
		Dealer dealer = new Dealer(new Wallet(dealerCache));
		InforMation info = new InforMation();

		info.setMoney(player, dealer, 5);

		while (!(player.getWallet().isInsolvency() && dealer.getWallet().isInsolvency())) {
			Table table = new Table();
			table.setVisible(true); // Windowを開く
			for (int i = 0; i < 5; i++) {
				try { // 制限時間まで待っている
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
				}
				info.setMoney(player, dealer, 5 - i - 1); // 制限時間の表示を更新
			}

			table.setLock(true); // 制限時間が来たら，もう選択できない
			System.out.println("setLock(t) = " + table.getLock());

			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
			}
			table.setVisible(false); // Windowを閉じる

			// player.setAddress(table.getNumber()); // 変更
			// System.out.println(player.getAddress());
			// player.setCoin(table.getMoney()); // 変更
			// System.out.println(player.getCoin());

			AnyNumMoney[] ret = table.getNumMoney(); // 賭けているデータを取得
			for (int i = 0; i < ret.length; i++) {
				System.out.println(ret[i].num + "," + ret[i].money); // 表示しているだけ
			}

			Roulette roulette = new Roulette();

			// Rouletteの停止位置の決定
			int stopAddress = roulette.getIntRouletteValue();
			// Rouletteの回転開始
			new Ball(stopAddress);

			// 掛けた場所への判定と、支払い処理
			Payment.calc(player, dealer, stopAddress);

			info.setMoney(player, dealer, 0);
		}
		// 7. 終了
		System.out.println("END");
		System.exit(0);

	}
}