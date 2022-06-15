package fr.dodo.AP4B.projet.house;

//import fr.dodo.AP4B.projet.image.*;
import java.awt.Image;

import javax.swing.ImageIcon;

public class City  extends House{

	public City() {
		/*
		 * B�timent qui ne peut pas �tre am�liorable ni vendable
		 * Il est directement construit au d�but du gens
		 */
		super(true, 5,"City");
		
		this.image = new ImageIcon(this.getClass().getResource("mairie.png"));
		Image scaleImage = image.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
		image.setImage(scaleImage);
	}
}
