package fikoun.firework.core.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import fikoun.firework.core.FireworkGame;

public class ColorButton 
{
	//	ATRIBUTY
	public int x = 0;
	public int y = 0;
	public BufferedImage image;
	public Color color;
	public double value = 0;
	public boolean hovered = false;
	public int pressed = 0;
	
	//	KONSTRUKTOR NASTAVENÍ ColorButton
	public ColorButton(int x, int y,Color c, BufferedImage i)
	{
		this.x = x;
		this.y = y;
		image = i;
		color = c;
	}
	
	//	RENDER VYKRESLÍ SEBE JAKO KRUH BARVY(color) NA [x,y]
	public void render(Graphics g)
	{
		((Graphics2D)g).setStroke(new BasicStroke(2));
		g.setColor(color);
		// JE-LI PŘEKRYTÝ VYKRESLÍ PRZDNÝ KRUH S HODNOTOU, JINAK VYKESLÍ PLNÝ KRUCH TEEŽ BARVY.
		if(!hovered)
			g.drawImage(image, x, y, null);
		else
			g.drawArc(x+2, y+2, 35, 35, 0, 360);
		
		//	POSOUVÁ S HODNOTOU BARVY ABY BYLA POŘÁD UPROSTŘED(JSEM UDĚLAL OD OKA) ;)
		if(value < 10)
			g.drawString(((int)value)+"", x+16, y+24);
		else if(value < 100)
			g.drawString(((int)value)+"", x+12, y+24);
		else
			g.drawString(((int)value)+"", x+8, y+24);
		
	}

	// POPOHNUTÍ MÝŠÍ KONTROLA ZDA JE NA TLKAČÍTKU
		public void move(MouseEvent e)
		{
			if(isMouseOver(e.getX(), e.getY()))
				hovered = true;
			else
				hovered = false;
		}

	//	KOONTROLA JE-LI MYŠ UVNITŘ KRUHU (vypočítá délku usečky mezi střede kruhu a bodem kliku, je-li delší jako poloměr vrátí false jinak true)
	public boolean isMouseOver(int mX, int mY)
	{
		int r = image.getWidth()/2;
		int pX = this.x + r;
		int pY = this.y + r;
		
		// a(na duhou) + b(na druhou) = c(na druhou)
		if(r > Math.sqrt(  (mX-pX)*(mX-pX)  +  (mY-pY)*(mY-pY)  ))
			return true;
		
		return false;
	}
	
	
	// PROBĚHNE OBNOVE COLORBUTTONU
	public void update()
	{
		// PŘEVOD Na REÁLNÝ ČAS ZA jeden cyklus se zvetší o hodnotu která je v 60fps +0.5 za cyklus => za třicetinu sekundy se zvětší o 1.
		if(pressed == 1 && hovered)
			value+=(30.0/(double)FireworkGame.fps);
		else if(pressed == 3 && hovered)
			value-=(30.0/(double)FireworkGame.fps);
		
		// KONTROLA NENÍ-LI VĚTŠI JA 100 NEBO MENŠÍ JAK 0
		if(value < 0)
			value = 0;
		if(value > 100)
			value = 100;			
	}
	

}
