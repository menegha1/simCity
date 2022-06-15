package fr.dodo.AP4B.projet;

import java.awt.Color;

import javax.swing.ImageIcon;

public enum Element {

	RIVIERE("riviere", Color.BLUE),
	PLAINE("plaine",Color.GREEN),
	DESERT("desert",Color.ORANGE),
	FORET("foret", new Color(0,153,0)),
	MONTAGNE("montagne", new Color(102,51,0)),
	NORMAL("normal",Color.GRAY);

	private String string;
	private Color color;
	private ImageIcon image;
	
	private Element(String textToFind, Color c)
	{
		this.string= textToFind;
		this.color = c;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	public ImageIcon getImageIcon()
	{
		return this.image;
	}
	
	public String getTextToFind() {
		return this.string;
	}

}
