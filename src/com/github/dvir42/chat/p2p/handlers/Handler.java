package com.github.dvir42.chat.p2p.handlers;

import java.io.IOException;

import com.github.dvir42.chat.p2p.PeerConnection;

public interface Handler {

	public void handle(PeerConnection peerConnection, String[] args)
			throws IOException;

	@Override
	public String toString();

}
