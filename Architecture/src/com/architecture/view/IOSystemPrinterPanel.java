package com.architecture.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextArea;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class IOSystemPrinterPanel extends JPanel{
	
	private static final long serialVersionUID = -3819691367521335747L;
	
	private boolean flashing = false;
	private int degree = 0;
	private int increase = 1;
	private int MAX = 20;
	
	private static IOSystemPrinterPanel ioSystemPrinterPanel;
	
	private Thread runningThread;
	
	private JTextArea outputField = new JTextArea();

	public IOSystemPrinterPanel()
	{
		
		this.setLayout(null);
		AbstractButton iosystemprinterPanel;
//		this.setBounds(608,0,210,250);		
		this.setBorder(BorderFactory.createTitledBorder("I/O SystemPrinter "));
		
		outputField.setLayout(null);
//		outputField.setBounds(620,20,200,100);		
		outputField.setVisible(true);
		outputField.setSize(180, 200);
		outputField.setLocation(10, 32);
		outputField.setLineWrap(true);
		this.add(outputField);
		this.setVisible(true);
	}
	
	public void startFlash(){
		flashing = true;
		
		if(runningThread==null||!runningThread.isAlive()){
			runningThread = new Thread(){
				@Override
				public void run(){
					while(flashing){
						repaint();
						try {
							Thread.sleep(50);
							degree+=increase;
							if(degree>=MAX)
								increase = -1;
							if(degree<=0)
								increase = 1;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					runningThread = null;
				}
			};
			runningThread.start();
		}
	}
	
	public void stopFlash(){
		flashing = false;
		repaint();
	}
	
	
	

	@Override
	protected void paintComponent(Graphics g) {
		
		System.out.println("paint");
		//Dimension size = this.getSize();
		if(flashing){
			g.setColor(new Color(20+8*degree,150+5*degree,20+5*degree));
			g.fillRoundRect(5, 17, 200, 175, 4, 4);
			g.setColor(Color.gray);
			g.drawRoundRect(5, 17, 200, 175, 6, 6);
		}
		
		super.paintComponent(g);
		this.paintComponents(g);
		
	}
	
	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		System.out.println("updateui");
		super.updateUI();
	}

	public static IOSystemPrinterPanel getInstance(){
		if (ioSystemPrinterPanel==null) {
			ioSystemPrinterPanel=new IOSystemPrinterPanel();
		}
		return ioSystemPrinterPanel;
	}

}
