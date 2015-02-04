package com.architecture.view;

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

	private JButton save = new JButton("Save");

	private JTextField display1 = new JTextField();// show content of register
	private JTextField display2 = new JTextField();

	private String currentReg;

	public RegisterPanel() {

		this.setLayout(null);

		// components in P1
		this.setSize(300, 250);
		this.setBorder(BorderFactory.createTitledBorder("Register"));

		R0.setSize(60, 25);
		R0.setLocation(8, 55);

		R1.setLocation(105, 55);
		R1.setSize(60, 25);

		R2.setSize(60, 25);
		R2.setLocation(200, 55);

		R3.setSize(60, 25);
		R3.setLocation(8, 90);

		X1.setSize(60, 25);
		X1.setLocation(105, 90);

		X2.setSize(60, 25);
		X2.setLocation(200, 90);

		X3.setSize(60, 25);
		X3.setLocation(8, 130);

		MAR.setSize(60, 25);
		MAR.setLocation(105, 130);

		MDR.setSize(60, 25);
		MDR.setLocation(200, 130);

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
						&& !display2.getText().equals("")
						&& currentReg != null) {

					update(display1, Utils.getBinaryFromDecInString(Integer
							.valueOf(display2.getText()), Application
							.getRegisterByName(currentReg).getSize()));
					System.out.println(Utils.getBinaryFromDecInString(
							Integer.valueOf(display2.getText()),
							Application.getRegisterByName(currentReg)
									.getSize()));
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

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currentReg != null) {
					Application.getRegisterByName(currentReg).setDataByDec(
							Integer.valueOf(display2.getText()));
				} else {
					System.out.println("Select a register first.");
				}
				System.out.println("save");
			}
		});

	}

	private void addListener(JButton btn, String name) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentReg = name;
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
