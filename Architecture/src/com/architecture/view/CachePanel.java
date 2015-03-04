package com.architecture.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.rmi.CORBA.Util;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.architecture.app.CPU;
import com.architecture.app.Cache;
import com.architecture.util.Config;
import com.architecture.util.Utils;


public class CachePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Color colorUnused = Color.gray;
	private Color colorJustnew = Color.red;
	private int colorDegree = 200/Cache.R_IN_USE_JUST_NOW;
	
	private int startPosX = 70;
	private int startPosY = 70;
	
	private int hoverX = -1;
	private int hoverY = -1;
	
	private int blockWidth = 120 / Config.CACHELINE_CAPACITY;
	private int blockHeight = 160 / Config.CACHELINE_SIZE;
	
	
	private JCheckBox enableCache;
	private JLabel selectedData;
	private String defaultLabelMessage = "Hove on a word to show value";
	private JLabel hitRateInfo;
	
	
	private Color getColorByUseDegree(int use){
		if(use == Cache.R_NOT_UESED)
			return colorUnused;
		if(use == Cache.R_JUST_NEW)
			return colorJustnew;
		return new Color(10,40+use*colorDegree,220-use*colorDegree);
	}
	
	/**
	 * calculate which block of cache is cursor hovering on now
	 * @param x
	 * @param y
	 */
	private void getHoveredCell(int x,int y){
		
		hoverX = (x-startPosX)/blockWidth;
		if(x<=startPosX||hoverX>=Config.CACHELINE_CAPACITY)
			hoverX = -1;
			
		hoverY = (y-startPosY)/blockHeight;
		if(y<=startPosY||hoverY>=Config.CACHELINE_SIZE)
			hoverY = -1;
		
	}
	
	public CachePanel(){
		super();
		
		
		TitledBorder nameTitle =new TitledBorder("Cache"); 	
		this.setBorder(nameTitle);
		
		this.setLayout(null);
		
		enableCache = new JCheckBox("Enable Cache");
		enableCache.setSelected(true);
		enableCache.setBounds(10, 20, 150, 20);
		
		//enableCache.setEnabled(true);
		this.add(enableCache);
		
		hitRateInfo = new JLabel("HitRate Info");
		hitRateInfo.setFont(getFont().deriveFont(10f));
		this.add(hitRateInfo);
		hitRateInfo.setBounds(20, 36, 250, 20);
		
		selectedData = new JLabel(defaultLabelMessage);
		selectedData.setFont(getFont().deriveFont(10f));
		this.add(selectedData);
		selectedData.setBounds(20, 50, 250, 20);
		
		//this.setSize(200, 300);
		
		
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enableCache.addChangeListener(
				new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {
						CPU.getInstance().setCahceEnable(enableCache.isSelected());
					}
					
				});
		
		this.addMouseMotionListener(
				new MouseMotionListener() {
					
					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						getHoveredCell(e.getX(),e.getY());
						if(hoverX>=0&&hoverY>=0){
							setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						}else{
							setCursor(Cursor.getDefaultCursor());
						}
						update();
						//System.out.println(hoverX+" "+hoverY);
						//setCursor(Cursor.HAND_CURSOR);
					}
					
					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int[][]records = Cache.getInstance().getCacheRecord();
		
		for(int i=0;i<Config.CACHELINE_SIZE;i++){
			
			g.setColor(Color.black);
			g.setFont(getFont().deriveFont(9.5f));
			g.drawString("Line "+i, 20, startPosY+(i+1)*blockHeight-2);
			
			for(int j=0;j<Config.CACHELINE_CAPACITY;j++){
				g.setColor(getColorByUseDegree(records[i][j]));
				g.fillRect(startPosX+j*blockWidth, startPosY+i*blockHeight, blockWidth-1, blockHeight-1);
			}
			
			
			
		}
		
		Graphics2D g2d=(Graphics2D)g;
		Stroke stroke=new BasicStroke(2.0f);//设置线宽为3.0
		g2d.setStroke(stroke);
			
		/*
		 * set hovered block selected
		 */
		g2d.setColor(Color.red);
		if(hoverX>=0&&hoverY>=0){
			//g.s
			g2d.drawRect(startPosX+hoverX*blockWidth-1, startPosY+hoverY*blockHeight-1, blockWidth, blockHeight);
		}
		//System.out.println("hi");
		//g.drawLine(0, 0, 100, 100);
		
		
	}
	


	public void update(){
		this.repaint();
		if(hoverX>=0&&hoverY>=0){
			selectedData.setText("L"+hoverY+"W"+hoverX+":"+
					Utils.getStringFromIntArray(Cache.getInstance().getWordByLineAndOffset(hoverY, hoverX).getData()));
		}else{
			selectedData.setText(defaultLabelMessage);
		}
		hitRateInfo.setText(Cache.getInstance().getRateInfo());
		
	}
	
}
