
public class Payment {
	public static void calc(Player player, Player dealer, int stopAddress) {
		if (judge(player, stopAddress)) {
			dealer.sendCache(player, player.getCoin() * 36);
		} else {
			player.sendCache(dealer, player.getCoin());
		}
	}

	private static boolean judge(Player player, int stopAddress) {
		if (player.getAddress() == stopAddress) {
			return true;
		} else {
			return false;
		}
	}
}
