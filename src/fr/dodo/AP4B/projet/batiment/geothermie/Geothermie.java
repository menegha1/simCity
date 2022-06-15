package fr.dodo.AP4B.projet.batiment.geothermie;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Element;
import fr.dodo.AP4B.projet.batiment.Architecture;

public class Geothermie extends Architecture{

	public Geothermie() {
		super("Geothermie", 1, 50, 100,0,3, Element.MONTAGNE,Element.RIVIERE);
		
		this.image = new ImageIcon(this.getClass().getResource("geothermique.png"));
		Image scaleImage = image.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
    	image.setImage(scaleImage);
    	
    	this.image2 = new ImageIcon(this.getClass().getResource("geothermique2.png"));
		 scaleImage = image2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
    	this.image3 = new ImageIcon(this.getClass().getResource("geothermique3.png"));
		 scaleImage = image3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image3.setImage(scaleImage);
    	
	}
}

