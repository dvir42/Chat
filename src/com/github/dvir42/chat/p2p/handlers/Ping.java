package com.github.dvir42.chat.p2p.handlers;

import java.io.IOException;

import com.github.dvir42.chat.p2p.Message;
import com.github.dvir42.chat.p2p.PeerConnection;

public class Ping implements Handler {

	public Ping() {
		super();
	}

	@Override
	public void handle(PeerConnection peerConnection, String[] args)
			throws IOException {
		PeerConnection newConnection = new PeerConnection(args[0]);
		newConnection.sendMessage(new Message(new SendString(), new String[] {
				peerConnection.getLocalIP(), peerConnection.getPeerID() }));
		newConnection.close();
	}

	@Override
	public String toString() {
		return "PING";
	}

}
