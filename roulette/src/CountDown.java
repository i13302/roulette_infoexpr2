// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
public class CountDown implements Runnable {
	private volatile Thread thread = null;
	private long time_min;

	CountDown(int t) {
		this.time_min = 1000 * t;
	}

	public void startCountDown() {
		this.startThread();

	}

	private void startThread() {
		if (this.thread == null) {
			this.thread = new Thread(this);
			this.thread.start();
		}
	}
	
	public void run(){
		try {
			Thread.sleep(this.time_min);
		} catch (InterruptedException e) {
			this.stopThread();
		}
	}

	private void stopThread() {
		this.thread = null;
	}

}