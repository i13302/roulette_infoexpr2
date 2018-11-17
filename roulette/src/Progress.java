
public class Progress {
	public static void main(String args[]) {
		System.out.println("Hello! World!");
		InforMation info = new InforMation();
		for (int i = 0; i < 20; i++) {
			System.out.println("Main");

			try {
				Thread.sleep(1 * 1000);
			} catch (InterruptedException e) {

			}
			info.setMoney(i, i * 10);
		}
		System.exit(-1);
	}
}