package fr.dodo.AP4B.projet.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import fr.dodo.AP4B.projet.Case;
import fr.dodo.AP4B.projet.GameMaster;
/* 
 * On crée une classe MouseC héritant de MouseAdapter ( une classe de swing) permettant
 * à l'utilisateur d'interagir avec la Case en générale 
 * On override les fonctions qu'on souhaite, soit mousePressed(Event e) dans notre cas
 * 
 */
class MouseC extends MouseAdapter 
{

	@Override public void mousePressed(MouseEvent e)
	{
		JComponent c = (JComponent) e.getSource();// on récupère le composant de la source
	    TransferHandler handler = c.getTransferHandler();
	    JLabel l = ((JLabel) c);//on le cast en JLabel
	    if(l.getIcon() != null)
	    {
	    	handler.setDragImage( ((ImageIcon) l.getIcon()).getImage());
	    }
	    //s'enclenche  lorsqu'on drag l'Image
	    handler.exportAsDrag(c, e, TransferHandler.COPY);
	}
}


public class CaseAffichage extends JLabel
{

	private final int indexCase; // index de la case 
	private int indexBat;// index du bâtiment qui sera construit sur la case
	private GameMaster game;//on lie cette dernière au gameMaster
	private boolean isGrid; // permet de savoir si la case est sur la grille ou pas
	
	
	private static final TransferHandler TRANSFER_H = new TransferHandler("icon") {
		
		/*
		 * On créer un objet TransferHandler pour chaque case
		 * 
		 * On override les classes exportAsDrag(), importData(), afin qu'elle corresponde à ce qu'on souhaite
		 */
		
		private int dataSource= 0; // représente l'index de la case Source
		private int dataDestination = 0; // représente l'index de la case Destination
		
		private CaseAffichage currentCase;//case correspondant à la caseSource
		private ImageIcon img;// image qui sera transférer
		
		@Override public void exportAsDrag(JComponent comp, InputEvent e, int action)
		{
			
			CaseAffichage label = (CaseAffichage) comp;
			//on vérifie si la case Source qu'on drag comporte une case
			if(label.getIcon() == null)
			{
				//on fait rien
			}else {
				//on affecte aux attributs  les valeurs de la case Source
				currentCase = label;
				img =(ImageIcon) label.getIcon();
				dataSource = label.indexCase;
				//on effectue ensuite la méthode drag
			    super.exportAsDrag(comp, e, action);
			}
			
		    
		}
		
		
		@Override public boolean importData(JComponent comp, Transferable data)
		{
			CaseAffichage label = (CaseAffichage) comp;
			
			GameMaster g = label.game;//on recupère le gameMaster 
			
			//label.getDropTarget().setActive(false);
			dataDestination = label.indexCase; // on affecte l'index de la case Destination
			
			//on test si le joueur clique sur la même case que la case Sourec
			if(!currentCase.equals(label))
			{
				//Si non, on test si la case Source provient de la grille ou pas
				if(currentCase.isGrid)
				{
					//on fait rien
				}else {
					
					// on test si la case Destination ne contient pas d'image ( soit le bâtiment)
					if(label.getIcon() == null)
					{
						//si oui, on test si le déplacement est possible
						if(g.isSetPossible(dataDestination,dataSource))
						{
							// si oui, alors on affecte l'index du bâtiment  à la Case Destination ( appartenant à la grille)
							label.indexBat = dataSource;
							
							
							int time;// correspondera à la durée de construction
							
							if(dataSource == 0)// on teste si le bâtiment est une maison
							{
								time = g.getListHouse(dataSource).getDuree();
								//on affecte la durée du bâtiment correspondant
							}else {
								time = g.getListArch(dataSource-1).getDuree();
							}
							//on déclenche le timer sur la case Destination
							label.setTimer(label,img,dataSource,dataDestination,time);
							
							
						}else {
							//si déplacement impossible
							JOptionPane.showMessageDialog( null,
					                  " C'est impossible de construire",
					                  "Invalid Input", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						//si il y a déjà une image ( bâtiment)
						JOptionPane.showMessageDialog( null,
				                  " il y a déjà un emplacement",
				                  "Invalid Input", JOptionPane.ERROR_MESSAGE);
					}
				}
			}else {
				//si oui,, on test si c'est une case de la grille
				if(!currentCase.isGrid)
				{
					return false;
				}
				//si non , on récupère la case Source , puis on test si c'est une maison ou pas
				if(g.getCaseTab(label.indexCase).isHouse())
				{
					if(g.getCaseTab(label.indexCase).getHouse().isCity())
					{
						//si c'est la mairie
						JOptionPane.showMessageDialog( null,
				                  " impossible d'améliorer et vendre City",
				                  "Invalid Input", JOptionPane.ERROR_MESSAGE);
						return false;
					}
				}
				//on affiche la boîte de dialog
				MyDialog m = new MyDialog(null, "My Dialog",label);
				m.setVisible(true);
				
				return false;
			}
		    return true;
		}
	};
	
	public boolean getIsGrid()
	{
		return this.isGrid;
	}
	
	public void setTimer(CaseAffichage comp, ImageIcon img, int dataS, int dataD, int t)
	{
		//On crée un objet Timer()
		Timer timer = new Timer();
		
		comp.getDropTarget().setActive(false);
		//on soustrait l'argent du batiment sur la case index dataS
        game.subArgentBat(dataS);
        
        //on lance le timer qui s'effectue toutes les secondes
        timer.scheduleAtFixedRate(new TimerTask() {
        	
        
        	int temp = t;// durée de construction
     
            public void run(){
            	
            	comp.setIcon(null);
            	comp.setForeground(Color.WHITE);
                comp.setText(""+temp);
              
                
                temp--;
                
                if( temp == -1)
                {
                	//timer fini, on installe le bâtiment  et l'image
                	
                	timer.cancel();
                	comp.setText("");
                	Image scaleImage = img.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
                	img.setImage(scaleImage);
                	comp.setIcon(img);
                	game.setBat(dataD, dataS);	
                	comp.getDropTarget().setActive(true);
                }
            }
        }, 0, 1000);
	}
	
	
	public CaseAffichage(ImageIcon img, int row , int col,boolean isGrille, GameMaster g) {
		// TODO Auto-generated constructor stub
		super(img);
		this.game = g;
		
		
		MouseC listener = new MouseC();
		Border border = null;
		// on affecte le MouseListener et le TransferHandler
		addMouseListener(listener);
		setTransferHandler(TRANSFER_H);
		
		this.isGrid = isGrille;
		this.setOpaque(true);// pour afficher le backGround de la Case
		
		if(isGrid) // si la case est dans la grille
		{
			setPreferredSize(new Dimension(50,50));
			this.indexCase = (row+20*col);
			
			//on place une mairie au centre ( l'image)
			if(indexCase == 129)
			{
				this.setIcon(g.getCaseTab(indexCase).getHouse().getImage());
			}
			//on set les bordure des cases
			
			if (row < 	12) {
				if (col < 20) {
					border = new MatteBorder(2, 2, 0, 0, Color.GRAY);
				} else {
						border = new MatteBorder(2, 2, 0, 2, Color.GRAY);
				}
			} else {
				if (col < 20) {
					border = new MatteBorder(2, 2, 2, 0, Color.GRAY);
				} else {
					border = new MatteBorder(2,2, 2, 2, Color.GRAY);
				}
			}
			setBorder(border);
			
			//on set la couleur de la case 
			Color c =this.game.getCaseTab(indexCase).getElement().getColor();
			this.setBackground(c);
		}else {
			//si il appartient à la ScrollBar , on set ses attributs
			
			setPreferredSize(new Dimension(100,100));
			border = new MatteBorder(2,0,0,0,Color.BLACK);
			setBorder(border);
			this.indexCase = row;
		}
	}
	public GameMaster getGame()
	{
		return this.game;
	}
	public int getIndexCase()
	{
		return this.indexCase;
	}
	
	/*
	 * méthode permettant d'update l'affichage de la case quand on upgrade un bâtiment
	 */
	public void upgradeAffichage()
	{
		if(this.getIcon() != null)
		{
			Case c = this.game.getCaseTab(indexCase);
			if(c.isArchitecture())
			{
			this.setIcon(c.getArchitecture().getImage());
			}else {
				this.setIcon(c.getHouse().getImage());
			}
		}
	}
}
/*
 * On crée 2 classes MyDialog et MyDialog2 héritant JDialog
 * Elles permettent d'afficher une boîte de dialogue en fonction des actions de l'utilisateurs
 */

/*
 * Gère la première boite de dialogue quand l'utilisateur clique sur un bâtiment
 */
class MyDialog extends JDialog {
	
		//on attribut deux boutons
	   private JButton amelioBtn = new JButton("Améliorer");
	   private JButton  vendreBtn = new JButton("vendre");
	   private boolean bool;
	   
	   public MyDialog(JFrame frame, String title, CaseAffichage c) {
		   
	     super(frame, title, true);
	     //on récupère les attributs de la Case 
	     int indexCase = c.getIndexCase();
	 	 Case caseGame = c.getGame().getCaseTab(indexCase);//Case du GameMaster
	 	 String textCase ;
	 	 if(caseGame.isHouse())
	 	 {
	 		 textCase = caseGame.getHouse().getName() + " /"+  "niveau: " + caseGame.getHouse().getNiveau();
	 	 }else {
	 		 textCase = caseGame.getArchitecture().getName() + " /"+  "niveau: " + caseGame.getArchitecture().getNiveau();
	 	 }
	 	 //nouveau panel pour afficher les deux boutons
	      JPanel panel = new JPanel();
	      
	      panel.add(new JLabel(textCase+ " \n Que souhaitez-vous faire ?"));
	      panel.add(amelioBtn);
	      panel.add(vendreBtn);
	      //on config les events des boutons
		  //puis on crée la deuxième boîte de dialogue en fonction du bouton cliqué 
	      amelioBtn.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent arg0) {
	    		  
	    		  MyDialog.this.setVisible(false);
	    	      MyDialog2 m = new MyDialog2(null,"","améliorer",c);
	    	      m.setVisible(true);
	    		  bool = m.isTrue();
	    	  }
	      });
	      vendreBtn.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent arg0) {
	    		  System.out.println(" Vente ");
	    		  MyDialog.this.setVisible(false);
	    		  MyDialog2 m = new MyDialog2(frame,title,"vendre", c);
	    		  
	    		  m.setVisible(true);
	    		  bool = m.isTrue();
	    	  }
	      });
	      //on ajoute le panel à la boite de dialogue
	      add(panel);
	      pack();
	      setLocationRelativeTo(frame);
	   }
	   
	   public boolean isTrue()
	   {
	   	return this.bool;
	   }
	}
class  MyDialog2 extends JDialog{
	private JLabel text = new JLabel();
	
	//deux boutons crées
	private JButton ouiBtn = new JButton("Oui");
	private JButton  nonBtn = new JButton("Non");
	//permet de savoir si le joueur a appuyé sur oui
	private boolean bool;
	   
	public MyDialog2(JFrame frame, String title, String t, CaseAffichage c)
	{
		super(frame,title,true);
		JPanel panel = new JPanel();
		Case caseGame =c.getGame().getCaseTab(c.getIndexCase());
		
		//on affecte le texte en fonction du bouton cliqué, soit améliorer ou vente
		if(t.equals("améliorer"))
		{
			if(caseGame.isHouse())
			{
				this.text.setText(" Etes vous sûr de vouloir " + t + "pour :"+ c.getGame().getCaseTab(c.getIndexCase()).getHouse().getPrix() * 2 + "$ ");
			}else {
				this.text.setText(" Etes vous sûr de vouloir " + t + " pour : " + c.getGame().getCaseTab(c.getIndexCase()).getArchitecture().getPrix() * 2 + " $" );
			}
		}else { 
			this.text.setText(" Etes vous sûr de vouloir " + t );
		}
		 	 
		panel.add(text);
		panel.add(ouiBtn);
		panel.add(nonBtn);
		// evenement si l'utilisateur clique sur oui
		ouiBtn.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent arg0) {
			 // si c'est vente , on supprime le batiment
			 if( t.equals("vendre"))
		  		{
				 	c.setIcon(null);
			  		c.getGame().removeBat(c.getIndexCase());;
			  		System.out.println(" vendre ");
		  		}else {
		  			//sinon on l'améliore
		  			if(t.equals("améliorer"))
		  			{
		  				//on vérifie si le batiment peut être améliorer
		  				if(c.getGame().upgradeBat(c.getIndexCase()))
		  				{
		  					c.upgradeAffichage();
		  				}
		  			}
		  		
		  		}
		  			MyDialog2.this.bool = true;
		  			MyDialog2.this.setVisible(false);
	  		}
	 	});
	 	nonBtn.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent arg0) {
		  	System.out.println(" Non");
		  	MyDialog2.this.bool = false;
		  	MyDialog2.this.setVisible(false);
	  			}
  		});
	 	//on ajoute le panel à la boite de dialogue
  			add(panel);
  			pack();
  			setLocationRelativeTo(frame);
  			
	 
		}

		public boolean isTrue()
		{
			return this.bool;
		}
}


