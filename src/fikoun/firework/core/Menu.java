package fikoun.firework.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import fikoun.firework.core.components.MenuButton;

public class Menu
{
	// POLE TLAČITEK V MENU
	public ArrayList<MenuButton> mButtons = new ArrayList<MenuButton>();
	
	
	public Menu()
	{
		//	PŘIDÁ TLAČITKA DO POLE A URČI JEJICH ATRIBUTY
		mButtons.add(new MenuButton(834,300,33,220,50,"Spustit Hru",new Color[]{new Color(255,0,0), new Color(204,255,0), Color.BLACK}){
				//	AKCE PO KLIKNUTÍ NA TLAČITKO
				@Override
				public void clickAction()
				{
					FireworkGame.gameOrMenu = true;
				}
		});
		
		//	EXIT
		mButtons.add(new MenuButton(10,10,16,50,50,"X",new Color[]{Color.RED, Color.BLACK, Color.WHITE}){
			//	AKCE PO KLIKNUTÍ NA TLAČITKO
			@Override
			public void clickAction()
			{
				
				System.exit(0);
			}
	});
	}
	
	
	//	VYKLESLÍ MENU
	public void render(Graphics g)
	{
		//	NADPIS Ohňostroj 2
		g.setFont(new Font ("Verdana", Font.ITALIC , 80));
		g.setColor(Color.white);
		g.drawString("Ohňostroj 2", 710, 200);
		
		//	VYKRELÍ PRVKY V MENU
		for(MenuButton butt : mButtons)
		{
			butt.render(g);
		}
	}
	
	//	OBNOVÝ MENU
	public void update()
	{
		
	}
	
	//	PO KLIKNUTÍ NA CANVAS VOLÁ FIREWORKGAME TUTO METODU
	public void click(MouseEvent e)
	{
		//	VOLÁ METODU CLICK V PRVCÍCH MENU
		for(MenuButton butt : mButtons)
		{
			butt.click(e);
		}
	}
	
	//	POPOHNUTÍ MYŠÍ V CANVASU FIREEORKGAME VOLÁ TUTO METODU
	public void move(MouseEvent e)
	{
		//	VOLÁ METODU MOVE V PRVCÍCH MENU
		for(MenuButton butt : mButtons)
		{
			butt.move(e);
		}
	}
	
}
