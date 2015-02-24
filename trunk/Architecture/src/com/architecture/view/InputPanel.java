package com.architecture.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.architecture.app.Application;
import com.architecture.app.CPU;
import com.architecture.model.Memory;
import com.architecture.util.Utils;

public class InputPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8985452269697439457L;

	private JTextArea inputField = new JTextArea();

	private JButton clear = new JButton("Clear");
	private JButton PowerOn = new JButton("IPL");
	private JButton PowerOff = new JButton("PowerOff");

	private JButton singleStep = new JButton("Single Step");

	public InputPanel() {
		this.setLayout(null);
		AbstractButton inputPanel;
		this.setBorder(BorderFactory.createTitledBorder("Console Keyboard"));

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

		singleStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int PCValue = Application.getRegisterByName("PC").getDecData();
				// push instruction into memory which PC point to
				String instruction = Utils.getCodeFromInstr(inputField
						.getText());
						
				Memory.getInstance().write(PCValue,
						Utils.getIntArrayFromString(instruction));
				System.out.println("instruction:"+instruction);
				// for DEBUG use
//				for (int i=0;i<10;i++) {
//					System.out.println("Mem Loc at"+i+":"+Memory.getInstance().read(i).getDataInString());
//				}
				CPU.getInstance().execute();
			}
		});
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inputField.setText("");
			}
		});
	}

}
