package fikoun.firework.core.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class MenuButton
{
	//		~~ATRIBUTY~~
	//	souradnice,velikost
	public int x, y,width,height, xMargin;
	//	je překryty myší????
	public boolean hovered = false;
	//	text tlačitka
	public String label;
	//	styl barev	(0> OHRANIČENÍ, 1> VYPLŇ, JE-LI PŘEKRYTÝ)
	public Color[] coloring = new Color[3];
	
	
	//	KONSTRUKTOR
	public MenuButton(int x, int y,int xM,int width, int height, String label, Color[] colors)
	{
		this.x = x; this.y = y; xMargin = xM;
		this.width = width; this.height = height;
		this.label = label;
		coloring = colors;
	}
	
	
	//	VYKRELENÍ
	public void render(Graphics g)
	{
		//	NATAVENÍ TLOUŠŤKY OKRAJE A FONTU PRO VYKRESLENÍ BUTTONU
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.setFont(new Font ("Verdana", Font.BOLD , 25));
		//	NENÍ-LI PŘEKRYTÝ MYŠÍ --ZÁKLADNÍ VYKRESLENÍ--
		if(!hovered){
			g.setColor(coloring[1]);
			g.fillRect(x, y, width, height);
			g.setColor(coloring[0]);
			g.drawRect(x, y, width, height);
			g.setColor(coloring[0]);
			g.drawString(label, x+xMargin, y+34);	
		}
		//	JE-LI PŘEKRYT MYŠÍ --VYKRESLENÍ PŘERKRYTÉHO--
		else{
			g.setColor(coloring[2]);
			g.fillRect(x, y, width, height);
			g.setColor(coloring[1]);
			g.drawString(label, x+xMargin, y+34);						
		}
		
	}
	
	
	//	ZAVOLÁN POKUD SE POHNE MYŠÍ NA ORAZOVCE
	public void move(MouseEvent e)
	{
		int mX = e.getX();
		int mY = e.getY();
		// POKUD SOUŘADNICE MYŠI JE UVNITŘ OBDELNÍKOVÉHO TLAČITKA ZMĚNÍ hovered=true
		if(mX > x && mX < x+width && mY > y && mY < y+height)
			hovered= true;
		else
			// OPAK PŘEDCHOZÍHO KOMENTÁŘE
			hovered = false;
	}
	
	public void clickAction(){}
	
	// POKLIKNUTÍ NA OBRAZOVKU JE-LI MYŠ NA TLAČITKU ZAVOLÁ clickAction(e) JEJÍŽ AKCE JE URČENA V Menu.java
	public void click(MouseEvent e){
		if(hovered && e.getButton() == 1){
			clickAction();
			hovered = false;
		}
	}

}













