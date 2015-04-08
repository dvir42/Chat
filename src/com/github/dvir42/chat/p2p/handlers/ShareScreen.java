package com.github.dvir42.chat.p2p.handlers;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.github.dvir42.chat.p2p.PeerConnection;

public class ShareScreen implements Handler {

	@Override
	public void handle(PeerConnection peerConnection, String[] args)
			throws IOException {
		PeerConnection newConnection = new PeerConnection(args[0]);
		try {
			BufferedImage img = new Robot().createScreenCapture(new Rectangle(
					Toolkit.getDefaultToolkit().getScreenSize()));
			newConnection.sendImage(img, peerConnection.getPeerID());
		} catch (HeadlessException | AWTException e) {
			e.printStackTrace();
		}
		newConnection.close();
	}
}
