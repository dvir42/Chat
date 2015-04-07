package com.github.dvir42.screenshare.graphics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.github.dvir42.screenshare.p2p.utils.NetworkUtils;

public class ShareStartScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8402649677103909324L;

	private final JTextField ip;
	private final JTextField port;
	private final JButton share;

	private boolean ready;

	public ShareStartScreen() {
		ready = false;
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		ip = new JTextField("ip");
		ip.setColumns(10);
		ip.setDisabledTextColor(Color.black);
		try {
			ip.setText(NetworkUtils.getLocalIP());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ip.setEditable(false);
		port = new JTextField("port");
		port.setColumns(5);
		share = new JButton("share");
		share.addActionListener(this);
		add(ip);
		add(port);
		add(share);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ready = true;
	}

	public boolean isReady() {
		return ready;
	}

	public int getPort() {
		return Integer.parseInt(port.getText());
	}

}
