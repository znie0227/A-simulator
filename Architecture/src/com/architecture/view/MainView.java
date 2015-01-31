package com.architecture.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

             JPanel p1=new JPanel();         //Register Panel
             JPanel p11=new JPanel();

             JPanel p2=new JPanel();         //Input Panel
             JPanel p21=new JPanel();
             
             JPanel p3=new JPanel();         //Memory Panel
             JPanel p31=new JPanel();
             
             JPanel p4=new JPanel();         //Cache Panel
             JPanel p41=new JPanel();
             
             JPanel p5=new JPanel();        // Console Panel
             JPanel p51=new JPanel();
             
             JPanel p6=new JPanel();       // Record Panel
             JPanel p61=new JPanel();
             
              p1.setSize(300,250);            
              p11.setSize(250,200);
              p11.setLocation(25, 25);
              p11.setBorder(BorderFactory.createTitledBorder("Register"));
              p1.add(p11);
              
              p2.setSize(300,250);
              p2.setLocation(300, 0);
              p21.setSize(250,200);
              p21.setLocation(325, 25);
              p21.setBorder(BorderFactory.createTitledBorder("Input"));
              p2.add(p21);
              
              
              p3.setSize(250,200);
              p3.setLocation(0, 250);
              p31.setSize(200,150);
              p31.setLocation(25, 275);
              p31.setBorder(BorderFactory.createTitledBorder("Memory"));
              p3.add(p31);
              
              p4.setSize(350,200);
              p4.setLocation(250, 250);
              p41.setSize(300,150);
              p41.setLocation(275, 275);
              p41.setBorder(BorderFactory.createTitledBorder("Cache"));
              p4.add(p41);
              
              p5.setSize(200,700);
              p5.setLocation(600,0);
              p51.setSize(150,650);
              p51.setLocation(625, 25);
              p51.setBorder(BorderFactory.createTitledBorder("Console"));
              p5.add(p51);
              
              f.getContentPane().add(p1); 
              f.getContentPane().add(p2);
              f.getContentPane().add(p3);
              f.getContentPane().add(p4);
              f.getContentPane().add(p5);
             

     }


	
	

}
