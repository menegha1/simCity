package fr.dodo.AP4B.projet.batiment.combustible;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Element;
import fr.dodo.AP4B.projet.batiment.Architecture;

public class Combustible extends Architecture{

	public Combustible() {
		super("Combustible", 30, 500, 1000, 10, 15, Element.FORET,Element.RIVIERE);
		
		this.image = new ImageIcon(this.getClass().getResource("centrale_bois.png"));
		
		this.image2 = new ImageIcon(this.getClass().getResource("centrale_charbon.png"));
		 Image scaleImage = image2.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
		this.image3 = new ImageIcon(this.getClass().getResource("centrale_fioul.png"));
		scaleImage = image3.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
    	image3.setImage(scaleImage);
    	
	}

}
