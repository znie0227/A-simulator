package com.architecture.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MainView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4695502668841167438L;
	public static void main(String[] args)throws Exception

    {   JFrame f=new JFrame("Simulator");                   

        f.setSize(800,700);         

         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

           f.setVisible(true);         

           f.setResizable(false);

             f.setLayout(null);  

             JPanel p1=new JPanel(); 
             p1.setLayout(null);//Register Panel
             JButton R0=new JButton("R0");
             JButton R1=new JButton("R1");
             JButton R2=new JButton("R2");
             JButton R3=new JButton("R3");
             JButton X1=new JButton("X1");
             JButton X2=new JButton("X2");
             JButton X3=new JButton("X3");
             JButton MAR=new JButton("MAR");
             JButton MBR=new JButton("MBR");
             
             JPanel p2=new JPanel();         //Input Panel
             p2.setLayout(null);
             JTextArea inputField= new JTextArea();
             inputField.setLayout(null);
             JButton clear=new JButton("Clear");
             JButton PowerOn=new JButton("PowerOn");
             JButton PowerOff=new JButton("PowerOff");
             
             JPanel p3=new JPanel();         //Memory Panel
             
             
             JPanel p4=new JPanel();         //Cache Panel
             
             
             JPanel p5=new JPanel();        // Record Panel
             
             
             JPanel p6=new JPanel();       // Console Panel
             p6.setLayout(null);
             
             
             //components in P1
              p1.setSize(300,250);  
              p1.setBorder(BorderFactory.createTitledBorder("Register"));
              
              R0.setSize(60,25);
              R0.setLocation(10, 50);
              
              R1.setLocation(105,50);
              R1.setSize(60,25);
              
              R2.setSize(60,25);
              R2.setLocation(200,50);
              
              R3.setSize(60,25);
              R3.setLocation(10,90);
              
              X1.setSize(60,25);
              X1.setLocation(105,90);
              
              X2.setSize(60,25);
              X2.setLocation(200,90);
              
              X3.setSize(60,25);
              X3.setLocation(10,130);
              
              MAR.setSize(60,25);
              MAR.setLocation(105,130);
              
              MBR.setSize(60,25);
              MBR.setLocation(200,130);
              
              Label l1 = new Label("java"); //内容根据编程改，这里只是做个例子
              l1.setLocation(10,15);
              l1.setSize(50,25);
              JTextArea display1= new JTextArea();// show content of register
              display1.setSize(75,20);
              display1.setLocation(65,20);
              display1.setEditable(false);
              JTextArea display2=new JTextArea();
              display2.setSize(75,20);
              display2.setLocation(150,20);
              display2.setEditable(false);
              
                          
              p1.add(R0);
              p1.add(R1);
              p1.add(R2);
              p1.add(R3);
              p1.add(X1);
              p1.add(X2);
              p1.add(X3);
              p1.add(MAR);
              p1.add(MBR);
                        
              p1.add(l1);
              p1.add(display1);
              p1.add(display2);
              
              //components in P2
              p2.setSize(300,250);
              p2.setLocation(300, 0);
              
              p2.setBorder(BorderFactory.createTitledBorder("Input"));
              p2.add(inputField);
              inputField.setSize(250, 150);
              inputField.setLocation(22, 82);
              inputField.setLineWrap(true);//multiple lines
              clear.setSize(70,20);
              clear.setLocation(200,50);
              PowerOn.setSize(90,20);
              PowerOn.setLocation(22,20);
              PowerOff.setSize(90,20);
              PowerOff.setLocation(22,50);
              p2.add(clear);
              p2.add(PowerOn);
              p2.add(PowerOff);
              
              p3.setSize(250,200);
              p3.setLocation(0, 250);
             
              p3.setBorder(BorderFactory.createTitledBorder("Memory"));
          
              
              p4.setSize(350,200);
              p4.setLocation(250, 250);
              
              p4.setBorder(BorderFactory.createTitledBorder("Cache"));
              
              
              p5.setSize(200,700);
              p5.setLocation(600,0);
              p5.setBorder(BorderFactory.createTitledBorder("Record"));
            //JComboBox cb = new JComboBox();
              //JScrollPane js=new JScrollPane();//添加滚动条
              //p6.add(cb);
              
              
             // the components in console 
              
              p6.setSize(600,200);
              p6.setLocation(0,450);
              p6.setBorder(BorderFactory.createTitledBorder("Console"));
              JTextArea showResult=new JTextArea();  
              showResult.setEditable(false);
              showResult.setLayout(null);
              showResult.setSize(450,150);
              showResult.setLocation(10,480);
              showResult.setBackground(Color.black);
              f.add(showResult);
              
              JButton clear2=new JButton("Clear");
              clear2.setSize(80,30);
              clear2.setLocation(500,600);
              f.add(clear2);
              
              
              
              
              
               
              
              
              f.getContentPane().add(p1); 
              f.getContentPane().add(p2);
             f.getContentPane().add(p3);
              f.getContentPane().add(p4);
              f.getContentPane().add(p5);
              f.getContentPane().add(p6);
             

     }


	
	

}
