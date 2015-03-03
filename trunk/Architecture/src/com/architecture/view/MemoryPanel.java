package com.architecture.view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JFrame;
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
		GridBagConstraints c;
	    int gridx,gridy,gridwidth,gridheight,anchor,fill,ipadx,ipady;
	    double weightx,weighty;
	    Insets inset;
	    
	    GridBagLayout gridbag=new GridBagLayout();

        this.setLayout(gridbag);
        this.setBorder(BorderFactory.createTitledBorder("Memory"));
        JLabel addr = new JLabel("Addr");
		JLabel data = new JLabel("Data");
		
        //addr
        gridx=0;
        gridy=0;
        gridwidth=1;
        gridheight=1;
        weightx=0;
        weighty=0;        
        anchor=GridBagConstraints.WEST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(addr,c);
        this.add(addr);
        
        //addr field
        gridx=0;
        gridy=1;
        gridwidth=1;
        gridheight=1;
        weightx=0;
        weighty=0;
        anchor=GridBagConstraints.WEST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
		ADDRField.setEnabled(true);
		ADDRField.setText("00000000000");
		ADDRField.setEditable(false);
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(ADDRField,c);
        this.add(ADDRField);
        
        //addr input
        gridx=1;
        gridy=1;
        gridwidth=1;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.WEST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        inputField2.setText("0000");
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(inputField2,c);
        this.add(inputField2);
        
        //data
        gridx=0;
        gridy=2;
        gridwidth=1;
        gridheight=1;
        weightx=0;
        weighty=0;        
        anchor=GridBagConstraints.CENTER;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(data,c);
        this.add(data);
        
        //data field
        gridx=0;
        gridy=3;
        gridwidth=1;
        gridheight=1;
        weightx=0;
        weighty=0;
        anchor=GridBagConstraints.CENTER;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
		DATAField.setEnabled(true);
		DATAField.setText("0000000000000000");
		DATAField.setEditable(false);
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(DATAField,c);
        this.add(DATAField);
        
        //data input
        gridx=1;
        gridy=3;
        gridwidth=1;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.CENTER;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        inputField3.setText("0000");
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(inputField3,c);
        this.add(inputField3);
        
        //query
        gridx=0;
        gridy=4;
        gridwidth=1;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.CENTER;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
                fill,inset,ipadx,ipady);
        gridbag.setConstraints(query,c);
        this.add(query);
        
        //save
        gridx=1;
        gridy=4;
        gridwidth=1;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.CENTER;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
                fill,inset,ipadx,ipady);
        gridbag.setConstraints(save,c);
        this.add(save);
		


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
//					System.out.println(Utils.getBinaryFromDecInString(
//							Integer.valueOf(inputField2.getText()),
//							Config.NUMBER_OF_BITS_OF_MEMORY_SIZE));
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
