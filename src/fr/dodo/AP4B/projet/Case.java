package fr.dodo.AP4B.projet;

import fr.dodo.AP4B.projet.batiment.*;
import fr.dodo.AP4B.projet.batiment.combustible.Combustible;
import fr.dodo.AP4B.projet.batiment.eolienne.Eolienne;
import fr.dodo.AP4B.projet.batiment.geothermie.Geothermie;
import fr.dodo.AP4B.projet.batiment.hydraulique.Hydraulique;
import fr.dodo.AP4B.projet.batiment.nucleaire.Nucleaire;
import fr.dodo.AP4B.projet.batiment.solaire.Solaire;
import fr.dodo.AP4B.projet.batiment.stockage.Stockage;
import fr.dodo.AP4B.projet.house.House;

public class Case {
	private Architecture architecture;
	private House house;
	private Element element;
	private int id;
	
	public Case() 
	{
		element = Element.NORMAL;
		id = 0;
	}
	
	public Case(int i)
	{
		id = i;
		element = this.setElement();
	}
	
	//renvoie l'architecture de la case s'il y en a une
	public Architecture getArchitecture()
	{
		if(this.isArchitecture())
		{
			return this.architecture;
		}else{
			return null;
		}
	}
	
	// place une architecture a
	public void setArchitecture(Architecture a)
	{
		this.architecture = a;
	}
	
	//place une architecture en fonction du numéro associé
	public void setArchitecture(int i)
	{
		switch(i) {
		case 0: 
			this.architecture = new Stockage();
			break;
		case 1:
			this.architecture = new Geothermie();
			break;
		case 2:
			this.architecture =new Hydraulique();
			break;
		case 3:
			this.architecture = new Solaire();
			break;
		case 4:
			this.architecture = new Eolienne();
			break;
		case 5:
			this.architecture = new Combustible();
			break;
		case 6:
			this.architecture = new Nucleaire();
			break;
		default :
			this.architecture =null;
			break;
		}
	}
	
	//renvoie la maison de la case s'il y en a une
	public House getHouse()
	{
		if(this.isHouse())
		{
			return this.house;
		}else{
			return null;
		}
	}
	
	public void setHouse(House h)
	{
		this.house = h;
	}
	
	public Element getElement()
	{
		return this.element;
	}
	
	//permet l'attribution d'un élément en fonction de la case (génération du plateau)
	public Element setElement()
	{
			int i= this.id/20;
			int j = this.id%20;
			//carré vert haut gauche	
			if( (i <= 2 && j <= 4) || ( (i == 9 || i == 10) && j >= 13) || ( i == 8 && j>= 15) || ( i == 7 && j >= 16) || (i == 11 && j >= 14))
			{
				return Element.FORET;
			}
			if( (i <= 3 && j >= 14) || (j == 0 && (i>= 4 && i<= 7)) || ((j==1 || j== 2) && (i >= 5 && i<= 7 )) || (j == 3 && (i >= 6 && i<= 7)))
			{
				return Element.MONTAGNE;
			}
			if( i<= 7)
			{
				if((j == 0 && i>= 4 ) || ((j==1 || j== 2) && i >= 5) || (j == 3 && i >= 6) )
				{
					return Element.MONTAGNE;
				}
			}
			
			if((i == 1 && ( j >= 10 && j <= 14)) || ( i == 0 && ( j >= 7 && j<= 9)) || ( i == 2 && j == 9) || ( i==3  && (j== 8 || j == 7)))
			{
				return Element.RIVIERE;
			}
			if(( i == 4 &&  j >=  15) || ( i == 5 && j>= 17 ) || ( i == 6 && j>= 18))
			{
				return Element.RIVIERE;
			}
			if( (i == 9 && j <= 3) || ( i == 10 && j <= 7) || ( i == 11 && j <= 10 ))
			{
				return Element.DESERT;
			}
			if( (i == 8 && j<= 4) || (i == 9 && (j >= 4 && j <= 8)) || ( i == 10 &&( j >= 8 && j<= 11)) || ( i== 11 && (j >= 11 && j <= 13)))
			{
				return Element.RIVIERE;
			}
			return Element.PLAINE;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	//vérifie si la case contient est vide
	public boolean isEmpty()
	{
		if(architecture == null && house == null)
		{
			return true;
		}else {
			return false;
		}
	}
	
	//vérifie si la case contient une architecture
	public boolean isArchitecture()
	{
		if(architecture == null)
		{
			return false;
		}else {
			return true;
		}
	}
	
	//vérifie si la case contient une maison
	public boolean isHouse()
	{
		if(house == null)
		{
			return false;
		}else {
			return true;
		}
	}
	
	public String toString()
	{
		if(isHouse()) 
		{
			return " Case id : " + id + " / Installation : " 
			+ getHouse() + " / Element : " + element.getTextToFind();
		}else {
			return " Case id : " + id + " / Installation : "
			+  getArchitecture()+ " / Element : " + element.getTextToFind();
		}
	}
}
