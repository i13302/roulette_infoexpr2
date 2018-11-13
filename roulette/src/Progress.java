
public class Progress {

	// この辺の値は要調整
	private static final int playerCache = 100000;
	private static final int dealerCache = 100000;

	public static void main(String args[]) {
		Player player = new Player(new Wallet(playerCache));
		Dealer dealer = new Dealer(new Wallet(dealerCache));
		
		player.getCoin();
		dealer.getCoin();
		
		System.out.println(player.getCoin());
		System.out.println(dealer.getCoin());
		
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