import java.util.Random;

public class Roulette {
	private Random random;

	private int minValue = 0; // 最低値
	private int maxValue = 38; // 最大値

	public Roulette() {
		this.generateRandomInstance();
	}

	/* ランダムインスタンスを生成 */
	public void generateRandomInstance() {
		long seed = System.currentTimeMillis();
		random = new Random(seed);
	}

	/* ルーレットの値を数値として出す */
	public int getIntRouletteValue() {
		return random.nextInt(this.maxValue) + this.minValue;
	}

	/* ルーレットとしての値を出す． */
	public String getStrRouletteValue() {
		int v = this.getIntRouletteValue();

		if (v == 37) { // 37の場合は00とする
			return "00";
		}
		return Integer.toString(v);
	}

}