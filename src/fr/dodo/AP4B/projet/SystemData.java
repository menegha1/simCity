	package fr.dodo.AP4B.projet;

import java.util.Random;

public class SystemData extends Thread {
	/*
	 * valeur en lien avec l'énergie (production, consommation total des habitants,
	 * stock maximum de la ville, énergie en stock, énergie du stock perdu, bonus de production)
	 */
	private float prodElec, consElec, stockMaxElec, stockElec, deficitElecStock, boostElec;
	
	/*
	 * valeur de satisfaction des habitants
	 * taux de pollution des architectures
	 * nombre d'habitant total
	 */
	private float satisfaction, pollution, habTotal;

	private Meteo meteo;
	
	public SystemData() 
	{
		this.habTotal = 5;
		this.prodElec = 0;
		this.consElec = 5;
		this.boostElec = 0;
		this.stockMaxElec = 0;
		this.stockElec = 0;
		this.satisfaction = 100;
		this.pollution = 0;
	}
	
	public void addBoostElec(float v)
	{
		this.boostElec += v;
	}
	
	public void setBoostElec(float b)
	{
		this.boostElec = b;
	}
	
	public float getBoosElec()
	{
		return this.boostElec;
	}
	
	public void setMeteo()
	{
		Random r= new Random();
		int num = r.nextInt(9);
		switch(num)
		{
			case 1 :
				this.meteo = Meteo.PLUIE;
			break;
			case 2 :
				this.meteo = Meteo.ORAGE;
			break;
			case 3,4,5:
				this.meteo = Meteo.VENT;
			break;
				
			default :
				this.meteo = Meteo.SOLEIL;
			break;
			
		}
	}
	
	public Meteo getMeteo()
	{
		return this.meteo;
	}
	
	public float getDeficitElecStock()
	{
		return this.deficitElecStock;
	}
	
	public void setDeficitElecStock(float p)
	{
		this.deficitElecStock = p;
	}
	
	//renvoie le nombre d'habitant total
	public float getHabTotal()
	{
		return this.habTotal;
	}
	
	//ajoute un nombre d'habitant au nombre d'habitant total
	public void addHabTotal(float newHabTotal)
	{
		this.habTotal += newHabTotal;
	}
	
	//renvoie la valeur de production d'énergie de la ville
	public float getProdElec()
	{
		return this.prodElec;
	}
	
	//ajoute une valeur à la production d'énergie de la ville
	public void addProdElec(float newProdElec)
	{
		this.prodElec += newProdElec;
	}
	
	//renvoie la valeur d'énergie stockée
	public float getStockElec()
	{
		return this.stockElec;
	}
	
	//modifie la valeur d'énergie stockée
	public void setStockElec(float newStockElec)
	{
		this.stockElec = newStockElec;
	}
	
	//ajoute une valeur à l'énergie stockée
	public void addStockElec(float newStockElec)
	{
		this.stockElec += newStockElec;
	}
	
	//renvoie la valeur d'énergie stockable
	public float getStockMaxElec()
	{
		return this.stockMaxElec;
	}
	
	//ajoute une valeur à l'énergie stockable
	public void addStockMaxElec(float newElecTotal)
	{
		this.stockMaxElec += newElecTotal;
	}
	
	//renvoie la satisfction de la population de la ville
	public float getSatisfaction()
	{
		return this.satisfaction;
	}
	
	//modifie la satisfaction de la population de la ville
	public void setSatisfaction(float newSatisfaction)
	{
		this.satisfaction = newSatisfaction;
	}
	
	//revoie le taux de pollution global
	public float getPollution()
	{
		return this.pollution;
	}
	
	//ajoute une valeur au taux d pollution global
	public void addPollution(float newPollution)
	{
		this.pollution += newPollution;
	}
	
	//renvoie la consommation en énergie de la ville
	public float getConsElec()
	{
		return this.consElec;
	}
	
	//ajoute une valeur à la consommation en énergie de la ville
	public void addConsElec(float newConsElec)
	{
		this.consElec += newConsElec;
	}
	
	public String toString() {
		return "\n\nHabitant total : " + habTotal + 
				"\nElectricité total : " + stockMaxElec + "\nSatisfaction : " + satisfaction +
				"\nPollution : " + pollution + "\nConsommation Electrique : " + consElec;
	}
}