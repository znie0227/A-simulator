package com.architecture.view;

import java.awt.Color;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3197282896267217246L;
	
	private RegisterPanel registerPanel;
	private InputPanel inputPanel;
	private MemoryPanel memoryPanel;
	private CachePanel cachePanel;
	private ConsolePanel consolePanel;
	private RecordPanel recordPanel;

	public MainFrame() {
		
		super("Simulator");
		
		
		this.setSize(830,700);
		this.setLocation(200, 120);
		this.setLayout(null);
		this.setResizable(true);
		
		
		registerPanel = new RegisterPanel();
		
		registerPanel.setBounds(5, 0, 300, 250);
		this.add(registerPanel);
		this.setVisible(true);
		
		inputPanel=new InputPanel();
		
		inputPanel.setBounds(305,0,300,250);
		this.add(inputPanel);
		this.setVisible(true);
		
		memoryPanel=new MemoryPanel();
		
		
		memoryPanel.setBounds(5,250,280,200);		
		//memoryPanel.setLayout(null);
		this.add(memoryPanel);
		this.setVisible(true);
		
		cachePanel=new CachePanel();
		this.add(cachePanel);
		this.setVisible(true);
		
		consolePanel=new ConsolePanel();
		this.add(consolePanel);
		this.setVisible(true);
		
		recordPanel=new RecordPanel();
		this.add(recordPanel);
		
	}
	
	
	public static void main(String args[]) {
		MainFrame mf = new MainFrame();
		System.out.println("sdf");
	}
}
