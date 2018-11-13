// テスト用のWalletクラス
public class Wallet {
	private int cache;

	public Wallet(int _cache) {
		this.cache = _cache;
	}

	public int getCache() {
		return this.cache;
	}

	// setterは用意せず、支払いのみ設定
	public void payCache(int _cache) {
		this.cache -= _cache;
	}

	public boolean isInsolvency() {
		return this.cache < 0;
	}
}