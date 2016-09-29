package fikoun.firework.core.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameButton
{
	//	ATRIBUTY
	public int x,y,width,height;
	public boolean hovered = false;
	public BufferedImage img, hoverImg;
	
	//	KONTRUKTOR
	public GameButton(int X, int Y, BufferedImage i, BufferedImage hI)
	{
		x= X;
		y = Y;
		width = i.getWidth();
		height = i.getHeight();
		img = i;
		hoverImg = hI;
	}
	
	//	VYKRESLENÍ BUTTONU HOVERED/NONHOVERED
	public void render(Graphics g)
	{
		((Graphics2D)g).setStroke(new BasicStroke(3));
		if(!hovered){
			g.setColor(Color.GRAY);
			g.drawImage(img, x,y,null);
			g.drawRect(x-3, y-2, width+2, height+2);
		}else{
			g.setColor(Color.CYAN);
			g.drawImage(hoverImg, x,y,null);
			g.drawRect(x-3, y-2, width+2, height+2);
		}
			
			
	}
	
	public void update()
	{
		
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
	
	// POKLIKNUTÍ NA OBRAZOVKU JE-LI MYŠ NA TLAČITKU ZAVOLÁ clickAction(e)
	public void click(MouseEvent e)
	{
		if(hovered){
			clickAction(e.getButton());
			hovered = false;
		}
	}
	
	public void clickAction(int butt){}

	public void release() {}

}
