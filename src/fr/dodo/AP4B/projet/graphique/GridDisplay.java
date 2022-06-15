package fr.dodo.AP4B.projet.graphique;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

import fr.dodo.AP4B.projet.GameMaster;

public class GridDisplay extends JPanel {

	
	ArrayList<CaseAffichage> c = new ArrayList<CaseAffichage>();
	public GridDisplay(GameMaster g) {

		//permet de set un Layout sous forme de grille
        setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	// On crée une grille 12*20
    	for (int row = 0; row < 12; row++)
    	{
    		for (int col = 0; col < 20; col++)
    		{
    			
    			gbc.gridx = col;//coordonnée x
    			gbc.gridy = row;//coordonnée y
    			c.add(new CaseAffichage(null,col,row,true,g));
    			add(c.get((col+20*row)), gbc); 
    		}		
    	}
    	
    	setPreferredSize(new Dimension(2000,1500));
	}
	
	public CaseAffichage getCaseA(int i)
	{
		return this.c.get(i);
	}

}
