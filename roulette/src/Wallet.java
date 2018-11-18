public class Wallet {
	private int cache; // 所持金

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

	// 破産フラグ
	public boolean isInsolvency() {
		return this.cache < 0;
	}
}