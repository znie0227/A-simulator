package com.architecture.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
	private JButton IPL = new JButton("IPL");
	private JButton Power = new JButton("PowerOn");

	private JButton singleStep = new JButton("Single Step");
	
	private List<String> instrList=new ArrayList<String>();

	private static boolean isPowerOn = false;

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
		IPL.setSize(110, 20);
		IPL.setLocation(22, 20);
		IPL.setEnabled(false);
		Power.setSize(110, 20);
		Power.setLocation(22, 50);
		singleStep.setSize(110, 20);
		singleStep.setLocation(150, 20);
		singleStep.setEnabled(false);
		this.add(clear);
		this.add(IPL);
		this.add(Power);
		this.add(singleStep);

		this.setVisible(true);

		singleStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int PCValue = Application.getRegisterByName("PC").getDecData();
				// push instruction into memory which PC point to
				String instruction = Utils.getCodeFromInstr(inputField
						.getText());
				// modified by BaoBao, use new location: Octal
				// 10+program.length+Octal 10
				// TODO 不应该在这里设置 待改
//				Application.getRegisterByName("PC").setDataByDec(17);
//				Memory.getInstance().write(17,
//						Utils.getIntArrayFromString(instruction));
				CPU.getInstance().execute();
			}
		});

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputField.setText("");
			}
		});

		Power.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (isPowerOn) {
					isPowerOn = false;
					Power.setText("Power On");
					Power.setBackground(null);
					IPL.setEnabled(false);
					singleStep.setEnabled(false);
					Application.reset();
				} else {
					isPowerOn = true;
					Power.setText("Power Off");
					Power.setBackground(Color.GREEN);
					IPL.setEnabled(true);
					singleStep.setEnabled(true);
					instrList.clear();
					String instructions=inputField.getText().trim();
					int lines=0;
					int address;
					int start=0;
					for (int i=0;i<instructions.length();i++) {
						if (instructions.charAt(i)=='\n'){
							instrList.add(instructions.substring(start, i));
							start=i+1;
							lines++;
						}
					}
					// the last \n is trimmed, so plus one
					lines++;
					instrList.add(instructions.substring(start));
					address=8+lines+8;
					Application.getRegisterByName("PC").setDataByDec(address);
					for (String s:instrList) {
						System.out.println(address);
						String instruction = Utils.getCodeFromInstr(s);
						Memory.getInstance().write(address,
								Utils.getIntArrayFromString(instruction));
						address++;
					}
				}
//				Power.validate();
			}
		});
		
		IPL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				instrList.clear();
//				String instructions=inputField.getText().trim();
//				int lines=0;
//				int address;
//				int start=0;
//				for (int i=0;i<instructions.length();i++) {
//					if (instructions.charAt(i)=='\n'){
//						instrList.add(instructions.substring(start, i));
//						start=i+1;
//						lines++;
//					}
//				}
//				// the last \n is trimmed, so plus one
//				lines++;
//				instrList.add(instructions.substring(start));
//				address=8+lines+8;
//				Application.getRegisterByName("PC").setDataByDec(address);
//				for (String s:instrList) {
//					System.out.println(address);
//					String instruction = Utils.getCodeFromInstr(s);
//					Memory.getInstance().write(address,
//							Utils.getIntArrayFromString(instruction));
//					address++;
//				}
				CPU.getInstance().executeComplete(instrList.size());
			}
		});
	}

}
