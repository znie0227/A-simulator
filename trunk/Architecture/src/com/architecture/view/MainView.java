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

              f.setLayout(null);  //设置窗体布局为空布局

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
              p1.setBackground(Color.BLUE);
              //p11.setSize(250,200);
             // p11.setLocation(25, 25);
             //p11.setBackground(Color.GRAY);
             p1.setBorder(new TitledBorder("Register"));
            // p1.add(p11);
              
//              p2.setSize(300,250);
//              p2.setBackground(Color.RED);
//              p2.setLocation(300, 0);
//              
//              
//              p3.setSize(250,200);
//              p3.setBackground(Color.GREEN);
//              p3.setLocation(0, 250);
//              
//              p4.setSize(350,200);
//              p4.setBackground(Color.YELLOW);
//              p4.setLocation(250, 250);
//              
//              p5.setSize(200,700);
//              p5.setBackground(Color.ORANGE);
//              p5.setLocation(600,0);
//              
              f.getContentPane().add(p1); 
//              f.getContentPane().add(p2);
//              f.getContentPane().add(p3);
//              f.getContentPane().add(p4);
//              f.getContentPane().add(p5);
             

     }


	
	

}
