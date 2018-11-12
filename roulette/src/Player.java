
public class Player {
	// Walletの実装次第
	private Wallet wallet; // 所持金
	private int coin; // 掛け金

	public Player(Wallet _wallet) {
		this.wallet = _wallet;
	}

	public Wallet getWallet() {
		return this.wallet;
	}

	public void setWallet(Wallet _wallet) {
		this.wallet = _wallet;
	}

	public void setCoin(int _coin) {
		// TODO: 掛け金がWallet内にあるかどうかチェックする
		// if ( this.wallet.getCache() - coin < 0) {
		// return HasanException;
		// }
		this.coin = _coin;
	}

	public int getCoin(int _coin) {
		return this.coin;
	}
}
