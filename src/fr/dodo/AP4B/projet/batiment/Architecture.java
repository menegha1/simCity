package fr.dodo.AP4B.projet.batiment;


import java.util.ArrayList;
import javax.swing.ImageIcon;

import fr.dodo.AP4B.projet.Case;
import fr.dodo.AP4B.projet.Element;

public abstract class Architecture {
	
	private String name; //nom de l'architecture
	protected ImageIcon image, image2, image3; //image des architectures
	private Element elemPositif; //element sur lequel le bâtiment produit plus
	protected ArrayList<Element> elemNeg = new ArrayList<Element>(); //element sur lequel le bâtiment ne peut être construit
	protected int niveau, duree; //niveau -> étape d'amélioration //duree -> temps de construction
	protected float prodElec, stockElec; //valeur de production et stockage de l'énergie
	protected float prix, pollution; // valeur du prix de l'architecture et du taux de pollution
	
	public Architecture(String n, float prodE, float stockE, float p ,float pol, int d, Element eP, Element eN)
	{
		this.name = n;
		this.prodElec = prodE;
		this.stockElec = stockE;
		this.prix = p;
		this.pollution = pol;
		this.elemPositif = eP;
		this.elemNeg.add(eN);
		this.duree = d; //met la durée de fabrication du bâtiment
		this.niveau = 1; //commence au niveau 1
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public float getProdElec()
	{
		return prodElec;
	}
	
	//permet la modification de la production en énergie
	public void setProdElec(Case c)
	{
		//si le bâtiment est sur une zone adaptée, la production est augmentée
		if(isElemPositif(c.getElement()))
		{
			System.out.println(" Boost elemebt");
			this.prodElec += 2;
		}
	}
	
	public void setProdElec(float newProdElec)
	{
		this.prodElec = newProdElec;
	}
	
	public float getStockElec()
	{
		return this.stockElec;
	}
	
	public float setStockElec(float newStockElec)
	{
		return this.stockElec = newStockElec;
	}
	
	public float getPrix()
	{
		return this.prix;
	}
	
	public float getPollution()
	{
		return this.pollution;
	}

	public int getDuree()
	{
		return this.duree;
	}
	
	public int getNiveau()
	{
		return this.niveau;
	}
	
	public ImageIcon getImage()
	{
		return this.image;
	}
	
	//test s'il s'agit d'une zone favorable
	public boolean isElemPositif(Element e) {
		return e.equals(this.elemPositif);
	}
	
	//test s'il s'agit d'une zone non constructible
	public boolean isElemNeg(Element e) {
		return this.elemNeg.contains(e);
	}
	
	//permet l'amélioration de l'architecture (changement d'image)
	public void upgrade()
	{
		//augmentation des capacités du bâtiment
		this.niveau++;
		this.prodElec *= 2;
		this.stockElec *= 2;
		this.prix *= 2;
			
		//changement d'image
		if(this.niveau == 2)
		{
			this.image = image2;
		}
		if(this.niveau == 3)
		{
			this.image = image3;
		}
	}

	public String toString()
	{
			return "Nom : " + this.name + "\nNiveau : " + this.niveau +
					"\nProd : " + this.prodElec + "\nStock : " + this.stockElec +
					"\nPrix : " + this.prix;
	}
	
}