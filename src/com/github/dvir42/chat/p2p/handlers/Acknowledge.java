package com.github.dvir42.chat.p2p.handlers;

import java.io.IOException;

import com.github.dvir42.chat.p2p.PeerConnection;

public class Acknowledge implements Handler {

	@Override
	public void handle(PeerConnection peerConnection, String[] args)
			throws IOException {
		System.out.println("acknowleged");
	}

	@Override
	public String toString() {
		return "ACK";
	}

}
