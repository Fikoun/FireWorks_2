package fikoun.firework.core.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class ColorBar
{
	//	ATRIBUTY
	public int x = 0;
	public int y = 0;
	public int colorSum;
	public Random r = new Random();
	
	
	
	//	KONSTRUKTOR NASTAVENÍ ColorBar
	public ColorBar(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	//	RENDER VYKRESLÍ SEBE JAKO PRUH S BARVAMY
	public void render(Graphics g, ArrayList<ColorButton> b)
	{
		// VYKRESLÍ KAŽDOU BARVU JAKO ČÁSTEČKU DLOUHOU 4PX A OHRANIČENÍ
		int y = this.y+401;
		for(int i = 0; i < b.size();i++)
		{
			if(b.get(i).value > 0.9)
			{
				
				for(int e = 0; e < ((int)b.get(i).value);e++)
				{
					g.setColor(b.get(i).color);
					g.fillRect(x+1, y-3, 18, 5);
					y-=4;
				}
			}
		}
		
		// OHRANÍČENÍ
		((Graphics2D)g).setStroke(new BasicStroke(2));
		g.setColor(Color.WHITE);
		g.drawRect(this.x, this.y+1, 19, 403);
		g.drawString(colorSum+"", this.x-3, this.y-7);
	}
	
	//	OBNOVÝ A SKONTROLUJE CELKOVÝ OBSAH BAREV
	public ArrayList<ColorButton> update(ArrayList<ColorButton> b)
	{
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		/*
		 * 	VYTVOŘÍ POLE INDEXŮ TĚCH BAREV KTERÉ MAJÍ NĚJAKOU HODNOTU VYŠŠÍ JAK 0 V TOMTO POLI POTÉ ,POKUD JE CELKOVÝ SOUČET
		 * 	HODNOT BAREV VĚTŠÍ JAK 100, VYBERE NÁHONÝ INDEX A OD NĚJ UBERE 1 V HODNOTĚ, OPAKUJE DOKUD HODNOTA NENÍ NIŽŠI NEBO ROVNA 100.
		 * 	souhrn: jsem moc líný udělat to složitěji , ale lépe. I na kometáře neberu dvakrát ohled tím myslím že je moc nekontroju jakko třeba pravopis
		 * 	a tak ale to přece nepaří do téhle části kódu ;P
		*/
		colorSum = 0;
		for(int i = 0; i < b.size(); i++){
			colorSum += b.get(i).value;
		}
		while(colorSum > 100)
		{		
			ArrayList<Integer> valueC = new ArrayList<Integer>();
			int i = 0;
			for(i = 0; i < b.size();i++)
			{
				if(b.get(i).value != 0 && b.get(i).hovered == false)
					valueC.add(i);
			}
			
			if(valueC.size() != 1)
				i = r.nextInt(valueC.size()-1);
			else
				i = 0;
			b.get(valueC.get(i)).value--;
			colorSum--;
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		}
		// ;)
		return b;
	}

	
}
