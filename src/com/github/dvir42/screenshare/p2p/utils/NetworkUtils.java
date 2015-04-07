package com.github.dvir42.screenshare.p2p.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkUtils {

	@SuppressWarnings("resource")
	public static String getLocalIP() throws UnknownHostException, IOException {
		return new Socket("212.179.180.101", 80).getLocalAddress()
				.getHostAddress();
	}

}
