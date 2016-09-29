package fikoun.firework.core.fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
public class Particle
{
	//	ATRIBUTY X,Y RYCHLOST X,Y,GRAVYTACE,SÍLA
	public double x,y,Vx,Vy,gravity,power;
	public int size;
	public Color color;
	public Random r = new Random();
	
	//	KONSTRUKTOR
	public Particle(int X, int Y,double VX, double VY, double power,Color c,int size)
	{
		color = c;
		x = X;
		y = Y;
		this.power= (r.nextFloat()+0.5f)*power ;
		gravity = power/100;
		Vx = VX;
		Vy = VY;
		this.size = size;
	}
	
	//	KONSTRUKTOR
	public void render(Graphics g)
	{
		if(x > 455 && x < 1900 && y > 0 && y < 1000){
			g.setColor(color);
			g.fillRect((int)x-size/2, (int)y-size/2, size, size);
		}
	}
	
	// STMAVÝ BARVU KTERA JE DANÁ O URČITOU HODNOTU
	public Color darker(Color c,int dec)
	{
		int b = c.getBlue();
		int r = c.getRed();
		int g = c.getGreen();
		b -= dec;
		r -= dec;
		g -= dec;
		if(b < 0)
			b = 0;
		if(r < 0)
			r = 0;
		if(g < 0)
			g = 0;
		return new Color(r,g,b);
	}
	
	//	OBNOVA
	public void update()
	{
		x += Vx*power;
		y -= Vy*power;
		power-=power/100;
		color = darker(color, 3);
	}

}
