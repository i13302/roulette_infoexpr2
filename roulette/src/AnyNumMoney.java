/*
 * 
 * 多点掛け用のクラス 
 * 
 */

public class AnyNumMoney {
	public static int init = -1;
	public int num = this.init; // 賭けた場所
	public static int numNext = 0; // 賭けた場所の番人
	public int money = this.init + 1; // 賭けたお金の額
	public static int moneyNext = 0; // 賭けたお金の番人

	public static void reset() {
		numNext = 0;
		moneyNext = 0;
	}
}