package com.github.dvir42.screenshare.graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7064970744253770550L;

	private final JButton connect;
	private final JButton share;

	public StartScreen() {
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		connect = new JButton("connect");
		connect.addActionListener(this);
		connect.setActionCommand("connect");
		share = new JButton("share");
		share.addActionListener(this);
		share.setActionCommand("share");
		add(connect);
		add(share);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("connect"))
			new ConnectStartScreen();
		else
			new ShareStartScreen();
		dispose();
	}

}
