package com.github.dvir42.p2p;

import com.github.dvir42.p2p.handlers.Handler;

public class Message {

	private final Handler handler;
	private final String[] data;

	public Message(Handler handler, String[] data) {
		this.handler = handler;
		this.data = data;
	}

	public Message(Handler handler, String data) {
		this.handler = handler;
		this.data = new String[] { data };
	}

	public Handler getHandler() {
		return handler;
	}

	public String[] getData() {
		return data;
	}

}
