package com.github.dvir42.screenshare.graphics;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {

	private static final long serialVersionUID = -4609653108365327285L;

	private BufferedImage capture;

	public Test() {
		JFrame f = new JFrame();
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.add(this);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true)
					try {
						Rectangle screenRect = new Rectangle(Toolkit
								.getDefaultToolkit().getScreenSize());
						capture = new Robot().createScreenCapture(screenRect);
						repaint();
						Thread.sleep(10);
					} catch (AWTException | InterruptedException e) {
						e.printStackTrace();
					}
			}
		}).start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(capture, 0, 0, null, null);
	}

	public static void main(String[] args) {
		new Test();
	}

}
