import java.util.Random;

public class Random {
	public static void main(String[] args) {
		// Randomクラスのインスタンス化
		Random random = new Random();

		int randomValue =random.nextInt(10);
		System.out.println(randomValue);
	}
}