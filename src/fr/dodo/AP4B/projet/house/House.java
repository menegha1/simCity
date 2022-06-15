package fr.dodo.AP4B.projet.house;

import javax.swing.ImageIcon;

public abstract class House {

	private String name; //nom du bâtiment
	protected ImageIcon image,image2,image3; //image des maisons
	private float habitant, consoElec; //nombre d'habitant et leur consommation en énergie
	private float prix; //prix des maisons
	private int duree, niveau; //niveau -> étape d'amélioration //duree -> temps de construction
	private boolean isCity; //booleen permettant de savoir si c'est une mairie ou une maison
	
	public House(boolean bool,int d,String n)
	{
		this.name = n;
		this.consoElec = 2;
		this.habitant = 5;
		this.prix = 50;
		this.duree = d;
		this.niveau = 1;
		this.isCity = bool;
	}
	
	public float getPrix()
	{
		return this.prix;
	}
	
	public void upgrade()
	{
		//mise à jour des données
		this.niveau++;
		this.prix = prix*2;
		this.habitant += 5;
		
		// changement d'image
		if(this.niveau == 2)
		{
			this.image = image2;
		}
		if(this.niveau == 3)
		{
			this.image = image3;
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public float getHabitant()
	{
		return this.habitant;
	}
	
	public float  getConso()
	{
		 return this.consoElec;
	}
	
	public int getDuree()
	{
		return this.duree;
	}
	
	public int getNiveau()
	{
		return this.niveau;
	}
	
	//retourne si la maison est une mairie ou non
	public boolean isCity()
	{
		return isCity;
	}
	
	public ImageIcon getImage()
	{
		return this.image;
	}
	
	public String toString()
	{
		return "habitant :" + this.habitant + "\nPrix : " + this.prix;
	}
}
