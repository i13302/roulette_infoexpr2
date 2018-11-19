public class Payment {
	// private static final NumbersTable numTable = new NumbersTable();

	public static void calcEach(Player player, Player dealer, int stopAddress) {
		for (AnyNumMoney anm : player.getAnyNumsMoney()) {
			calc(player, dealer, stopAddress, anm);
		}
	}

	public static void calc(Player player, Player dealer, int stopAddress, AnyNumMoney anm) {
		int playerAddress = anm.num;
		Number winNumber = getNumberByAddress(stopAddress);
		// インサイドベットの判定
		if (playerAddress < 0) {
			return;
		}
		if (playerAddress >= 0 && playerAddress <= 37) {
			if (isWinning36(anm, stopAddress)) {
				dealer.sendCache(player, anm.money * 36);
				return;
			}
			player.sendCache(dealer, anm.money);
			return;
		}
		System.out.println("playerAddress: " + playerAddress);

		// アウトサイドベットの判定
		// -- Range判定
		switch (NumbersTable.getSpecialNumberByAddress(playerAddress)) {
		// -- Range判定
		case SMALL:
		case MIDDLE:
		case LARGE:
			Number.Range playerRange = getRangeByAddress(playerAddress);
			if (playerRange == winNumber.getRange()) {
				System.out.println("Result: SMALL");
				System.out.println("Result: MIDDLE");
				System.out.println("Result: LARGE");
				dealer.sendCache(player, anm.money * 3);
				return;
			}
			break;
		case LOW:
			if (isLow(stopAddress)) {
				System.out.println("Result: LOW");
				dealer.sendCache(player, anm.money * 2);
				return;
			}
			break;
		case HIGH:
			if (isHigh(stopAddress)) {
				System.out.println("Result: LOW");
				dealer.sendCache(player, anm.money * 2);
				return;
			}
			break;
		// -- Parity
		case PARILLINEN:
			if (isEven(stopAddress)) {
				System.out.println("Result: PARILLINEN");
				dealer.sendCache(player, anm.money * 2);
				return;
			}
			break;
		case PARITON:
			if (!isEven(stopAddress)) {
				System.out.println("Result: PARITON");
				dealer.sendCache(player, anm.money * 2);
				return;
			}
			break;
		// -- Color
		case RED:
		case BLACK:
			Number.Color playerColor = getColorByAddress(playerAddress);
			if (playerColor == winNumber.getColor()) {
				System.out.println("Result: BLACK or RED");
				dealer.sendCache(player, anm.money * 2);
				return;
			}
			break;
		}
		player.sendCache(dealer, anm.money);
	}

	private static boolean isWinning36(AnyNumMoney anm, int stopAddress) {
		if (anm.num == stopAddress) {
			return true;
		} else {
			return false;
		}
	}

	private static Number getNumberByAddress(int address) {
		return NumbersTable.numbers.get(address);
	}

	// 偶数判定
	private static boolean isEven(int address) {
		return address % 2 == 0;
	}

	private static boolean isLow(int stopAddress) {
		return (stopAddress >= 1 && stopAddress <= 18);
	}

	private static boolean isHigh(int stopAddress) {
		return (stopAddress >= 19 && stopAddress <= 36);
	}

	private static Number.Range getRangeByAddress(int address) {
		switch (address) {
		case 38:
			return Number.Range.SMALL;
		case 39:
			return Number.Range.MEDIUM;
		case 40:
			return Number.Range.LARGE;
		}
		return null;
	}

	private static Number.Color getColorByAddress(int address) {
		switch (address) {
		case 45:
			return Number.Color.RED;
		case 46:
			return Number.Color.BLACK;
		}
		return null;
	}
}
