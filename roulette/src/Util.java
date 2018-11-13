public class Util {
	/**
	 * 実行中のメソッド名を取得します。
	 * 
	 * @return メソッド名
	 */
	public static String getMethodName() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
}