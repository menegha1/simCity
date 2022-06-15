package fr.dodo.AP4B.projet.graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.dodo.AP4B.projet.GameMaster;


public class displayGame extends JFrame{
	
	GameMaster game ;
	TopPanel top;
	GridDisplay grid;
	ScrollDisplay scroll;
	public displayGame(GameMaster g) {
		super("DisplayGame");
		game = g ;
		/*
		 * On crée un panel qui s'affichera en premier 
		 */
		ImageIcon img = new ImageIcon(this.getClass().getResource("environnement.jpg"));
		Image scaleImage = img.getImage().getScaledInstance(800,300,Image.SCALE_DEFAULT);
    	img.setImage(scaleImage);
		JPanel panel = new JPanel(){
			 @Override public void paintComponent(Graphics g) {
		            g.drawImage(img.getImage(), 0, 0, null);
		            super.paintComponent(g);
		        }
		};	
		panel.setPreferredSize(new Dimension(500,500));

		panel.setOpaque(false);
		/*
		 * on crée un bouton qui permettra de lancer la parti ( newDisplay)
		 */
		JButton b = new JButton(" Lancez la parti ! ");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 displayGame.this.getContentPane().removeAll();
				  newDisplay();
				  game.start();//on lance le gameMaster
			}
		  	});
		panel.add(b,BorderLayout.CENTER);
        setSize(800, 300);
		this.getContentPane().add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void  newDisplay()
	{
		/*
		 * affiche  les panels permettant de jouer la game
		 */
		
		top = new TopPanel(game);
		grid = new GridDisplay(game);
		scroll = new ScrollDisplay(game);
        add(scroll, BorderLayout.EAST);
        add(grid,BorderLayout.CENTER);
        add(top,BorderLayout.NORTH);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
	
	public void setRessource()
	{
		/*
		 * permet d'update l'affichage de TopPanel();
		 */
		this.top.setTextRessource();
	}

}
