package fr.dodo.AP4B.projet.batiment.eolienne;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Element;
import fr.dodo.AP4B.projet.batiment.Architecture;

public class Eolienne extends Architecture{

	public Eolienne() {
		super("Eolienne", 10, 250, 750, 0,5, Element.PLAINE,Element.RIVIERE);
		
		this.image = new ImageIcon(this.getClass().getResource("eolienne.png"));
		
		this.image2 = new ImageIcon(this.getClass().getResource("eolienne2.png"));
		Image scaleImage = image2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
		this.image3 = new ImageIcon(this.getClass().getResource("eolienne3.png"));
		scaleImage = image3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image3.setImage(scaleImage);
		
    	this.duree = 10; //10 sec de fabrication
	}

}

