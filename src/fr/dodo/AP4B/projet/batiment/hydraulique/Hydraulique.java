package fr.dodo.AP4B.projet.batiment.hydraulique;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Element;
import fr.dodo.AP4B.projet.batiment.Architecture;

public class Hydraulique extends Architecture{

	public Hydraulique() {
		super("Hydraulique", 18, 300,300 ,0,5, Element.RIVIERE,Element.NORMAL);
		this.elemNeg.add(Element.MONTAGNE);
		this.elemNeg.add(Element.DESERT);
		this.elemNeg.add(Element.FORET);
		this.elemNeg.add(Element.PLAINE);
		
		this.image = new ImageIcon(this.getClass().getResource("barrage1.png"));
		
		this.image2 = new ImageIcon(this.getClass().getResource("barrage2.png"));
		Image scaleImage = image2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
		this.image3 = new ImageIcon(this.getClass().getResource("barrage3.png"));
		scaleImage = image3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image3.setImage(scaleImage);
    	
	}


}
