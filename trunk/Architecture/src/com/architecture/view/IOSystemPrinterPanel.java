package com.architecture.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.architecture.app.CPU;
import com.architecture.thread.InputFromDeviceThread;

public class IOSystemPrinterPanel extends JPanel {

	private static final long serialVersionUID = -3819691367521335747L;

	private boolean flashing = false;
	private int degree = 0;
	private int increase = 1;
	private int MAX = 20;

	private static IOSystemPrinterPanel ioSystemPrinterPanel;

	private Thread runningThread;

	private static JTextArea outputField = new JTextArea();

	private JCheckBox linkToKeyboard = new JCheckBox("Link To Keyboard");
	
	public static boolean input=false;

	public IOSystemPrinterPanel() {
		ioSystemPrinterPanel=this;
		this.setLayout(null);
		// this.setBounds(608,0,210,250);
		this.setBorder(BorderFactory.createTitledBorder("I/O System Device"));

		outputField.setLayout(null);
		// outputField.setBounds(620,20,200,100);
		outputField.setVisible(true);
		outputField.setSize(180, 150);
		outputField.setLocation(10, 82);
		outputField.setLineWrap(true);
		outputField.setEditable(false);
		outputField.setBackground(Color.BLACK);
		outputField.setForeground(Color.WHITE);
		linkToKeyboard.setVisible(true);
		linkToKeyboard.setLocation(10, 50);
		linkToKeyboard.setSize(180, 20);
		this.add(outputField);
		this.add(linkToKeyboard);
		this.setVisible(true);

		linkToKeyboard.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int value = (int) e.getKeyChar();
				if (value > 0 && value < 128) {
					System.out.println((char)value);
					input=true;
					InputFromDeviceThread.setKeyEvent(value);
					CPU.keyEvent=value;
					synchronized (CPU.currentThread) {
					
					CPU.currentThread.notify();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void startFlash() {
		flashing = true;

		if (runningThread == null || !runningThread.isAlive()) {
			runningThread = new Thread() {
				@Override
				public void run() {
					while (flashing) {
//						IOSystemPrinterPanel.this.repaint();
						try {
							Thread.sleep(50);
							degree += increase;
							if (degree >= MAX)
								increase = -1;
							if (degree <= 0)
								increase = 1;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						paintComponent(IOSystemPrinterPanel.getInstance().linkToKeyboard.getGraphics());
					}
					runningThread = null;
//					ioSystemPrinterPanel.linkToKeyboard.setBackground(
//							new Color(20 + 8 * degree, 150 + 5 * degree,
//							20 + 5 * degree)
//							);
				}
			};
			runningThread.start();
		}
	}

	public void stopFlash() {
		flashing = false;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		g=this.linkToKeyboard.getGraphics();
		// Dimension size = this.getSize();
		if (flashing) {
			g.setColor(new Color(20 + 8 * degree, 150 + 5 * degree,
					20 + 5 * degree));
//			g.fillRoundRect(5, 17, 200, 175, 4, 4);
//			g.setColor(Color.gray);
//			g.drawRoundRect(5, 17, 200, 175, 6, 6);
		}

//		super.paintComponent(g);
		this.paintComponents(g);
	}


	public static IOSystemPrinterPanel getInstance() {
		if (ioSystemPrinterPanel == null) {
			ioSystemPrinterPanel = new IOSystemPrinterPanel();
		}
		return ioSystemPrinterPanel;
	}
	
	public JCheckBox getLinkToKeyboardBox(){
		return this.linkToKeyboard; 
	}
	
	public static void writeToPrinter(String msg){
		outputField.setText(outputField.getText()+msg);
	}
	
	public static void reset(){
		outputField.setText("");
	}

	
}
