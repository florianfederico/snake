package Fenetres;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Scores.Score;

/**
 * 
 * @author GUILLAUME_Corentin
 * @version 1.0
 * 
 * Cette classe correspond au JPanel des meilleures scores.
 * C'est ici que les 5 meilleurs scores effectués s'affichent.
 *
 */


public class MeilleursScores extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int NB_MEILLEURS_SCORES = 5;
	private final String CHEMIN_FICHIER_SCORES = "images/scores.data";
	
	private JButton retour;
	private Score[] tabScores;
	
	private JLabel[] tabLabelNom;
	private JLabel[] tabLabelScore;
	private Font policeScores;
	
	private ObjectInputStream fileIn;
	private ObjectOutputStream fileOut;
	
	public MeilleursScores() {
		super();
		this.setLayout(null);
		
		this.initFichierScores();
		this.lireScores();
		this.afficher();
		
	}
	
	/**
	 * Cette méthode permet de charger une image en arrière plan.
	 */
	
	public void paintComponent(Graphics g){
		 try {
		      Image img = ImageIO.read(new File("images/meilleurs_scores.png"));
		      g.drawImage(img, 0, 0, this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }                
	}
	
	/**
	 * Cette méthode permet d'afficher les JLabels des noms et des scores
	 * Elle permet aussi de mettre à jour la JFrame juste après l'entrée d'un meilleur score
	 * pour éviter d'avoir à relancer le programme pour voir le nouveau meilleur score.
	 */
	
	public void afficher() {
		this.removeAll();
		this.policeScores = new Font("Tahoma", Font.BOLD, 32);
		
		this.tabLabelNom = new JLabel[this.NB_MEILLEURS_SCORES];
		for(int i=0; i < this.NB_MEILLEURS_SCORES; i++) {
			this.tabLabelNom[i] = new JLabel(this.tabScores[i].getNomJoueur());
			this.tabLabelNom[i].setFont(this.policeScores);
			this.add(this.tabLabelNom[i]);
		}
		
		this.tabLabelNom[0].setForeground(Color.YELLOW);
		this.tabLabelNom[1].setForeground(Color.GRAY);
		this.tabLabelNom[2].setForeground(Color.RED);
		this.tabLabelNom[3].setForeground(Color.GREEN);
		this.tabLabelNom[4].setForeground(Color.GREEN);
		
		this.tabLabelNom[0].setBounds(170, 105, 200, 40);
		this.tabLabelNom[1].setBounds(170, 165, 200, 40);
		this.tabLabelNom[2].setBounds(170, 225, 200, 40);
		this.tabLabelNom[3].setBounds(170, 285, 200, 40);
		this.tabLabelNom[4].setBounds(170, 345, 200, 40);
		
		this.tabLabelScore = new JLabel[this.NB_MEILLEURS_SCORES];
		for(int i=0; i < this.NB_MEILLEURS_SCORES; i++) {
			this.tabLabelScore[i] = new JLabel(this.tabScores[i].getValeurString());
			this.tabLabelScore[i].setFont(this.policeScores);
			this.add(this.tabLabelScore[i]);
		}
		
		this.tabLabelScore[0].setForeground(Color.YELLOW);
		this.tabLabelScore[1].setForeground(Color.GRAY);
		this.tabLabelScore[2].setForeground(Color.RED);
		this.tabLabelScore[3].setForeground(Color.GREEN);
		this.tabLabelScore[4].setForeground(Color.GREEN);
		
		this.tabLabelScore[0].setBounds(300, 105, 200, 40);
		this.tabLabelScore[1].setBounds(300, 165, 200, 40);
		this.tabLabelScore[2].setBounds(300, 225, 200, 40);
		this.tabLabelScore[3].setBounds(300, 285, 200, 40);
		this.tabLabelScore[4].setBounds(300, 345, 200, 40);
		
		this.retour = new JButton("Retour");
		this.add(retour);
		retour.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Principale.monCL.show(Principale.pan, "Menu");
			}
		});
		retour.setBounds(270, 410, 100, 30);
	}
	
	/**
	 * Cette méthode teste si le fichier score.data existe.
	 * Si c'est le cas, elle ne fait rien.
	 * Si ce n'est pas le cas, elle le crée et charge des noms et des scores par défault.
	 */
	
	public void initFichierScores() {
		this.fileIn = null;
		this.fileOut = null;
		
		this.tabScores = new Score[this.NB_MEILLEURS_SCORES];
		for(int i=0; i < this.NB_MEILLEURS_SCORES; i++) {
			this.tabScores[i] = new Score("AAA", 0);
		}
		
		try {
			this.fileIn = new ObjectInputStream(
		              		new BufferedInputStream(
		                      new FileInputStream(
		                        new File(this.CHEMIN_FICHIER_SCORES))));
		}catch (FileNotFoundException e) {
			
	         try {
				this.fileOut = new ObjectOutputStream(
			              		new BufferedOutputStream(
			                      new FileOutputStream(
			                        new File(this.CHEMIN_FICHIER_SCORES))));
				for(Score s : this.tabScores) {
					this.fileOut.writeObject(s);
				}
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try{
					if(this.fileOut != null) 
						this.fileOut.close();
				}catch(IOException e1) {
					e1.printStackTrace();
				}
			}
	         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	         try {
	            if (this.fileIn != null)
	               this.fileIn.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
		}
	}
	
	/**
	 * Cette méthode récupère les Objets Score du fichier scores.data dans
	 * le tableau de scores : tabScores
	 */
	
	public void lireScores() {
		// ********** LECTURE *********
		
		try {
			this.fileIn = new ObjectInputStream(
              					new BufferedInputStream(
              						new FileInputStream(
              							new File(this.CHEMIN_FICHIER_SCORES))));
			
			for(int i=0; i< this.NB_MEILLEURS_SCORES; i++) {
				this.tabScores[i] = ((Score)this.fileIn.readObject());
			}
			
		} catch(FileNotFoundException e3) {
			e3.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(this.fileIn != null)
						this.fileIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	/**
	 * Cette méthode écrit le nouveau score dans le fichier score.data
	 * et met à jour le tableau de scores puis l'affichage.
	 * 
	 * @param score 
	 * 		Le nouveau meilleur score.
	 * 
	 */
	
	public void ecrireMeilleurScore(Score score) {
		int rang; // par default le dernier
		
		if(score.getValeur() > this.tabScores[0].getValeur())
			rang = 0;
		else if(score.getValeur() > this.tabScores[1].getValeur())
			rang = 1;
		else if(score.getValeur() > this.tabScores[2].getValeur())
			rang = 2;
		else if(score.getValeur() > this.tabScores[3].getValeur())
			rang = 3;
		else
			rang = 4;

			
			for(int i=this.NB_MEILLEURS_SCORES-1; i > rang; i--) {
				this.tabScores[i] = this.tabScores[i-1];
			}
			this.tabScores[rang] = score;
			
		
		
		try {
			this.fileOut = new ObjectOutputStream(
		              		new BufferedOutputStream(
		                      new FileOutputStream(
		                        new File(this.CHEMIN_FICHIER_SCORES))));
			for(Score s : this.tabScores) {
				this.fileOut.writeObject(s);
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try{
				if(this.fileOut != null) 
					this.fileOut.close();
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		}
		
		this.afficher();
	}

	public Score[] getTabScores() {
		return tabScores;
	}
}
