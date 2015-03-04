package com.architecture.view;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class CachePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6882217794241130118L;

	public CachePanel()
	{
		this.setLayout(null);
		AbstractButton cachePanel;
		this.setBounds(305,250,313,200);
		this.setBorder(BorderFactory.createTitledBorder("Cache"));
	}

}
