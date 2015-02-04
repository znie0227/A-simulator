package com.architecture.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3197282896267217246L;
	
	private RegisterPanel registerPanel;

	public MainFrame() {
		
		super("Simulator");
		
		this.setSize(865,686);
		this.setLocation(200, 120);
		this.setLayout(null);
		
		
		registerPanel = new RegisterPanel();
		
		registerPanel.setBounds(5, 0, 480, 200);
		this.add(registerPanel);
		this.setVisible(true);
		
	}
	
	
	public static void main(String args[]) {
		MainFrame mf = new MainFrame();
		System.out.println("sdf");
	}
}
