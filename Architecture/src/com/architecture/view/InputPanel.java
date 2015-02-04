package com.architecture.view;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InputPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8985452269697439457L;

    public InputPanel()
    {
    	this.setLayout(null); 
	    AbstractButton inputPanel;
	    this.setBorder(BorderFactory.createTitledBorder("Input"));
	    
	        
    
    JTextArea inputField= new JTextArea();
    inputField.setLayout(null);
    JButton clear=new JButton("Clear");
    JButton PowerOn=new JButton("PowerOn");
    JButton PowerOff=new JButton("PowerOff");
    
    
    
    this.add(inputField);
    inputField.setSize(250, 150);
    inputField.setLocation(22, 82);
    inputField.setLineWrap(true);//multiple lines
    clear.setSize(70,20);
    clear.setLocation(200,50);
    PowerOn.setSize(90,20);
    PowerOn.setLocation(22,20);
    PowerOff.setSize(90,20);
    PowerOff.setLocation(22,50);
    this.add(clear);
    this.add(PowerOn);
    this.add(PowerOff);
    
    this.setVisible(true);
    }

}
