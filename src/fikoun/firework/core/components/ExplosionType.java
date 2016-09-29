package fikoun.firework.core.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import other.Image;
import fikoun.firework.core.FireworkGame;

public class ExplosionType extends GameButton
{
	//	ATRIBUTY JELI AKTIVNÍ
	public boolean active = false;
	//		VELIKOST ČÁSTIC V PIXELECH,SÍLA VÝBUCHU,UHEL LEVÝ A PRAVÝ,RYCHLOST SVĚTŠIVÁNÍ
	public double size=1,power=10,angleL=Math.PI,angleR=0,exp=1;
	
	public ArrayList<GameButton> explodeB = new ArrayList<GameButton>();
	public ArrayList<GameButton> angleB = new ArrayList<GameButton>();
	public ArrayList<GameButton> sparkB = new ArrayList<GameButton>();

	public ExplosionType(int X, int Y, BufferedImage I,BufferedImage hI){
		super(X, Y, I, hI);
		//	TLAČÍTKA PRO NASTAVENÍ EXPLODE
		explodeB.add(new GameButton(20, FireworkGame.height-150, new Image("/Buttons/LeftArrow.png").get, new Image("/Buttons/LeftArrow.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{
				if (i == 3){
					if(power-10 >= 0)
						power-=10;
					return;
				}
				if(this.clicked)
					return;
				if(i == 1){
					this.clicked = true;
					if(power != 0)
						power--;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});
		explodeB.add(new GameButton(120, FireworkGame.height-150, new Image("/Buttons/RightArrow.png").get, new Image("/Buttons/RightArrow.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{
				if (i == 3){
					if(power+10 < 51)
						power+=10;
					return;
				}
				if(this.clicked)
					return;
				if(i == 1){
					this.clicked = true;
					if(power != 50)
						power++;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});
		explodeB.add(new GameButton(300, FireworkGame.height-150, new Image("/Buttons/LeftArrow.png").get, new Image("/Buttons/LeftArrow.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{
				if (i == 3){
					size=0;
					return;
				}
				if(this.clicked)
					return;
				if(i == 1){
					this.clicked = true;
					if(size!= 0)
						size--;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});
		explodeB.add(new GameButton(400,FireworkGame.height-150, new Image("/Buttons/RightArrow.png").get, new Image("/Buttons/RightArrow.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{
				if (i == 3){
					size=5;
					return;
				}
				if(this.clicked)
					return;
				if(i == 1){
					this.clicked = true;
					if(size != 5)
						size++;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});	
		
		//	TLAČÍTKA PRO NASTAVENÍ ANGLE , ÚHLU	
		angleB.add(new GameButton(20, FireworkGame.height-490, new Image("/Buttons/RightTop.png").get, new Image("/Buttons/RightTop.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{			
				if(i == 1)
					clicked = true;
			}
			public void update()
			{
				if(clicked){
					if(angleL> 0)
						angleL-=0.01;
					else
						angleL=0;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});	
		angleB.add(new GameButton(20, FireworkGame.height-300, new Image("/Buttons/RightBot.png").get, new Image("/Buttons/RightBot.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{			
				if(i == 1)
					clicked = true;
			}
			public void update()
			{
				if(clicked){
					if(angleL < Math.PI*2)
						angleL+=0.01;
					else
						angleL=2*Math.PI;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});	angleB.add(new GameButton(400, FireworkGame.height-490, new Image("/Buttons/LeftTop.png").get, new Image("/Buttons/LeftTop.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{			
				if(i == 1)
					clicked = true;
			}
			public void update()
			{
				if(clicked){
					if(angleR < Math.PI*2)
						angleR+=0.01;
					else
						angleR=2*Math.PI;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});	angleB.add(new GameButton(400,FireworkGame.height-300, new Image("/Buttons/LeftBot.png").get, new Image("/Buttons/LeftBot.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{			
				if(i == 1)
					clicked = true;
			}
			public void update()
			{
				if(clicked){
					if(angleR > 0)
						angleR-=0.01;
					else
						angleR=0;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});	
		
		//	TLAČÍTKA PRO NASTAVENÍ SPARK	
		sparkB.add(new GameButton(160, FireworkGame.height-400, new Image("/Buttons/LeftArrow.png").get, new Image("/Buttons/LeftArrow.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{
				if (i == 3){
						exp=1;
					return;
				}
				if(this.clicked)
					return;
				if(i == 1){
					this.clicked = true;
					if(exp != 1)
						exp--;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});sparkB.add(new GameButton(260, FireworkGame.height-400, new Image("/Buttons/RightArrow.png").get, new Image("/Buttons/RightArrow.png").get){
			public void click(MouseEvent e){
				if(hovered){
					clickAction(e.getButton());
				}
			}
			public boolean clicked = false;
			public void clickAction(int i)
			{
				if (i == 3){
						exp=10;
					return;
				}
				if(this.clicked)
					return;
				if(i == 1){
					this.clicked = true;
					if(exp != 10)
						exp++;
				}
			}
			public void release(){
				this.clicked = false;
			}
		});
	}
	
	//	VYKRESLÍ
	public void render(Graphics g)
	{
		BufferedImage image = null;
		((Graphics2D)g).setStroke(new BasicStroke(3));
		//	NEPŘEKRYTÝ NE AKTIVNÍ
		if(!hovered && !active){
			g.setColor(Color.GRAY);
			image= img;
		//	PŘEKRYTÝ
		}else if(hovered){
			g.setColor(Color.CYAN);
			image = hoverImg;
		//	NEPŘRKRYTÝ AKTIVNÍ
		}else if(!hovered && active){
			g.setColor(new Color(255, 215, 0));	
			image = hoverImg;
		}
		g.drawRect(x-4, y-4, width, height);
		g.drawImage(image, x,y,null);
		
	}
	
	//	PO KLIKNUTÍ VYVOLÁ clickAction(e)
	public void click(MouseEvent e)
	{
		if(hovered){
			clickAction(e.getButton());
		}
		if(active){
			for(GameButton b : explodeB){
				b.click(e);
			}
			for(GameButton b : angleB){
				b.click(e);
			}
			for(GameButton b : sparkB){
				b.click(e);
			}
	}
	}
	
	//	po pohnutí myší
	public void move(MouseEvent e)
	{
		super.move(e);
		for(GameButton b : explodeB){
			b.move(e);
		}
		for(GameButton b : angleB){
			b.move(e);
		}
		for(GameButton b : sparkB){
			b.move(e);
		}
	}
	
	//	PO PUŠTENÍ MYŠI
	public void release()
	{
		for(GameButton b : explodeB){
			b.release();
		}
		for(GameButton b : angleB){
			b.release();
		}
		for(GameButton b : sparkB){
			b.release();
		}
	}
	public void update()
	{
		
	}

	

}
