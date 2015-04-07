package com.github.dvir42.screenshare;

import java.io.IOException;
import java.net.UnknownHostException;

import com.github.dvir42.screenshare.graphics.Screen;
import com.github.dvir42.screenshare.p2p.Message;
import com.github.dvir42.screenshare.p2p.Peer;
import com.github.dvir42.screenshare.p2p.exceptions.UnhandledMessageTypeException;
import com.github.dvir42.screenshare.p2p.handlers.SendString;
import com.github.dvir42.screenshare.p2p.handlers.ShareScreen;
import com.github.dvir42.screenshare.p2p.utils.NetworkUtils;

public class Connect {

	public static Screen screen;

	public Connect(String ip, int port) throws UnknownHostException,
			IOException, UnhandledMessageTypeException {
		Peer peer = new Peer(NetworkUtils.getAvailablePort());
		Peer.addHandler(new SendString());
		screen = new Screen();
		peer.connectAndSend(Peer.getID(port, ip), new Message(
				new ShareScreen(), peer.getID()));
	}
}
