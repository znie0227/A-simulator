package com.architecture.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MemoryPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2060086986375643605L;

	public MemoryPanel(){
		this.setLayout(new FlowLayout(FlowLayout.LEFT,23,10));
		
		this.setBorder(BorderFactory.createTitledBorder("Memory"));
		AbstractButton memoryPanel;			
        JLabel addr=new JLabel("Addr");
        JLabel data=new JLabel("Data");
        JTextField ADDRField = new JTextField();
        JTextField DATAField=new JTextField();
		TextField inputField2=new TextField();
		TextField inputField3=new TextField();
		JButton query=new JButton("Query");
		JButton load=new JButton("Load");	             
        
        addr.setSize(10,25);
        addr.setLocation(0,280);
        
        data.setSize(40,25);
        data.setLocation(8,320);  
        
		ADDRField.setEnabled(false);
		ADDRField.setText("000000000000000000");
		ADDRField.setBounds(20, 280, 140, 28);      		  
		ADDRField.setEditable(false);
		//ADDRField.setColumns(20);
		
		
		DATAField.setEnabled(false);
		DATAField.setText("000000000000000000");
		DATAField.setBounds(50, 320, 130, 28);      		  
		DATAField.setEditable(false);
		
		
		inputField2.setSize(10,25);
		inputField2.setLocation(200,282);
		inputField3.setSize(40,25);
		inputField3.setLocation(200,322);
		query.setSize(68,23);
		query.setLocation(17,370);
		load.setSize(68,23);
		load.setLocation(110,369);
        this.add(addr);        
        this.add(ADDRField);
        this.add(inputField2);
        this.add(data);
        this.add(DATAField);        
        this.add(inputField3);
        this.add(query);
        this.add(load);
        this.setVisible(true);
	}

}
