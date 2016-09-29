package fikoun.firework.core.fireworks;

import java.awt.Color;

import fikoun.firework.core.objectContainer;

public class Spark extends Firework {
	// 	ATRIBUTY
	public double power,exp;
	
	//	KONSTRUKTOR
	public Spark(int X, int Y, int[] i, int w, int h,int id) {
		super(X, Y, i, w, h,id);
		
		power = objectContainer.eButtons.get(1).power;
		size = (int) objectContainer.eButtons.get(1).size;
		exp = objectContainer.eButtons.get(1).exp;

	}
	
	
	// DRUH EXPLOZE JISKŘENÍ ROZHODÍ ČÁSTICE V URČITÉM POLI EXP A DÁ UHEL BĚHU
	// BARVY JE STÁLE ŽLUTÁ
	public void explode()
	{
		double StartAngle = 0;
		double PlusAngle = 360/(particles.length-1);
		for(int i = 0; i < particles.length; i++)
		{
			double vy = Math.sin(StartAngle);
			double vx = Math.cos(StartAngle);
			if(exp == 0)
				exp = 1;
			particles[i] = new Particle((int) ((int)Ex+r.nextInt((int)(exp*100))-exp/2*100), (int) ((int)Ey+r.nextInt((int)(exp*100))-exp/2*100), vx, vy, power/10, new Color(255,255,0), (int)size);
			StartAngle += PlusAngle;
		}
	}

}
