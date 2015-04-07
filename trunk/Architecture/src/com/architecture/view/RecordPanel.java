package com.architecture.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.architecture.util.MachineState;

public class RecordPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535290475627657564L;

	private JTextField fileInput = new JTextField();
	private JTextField fileOutput = new JTextField();

	private JButton changeInput = new JButton("Change");
	private JButton resetInput = new JButton("Reset");
	private JButton changeOutput = new JButton("Change");
	private JButton resetOutput = new JButton("Reset");

	public RecordPanel() {
		GridBagConstraints c;
		int gridx, gridy, gridwidth, gridheight, anchor, fill, ipadx, ipady;
		double weightx, weighty;
		Insets inset;

		GridBagLayout gridbag = new GridBagLayout();

		this.setLayout(gridbag);
		this.setBorder(BorderFactory.createTitledBorder("Record"));
		JLabel INPUT = new JLabel("file IO (DevID:3, Type:INPUT)");
		JLabel OUTPUT = new JLabel("file IO (DevID:4, Type:OUTPUT)");

		// input text
		gridx = 0;
		gridy = 0;
		gridwidth = 1;
		gridheight = 1;
		weightx = 0;
		weighty = 0;
		anchor = GridBagConstraints.CENTER;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(INPUT, c);
		this.add(INPUT);

		// input field
		gridx = 0;
		gridy = 1;
		gridwidth = 2;
		gridheight = 1;
		weightx = 0;
		weighty = 0;
		anchor = GridBagConstraints.CENTER;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		fileInput.setEnabled(true);
		fileInput.setText("./program 2.txt");
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(fileInput, c);
		this.add(fileInput);

		// change for input
		gridx = 0;
		gridy = 2;
		gridwidth = 1;
		gridheight = 1;
		weightx = 1;
		weighty = 1;
		anchor = GridBagConstraints.WEST;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(changeInput, c);
		this.add(changeInput);

		// reset for input
		gridx = 1;
		gridy = 2;
		gridwidth = 1;
		gridheight = 1;
		weightx = 1;
		weighty = 1;
		anchor = GridBagConstraints.WEST;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(resetInput, c);
		this.add(resetInput);

		// output text
		gridx = 0;
		gridy = 3;
		gridwidth = 1;
		gridheight = 1;
		weightx = 0;
		weighty = 0;
		anchor = GridBagConstraints.WEST;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(OUTPUT, c);
		this.add(OUTPUT);

		// output field
		gridx = 0;
		gridy = 4;
		gridwidth = 2;
		gridheight = 1;
		weightx = 0;
		weighty = 0;
		anchor = GridBagConstraints.WEST;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		fileOutput.setEnabled(true);
		fileOutput.setText("./output.txt");
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(fileOutput, c);
		this.add(fileOutput);

		// change for output
		gridx = 0;
		gridy = 5;
		gridwidth = 1;
		gridheight = 1;
		weightx = 1;
		weighty = 1;
		anchor = GridBagConstraints.EAST;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(changeOutput, c);
		this.add(changeOutput);

		// reset for output
		gridx = 1;
		gridy = 5;
		gridwidth = 22;
		gridheight = 1;
		weightx = 1;
		weighty = 1;
		anchor = GridBagConstraints.EAST;
		fill = GridBagConstraints.HORIZONTAL;
		inset = new Insets(0, 0, 0, 0);
		ipadx = 0;
		ipady = 0;
		c = new GridBagConstraints(gridx, gridy, gridwidth, gridheight,
				weightx, weighty, anchor, fill, inset, ipadx, ipady);
		gridbag.setConstraints(resetOutput, c);
		this.add(resetOutput);

		changeInput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(fileInput.getText());
				FileNameExtensionFilter ff = new FileNameExtensionFilter(
						"*.txt", "txt");
				fileChooser.setFileFilter(ff);
				int option = fileChooser.showOpenDialog(null);

				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					MachineState.fileInput = file;
					fileInput.setText(file.getPath());
					// System.out.println(file.getAbsolutePath());
				}

			}

		});
		
		resetInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileInput.setText("./program 2.txt");
				MachineState.fileInput=new File(fileInput.getText());
			}
		});

		changeOutput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser(fileInput.getText());
				FileNameExtensionFilter ff = new FileNameExtensionFilter(
						"*.txt", "txt");
				fileChooser.setFileFilter(ff);
				int option = fileChooser.showOpenDialog(null);
				
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					MachineState.fileOutput = file;
					fileOutput.setText(file.getPath());
					// System.out.println(file.getAbsolutePath());
				}
				
			}
			
		});
		resetOutput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileInput.setText("./output.txt");
				MachineState.fileOutput=new File(fileOutput.getText());
			}
		});
		
	}

}
