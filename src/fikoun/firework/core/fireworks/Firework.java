package fikoun.firework.core.fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import fikoun.firework.core.FireworkGame;

public class Firework
{
	//	ATRIBUTY FIREWORK 
	//	EXPLOSION CORDS, CORDS, WIDTH,HEIGHT
	public int Ex, Ey,x,y,width,height;
	//	BARVY
	public int [] colorsV;
	//	VYBOUCHNUL?
	public boolean exploded = false;
	//	POLE ČÁSTEČEK
	public Particle[] particles;
	//	ID STŘELY
	public int id = 0;
	//	NÁHODA
	public Random r = new Random();
	public int size = 1;
	
	// BARVY ROVNAJÍCÍ SE TĚM V OBJECTCONTAINER VE SETEJNÉM POŘADÍ
	public Color[] colors = {	new Color(255,0,0),new Color(0,255,0),
								new Color(0,0,255),new Color(249,244,190),
								new Color(204,255,0),new Color(102,0,204),
								new Color(255,153,0),new Color(0,255,255),
								new Color(255,255,0),new Color(255,0,255)};
	
	//	KONSTRUKTOR
	public Firework(int X, int Y, int[] i,int w,int h,int id)
	{
		Ex = X;
		Ey = Y;
		this.id = id;
		width = w;
		height = h;
		colorsV = i;
		
		//	POZICE X SE ROVNÁ POZICI KLIKNUTÉ X. POZICE Y SE ROVNÁ VÝŠCE OKNA=> ŠTŘELA ZAČÍNÁ OD SPODA
		x = X;
		y = h;

		//	spočítá hodnotu všech barev a uřčí ji jako velikost pole částic
		int s = 0;
		for(int r : i)
			s+=r;
		particles = new Particle[s];
	}
	
	//	VYKRESLENÍ
	public void render(Graphics g)
	{
		// VYBOUCHNUL-LI VYKRESLENÍ ČÁSTIC
		if(exploded){
		for(Particle p : particles)
			p.render(g);
		}else{
			//	JINAK VYKRESLÍ STŘELU
			g.setColor(Color.red);
			g.fillRect(x-3/2, y-3/2, 3, 3);
		}		
	}
	
	//	OBNOVA
	public void update()
	{
		//	VYBOUCHNUL-LI OBNOVÝ ČÁSTICE
		// JINAK POSUNE STŘELU
		if(!exploded)
			y-=900/FireworkGame.fps;
		else{
			for(Particle p : particles)
				p.update();
		}
		// POKUD JE SOUŘEDNICE STŘELY ROVNA SOUŘADNICI KLIKU VYBOUCHNI
		if(y <= Ey && !exploded){	
			explode();
			exploded = true;
		}
		
	}
	
	// VÝBUCH NASTAVÝ POLE ČÁSTIC A ROZHODÍ BARVY(to zabralo tak půl hodiny)
	public void explode()
	{
		
	}
}



























