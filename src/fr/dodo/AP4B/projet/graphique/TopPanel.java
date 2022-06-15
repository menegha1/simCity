package fr.dodo.AP4B.projet.graphique;

import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.dodo.AP4B.projet.GameMaster;
import fr.dodo.AP4B.projet.PlayerData;
import fr.dodo.AP4B.projet.SystemData;

public class TopPanel extends JPanel{

	/*
	 * on mets les classes contenant des infos en attributs
	 */
	GameMaster game;
	SystemData system;
	PlayerData player;
	
	JLabel meteo = new JLabel();
	JLabel argent = new JLabel();
	JLabel satisfaction = new JLabel();
	JLabel pollution = new JLabel();
	JLabel elec = new JLabel( " elecActuel  /   elecStockable  ( prodElec/s)");	
	JLabel consoElec = new JLabel();
	JLabel habTotale = new JLabel();
	
	public TopPanel(GameMaster g) 
	{
		/*
		 * on affecte les attributs
		 */
		this.game = g;
		this.system = g.getSystemData();
		this.player = g.getPlayerData();
		
		setPreferredSize(new Dimension(1500,50));
		//on ajoute les textes
		add(meteo);add(pollution);add(habTotale);add(consoElec);add(argent);add(elec);add(satisfaction);
	}
	
	public void setTextRessource()
	{
		/*
		 * on update l'affichage en fonction du gameMaster
		 */
		
		DecimalFormat df = new DecimalFormat ( ) ;
		df.setMaximumFractionDigits (0);// permet d'avoir '0' chiffre après la virgule
		DecimalFormat df2 = new DecimalFormat ( ) ;
		df2.setMaximumFractionDigits (2);
		
		meteo.setText("( boost Elec :" + system.getBoosElec() + ") Meteo :" + system.getMeteo().getTextToFind());
		habTotale.setText(" Habitant : " + df.format(system.getHabTotal()));
		argent.setText( " Argent : " + df.format(player.getArgent()) + " ( " + df2.format(system.getHabTotal()/5)+ " Arg/s)       ");
		satisfaction.setText(" Satisfaction : " + system.getSatisfaction() +" ( -" + system.getPollution()/5 + " pollution / "  + system.getDeficitElecStock()/10 + " perteElec)");
		if(system.getStockElec() < 0)
		{
			elec.setText("Elec : 0" + "/ " + system.getStockMaxElec()  + " ( " + system.getProdElec() + " Elec/s)      " );
		}else {
			elec.setText("Elec : " + system.getStockElec() + " / " + system.getStockMaxElec()  + " ( " + system.getProdElec() + " Elec/s)");
		}
		pollution.setText(" Pollution : " + system.getPollution() + "         ");
		consoElec.setText(" Consomation Elec :" + system.getConsElec());	
	}

}
