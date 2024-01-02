package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CountDownTime extends JPanel implements ActionListener {
	public static int minutes = 10;
	public static int seconds = 0;
	public static Timer timer;
	public JLabel timeLabel;
	
	public CountDownTime() {
		this.setPreferredSize(new Dimension(100, 50));
		timeLabel = new JLabel();
		this.add(timeLabel);
		
		timer = new Timer(1000, this);
		timer.start();
	}
	
	private static void updateTime(JLabel label) {
		String formatMin = String.format("%02d", minutes);
		String formatSec = String.format("%02d", seconds);
		label.setText(formatMin + ":" + formatSec);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (minutes >= 0 && seconds >= 0) {
			updateTime(timeLabel);
			if (minutes == 0 && seconds == 0) {
				timer.stop();
				
			}
			if (seconds == 0) {
				seconds = 59;
				minutes--;
			}
			else {
				seconds--;
			}
		}
	}
	
	 
}
