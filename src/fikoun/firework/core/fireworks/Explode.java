package fikoun.firework.core.fireworks;

import java.awt.Graphics;
import java.util.ArrayList;

import fikoun.firework.core.objectContainer;

public class Explode extends Firework
{
	//	ATRYBUTY VELIKOST A SÍLA
	public double size,power;
	
	//	KONSRUKTOR
	public Explode(int X, int Y, int[] i, int w, int h,int id) {
		super(X, Y, i, w, h,id);
		
		power = objectContainer.eButtons.get(0).power;
		size = objectContainer.eButtons.get(0).size;
		
	}
	

	//	VYKRESLENÍ
	public void render(Graphics g)
	{
		super.render(g);
		
	}
	
	//	DRUH EXPLOZE ROZDELÝ HODNOTY ČÁSTICÍM A BARVY ROZHÁZÍ NÁHODNĚ
	public void explode()
	{
		//BARVY
		ArrayList<Integer> colorsI = new ArrayList<Integer>();
		ArrayList<Integer> colorsMixed = new ArrayList<Integer>();
		for(int i = 0; i < colorsV.length; i++)
		{
			if(colorsV[i] > 0){
				for(int e = 0; e < colorsV[i]; e++)
				{
					colorsI.add(i);
				}	
			}
		}
		while(colorsI.size() > 0)
		{
			int rand = r.nextInt(colorsI.size())-1;
			if(rand < 0)
				rand = 0;
			colorsMixed.add(colorsI.get(rand));
			colorsI.remove(rand);
		}
		
		//ČÁSTICE
		for(int i = 0; i < particles.length; i++)
		{		
			double vx = (r.nextFloat()-0.5F);
			double vy = (r.nextFloat()-0.3F);
			particles[i] = new Particle(Ex,Ey,vx,vy,power,colors[colorsMixed.get(i)],(int) size);
		}
	}
}
