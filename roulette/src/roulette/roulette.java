/**
 * 
 * Roulette メインクラス
 * 
 */
import java.util.Random;
public class roulette {
	public static void main(String args[]) {
		System.out.println("Hello! World!");
		// Randomクラスのインスタンス化
		Random random = new Random();

		int randomValue =random.nextInt(10);
		System.out.println(randomValue);
	}
}