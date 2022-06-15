package fr.dodo.AP4B.projet.house;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Home extends House{

	public Home() {
		super(false, 3,"Home");
		
		this.image = new ImageIcon(this.getClass().getResource("home.png"));
		
    	this.image2 = new ImageIcon(this.getClass().getResource("home2.png"));
		Image scaleImage = image2.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
    	image2.setImage(scaleImage);
    	
    	this.image3 = new ImageIcon(this.getClass().getResource("home3.png"));
		 scaleImage = image3.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
		 image3.setImage(scaleImage);
	}

}
