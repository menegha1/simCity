package fr.dodo.AP4B.projet.batiment.stockage;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Element;
import fr.dodo.AP4B.projet.batiment.Architecture;

public class Stockage extends Architecture {	
	
	public Stockage() {
		super("Stockage", 0, 200, 100, 5,5, null, Element.NORMAL);
		
		this.image = new ImageIcon(this.getClass().getResource("batterie1.png"));
		
		this.image2 = new ImageIcon(this.getClass().getResource("batterie2.png"));
		Image scaleImage = image2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
		this.image3 = new ImageIcon(this.getClass().getResource("batterie3.png"));
		scaleImage = image3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image3.setImage(scaleImage);
    	
	}
}