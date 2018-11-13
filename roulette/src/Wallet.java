// テスト用のWalletクラス
public class Wallet {
	private int cache;

	public Wallet(int _cache) {
		this.cache = _cache;
	}

	public boolean isInsolvency() {
		return this.cache < 0;
	}
}