package fikoun.firework.core.fireworks;

import java.util.ArrayList;

import fikoun.firework.core.objectContainer;

public class Angle extends Firework 
{
	//ATRIBUTY
	public double power,angleL,angleR;
	//	KONSTRUKTOR
	public Angle(int X, int Y, int[] i, int w, int h,int id) {
		super(X, Y, i, w, h,id);
		
		power = objectContainer.eButtons.get(2).power;
		size = (int) objectContainer.eButtons.get(2).size;
		angleL = objectContainer.eButtons.get(2).angleL;
		angleR = objectContainer.eButtons.get(2).angleR;
	}
	// DRUH EXPLOZE A ROZHÁZÍ BARVY NAHONÉ HOODNOTY RYCHLOSTÍ PODLE UHLY NASTAVÝ SMĚRY A ROZPOČÍTÁ JE
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
		//	UHLY A ČÁSTICE
		double StartAngle = angleR;
		double PlusAngle = (angleL-angleR)/(particles.length-1);
		for(int i = 0; i < particles.length; i++)
		{
			double vy = Math.sin(StartAngle);
			double vx = Math.cos(StartAngle);
			particles[i] = new Particle(Ex,Ey,vx,vy,power,colors[colorsMixed.get(i)],size);
			StartAngle += PlusAngle;
		}
	}


}
