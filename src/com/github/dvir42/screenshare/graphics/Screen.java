package com.github.dvir42.screenshare.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JPanel {

	private static final long serialVersionUID = 3564393563869164515L;

	private BufferedImage capture;

	public Screen() {
		JFrame f = new JFrame();
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.add(this);
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(capture, 0, 0, null, null);
	}

	public void setImage(Image img) {
		capture = (BufferedImage) img;
	}

}
