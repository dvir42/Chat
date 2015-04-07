package com.github.dvir42.screenshare;

import java.io.IOException;
import java.net.UnknownHostException;

import com.github.dvir42.screenshare.p2p.Message;
import com.github.dvir42.screenshare.p2p.Peer;
import com.github.dvir42.screenshare.p2p.exceptions.UnhandledMessageTypeException;
import com.github.dvir42.screenshare.p2p.handlers.ShareScreen;
import com.github.dvir42.screenshare.p2p.utils.NetworkUtils;

public class Connect {

	public Connect(String ip, int port) throws UnknownHostException,
			IOException, UnhandledMessageTypeException {
		Peer peer = new Peer(NetworkUtils.getAvailablePort());
		peer.connectAndSend(Peer.getID(port, ip), new Message(
				new ShareScreen(), peer.getID()));
	}

}
