package com.architecture.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RecordPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7535290475627657564L;
	
	private JTextField FileInput = new JTextField();
	private JTextField FileOutput = new JTextField();
	
	private JButton change = new JButton("Change");
	private JButton reset = new JButton("Reset");
	private JButton change2 = new JButton("Change");
	private JButton reset2 = new JButton("Reset");
	
	
	public RecordPanel()
	{
		GridBagConstraints c;
	    int gridx,gridy,gridwidth,gridheight,anchor,fill,ipadx,ipady;
	    double weightx,weighty;
	    Insets inset;
	    
	    GridBagLayout gridbag=new GridBagLayout();

        this.setLayout(gridbag);
        this.setBorder(BorderFactory.createTitledBorder("Record"));
        JLabel INPUT = new JLabel("file IO (DevID:3, Type:INPUT)");
		JLabel OUTPUT = new JLabel("file IO (DevID:4, Type:OUTPUT)");
		
		//input text
        gridx=0;
        gridy=0;
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
        gridbag.setConstraints(INPUT,c);
        this.add(INPUT);
        
        //input field
        gridx=0;
        gridy=1;
        gridwidth=2;
        gridheight=1;
        weightx=0;
        weighty=0;
        anchor=GridBagConstraints.CENTER;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        FileInput.setEnabled(true);
        FileInput.setText("./devid3.txt");
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(FileInput,c);
        this.add(FileInput);
        
        //change for input
        gridx=0;
        gridy=2;
        gridwidth=1;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.WEST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
                fill,inset,ipadx,ipady);
        gridbag.setConstraints(change,c);
        this.add(change);
        
        //reset for input
        gridx=1;
        gridy=2;
        gridwidth=1;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.WEST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
                fill,inset,ipadx,ipady);
        gridbag.setConstraints(reset,c);
        this.add(reset);
        
        //output text
        gridx=0;
        gridy=3;
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
        gridbag.setConstraints(OUTPUT,c);
        this.add(OUTPUT);
        
        //output field
        gridx=0;
        gridy=4;
        gridwidth=2;
        gridheight=1;
        weightx=0;
        weighty=0;
        anchor=GridBagConstraints.WEST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        FileOutput.setEnabled(true);
        FileOutput.setText("./devid4.txt");
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
           fill,inset,ipadx,ipady);
        gridbag.setConstraints(FileOutput,c);
        this.add(FileOutput);
        
        //change for output
        gridx=0;
        gridy=5;
        gridwidth=1;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.EAST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
                fill,inset,ipadx,ipady);
        gridbag.setConstraints(change2,c);
        this.add(change2);
        
        //reset for output
        gridx=1;
        gridy=5;
        gridwidth=22;
        gridheight=1;
        weightx=1;
        weighty=1;
        anchor=GridBagConstraints.EAST;
        fill=GridBagConstraints.HORIZONTAL;
        inset=new Insets(0,0,0,0);
        ipadx=0;
        ipady=0;
        c=new GridBagConstraints(gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,
                fill,inset,ipadx,ipady);
        gridbag.setConstraints(reset2,c);
        this.add(reset2);
 
		
	}

}
