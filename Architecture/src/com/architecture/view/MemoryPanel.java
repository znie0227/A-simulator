package com.architecture.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.architecture.app.Application;
import com.architecture.model.Memory;
import com.architecture.util.Config;
import com.architecture.util.Utils;

public class MemoryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2060086986375643605L;

	private JTextField ADDRField = new JTextField();
	private JTextField DATAField = new JTextField();

	private JButton query = new JButton("Query");
	private JButton save = new JButton("Save");

	private JTextField inputField2 = new JTextField();
	private JTextField inputField3 = new JTextField();

	public MemoryPanel() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 23, 10));

		this.setBorder(BorderFactory.createTitledBorder("Memory"));
		AbstractButton memoryPanel;
		JLabel addr = new JLabel("Addr");
		JLabel data = new JLabel("Data");

		addr.setSize(10, 25);
		addr.setLocation(0, 280);

		data.setSize(40, 25);
		data.setLocation(8, 320);

		ADDRField.setEnabled(true);
		ADDRField.setText("00000000000           ");
		ADDRField.setBounds(20, 280, 140, 28);
		ADDRField.setEditable(false);
		;

		// ADDRField.setEditable(false);
		// ADDRField.setColumns(20);

		DATAField.setEnabled(true);
		DATAField.setText("0000000000000000");
		DATAField.setBounds(50, 320, 130, 28);
		DATAField.setEditable(false);
		;
		// DATAField.setEditable(false);

		inputField2.setSize(40, 25);
		inputField2.setLocation(200, 282);
		inputField2.setText("0000");
		// inputField2.setBounds(200, 282, 40, 25);
		inputField3.setSize(40, 25);
		inputField3.setLocation(200, 322);
		// inputField3.setBounds(200, 322, 40, 25);
		inputField3.setText("0000");

		inputField2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (inputField2.getText() != null
						&& !inputField2.getText().equals("")) {
					System.out.println(Utils.getBinaryFromDecInString(
							Integer.valueOf(inputField2.getText()),
							Config.NUMBER_OF_BITS_OF_MEMORY_SIZE));
					update(ADDRField, Utils.getBinaryFromDecInString(
							Integer.valueOf(inputField2.getText()),
							Config.NUMBER_OF_BITS_OF_MEMORY_SIZE));
				}
				super.keyReleased(e);
			}
		});
		inputField3.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (inputField3.getText() != null
						&& !inputField3.getText().equals("")) {

					update(DATAField, Utils.getBinaryFromDecInString(
							Integer.valueOf(inputField3.getText()),
							Config.WORD_SIZE));
				}
				super.keyReleased(e);
			}
		});
		query.setSize(68, 23);
		query.setLocation(17, 370);
		save.setSize(68, 23);
		save.setLocation(110, 369);
		this.add(addr);
		this.add(ADDRField);
		this.add(inputField2);
		this.add(data);
		this.add(DATAField);
		this.add(inputField3);
		this.add(query);
		this.add(save);
		this.setVisible(true);

		addListener();
	}

	private void addListener() {
		query.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(Integer.valueOf(inputField2.getText()) >= 0 && Integer
						.valueOf(inputField2.getText()) <= Config.MEMORY_SIZE)) {
					return;
				}
				int data = Memory.getInstance()
						.read(Integer.valueOf(inputField2.getText()))
						.getDataInDec();

				update(inputField3, String.valueOf(data));
				update(DATAField,
						Utils.getBinaryFromDecInString(data, Config.WORD_SIZE));
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO

				Memory.getInstance().write(
						Integer.valueOf(inputField2.getText()),
						Utils.getBinaryFromDec(
								Integer.valueOf(inputField3.getText()),
								Config.WORD_SIZE));
			}
		});
	}

	private void update(JTextField field, String text) {
		field.setText(text);
		field.updateUI();
	}

}
