package com.github.dvir42.p2p;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.github.dvir42.p2p.exceptions.UnhandledMessageTypeException;
import com.github.dvir42.p2p.handlers.Acknowledge;
import com.github.dvir42.p2p.handlers.Handler;
import com.github.dvir42.p2p.handlers.Ping;
import com.github.dvir42.p2p.utils.ArrayUtils;

public class PeerConnection {

	private final String peerID;
	private final Socket socket;
	private final BufferedReader in;
	private final BufferedWriter out;

	public PeerConnection(String peerID) throws UnknownHostException,
			IOException {
		this.peerID = peerID;
		socket = new Socket(Peer.getIP(peerID), Peer.getPort(peerID));
		socket.setReuseAddress(true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	}

	public PeerConnection(String peerID, Socket socket) throws IOException {
		this.peerID = peerID;
		this.socket = socket;
		this.socket.setReuseAddress(true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	}

	private String createMsg(Handler handler, String[] data) {
		return handler + ArrayUtils.arrayToString(data) + "\n";
	}

	public void sendMessage(Message message) throws IOException {
		String msg = createMsg(message.getHandler(), message.getData());
		out.write(msg);
		out.flush();
	}

	public Message receiveMessage() throws IOException,
			UnhandledMessageTypeException {
		String msg = in.readLine();
		if (msg == null)
			return null;
		String handlerStr = msg.substring(0, msg.indexOf('['));
		String dataStr = msg.substring(msg.indexOf('['));
		Handler handler = Peer.handlerToString(handlerStr);
		if (handler == null)
			throw new UnhandledMessageTypeException();
		String[] data = ArrayUtils.stringToArray(dataStr);
		return new Message(handler, data);
	}

	public void close() throws IOException {
		socket.close();
		in.close();
		out.close();
	}

	public String getPeerID() {
		return peerID;
	}

	public int getLocalPort() {
		return socket.getLocalPort();
	}

	public String getLocalIP() {
		return socket.getLocalAddress().getHostAddress();
	}

	public int getRemotePort() {
		return socket.getPort();
	}

	public String getRemoteIP() {
		return socket.getInetAddress().getHostAddress();
	}

	public String getRemotePeerID() {
		return Peer.getID(getRemotePort(), getRemoteIP());
	}

	public static void main(String[] args) {
		try {
			Peer p = new Peer(12345);
			Peer.addHandler(new Ping());
			Peer.addHandler(new Acknowledge());
			p.listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
