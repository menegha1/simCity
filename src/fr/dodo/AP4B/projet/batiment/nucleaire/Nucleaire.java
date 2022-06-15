package fr.dodo.AP4B.projet.batiment.nucleaire;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Element;
import fr.dodo.AP4B.projet.batiment.Architecture;

public class Nucleaire extends Architecture{

	public Nucleaire() {
		super("Nucleaire", 50, 750, 2000,20,25, Element.NORMAL,Element.RIVIERE);
		
		this.elemNeg.add(Element.MONTAGNE);
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("nucleaire.png"));
		this.image = img;
	
		this.image2 = new ImageIcon(this.getClass().getResource("nucleaire2.png"));
		Image scaleImage = image2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
    	this.image3 = new ImageIcon(this.getClass().getResource("nucleaire3.png"));
		 scaleImage = image3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image3.setImage(scaleImage);
		
    	this.duree = 25; //25 sec de fabrication
	}

}