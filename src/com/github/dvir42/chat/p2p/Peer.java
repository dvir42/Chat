package com.github.dvir42.chat.p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.github.dvir42.chat.p2p.exceptions.UnhandledMessageTypeException;
import com.github.dvir42.chat.p2p.handlers.Handler;
import com.github.dvir42.chat.p2p.handlers.Ping;
import com.github.dvir42.chat.p2p.handlers.SendString;
import com.github.dvir42.chat.p2p.utils.NetworkUtils;

public class Peer {

	private final String id;
	private final String ip;
	private final int port;

	private final static ArrayList<Handler> handlers = new ArrayList<Handler>();

	private final ArrayList<Peer> peers;

	private boolean alive;

	public Peer(int port, String ip) {
		this.port = port;
		this.ip = ip;
		id = getID(this.port, this.ip);
		peers = new ArrayList<Peer>();
		alive = true;
	}

	public Peer(int port) throws UnknownHostException, IOException {
		this.port = port;
		ip = NetworkUtils.getLocalIP();
		id = getID(this.port, this.ip);
		peers = new ArrayList<Peer>();
		alive = true;
	}

	public String getID() {
		return id;
	}

	public static String getID(int port, String ip) {
		return ip + ":" + port;
	}

	public String getIP() {
		return ip;
	}

	public static String getIP(String id) {
		return id.substring(0, id.indexOf(':'));
	}

	public int getPort() {
		return port;
	}

	public static int getPort(String id) {
		return Integer.parseInt(id.substring(id.indexOf(':') + 1));
	}

	public static ArrayList<Handler> getHandlers() {
		return handlers;
	}

	public static void addHandler(Handler handler) {
		boolean found = false;
		for (Handler temp : handlers)
			if (handler.toString() == temp.toString())
				found = true;
		if (!found)
			handlers.add(handler);
	}

	public static Handler handlerToString(String handlerStr) {
		for (Handler handler : handlers) {
			if (handlerStr.equals(handler.toString()))
				return handler;
		}
		return null;
	}

	public ArrayList<Peer> getPeers() {
		return peers;
	}

	public void addPeer(int port, String ip) {
		boolean found = false;
		for (Peer peer : peers)
			if (peer.id == getID(port, ip))
				found = true;
		if (!found)
			peers.add(new Peer(port, ip));
	}

	public void removePeer(String peerID) {
		Peer toBeRemoved = null;
		for (Peer peer : peers)
			if (peer.id == peerID) {
				toBeRemoved = peer;
				break;
			}
		peers.remove(toBeRemoved);
	}

	public Peer getPeer(String peerID) {
		Peer toBeReturned = null;
		for (Peer peer : peers)
			if (peer.id == peerID) {
				toBeReturned = peer;
				break;
			}
		return toBeReturned;
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		alive = false;
	}

	private void handlePeer(Socket socket) throws UnknownHostException,
			IOException, UnhandledMessageTypeException {
		PeerConnection peerConnection = new PeerConnection(id, socket);
		Message message = peerConnection.receiveMessage();
		if (message != null)
			message.getHandler().handle(peerConnection, message.getData());
		else
			throw new UnhandledMessageTypeException();
		peerConnection.close();
	}

	public void listen() throws IOException {
		ServerSocket listeningSocket = new ServerSocket(port);
		listeningSocket.setReuseAddress(true);
		try {
			while (alive) {
				final Socket connectionSocket = listeningSocket.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							handlePeer(connectionSocket);
						} catch (IOException | UnhandledMessageTypeException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			listeningSocket.close();
		} catch (IOException e) {
			listeningSocket.close();
			e.printStackTrace();
		}
	}

	public void connectAndSend(String id, Message message)
			throws UnknownHostException, IOException,
			UnhandledMessageTypeException {
		PeerConnection peerConnection = new PeerConnection(id);
		peerConnection.sendMessage(message);
		peerConnection.close();
	}

	public static void main(String[] args) {
		try {
			Peer p = new Peer(12346);
			Peer.addHandler(new SendString());
			p.connectAndSend("10.0.0.2:12345", new Message(new Ping(), p.id));
			p.listen();
		} catch (IOException | UnhandledMessageTypeException e) {
			e.printStackTrace();
		}
	}

}
