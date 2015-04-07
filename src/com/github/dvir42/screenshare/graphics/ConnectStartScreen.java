package com.github.dvir42.screenshare.graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.github.dvir42.screenshare.Connect;
import com.github.dvir42.screenshare.p2p.exceptions.UnhandledMessageTypeException;

public class ConnectStartScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 7064970744253770550L;

	private final JTextField ip;
	private final JTextField port;
	private final JButton connect;

	public ConnectStartScreen() {
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		ip = new JTextField("ip");
		ip.setColumns(10);
		port = new JTextField("port");
		port.setColumns(5);
		connect = new JButton("connect");
		connect.addActionListener(this);
		add(ip);
		add(port);
		add(connect);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			new Connect(ip.getText(), Integer.parseInt(port.getText()));
		} catch (NumberFormatException | IOException
				| UnhandledMessageTypeException e1) {
			e1.printStackTrace();
		}
		dispose();
		new Screen();
	}

}
