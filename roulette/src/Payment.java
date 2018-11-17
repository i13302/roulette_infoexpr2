
public class Payment {
	public static void calc(Player player, Player dealer, int stopAddress) {
		if (judge(player, stopAddress) == true) {
			player.getWallet().payCache(player.getCoin()*36*(-1));
			dealer.getWallet().payCache(player.getCoin()*36);
		}else {
			player.getWallet().payCache(player.getCoin());
			dealer.getWallet().payCache(player.getCoin()*(-1));
		}
	}
	private static boolean judge(Player player, int stopAddress) {
		if (player.getAddress() == stopAddress) {
			return true;
		}else {
			return false;
		}
	}
}
