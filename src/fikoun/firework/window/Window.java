package fikoun.firework.window;

import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;

	//	KONSTRUKTOR NASTAVENÍ frame
	public Window(int w, int h, String t, float v)
	{	
		this.setSize(w, h);
		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);;
		this.setVisible(true);
		this.setTitle(t + " " + v + "v"); // title + verze (FireWorks 2.0v)
		this.setBackground(new Color(0,0,0));
	
	}

}
