package com.github.dvir42.chat.users;

import java.io.IOException;
import java.net.UnknownHostException;

import com.github.dvir42.chat.p2p.Message;
import com.github.dvir42.chat.p2p.Peer;
import com.github.dvir42.chat.p2p.exceptions.UnhandledMessageTypeException;
import com.github.dvir42.chat.p2p.handlers.SendString;
import com.github.dvir42.chat.p2p.utils.NetworkUtils;

public class User {

	private final String userName;
	private final Peer peer;

	public User(String userName) throws UnknownHostException, IOException {
		this.userName = userName;
		peer = new Peer(NetworkUtils.getAvailablePort());
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					peer.listen();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void send(User user, String msg) {
		try {
			peer.connectAndSend(user.peer.getID(), new Message(
					new SendString(), msg));
		} catch (IOException | UnhandledMessageTypeException e) {
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}

}
