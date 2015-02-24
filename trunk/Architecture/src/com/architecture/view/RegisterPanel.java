package com.architecture.view;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.architecture.app.Application;
import com.architecture.util.Utils;

public class RegisterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3571459367666941550L;

	private JButton R0 = new JButton("R0");
	private JButton R1 = new JButton("R1");
	private JButton R2 = new JButton("R2");
	private JButton R3 = new JButton("R3");
	private JButton X1 = new JButton("X1");
	private JButton X2 = new JButton("X2");
	private JButton X3 = new JButton("X3");
	private JButton MAR = new JButton("MAR");
	private JButton MDR = new JButton("MDR");
	private JButton PC = new JButton("PC");
	private JButton CC = new JButton("CC");
	private JButton EA = new JButton("EA");
	private JButton IR_R = new JButton("IR_R");
	private JButton IR_X =new JButton("IR_X");
	private JButton IR_I =new JButton("IR_I");
	private JButton AL =new JButton("AL");
	private JButton FR_0 =new JButton("FR_0");
	private JButton FR_1 =new JButton("Fr_1");
	
	private JButton save = new JButton("Save");

	private JTextField display1 = new JTextField();// show content of register
	private JTextField display2 = new JTextField();

	private String currentReg = "R0";
	private JButton currentBtn = R0;

	public RegisterPanel() {

		this.setLayout(null);

		// components in P1
		this.setSize(300, 250);
		this.setBorder(BorderFactory.createTitledBorder("Register"));

		R0.setSize(40, 20);
		R0.setLocation(25, 55);
		// initial background of R0
		R0.setBackground(Color.GREEN);

		R1.setLocation(90, 55);
		R1.setSize(40, 20);

		R2.setSize(40, 20);
		R2.setLocation(155, 55);

		R3.setSize(40, 20);
		R3.setLocation(220, 55);

		X1.setSize(40, 20);
		X1.setLocation(25, 90);

		X2.setSize(40, 20);
		X2.setLocation(90, 90);

		X3.setSize(40, 20);
		X3.setLocation(155, 90);

		MAR.setSize(40, 20);
		MAR.setLocation(220, 90);

		MDR.setSize(40, 20);
		MDR.setLocation(25, 130);

		PC.setSize(40, 20);
		PC.setLocation(90, 130);
		
		CC.setSize(40, 20);
		CC.setLocation(155, 130);
		
		EA.setSize(40, 20);
		EA.setLocation(220, 130);
		
		IR_R.setSize(40, 20);
		IR_R.setLocation(25, 170);
		
		IR_X.setSize(40, 20);
		IR_X.setLocation(90, 170);
		
		IR_I.setSize(40, 20);
		IR_I.setLocation(155, 170);
		
		AL.setSize(40, 20);
		AL.setLocation(220, 170);
		
		FR_0.setSize(40, 20);
		FR_0.setLocation(25, 210);
		
		FR_1.setSize(40, 20);
		FR_1.setLocation(90, 210);

		Label l1 = new Label("Value"); // 内容根据编程改，这里只是做个例子
		l1.setLocation(10, 15);
		l1.setSize(35, 25);

		display1.setSize(120, 20);
		display1.setLocation(55, 20);
		display1.setText("0000000000000000");
		display1.setEnabled(false);
		// limit to number only
		display1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume();
				}
			}
		});

		display2.setSize(40, 20);
		display2.setLocation(180, 20);
		display2.setText("0");
		// limit to number only
		display2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (display2.getText() != null
						&& !display2.getText().equals("") && currentReg != null) {

					update(display1, Utils.getBinaryFromDecInString(Integer
							.valueOf(display2.getText()), Application
							.getRegisterByName(currentReg).getSize()));
				}
				super.keyReleased(e);
			}

		});

		save.setSize(65, 25);
		save.setLocation(225, 20);

		this.add(l1);
		this.add(display1);
		this.add(display2);

		this.add(R0);
		this.add(R1);
		this.add(R2);
		this.add(R3);
		this.add(X1);
		this.add(X2);
		this.add(X3);
		this.add(MAR);
		this.add(MDR);
		this.add(save);
		this.add(PC);
		this.add(CC);
		this.add(EA);
		this.add(IR_R);
		this.add(IR_X);
		this.add(IR_I);
		this.add(AL);
		this.add(FR_0);
		this.add(FR_1);

		
		addListener();

		this.setVisible(true);
	}

	private void addListener() {
		addListener(R0, "R0");
		addListener(R1, "R1");
		addListener(R2, "R2");
		addListener(R3, "R3");
		addListener(X1, "X1");
		addListener(X2, "X2");
		addListener(X3, "X3");
		addListener(MAR, "MAR");
		addListener(MDR, "MDR");
		addListener(PC, "PC");

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currentReg != null) {
					Application.getRegisterByName(currentReg).setDataByDec(
							Integer.valueOf(display2.getText()));
				} else {
					System.out.println("Select a register first.");
				}
			}
		});

	}

	private void addListener(JButton btn, String name) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (btn != currentBtn) {
					btn.setBackground(Color.GREEN);
					currentBtn.setBackground(null);
					currentReg = name;
					currentBtn = btn;
				}
				update(display1, Application.getRegisterByName(name)
						.getDataInString());
				update(display2, Application.getRegisterByName(name)
						.getDecData() + "");

			}

		});
	}

	public void update(JTextField field, String text) {
		field.setText(text);
		field.updateUI();
	}
}
