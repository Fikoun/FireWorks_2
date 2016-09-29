package fikoun.firework.core.fireworks;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class RandomFireWork extends Firework
{
	// ATRIBUTY
	public Random r = new Random();
	public int type;
	
	public double power;
	public double angleL,angleR;
	public double exp;
	
	//KONSTRUKTOR
	public RandomFireWork(int X, int Y, int[] i, int w, int h, int id) {
		super(X, Y, i, w, h, id);
		//VYTVOŘÍ NÁHODNÝ DRUH EXPLOZE
		type = r.nextInt(3);
		size = r.nextInt(3)+1;	
		power = r.nextInt(24)+1;
		if(type == 1){
			angleL = (r.nextDouble()-0.5D)*4;
			angleR = (r.nextDouble()-0.5D)*4;
		}else if(type == 2){
			exp = r.nextInt(9)+1;
		}	
	}
	// EXPLOZE VYBRÁNA PODLE DRUHU EXPLOZE
	public void explode()
	{
		if(type == 0){
			explodeExplode();
		}else if(type == 1){
			explodeAngle();
		}else{
			explodeSpark();
		}	
	}
	// KKLASICKÁ EXPLOZE
	public void explodeExplode()
	{
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
		
		
		for(int i = 0; i < particles.length; i++)
		{		
			double vx = (r.nextFloat()-0.5F);
			double vy = (r.nextFloat()-0.3F);
			particles[i] = new Particle(Ex,Ey,vx,vy,power,colors[colorsMixed.get(i)],(int) size);
		}
	}
	
	// ÚHLOVÁ EXPLOZE NÁHODNÝ ÚHEL
	public void explodeAngle()
	{
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
	
	//	JISKERNÁ EXPLOZE
	public void explodeSpark()
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
