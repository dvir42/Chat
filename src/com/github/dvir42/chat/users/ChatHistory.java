package com.github.dvir42.chat.users;

public class ChatHistory {

	private final User me, you;
	private String chatHistory;

	public ChatHistory(User me, User you) {
		this.me = me;
		this.you = you;
		chatHistory = "";
	}

	public String getChatHistory() {
		return chatHistory;
	}

	public void addToChatHistory(String msg) {
		chatHistory += msg + "\n";
	}

	public User getMe() {
		return me;
	}

	public User getYou() {
		return you;
	}

}
