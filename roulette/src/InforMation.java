import javax.swing.*;
import java.awt.*;

public class InforMation {
	private int playerMoney;
	private int dealerMoney;
	
	private String gameInfo = "<html> 1. 賭ける場所と金額を設定します．<br>       制限時間を過ぎてしまうと，賭けられなくなるのでお早めに．<br> 2. 自動でルーレットが始まります．当たるといいね！\n 3. 終了後，支払いが行われます．<br> 4. 1.に戻ります．いっぱい楽しんでね！<br>";
	JFrame jframe;
	JLabel jLabel = new JLabel();
	JPanel panel = new JPanel();

	private StringBuilder buf = new StringBuilder(); // 表示内容

	public InforMation() {
		jframe = new JFrame("ルーレットの説明");// フレーム表示

		jframe.setSize(500, 300);// フレームのサイズを指定
		jframe.setVisible(true);// フレームの表示・非表示を指定
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ×を押した時の処理
		this.setMoney(-100, -100);
	}

	public void setMoney(int player, int dealer) {
		this.playerMoney = player;
		this.dealerMoney = dealer;
		StringBuilder moneyInfo = new StringBuilder();
		moneyInfo.append("Player");
		moneyInfo.append(Integer.toString(this.playerMoney));
		moneyInfo.append("Dealer");
		moneyInfo.append(Integer.toString(this.dealerMoney));

		StringBuilder showStrBuild =new StringBuilder();
		showStrBuild.append(gameInfo);
		showStrBuild.append(moneyInfo);
		showStrBuild.append("</html>");
		
		jLabel.setText(showStrBuild.toString()); // TODO 処理に時間がかかって追いつかない．

		panel = new JPanel();
		panel.add(jLabel);

		Container contentPane = jframe.getContentPane();
		contentPane.add(jLabel, BorderLayout.CENTER);

	}
}
