package fr.dodo.AP4B.projet;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

import fr.dodo.AP4B.projet.batiment.*;
import fr.dodo.AP4B.projet.batiment.combustible.Combustible;
import fr.dodo.AP4B.projet.batiment.eolienne.Eolienne;
import fr.dodo.AP4B.projet.batiment.geothermie.Geothermie;
import fr.dodo.AP4B.projet.batiment.hydraulique.Hydraulique;
import fr.dodo.AP4B.projet.batiment.nucleaire.Nucleaire;
import fr.dodo.AP4B.projet.batiment.solaire.Solaire;
import fr.dodo.AP4B.projet.batiment.stockage.Stockage;
import fr.dodo.AP4B.projet.graphique.displayGame;
import fr.dodo.AP4B.projet.house.City;
import fr.dodo.AP4B.projet.house.Home;
import fr.dodo.AP4B.projet.house.House;

public class GameMaster extends Thread{
/*
 * on ajoute les attributs soit :
 * une liste de cases ( soit la grille centrale)
 * 
 * une liste d'Architecture correspondant à tous les bâtiments possible
 * 
 * une liste d'House correspondant à  l'habitation Home()
 * 
 * playerData et SystemData pour les données de la partie
 * 
 * displayGame pour l'affichage
 */
	private Case[][] tableauCase = new Case[12][20];
	private ArrayList<Architecture> listArchi = new ArrayList<Architecture>();
	private ArrayList<House> listHouse = new ArrayList<House>();
	
	private PlayerData playerdata;
	private SystemData systData;
	private displayGame display;
	

	public GameMaster() 
	{
		/*
		 * on initialise les attributs
		 */
		playerdata = new PlayerData();
		systData = new SystemData();
		   
		for(int i = 0 ; i< 12 ;i++)
		{
			for(int j = 0; j< 20 ; j++)
			{
				this.tableauCase[i][j] = new Case(i*20+j);
				if((i*20+j) == 129)
					{
						this.getCaseTab(129).setHouse(new City());
					}
			}
		}
		
		this.listHouse.add(new Home());
		this.listArchi.addAll(Arrays.asList(new Stockage(),new Geothermie(),new Hydraulique(),
				new Solaire(),new Eolienne(),new Combustible(), new Nucleaire()));	
	}
	/*
	 * méthode run() du Thread de GameMaster ( héritage)qui tournera en permanence
	 */
	public  void run() 
	{
		
	    int n = 0, rep = 0;// n->nombre de répetitions //rep->nombre de fois où le joueur est en déficite d'affilée
	    float  bonusHab = 0, habMax= 0;//bonusHab ->nombre d'habitant en plus avec le boost de House//habMax->nombre d'habitant maximum 
	    
		while (true) 
		{
			//toutes les 3 secondes on set la satisfaction
			if(n%3 == 0)
			{
				//on calcule la somme d'electricite perdu ( en fonction de la consommation des habitants)
				float stockElecPerdu = this.systData.getStockElec() - (this.systData.getConsElec());
				//on teste la valeur
				if(stockElecPerdu <= 0)
				{
					//si oui
					//on set le deficite d'Electricité du système et la satisfaction
					this.systData.setDeficitElecStock(stockElecPerdu*rep/10);
					this.systData.setSatisfaction(100+(stockElecPerdu/10)-(this.systData.getPollution()/5));
					
					if(this.systData.getSatisfaction() <= 0)
					{
						this.systData.setSatisfaction(0);
					}else{
						rep++;
					}
				}else {
					//si non
					rep = 0;
					//on affecte les données sans déficite d'Electricit&
					this.systData.setSatisfaction(100-(this.systData.getPollution()/5));
					this.systData.addStockElec(-(this.systData.getConsElec()));
					this.systData.setDeficitElecStock(0);
				}

			}
			
			//toutes les 2 secondes, on calcule les boost d'Elec et d'Habitant, et on l'affecte
			if(n%2==0)
			{
				// rénitialisation des valeurs de prodElec et HabTotal en enlevant les boosts 
				this.systData.addProdElec(-this.systData.getBoosElec());
				this.systData.addHabTotal(-bonusHab);
				this.systData.setBoostElec(0);
				
				bonusHab = 0;
				habMax = 0;
				/*
				 * on parcourt l'ensemble de la grille
				 */
				for(int i = 0; i< 239;i++)
				{
					Case c = this.getCaseTab(i);
					//on test si on ajoute le boost des maison
					if(this.systData.getSatisfaction() > 0 && c.isHouse())
					{
							bonusHab += this.nbHouseBoost(c);
							habMax  = habMax +c.getHouse().getHabitant()*2 + this.nbHouseBoost(c);
					}
					//si la case comporte une architecture, alors on ajoute le boost selon le type de Meteo et l'element de la Case
					if(c.isArchitecture())
					{
						if(c.getElement().equals(this.systData.getMeteo().getElementN()))
						{
							this.systData.addBoostElec(-5);
						}
						if(c.getElement().equals(this.systData.getMeteo().getElementP()) && c.getArchitecture().isElemPositif(this.systData.getMeteo().getElementP()))
						{
							this.systData.addBoostElec(+5);
						}
					}
				}
				//on ajoute les boost au donnée du système
				this.systData.addProdElec(this.systData.getBoosElec());
				this.systData.addHabTotal(bonusHab);	
			}
			
			//toutes les 4 secondes, on ajoute ou supprime un nombre d'habitant en fonction de la satisfaction
			if( n%4 == 0)
			{
				
				if(this.systData.getSatisfaction() >= 80)
				{
					//si oui on ajoute des maisons si le nombre d'habitant est inférieur aux maximum d'hab
					if(this.systData.getHabTotal() < habMax)
					{
						this.systData.addHabTotal(this.systData.getSatisfaction()/50);
					}
				}else {
					//si non , on réduit en fonction de la satisfaction
					
					this.systData.addHabTotal(-(this.systData.getHabTotal()* ((100-this.systData.getSatisfaction())/100) ));
				}
			}
			//toutes les 10 secondes , on change la météo
			if(n%10 == 0)
			{
				this.systData.setMeteo();
			}
			
			/*
			 * toutes les secondes , on ajoute l'argent et le StockElec 
			 */
			this.playerdata.addArgent(this.systData.getHabTotal() / 5);
			
			if(this.systData.getStockElec() < 0)
			{
				this.systData.setStockElec(0);
			}else {
					if(this.systData.getStockElec() + this.systData.getProdElec() < this.systData.getStockMaxElec())
					{
						this.systData.addStockElec(this.systData.getProdElec());
					}else {
						this.systData.setStockElec(this.systData.getStockMaxElec());
					}
			}
			this.display.setRessource(); //affiche les ressources
			try {Thread.sleep(1000);
			} catch (InterruptedException e) {}
			n++;
		}
	}
	
	public void setDisplayGame(displayGame d)
	{
		this.display = d;
	}
	
	public Architecture getListArch(int i)
	{
		return this.listArchi.get(i);
	
	}
	public House getListHouse(int i)
	{
		return this.listHouse.get(i);
	}
	
	public void subArgentBat( int j)
	{
		if(j == 0)
		{
			//si House
			this.playerdata.subArgent(this.listHouse.get(j).getPrix());
		}else{
			//si Architecture
        	this.playerdata.subArgent(this.listArchi.get(j-1).getPrix());
        }
		this.display.setRessource();
	}
	/*
	 * méthoe permettant de set un bâtiment sur une case
	 */
	public void setBat(int i, int j)
	{
		Case c = this.getCaseTab(i);
		if( j ==0)
		{
			//si House
			House h = new Home();
			//on ajoute
			c.setHouse(h);
			
			// mise à jour des données
			this.systData.addHabTotal(h.getHabitant());
			this.systData.addConsElec(h.getConso());
			this.display.setRessource();
		}else {
			//si Architecture
			
			//on ajoute
			c.setArchitecture(j-1);
			Architecture arch = c.getArchitecture();
			
		    arch.setProdElec(c);
		    
		    // mise à jour des données
			this.systData.addStockMaxElec(arch.getStockElec());
			this.systData.addPollution(arch.getPollution());
			this.systData.addProdElec(arch.getProdElec());
			this.display.setRessource();
		}
	}
	/*
	 * méthode retournant un boolean si il peut être upgraded 
	 * si oui il met à jour les données 
	 */
	public boolean upgradeBat(int i)
	{
		Case c = this.getCaseTab(i);
		if(c.isArchitecture())
		{
			//si architecture
			Architecture arch = c.getArchitecture();
			//on teste si on peut upgrade
			if(playerdata.getArgent() >= arch.getPrix() * 2 && arch.getNiveau()  < 3)
			{	
				//si oui on met à jour
				playerdata.subArgent(arch.getPrix());//retrait d'argent
				this.systData.addStockMaxElec(arch.getStockElec());//ajout StockMax
				this.systData.addProdElec(arch.getProdElec());//ajout Prod Electricite
				this.display.setRessource(); // set Texte Affichage
				//upgrade
				arch.upgrade();
				return true;
			}else {
				// affiche boîte de dialogue
				JOptionPane.showMessageDialog( null,
			            " niveau dépassé",
			            "Invalid Input", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}else {
			//si maison
			House h = c.getHouse();
			//on teste si on peut upgrade
			if(h.getNiveau() <3 )
			{
				if(playerdata.getArgent() >= c.getHouse().getPrix() * 2)// car l'amlélioration coûte x2 le prix
				{
					//upgrade
					h.upgrade();
					//met à jour donnée
					playerdata.subArgent(h.getPrix());//retrait d'argent
					systData.addHabTotal(h.getHabitant()); //updateHab
					
					return true;
				}else {
					//affiche boîte de dialogue
					JOptionPane.showMessageDialog( null,
				            " Pas assez d'argent",
				            "Invalid Input", JOptionPane.ERROR_MESSAGE);
					return false;
				}	
			}
			else {
				//affiche boîte de dialogue
				JOptionPane.showMessageDialog( null,
			            " niveau dépassé",
			            "Invalid Input", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}
	/*
	 * méthoe permettant de supprimer un bâtiment sur une case
	 */
	 
	public void removeBat(int i)
	{
		Case c = this.getCaseTab(i);
		if(c.isArchitecture())
		{
			//si architecture
			Architecture arch = c.getArchitecture();
			c.setArchitecture(null);
			
			// mise à jour des données
			this.playerdata.subArgent(- (arch.getPrix() / 2)); //retrait d'argent
			this.systData.addStockMaxElec(- arch.getStockElec());//retrait de StockMax
			this.systData.addProdElec(-arch.getProdElec());//retrait de la Prod d'electricite
			this.systData.addPollution(-(arch.getPollution()));//retrait pollution
		}else {
			if(c.isHouse())
			{
				//si house
				House h = c.getHouse();
				c.setHouse(null);
				
				// mise à jour des données
				this.systData.addHabTotal(- h.getHabitant()); //retrait d'habitant
				this.playerdata.subArgent(-(h.getPrix() / 2)); //ajout de la moitie du prix
			}
		}
	}
	/*
	 * Permet de savoir si on peut set le batiment d'index j sur la case d'index i
	 */
	public boolean isSetPossible(int i,int j)
	{
		Case c = this.getCaseTab(i);
		if(j == 0)
		{
			//si House
			Home h = new Home();
			// vérifie condition
			return (c.getElement() != Element.RIVIERE && h.getPrix() < playerdata.getArgent());
		}else {
			//si architecture
			Architecture arch = this.listArchi.get(j-1);
			// vérifie condition
			if(this.playerdata.getArgent() > arch.getPrix())
			{
				if(!arch.isElemNeg(this.getCaseTab(i).getElement()))
				{
					return true;
				}
			}
			return false;
		}
	}
	


	//retour de la valeur du boost d'habitant
	public int  nbHouseBoost(Case c )
	{
		// récupere les coordonnées de case sur la  grille;
		int id = c.getId(), i = c.getId()/20, j = c.getId()%20;
		int boost = 0;
		//liste des casePossible
		ArrayList<Integer> casePossible = new ArrayList<Integer>();
		
		//on affecte à la liste toutes les cases possibles en fonction de la position de la case
		if( i== 0 || j == 0 || i == 11 || j == 11)
		{
			if(i == 0){casePossible.addAll(Arrays.asList((id-1),(id+1),(id+20),(id+19),(id+21)));}
			if(j == 0){casePossible.addAll(Arrays.asList((id-20),(id+1),(id+20),(id-19),(id+21)));}
			if(i == 11){casePossible.addAll(Arrays.asList((id-20),(id-1),(id+1),(id-19),(id-21)));}
			if(j == 11){casePossible.addAll(Arrays.asList((id-20),(id-1),(id+20),(id-19),(id+19)));}
		}else{
			casePossible.addAll(Arrays.asList((id+1),(id-20),(id-1),(id+20),(id-19),(id+19),(id+21),(id-21)));
		}
		
		//on vérifie sur chaque case possible s'il y a une maison ou pas 
		for(Integer p : casePossible)
		{
			if( this.getCaseTab(p).isHouse())
			{
				//si oui on increment la variable boost
				if(this.getCaseTab(p).getHouse().isCity())
				{
					boost = boost+2;
				}else {
				boost ++;
				}
			}
		}
		return boost;
	}
	
	public Case getCaseTab(int i)
	{
		return this.tableauCase[i/20][i%20];
	}
		
	public SystemData getSystemData()
	{
		return this.systData;
	}
	
	public PlayerData getPlayerData()
	{
		return this.playerdata;
	}
}
	