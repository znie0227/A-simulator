package com.architecture.view;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InputPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8985452269697439457L;

	private JTextArea inputField = new JTextArea();

	private JButton clear = new JButton("Clear");
	private JButton PowerOn = new JButton("PowerOn");
	private JButton PowerOff = new JButton("PowerOff");

	private JButton singleStep = new JButton("Single Step");

	public InputPanel() {
		this.setLayout(null);
		AbstractButton inputPanel;
		this.setBorder(BorderFactory.createTitledBorder("Input"));

		inputField.setLayout(null);

		this.add(inputField);
		inputField.setSize(250, 150);
		inputField.setLocation(22, 82);
		inputField.setLineWrap(true);// multiple lines
		clear.setSize(70, 20);
		clear.setLocation(150, 50);
		PowerOn.setSize(90, 20);
		PowerOn.setLocation(22, 20);
		PowerOff.setSize(90, 20);
		PowerOff.setLocation(22, 50);
		singleStep.setSize(110, 20);
		singleStep.setLocation(150, 20);
		this.add(clear);
		this.add(PowerOn);
		this.add(PowerOff);
		this.add(singleStep);

		this.setVisible(true);
	}

}
