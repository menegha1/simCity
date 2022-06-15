package fr.dodo.AP4B.projet;

public class PlayerData {
	
private float argent;
	
	public PlayerData() 
	{
		this.argent = 200;
	}
	
	public float getArgent ()
	{
		return this.argent;
	}
	
	public void setArgent(float a)
	{
		this.argent = a;
	}
	
	public void addArgent(float a)
	{
		this.argent += a;
	}
	
	public void subArgent(float a)
	{
		this.argent -= a;
	}
	
	public String toString()
	{
		return "Argent joueurs : " + argent;
	}
}
