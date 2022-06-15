package fr.dodo.AP4B.projet;

import java.io.IOException;

import fr.dodo.AP4B.projet.graphique.displayGame;

public class Main_game  {
	
	public static void main(String[] args) throws IOException
	{
		GameMaster game = new GameMaster();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               displayGame d = new displayGame(game);
                game.setDisplayGame(d);
            }
        });
	}
	
}