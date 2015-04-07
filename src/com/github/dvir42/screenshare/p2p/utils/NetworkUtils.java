package com.github.dvir42.screenshare.p2p.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkUtils {

	public static int[] recommendedPorts() {
		int[] ports = new int[65535 + 1 - 49152];
		for (int i = 0; i < ports.length; i++)
			ports[i] = i + 49152;
		return ports;
	}

	public static Integer getAvailablePort() {
		for (int port : recommendedPorts())
			try {
				new ServerSocket(port);
				return port;
			} catch (IOException ex) {
				continue;
			}
		return null;
	}

	public static Integer getAvailablePort(int[] ports) {
		for (int port : ports)
			try {
				new ServerSocket(port);
				return port;
			} catch (IOException ex) {
				continue;
			}
		return null;
	}

	@SuppressWarnings("resource")
	public static String getLocalIP() throws UnknownHostException, IOException {
		return new Socket("212.179.180.101", 80).getLocalAddress()
				.getHostAddress();
	}

}
