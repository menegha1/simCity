package fr.dodo.AP4B.projet.batiment.solaire;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Element;
import fr.dodo.AP4B.projet.batiment.Architecture;

public class Solaire extends Architecture{

	public Solaire() {
		super("Solaire", 5, 75, 400 ,0,7, Element.DESERT,Element.RIVIERE);
		
		this.image = new ImageIcon(this.getClass().getResource("solaire.png"));
		
		this.image2 = new ImageIcon(this.getClass().getResource("solaire2.png"));
		Image scaleImage = image2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
    	this.image3 = new ImageIcon(this.getClass().getResource("solaire3.png"));
		 scaleImage = image3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image3.setImage(scaleImage);
    	
	}

}