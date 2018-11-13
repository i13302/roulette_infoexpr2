public class Progress {
	public static void main(String args[]) {
		System.out.println("Hello! World!");

		Roulette roulette = new Roulette();

		for (int i = 0; i < 38; i++) {
			System.out.println(roulette.getStrRouletteValue());

		}
	}
}