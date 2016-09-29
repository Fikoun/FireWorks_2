package fikoun.firework.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import other.Image;
import fikoun.firework.core.components.ColorBar;
import fikoun.firework.core.components.ColorButton;
import fikoun.firework.core.components.ExplosionType;
import fikoun.firework.core.components.GameButton;
import fikoun.firework.core.fireworks.Angle;
import fikoun.firework.core.fireworks.Explode;
import fikoun.firework.core.fireworks.Firework;
import fikoun.firework.core.fireworks.RandomFireWork;
import fikoun.firework.core.fireworks.Spark;

public class objectContainer 
{
	//	ATRIBUTY POLÍ HERNÍCH OBJEKTŮ
	public ArrayList<ColorButton> cButtons;
	public ArrayList<GameButton> gButtons;
	public static ArrayList<ExplosionType> eButtons;
	//		POLE RAKET S PARTICLY
	public ArrayList<Firework> fireworks;
	//	POLE OBRÁTKŮ BAREV(kolečka) V png 
	public BufferedImage[] colorsImg = {	new Image("/Colors/red.png").get, new Image("/Colors/green.png").get,
											new Image("/Colors/blue.png").get, new Image("/Colors/milk.png").get,
											new Image("/Colors/lime.png").get, new Image("/Colors/purple.png").get,
											new Image("/Colors/orange.png").get, new Image("/Colors/azure.png").get,
											new Image("/Colors/yellow.png").get, new Image("/Colors/pink.png").get
									};
	//	POLE BAREV V RBG
	public Color[] colors = {				new Color(255,0,0),new Color(0,255,0),
											new Color(0,0,255),new Color(249,244,190),
											new Color(204,255,0),new Color(102,0,204),
											new Color(255,153,0),new Color(0,255,255),
											new Color(255,255,0),new Color(255,0,255),
									};
	//	SLOUPEC ZOBRAZUJÍCÍ NÁZORNĚ NAMÍCHANÉ BARVY
	public ColorBar cBar;
	//	OSTATNÍ
	private int colorPanelX = 0;
	private int colorPanelY = 0;
	public int width;
	public int Height;
	
	
	
	//	KONSTRUKTOR
	public objectContainer(int w, int h)
	{
		width = w;
		Height = h;
		cButtons = new ArrayList<ColorButton>();
		gButtons = new ArrayList<GameButton>();
		eButtons = new ArrayList<ExplosionType>();
		fireworks = new ArrayList<Firework>();

		//	TLAČITKO ZPĚT DO MENU
		gButtons.add(new GameButton(10, Height-80, new Image("/Buttons/LeftArrow.png").get, new Image("/Buttons/LeftArrow.png").get){public void clickAction(int butt){FireworkGame.gameOrMenu=false;}});
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//				NASTAVOVÁNÍ TLÁČITEK VÝBĚRU DRUHU EXPLOZE
		//	POKUD JE KLIKNUTO TLAČÍTKO ZAKTIVNÍ SE ALE ZNEAKTIVNÍ OSTATNÍ
		//		NASTAVOVÁNÍ VÝPISU HODNOT V NASTAVENÍ DRUHU EXPLOZE
		//	NASTAVENÍ KDY CO A JAK RENDEROVAT :D
		
		//////								EXPLODE						//////
		eButtons.add(new ExplosionType(220, 80,new Image("/Buttons/EffectExplode.png").get , new Image("/Buttons/EffectExplodeActive.png").get){
			public void clickAction(int butt)
			{
				eButtons.get(0).active = true;
				eButtons.get(1).active = false;
				eButtons.get(2).active = false;
				eButtons.get(3).active = false;
			}
			
			public void render(Graphics g)
			{
				super.render(g);
				if(this.active){
					for(GameButton b : explodeB)
					{
						b.render(g);
						
					}
					g.setColor(Color.white);
					g.setFont(new Font("Verdana",Font.BOLD, 20));
					g.drawString("POWER", 45, h-165);
					g.setFont(new Font("Verdana",Font.ITALIC, 15));
					g.drawString(""+power, 70, h-124);
					
					g.setFont(new Font("Verdana",Font.BOLD, 20));
					g.drawString("SIZE", 340, h-165);
					g.setFont(new Font("Verdana",Font.ITALIC, 15));
					g.drawString(""+size, 356, h-124);
				}
					
			}
			public void update()
			{
				super.update();
				for(GameButton b : explodeB)
				{
					b.update();;
					
				}
			}
		});
		//	JAKO VÝCHOZÍ BUDE AKTIVNÍ EXPLODE
		eButtons.get(0).active = true;
		////////////////////////////////////
		//////								SPARK						//////
		eButtons.add(new ExplosionType(330, 80,new Image("/Buttons/EffectSpark.png").get , new Image("/Buttons/EffectSparkActive.png").get){
			public void clickAction(int butt)
			{
				eButtons.get(0).active = false;
				eButtons.get(1).active = true;
				eButtons.get(2).active = false;
				eButtons.get(3).active = false;
			}
			public void render(Graphics g)
			{
				super.render(g);
				if(this.active){
					for(GameButton b : explodeB)
					{
						b.render(g);
						
					}
					for(GameButton b : sparkB)
					{
						b.render(g);
					}
						g.setColor(Color.white);
						g.setFont(new Font("Verdana",Font.BOLD, 20));
						g.drawString("POWER", 45, h-165);
						g.setFont(new Font("Verdana",Font.ITALIC, 15));
						g.drawString(""+power, 70, h-124);
						
						g.setFont(new Font("Verdana",Font.BOLD, 20));
						g.drawString("SIZE", 340, h-165);
						g.setFont(new Font("Verdana",Font.ITALIC, 15));
						g.drawString(""+size, 356, h-124);
						
						g.setFont(new Font("Verdana",Font.BOLD, 20));
						g.drawString("EXPANDING", 161, h-415);
						g.setFont(new Font("Verdana",Font.ITALIC, 15));
						g.drawString(""+exp, 215,  h-375);
					
				}
					
			}
			public void update()
			{
				super.update();
				for(GameButton b : explodeB)
				{
					b.update();;
					
				}
				for(GameButton b : sparkB)
				{
					b.update();;
					
				}
			}
		});
////////////////////////////////////
		//////								ANGLE					//////
		eButtons.add(new ExplosionType(220, 220,new Image("/Buttons/EffectAngle.png").get , new Image("/Buttons/EffectAngleActive.png").get){
			public void clickAction(int butt)
			{
				eButtons.get(0).active = false;
				eButtons.get(1).active = false;
				eButtons.get(2).active = true;
				eButtons.get(3).active = false;
			}
			public void render(Graphics g)
			{
				super.render(g);
				if(this.active){
					for(GameButton b : explodeB)
					{
						b.render(g);
						
					}
					for(GameButton b : angleB)
					{
						b.render(g);
					}
						g.setColor(Color.white);
						g.setFont(new Font("Verdana",Font.BOLD, 20));
						g.drawString("POWER", 45, h-165);
						g.setFont(new Font("Verdana",Font.ITALIC, 15));
						g.drawString(""+power, 70, h-124);
						
						g.setFont(new Font("Verdana",Font.BOLD, 20));
						g.drawString("SIZE", 340, h-165);
						g.setFont(new Font("Verdana",Font.ITALIC, 15));
						g.drawString(""+size, 356, h-124);
						
						//vykreslování ůhlu
						Graphics2D g2 = (Graphics2D)g;
						g.setColor(new Color(48,48,48));
						g.fillRect(105, Height-495, 250, 250);
						g.setColor(new Color(100,100,100));
						g.drawArc(105, Height-495, 250, 250, 0, 360);
						g.drawLine(105+250/2, Height-494, 105+250/2, Height-495+248);
						g.drawLine(106, Height-495+250/2, 105+248, Height-495+250/2);
						
						g2.setStroke(new BasicStroke(1));
						g.setColor(new Color(0,100,0));
						g.fillArc(105+250/6, Height-495+250/6, (int)(250/1.5), (int)(250/1.5), (int)(Math.toDegrees(angleL)), (int)(Math.toDegrees(angleR)-Math.toDegrees(angleL)));
						
						g2.setStroke(new BasicStroke(4));
						g.setColor(new Color(100,0,0));
						g.drawLine((105+250/2), (Height-495+250/2), (int)((105+250/2)+250/2*Math.cos(angleL)), (int)((Height-495+250/2)-250/2*Math.sin(angleL)));
						g.setColor(new Color(0,0,100));
						g.drawLine((105+250/2), (Height-495+250/2), (int)((105+250/2)+250/2*Math.cos(angleR)), (int)((Height-495+250/2)-250/2*Math.sin(angleR)));
						
				}
					
			}
			public void update()
			{
				super.update();
				for(GameButton b : explodeB)
				{
					b.update();		
				}
				for(GameButton b : angleB)
				{
					b.update();	
				}
			}
		});
////////////////////////////////////
	//////								RANDOM					//////
		eButtons.add(new ExplosionType(330, 220,new Image("/Buttons/EffectRandom.png").get , new Image("/Buttons/EffectRandomActive.png").get){
			public void clickAction(int butt)
			{
				eButtons.get(0).active = false;
				eButtons.get(1).active = false;
				eButtons.get(2).active = false;
				eButtons.get(3).active = true;
			}
			
		});
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// NASTAVOVÁNÍ OVLÁDACÍHO PANELU
		boolean cButtFS = false;
		int cButtY = 0;
		//		BAREVNÁ TLAČÍTKA
		for(int i = 0; i < 10; i++)
		{
			if(cButtFS){
				cButtFS = false;
				cButtons.add(new ColorButton(60,cButtY,colors[i],colorsImg[i]));
				cButtY += 91;
			}
			else{
				cButtFS = true;
				cButtons.add(new ColorButton(0,cButtY,colors[i],colorsImg[i]));
			}	
		}
		cBar = new ColorBar(130, 0);
	}
	
	
	//		~~OSTATNÍ FUNKCE~~
	
	//	NASTAVÍ POZICI OVLADAČE BAREV
	public void setColorPanelXY(int x, int y)
	{
		//		BAREVNÁ TLAČÍTKA
		for(int i = 0; i < cButtons.size(); i++)
		{			
			cButtons.get(i).x = cButtons.get(i).x-colorPanelX+x;
			cButtons.get(i).y = cButtons.get(i).y-colorPanelY+y;
		}
		cBar.x = cBar.x-colorPanelX+x;
		cBar.y = cBar.y-colorPanelX+y;
		this.colorPanelX = x;
		this.colorPanelY = y;	
	}
	
	
	
	// VYVOLÁN PŘI KLIKNUTÍ V OKNĚ ZAVOLÁ METODU
	public void click(MouseEvent e)
	{
		//	BAREVNÁ TLAČÍTKA
		for(int i = 0; i < cButtons.size(); i++)
		{
			cButtons.get(i).pressed = e.getButton();
		}
		//		TLAČÍTKA
		if(e.getButton() == 1){
			for(int i = 0; i < gButtons.size(); i++)
			{
				gButtons.get(i).click(e);
			}
		}
		// TLAČÍTKA DRUHU EXPLOZE
		for(int i = 0; i < eButtons.size(); i++)
		{
				eButtons.get(i).click(e);
		}
		
		
		// POKUD JE V POLY(ČÁSTI OBRAZOVKY) KDE SE SMÍ SŘÍLET OHŇOSTROJ, ... VYSTŘELÍ. :)
		if(e.getX() >= 455){
			int[] colorsV = new int[cButtons.size()];
			int sum = 0;
			for(int i = 0; i < cButtons.size(); i++)
			{
				colorsV[i] = (int) cButtons.get(i).value;
				sum += (int) cButtons.get(i).value;
			}
			//	VYSTŘELÍ POKUD JE SOUČET HODNOT BAREV VĚTŠÍ JAK 0
			if(sum != 0){
				if(eButtons.get(0).active)
					fireworks.add(new Explode(e.getX(), e.getY(), colorsV, width, Height,0));
				else if(eButtons.get(1).active)
					fireworks.add(new Spark(e.getX(), e.getY(), colorsV, width, Height,1));
				else if(eButtons.get(2).active)
					fireworks.add(new Angle(e.getX(), e.getY(), colorsV, width, Height,2));
				else if(eButtons.get(3).active)
					fireworks.add(new RandomFireWork(e.getX(), e.getY(), colorsV, width, Height,3));
					
			}
		}
	}
	
	// VYVOLÁN PŘI PUŠTĚNÍ MÝŠI V OKNĚ 
		public void released(MouseEvent e)
		{
			//	BAREVNÁ TLAČÍTKA
			for(int i = 0; i < cButtons.size(); i++)
			{
				cButtons.get(i).pressed = 0;
			}
			for(int i = 0; i < eButtons.size(); i++)
			{
				eButtons.get(i).release();
			}
		}
	
	// VYVOLÁN PŘI POHNUTÍ MÝŠÍ V OKNĚ ZAVOLÁ METODU move() VE VŠECH OBJEKTECH
		public void move(MouseEvent e)
		{
			//	BAREVNÁ TLAČÍTKA
			for(int i = 0; i < cButtons.size(); i++)
			{
				cButtons.get(i).move(e);
			}
			for(int i = 0; i < gButtons.size(); i++)
			{
				gButtons.get(i).move(e);
			}
			for(int i = 0; i < eButtons.size(); i++)
			{
				eButtons.get(i).move(e);
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	// VYKRESLÍ VŠECHNY OBJEKTY
	public void Render(Graphics g)
	{	
		//		BAREVNÁ TLAČÍTKA
		for(int i = 0; i < cButtons.size(); i++)
		{
			cButtons.get(i).render(g);
		}
		//		TLAČÍTKA
		for(int i = 0; i < gButtons.size(); i++)
		{
			gButtons.get(i).render(g);
		}
		cBar.render(g,cButtons);
		for(int i = 0; i < eButtons.size(); i++)
		{
			eButtons.get(i).render(g);
		}
		for(int i = 0; i < fireworks.size(); i++)
		{
			fireworks.get(i).render(g);
		}
		
		((Graphics2D)g).setStroke(new BasicStroke(5));
		g.setColor(Color.white);
		g.drawLine(450, 0, 450, Height);
		g.drawLine(0, 480, 450, 480);
		g.drawLine(0, Height-90, 450, Height-90);
		
	}
	
	
	
	
	// OBNOVÝ VŠECHNY OBJEKTY
	public void Update()
	{
		for(int i = 0; i < eButtons.size(); i++)
		{
			eButtons.get(i).update();
		}
		//		BAREVNÁ TLAČÍTKA
		for(int i = 0; i < cButtons.size(); i++)
		{
			cButtons.get(i).update();
		}
		//	OHŇOSTROJE
		for(int i = 0; i < fireworks.size(); i++)
		{
			fireworks.get(i).update();
			
		}
		
		// ODSRŇOVÁNÍ OHŇOSTRŮ NA ZÁKLADĚ ZČERNALÉ BARVY PRVNÍ ČÁSTICE
		for(int i = 0; i < fireworks.size(); i++)
		{
			if(fireworks.get(i).exploded){
				if(fireworks.get(i).particles[0].color.equals(new Color(0,0,0)))
					fireworks.remove(i);
			}
		}
		cButtons = cBar.update(cButtons);
		
	}
	
	
	
	
	
	
	
	
}
