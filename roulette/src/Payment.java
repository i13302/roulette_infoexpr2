public class Payment {
	private static final NumbersTable numTable = new NumbersTable();

	public static void calc(Player player, Player dealer, int stopAddress) {
		int playerAddress = player.getAddress();
		Number winNumber = getNumberByAddress(stopAddress);
		// インサイドベットの判定
		if (playerAddress >= 0 && playerAddress <= 37) {
			if (isWinning36(player, stopAddress)) {
				dealer.sendCache(player, player.getCoin() * 36);
				return;
			}
		}
		// アウトサイドベットの判定
		// -- Range判定
		else if (playerAddress >= 38 && playerAddress <= 40) {
			Number.Range playerRange = getRangeByAddress(playerAddress);
			if (playerRange == winNumber.getRange()) {
				dealer.sendCache(player, player.getCoin() * 3);
				return;
			}
		}
		// -- High and Low
		else if (playerAddress >= 41 && playerAddress <= 42) {
			// Low判定
			if (playerAddress == 41 && isLow(stopAddress)) {
				dealer.sendCache(player, player.getCoin() * 2);
				return;
			}
			// High判定
			if (playerAddress == 42 && isHigh(stopAddress)) {
				dealer.sendCache(player, player.getCoin() * 2);
				return;
			}
		}
		// -- Parity
		else if (playerAddress >= 43 && playerAddress <= 44) {
			if (playerAddress == 43 && isEven(stopAddress)) {
				dealer.sendCache(player, player.getCoin() * 2);
				return;
			}

			if (playerAddress == 44 && !isEven(stopAddress)) {
				dealer.sendCache(player, player.getCoin() * 2);
				return;
			}
		}
		// -- Color
		else if (playerAddress >= 45 && playerAddress <= 46) {
			Number.Color playerColor = getColorByAddress(playerAddress);
			if (playerColor == winNumber.getColor()) {
				dealer.sendCache(player, player.getCoin() * 3);
				return;
			}
		}
		// 負けたとき
		player.sendCache(dealer, player.getCoin());
	}

	private static boolean isWinning36(Player player, int stopAddress) {
		if (player.getAddress() == stopAddress) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isWinningColor(Player player, int stopAddress) {
		Number playerNumber = getPlayerNumber(player);
		Number winNumber = getNumberByAddress(stopAddress);
		return playerNumber.getColor() == winNumber.getColor();
	}

	// Playerが掛けた番号を返すメソッド
	private static Number getPlayerNumber(Player player) {
		return getNumberByAddress(player.getAddress());
	}

	private static Number getNumberByAddress(int address) {
		return numTable.numbers.get(address);
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
