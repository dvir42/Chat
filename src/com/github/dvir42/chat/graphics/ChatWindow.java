package com.github.dvir42.chat.graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.github.dvir42.chat.users.ChatHistory;
import com.github.dvir42.chat.users.User;

public class ChatWindow extends JPanel {

	private static final long serialVersionUID = 2800344890499501800L;

	private final JTextField input;
	private final JTextPane chatHistory;
	private final ChatHistory history;

	public ChatWindow(final User me, final User you) {
		history = new ChatHistory(me, you);
		JFrame frame = new JFrame("chat");
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this);
		input = new JTextField();
		chatHistory = new JTextPane();
		chatHistory.setEditable(false);
		add(input);
		add(chatHistory);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					me.send(you, input.getText());
					input.setText("");
				}
			}
		});
		new Thread(new Runnable() {
			@Override
			public void run() {
				chatHistory.setText(history.getChatHistory());
			}
		}).start();
	}

}
