import javax.swing.*;
import java.awt.*;

public class InforMation extends JFrame {
	private int playerMoney;
	private int dealerMoney;

	private String gameInfo = "<html>1. 賭ける場所と金額を設定します．<br>&nbsp;&nbsp;制限時間を過ぎてしまうと，賭けられなくなるのでお早めに．<br> 2. 自動でルーレットが始まります．当たるといいね！<br> 3.終了後，支払いが行われます．4. 1.に戻ります．いっぱい楽しんでね！<br>";
	JFrame jframe;
	JLabel jLabel = new JLabel();

	private StringBuilder buf = new StringBuilder(); // 表示内容

	public InforMation() {
		this.setTitle("ルーレットの説明");
		this.setLayout(new FlowLayout());
		this.setSize(500, 300); // フレームのサイズを指定
		this.setResizable(false); // フレームのResizeを禁止
		this.setLocationRelativeTo(null); // フレームの表示位置を中央に
		this.setVisible(true); // フレームの表示・非表示を指定
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ×を押した時の処理
	}

	public void setMoney(Player player, Player dealer, int time) { // Public なんだから，手動で呼び出せ．
		System.out.println("setMoney");
		this.playerMoney = player.getWallet().getCache();
		this.dealerMoney = dealer.getWallet().getCache();

		StringBuilder moneyInfo = new StringBuilder();
		moneyInfo.append("Player: ");
		moneyInfo.append(Integer.toString(this.playerMoney));
		moneyInfo.append("<br>");
		moneyInfo.append("Dealer: ");
		moneyInfo.append(Integer.toString(this.dealerMoney));
		moneyInfo.append("<br>");

		StringBuilder showStrBuild = new StringBuilder();
		showStrBuild.append(gameInfo);
		showStrBuild.append(moneyInfo.toString());
		showStrBuild.append("残り時間: ");
		showStrBuild.append(Integer.toString(time));
		showStrBuild.append(" [sec]");
		showStrBuild.append("<br>");
		showStrBuild.append("</html>");

		jLabel.setText(showStrBuild.toString()); // TODO 処理に時間がかかって追いつかない．

		this.add(jLabel);
		this.revalidate();
		this.repaint();
	}
}
