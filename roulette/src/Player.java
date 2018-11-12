

public class Player {
	// Walletの実装次第
	private Wallet wallet;
	public Player(Wallet _wallet) {
		this.wallet = _wallet;
	}
	public Wallet getWallet() { return this.wallet; }
	public void setWallet(Wallet _wallet) { this.wallet = _wallet; }

}
