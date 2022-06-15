package fr.dodo.AP4B.projet;

import javax.swing.ImageIcon;

public enum Meteo {

	PLUIE("pluie",null,Element.DESERT,Element.RIVIERE),
	SOLEIL("soleil",null, Element.RIVIERE,Element.DESERT),
	VENT(" vent ",null, Element.FORET,Element.PLAINE),
	ORAGE("orage",null,Element.PLAINE,null);

	private String string;
	private ImageIcon image;
	private Element elementN, elementP;

	private Meteo(String textToFind, ImageIcon img, Element eN, Element eP)
	{
		this.string = textToFind;
		this.image = img;
		this.elementN = eN;
		this.elementP = eP;
	}
	
	public ImageIcon getImageIcon()
	{
		return this.image;
	}

	public Element getElementN()
	{
		return this.elementN;
	}
	public Element getElementP()
	{
		return this.elementP;
	}
	
	public String getTextToFind() {
		return this.string;
	}
}
