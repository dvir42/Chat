package com.github.dvir42.screenshare.p2p.handlers;

import java.io.IOException;

import com.github.dvir42.screenshare.Connect;
import com.github.dvir42.screenshare.p2p.Message;
import com.github.dvir42.screenshare.p2p.PeerConnection;
import com.github.dvir42.screenshare.p2p.utils.ArrayUtils;

public class SendString implements Handler {

	@Override
	public void handle(PeerConnection peerConnection, String[] args)
			throws IOException {
		PeerConnection newConnection = new PeerConnection(args[args.length - 1]);
		if (args[0].equalsIgnoreCase("img")) {
			Connect.screen.setImage(newConnection.receiveImage());
		} else {
			System.out.println(ArrayUtils.arrayToString(args, 0,
					args.length - 1));
			newConnection.sendMessage(new Message(new Acknowledge(), ""));
			newConnection.close();
		}
	}

	@Override
	public String toString() {
		return "SS";
	}

}
