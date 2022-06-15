package fr.dodo.AP4B.projet.graphique;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.dodo.AP4B.projet.GameMaster;

public class ScrollDisplay extends JScrollPane{

	
	public ScrollDisplay(GameMaster g) {
		JPanel parentPanel = new JPanel();
    	parentPanel.setPreferredSize(new Dimension(160,1200));
    	//on set un Layout sous forme de grile avec uniquement 1 colonne
        parentPanel.setLayout(new GridLayout(0, 1,0,10));
        //on set 8 caseAffichage
        for(int i = 0 ; i< 8; i++)
    	{
        	
        	if( i == 0)//pour la maison 
        	{
        		//on récupère l'image
        		ImageIcon img = g.getListHouse(i).getImage();
        		Image scaleImage = img.getImage().getScaledInstance(75,75,Image.SCALE_DEFAULT);
        	    img.setImage(scaleImage);
        	    
        		parentPanel.add(new CaseAffichage(img,i,i,false,g));
        		parentPanel.add(new JLabel(" House : prix " + g.getListHouse(i).getPrix()));
        	}else{
        		ImageIcon img = g.getListArch(i-1).getImage();
        		Image scaleImage = img.getImage().getScaledInstance(75,75,Image.SCALE_DEFAULT);
    	    	img.setImage(scaleImage);
        		
    	    	parentPanel.add(new CaseAffichage(img,i,i,false,g));
        		parentPanel.add(new JLabel(""+g.getListArch(i-1).getName() + " : prix " + g.getListArch(i-1).getPrix()));
        	}
    	}
        //on set les caractéristiques de la ScrollBar
        setViewportView(parentPanel);
        getVerticalScrollBar().setUnitIncrement(30);
        getHorizontalScrollBar().setUnitIncrement(30);
        
	}

}
