package com.architecture.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsolePanel extends JPanel {
	private static TextArea showResult = new TextArea();
	private JButton clear2 = new JButton("Clear");

	public ConsolePanel()

	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));// 只有
																// flowLayout可以显示控件！！！
		AbstractButton consolePanel;
		this.setBounds(5, 450, 600, 200);
		this.setBorder(BorderFactory.createTitledBorder("Console"));
		showResult.setEditable(false);
		showResult.setSize(400, 80);
		showResult.setLocation(10, 500);
		showResult.setBackground(Color.BLACK);
		showResult.setForeground(Color.GREEN);

		clear2.setSize(80, 30);
		clear2.setLocation(34, 600);
		this.add(showResult);
		this.add(clear2);
		this.setVisible(true);

		clear2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showResult.setText("");
			}
		});
	}

	public static void writeToConsole(String msg) {
		if (showResult.getText() != null && !showResult.getText().equals("")) {
			showResult.setText(showResult.getText() + "\n" + msg);
		} else {
			showResult.setText(msg);
		}
	}

}
