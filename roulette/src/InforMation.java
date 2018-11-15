import javax.swing.*;
import java.awt.*;

public class InforMation {
	public InforMation() {

		JFrame jframe = new JFrame("ルーレットの説明");// フレーム表示

		Container container = jframe.getContentPane();// コンテンツ区画の取得

		JTextArea jta = new JTextArea(" 1. 賭ける場所と金額を設定します．\n        制限時間を過ぎてしまうと，賭けられなくなるのでお早めに．\n 2. 自動でルーレットが始まります．当たるといいね！\n 3. 終了後，支払いが行われます．\n 4. 1.に戻ります．いっぱい楽しんでね！"); // テキストエリアの作成

		container.add(jta);// テキストエリアをコンテンツ区画に追加する

		jframe.setSize(500, 300);// フレームのサイズを指定
		jframe.setVisible(true);// フレームの表示・非表示を指定
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ×を押した時の処理

	}
}
