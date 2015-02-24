package com.architecture.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class RecordPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535290475627657564L;
	public RecordPanel()
	{
		
		this.setLayout(null);
		this.setBounds(608,0,200,650);
		this.setVisible(true);
		this.setBorder(BorderFactory.createTitledBorder("Record"));
	}

}
