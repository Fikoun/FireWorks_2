/* ##################################################
 * 					Ohňostroj 2
 * 	
 * 
 * 
 * 
 * 
 * ##################################################
 * */

package fikoun.firework.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import fikoun.firework.window.Window;

public class FireworkGame implements Runnable
{
	//		~~ ATRIBUTY ~~
	//	HLAVNÍ
	private Window window=new Window(width, height,"Ohňostroj by Fikoun", 2f);
	private Thread thread = new Thread(this);
	private objectContainer objects;
	private Menu menu;
	//	ROZMĚRY
	public static final int width = 1900;
	public static final int height = 1000;
	//	OSTATNÍ
	private boolean running = false;
	public static double frameLock = 1.0 / 60.0;
	public static int fps = 0;
	public static boolean gameOrMenu = false;
	
	
	
	// PLÁTNO
	public JPanel canvas = new JPanel(){
		private static final long serialVersionUID = 6871752793965585458L;
		@Override
		public void paintComponent(Graphics g){
			Render(g);
		}
	};
	
	
	
	
	
	//	MOUSE LISTENER
	public MouseListener mouseL = new MouseListener()
	{
		//	STISKNUTÍ MYŠI
		@Override
		public void mousePressed(MouseEvent e) 
		{
			//	MENU NEBO HRA TOŤ OTÁZKA :D
			if(gameOrMenu)
				objects.click(e); 
			else
				menu.click(e);
		}
		//	PUŠTĚNÍ MYŠI
		@Override
		public void mouseReleased(MouseEvent e)
		{
			//	MENU NEBO HRA TOŤ OTÁZKA :D
			if(gameOrMenu)
				objects.released(e); 
		}
		
			
		
		@Override
		public void mouseClicked(MouseEvent e){}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
	};
	
	//	MOUSE MOTION LISTENER
	public MouseMotionListener mouseML = new MouseMotionListener()
	{
		//	POHNUTÍ MYŠÍ
		@Override
		public void mouseMoved(MouseEvent e)
		{	
			//	MENU NEBO HRA TOŤ OTÁZKA :D			
			if(gameOrMenu)
				objects.move(e); 
			else
				menu.move(e); 
		}
		
		
		@Override
		public void mouseDragged(MouseEvent e) {}
		
	};

	
	
	
	
	// 	~~ KONSTRUKTOR ~~
	public FireworkGame()
	{
		menu = new Menu();
		//	SETUP WINDOW
		canvas.setSize(new Dimension(width,height));
		window.add(canvas);
		// SETUP MOUSE LISTENER
		canvas.addMouseListener(mouseL);
		canvas.addMouseMotionListener(mouseML);
		//	SETUP MENU
		
		//	SETUP GAME OBJECTS
		objects = new objectContainer(width,height);
		objects.setColorPanelXY(10, 20);
		
		//	ZAVOLÁ run()
		thread.start();
	}
	
	
	
	
	//		~~ FUNKCE run VYVOLANÁ TŘIDOU thread (implementovaná z Runnable) ~~
	@Override
	public void run()
	{
		running = true;
		
		
		double fiTime = 0, laTime = System.nanoTime() / 1000000000.0, paTime = 0, upTime = 0, frTime = 0;
		int frames = 0;
		
		//	GAME LOOP :P
		while(running)
		{
			boolean lock = false;
			fiTime = System.nanoTime() / 1000000000.0;
			paTime = fiTime - laTime;
			laTime = fiTime;
			
			upTime += paTime; 
			frTime += paTime;
			
			while(upTime >= frameLock)
			{
				//	PROBĚHNE OBNOVA VŠEHO :o
				Update();
				upTime -= frameLock;
				lock = true;
				
				if(frTime >= 1)
				{	
					fps = frames;
					frames = 0; frTime = 0;
				}	
			}
			
			if(lock)
			{
				//	JFrame ZAVOLÁ paintComponent(Graphics g) A PROVEDE SE RENDER
				//	PROBĚHNE VYHRESLENÍ VŠEHO
				canvas.repaint();
				frames++;
			}else{
				try{Thread.sleep(1);}catch(InterruptedException e){e.printStackTrace();}
			}
		}
		//	END OF GAME LOOP :(
		
		
	}
	
	
	
	
	//		~~ METODY ~~
	
	//	VYKRESLÉNÍ VŠEHO
	private void Render(Graphics g)
	{
		//	VYČISTÍ CANVAS
		clearCanvas(new Color(0,0,0), g);
		
		//	VYPÍŠE FPS (VPRAVO NAHOŘE)
		g.setColor(Color.WHITE);
		g.drawString(fps+"fps", width-(fps+"").length()*10-30, 20);
		g.drawString("by Fikoun 2016", width-100, height-29);
		
		//	MENU NEBO HRA TOŤ OTÁZKA :D
		if(gameOrMenu)
			objects.Render(g);
		else
			menu.render(g);
	}
	
	//	PŘEKRYJE CELÝ CANVAS JEDNOU BARVOU > c (vykreslí obdelník dané barvy od [O,O]do[width,height])
	private void clearCanvas(Color c, Graphics g){
		g.setColor(c);
		g.fillRect(0, 0, width, height);
	}
	
	
	
	//	OBNOVA VŠEHO
	private void Update()
	{
		//	MENU NEBO HRA TOŤ OTÁZKA :D
		if(gameOrMenu)
			objects.Update();
		else
			menu.update();
	}
	
	
	
	
	
	
	
	
	
	
	//			~~~~~~~~~~~~~~START~~~~~~~~~~~~~~
	public static void main(String[] args){new FireworkGame();}
	//			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
























