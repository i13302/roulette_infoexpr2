
public class Progress {

	// この辺の値は要調整
	private static final int playerCache = 100000;
	private static final int dealerCache = 100000;

	public static void main(String args[]) {
		Player player = new Player(new Wallet(playerCache));
		Player dealer = new Player(new Wallet(dealerCache));
	}
}