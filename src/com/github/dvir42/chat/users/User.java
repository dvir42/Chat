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
		Users.users.add(new Simple(userName, peer.getPort(), peer.getIP()));
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

	public class Simple {
		private final String userName;
		private final int listeningPort;
		private final String ip;

		public Simple(String userName, int listeningPort, String ip) {
			this.userName = userName;
			this.listeningPort = listeningPort;
			this.ip = ip;
		}

		public String getUserName() {
			return userName;
		}

		public int getListeningPort() {
			return listeningPort;
		}

		public String getIP() {
			return ip;
		}
	}

}
