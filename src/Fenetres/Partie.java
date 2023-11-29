package Fenetres;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Cases.Bonus;
import Cases.Case;
import Cases.CaseVide;
import Cases.Obstacle;
import Cases.TypeCase;
import Jeu.Direction;
import Jeu.Quadrillage;
import Jeu.Snake;
import Scores.Score;

/**
 * 
 * @author FEDERICO_Florian
 * @version 1.0
 * 
 * Cette classe gère le déroulement d'une partie.
 *
 *
 *@see Snake
 *@see Quadrillage
 */

public class Partie extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int TAILLE_QUADRILLAGE_X = 40;
	private final int TAILLE_QUADRILLAGE_Y = 20;
	
	private final int SNAKE_POS_INIT_X = 20;
	private final int SNAKE_POS_INIT_Y = 10;
	
	//****** Quadrillage ******/
	private Case[][] tabTypeCase;
	private Case[][] tabSave;
	private Quadrillage quadrillage;
	
	private Snake snake;
	private Bonus bonus;
	private boolean continuerPartie;
	
	//*** Score ***/
	private int score;
	private JLabel labelScore;
	private Font policeScore;
	
	//*** Temps ****/
	private int tempsPartie;
	private final int TEMPS_SLEEP = 5;
	
	//*** Pause ***/
	private boolean pause = false;
	
	//*** Fin de la partie **/
	private String nomJoueur;
	private int rejouer;
	
	public Partie() {
		this.setLayout(new BorderLayout());
		Dimension dim = new Dimension(10,50);
		this.add(new Box.Filler(dim, dim, dim), BorderLayout.NORTH);
		
		//***** INITIALISATION DES VARIABLES *******/
		
		//****** Quadrillage ******/
		this.tabTypeCase = new Case[this.TAILLE_QUADRILLAGE_X][this.TAILLE_QUADRILLAGE_Y];
		tabSave=new Case[this.TAILLE_QUADRILLAGE_X][this.TAILLE_QUADRILLAGE_Y];
		this.snake = new Snake(this.SNAKE_POS_INIT_X, this.SNAKE_POS_INIT_Y, this.TAILLE_QUADRILLAGE_X, this.TAILLE_QUADRILLAGE_Y);
		this.bonus = new Bonus();
		this.continuerPartie = true;
		
		//**** Bords ***/
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.WEST);
		
		//*** Score ***/
		this.score = 0;
		this.labelScore = new JLabel("                  " +score);
		this.policeScore = new Font("Tahoma", Font.BOLD, 38);
		this.labelScore.setFont(policeScore);
		this.labelScore.setHorizontalAlignment(JLabel.CENTER);
		this.add(labelScore, BorderLayout.SOUTH);
		this.tempsPartie = 0;
		
		//****** CREATION DU QUADRILLAGE ********/

		for(int x=0; x < this.TAILLE_QUADRILLAGE_X; x++) {
			for(int y=0; y < this.TAILLE_QUADRILLAGE_Y; y++) {
				if(x == 0 || x == this.TAILLE_QUADRILLAGE_X-1 || y == 0 || y == this.TAILLE_QUADRILLAGE_Y-1)
				{
					this.tabTypeCase[x][y] = new Obstacle();//this.obstacle;
					this.tabSave[x][y] = new Obstacle();//this.obstacle;
				}
				else
				{
					this.tabTypeCase[x][y] = new CaseVide();//this.casesVide;
					this.tabSave[x][y] = new CaseVide();//this.casesVide;
				}
			}
		}
		
	    
	    
		this.quadrillage = new Quadrillage(this.TAILLE_QUADRILLAGE_X, this.TAILLE_QUADRILLAGE_Y);
		this.add(quadrillage, BorderLayout.CENTER);
		this.actualiserQuadrillage();
		
	}

	/**
	 * Cette méthode permet de charger une image en arrière plan.
	 */
	
	public void paintComponent(Graphics g){
		 try {
		      Image img = ImageIO.read(new File("images/partie.png"));
		      g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }                
	}
	
	/**
	 * Cette méthode est appelée par la méthode .start sur le Thread dans PanneauPartie
	 * Elle permet de lancer la partie.
	 */
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean hitBonus=false;
		
		this.addKeyListener(new ClavierListener());
		JOptionPane.showMessageDialog(null, "Flèches directionnelles pour diriger le Snake\n"
				+ "Touche Espace pour mettre le jeu en pause\n\n"
				+ "Bonne chance !", "Information", JOptionPane.INFORMATION_MESSAGE);
		
		// On ajoute une pause de 1s pour ne pas surprendre le joueur.
		try {
			Thread.sleep(1000);
			this.requestFocus();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		//***** DEBUT DE LA PARTIE *******/
		
		while(this.continuerPartie) {
			
			//** Gestion de la pause déclenchée par le joueur **/
			if(this.pause) {
				while(this.pause) {
					try {
						Thread.sleep(this.TEMPS_SLEEP);
						this.requestFocus(); // permet de garder le focus du clavier
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			this.requestFocus();	// permet de garder le focus du clavier
			
			//** Gestion des déplacements du snake **/
			
			// on teste si le snake doit avancer ou pas
			if(this.tempsPartie - snake.getTempsDerniereAction() == snake.getVITESSE()) {
				
				// On teste si le snake ne va pas en collision avec un obstacle
				if(!this.detectionCollisions()) {
					snake.setTempsDerniereAction(this.tempsPartie);
					
					 if(this.detectionBonus()) {
					      hitBonus=true;
					      snake.grandir();
					      this.bonus.setReapparaitre(true);
					 }
					 
					 snake.avancer();
					 this.actualiserQuadrillage();
					 if(hitBonus)
					 {
						 this.score += 10;
					     this.labelScore.setText("                  " +this.score);
					     hitBonus=false;
					     quadrillage.validate();
					     validate();
					 }
				}
				else
					this.continuerPartie = false;
			}
			
			//** Gestion de la pause nécessaire pour ne pas surcharger le CPU **/
			try {
				Thread.sleep(this.TEMPS_SLEEP);
				this.tempsPartie += this.TEMPS_SLEEP; 
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		//** FIN DE LA PARTIE **/
		
		// Si le joueur a battu le 5eme meilleur score
		if(this.score > Principale.meilleursScores.getTabScores()[4].getValeur()) {
			do {
				nomJoueur = JOptionPane.showInputDialog(null, "*** Nouveau meilleur score ***\n\nVotre score est de : " +this.score+ 
						"\nEntrez votre nom (3 lettres) :" , "Nouveau meilleur score !", JOptionPane.QUESTION_MESSAGE);
				
				// Si le joueur n'a pas appuyé sur 'Annuler'
				if(nomJoueur != null) {
					if(nomJoueur.length() > 3 || nomJoueur.length() < 3) {
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nomJoueur de 3 lettres", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				// Tant que le joueur clique sur 'Annuler' ou entre un nom à plus ou moins 3 lettres
			} while(nomJoueur == null || nomJoueur.length() < 3 || nomJoueur.length() > 3);
			nomJoueur = nomJoueur.toUpperCase();
			
			// On écrit le meilleur score
			Principale.meilleursScores.ecrireMeilleurScore(new Score(nomJoueur, this.score));
		}
		else
			JOptionPane.showMessageDialog(null, "Vous n'avez battu aucun meilleur score ...", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		
		this.rejouer = JOptionPane.showConfirmDialog(null, 
		        "Voulez-vous rejouer ?", 
		        "Rejouer", 
		        JOptionPane.YES_NO_OPTION, 
		        JOptionPane.QUESTION_MESSAGE);
		
		if(this.rejouer == JOptionPane.OK_OPTION) {
			Principale.monCL.show(Principale.pan, "Menu");
			Principale.panPartie.nouvellePartie();
			Principale.monCL.show(Principale.pan, "Partie");
		}
		else if(this.rejouer == JOptionPane.NO_OPTION) {
			Principale.monCL.show(Principale.pan, "Menu");
		}
		
	}
	
	/**
	 * Cette méthode permet de placer les éléments dans 
	 * le tableau de types de cases en fonction de leur position.
	 * 
	 * D'abord elle remplit le quadrillage de cases vides en laissant les obstacles.
	 * Puis elle place la tête et le corps du snake.
	 * Et enfin elle place le bonus en le repositionnant ou non.
	 * 
	 * Pour finir, elle recrée le quadrillage et elle valide l'affichage.
	 */
	
	public void actualiserQuadrillage() { 
		int posX, posY;
		
		for(int x=0; x < this.TAILLE_QUADRILLAGE_X; x++) {
			for(int y=0; y < this.TAILLE_QUADRILLAGE_Y; y++) {
				if(!(x == 0 || x == this.TAILLE_QUADRILLAGE_X-1 || y == 0 || y == this.TAILLE_QUADRILLAGE_Y-1))
					this.tabTypeCase[x][y] = tabSave[x][y];//this.casesVide;
			}
		}
		posX = snake.getTete().getPosX();
		posY = snake.getTete().getPosY();
		this.tabTypeCase[posX][posY] = snake.getTete();
		
		for(int i = 0; i < snake.getTailleCorpsSnake(); i++) {
			posX = snake.getCorps()[i].getPosX();
			posY = snake.getCorps()[i].getPosY();
			this.tabTypeCase[posX][posY] = snake.getCorps()[i];
		}

		if(this.bonus.isreapparaitre()) {
			bonus.setPos(this.tabTypeCase, this.TAILLE_QUADRILLAGE_X, this.TAILLE_QUADRILLAGE_Y);
		}
		posX = this.bonus.getPosX();
		posY = this.bonus.getPosY();
		this.tabTypeCase[posX][posY] = bonus;
		
		this.quadrillage.actualiser(this.tabTypeCase);
		this.validate();
	}
	
	/**
	 * Cette méthode permet de détecter si le prochain déplacement du snake va le faire
	 * entrer en collision avec un obstable ou non.
	 * 
	 * @return Elle renvoie vrai si il va y avoir une collision ou faux sinon.
	 */
	
	public boolean detectionCollisions() {
		boolean detection = false;
		int posX = snake.getTete().getPosX();
		int posY = snake.getTete().getPosY();
		
		switch(snake.getDirection()) {
		case GAUCHE:
			if(this.tabTypeCase[posX-1][posY].type() == TypeCase.Obstacle || this.tabTypeCase[posX-1][posY].type() == TypeCase.CorpsSnake)
				detection = true;
			break;
		case DROITE:
			if(this.tabTypeCase[posX+1][posY].type() == TypeCase.Obstacle || this.tabTypeCase[posX+1][posY].type() == TypeCase.CorpsSnake)
				detection = true;
			break;
		case HAUT:
			if(this.tabTypeCase[posX][posY-1].type() == TypeCase.Obstacle || this.tabTypeCase[posX][posY-1].type() == TypeCase.CorpsSnake)
				detection = true;
			break;
		case BAS:
			if(this.tabTypeCase[posX-1][posY+1].type() == TypeCase.Obstacle || this.tabTypeCase[posX][posY+1].type() == TypeCase.CorpsSnake)
				detection = true;
			break;
		}
		return detection;
	}
	
	/**
	 * Cette méthode permet de savoir si le prochain déplacement du snake va le placer sur un bonus.
	 * 
	 * @return Elle renvoie vrai si il y a un bonus et faux sinon.
	 */
	
	public boolean detectionBonus() {
		boolean detecte = false;
		int posX = snake.getTete().getPosX();
		int posY = snake.getTete().getPosY();
		
		switch(snake.getDirection()) {
		case GAUCHE:
			if(this.tabTypeCase[posX-1][posY].type() == TypeCase.Bonus)
				detecte = true;
			break;
		case DROITE:
			if(this.tabTypeCase[posX+1][posY].type() == TypeCase.Bonus)
				detecte = true;
			break;
		case HAUT:
			if(this.tabTypeCase[posX][posY-1].type() == TypeCase.Bonus)
				detecte = true;
			break;
		case BAS:
			if(this.tabTypeCase[posX][posY+1].type() == TypeCase.Bonus)
				detecte = true;
			break;
		}
		return detecte;
	}
	
	
	
	class ClavierListener implements KeyListener{
		public void keyPressed(KeyEvent event) {
			
			switch(event.getKeyCode()) {
			case 32: // touche espace
				if(pause)
					pause = false;
				else
					pause = true;
				break;
			}
			
			if(snake.isaAvancerVersDerniereDirection()) {
				switch(event.getKeyCode()) {
				case 37:
					if(snake.getDirection() != Direction.DROITE)
						snake.setDirection(Direction.GAUCHE);
					break;
				case 38:
					if(snake.getDirection() != Direction.BAS)
						snake.setDirection(Direction.HAUT);
					break;
				case 39:
					if(snake.getDirection() != Direction.GAUCHE)
						snake.setDirection(Direction.DROITE);
					break;
				case 40:
					if(snake.getDirection() != Direction.HAUT)
						snake.setDirection(Direction.BAS);
					break;
				}
				
				snake.setaAvancerVersDerniereDirection(false);
			}
			
	    }

		@Override
		public void keyReleased(KeyEvent event) {
			// TODO Auto-generated method stub
			
			switch(event.getKeyCode()) {
			case 37:
				if(snake.getDirection() != Direction.DROITE)
					snake.setDirection(Direction.GAUCHE);
				break;
			case 38:
				if(snake.getDirection() != Direction.BAS)
					snake.setDirection(Direction.HAUT);
				break;
			case 39:
				if(snake.getDirection() != Direction.GAUCHE)
					snake.setDirection(Direction.DROITE);
				break;
			case 40:
				if(snake.getDirection() != Direction.HAUT)
					snake.setDirection(Direction.BAS);
				break;
			}
		}

		@Override
		public void keyTyped(KeyEvent event) {
			// TODO Auto-generated method stub
		}    
	}	
}
