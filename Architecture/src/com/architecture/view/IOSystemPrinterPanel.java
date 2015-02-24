package com.architecture.view;

import java.awt.TextArea;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class IOSystemPrinterPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3819691367521335747L;
	
	private JTextArea outputField = new JTextArea();

	public IOSystemPrinterPanel()
	{
		this.setLayout(null);
		AbstractButton iosystemprinterPanel;
		this.setBounds(608,0,210,250);		
		this.setBorder(BorderFactory.createTitledBorder("I/OSystemPrinter "));
		
		outputField.setLayout(null);
		outputField.setBounds(620,20,200,100);		
		outputField.setVisible(true);
		this.add(outputField);
		this.setVisible(true);
	}

}
