import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
@SuppressWarnings("serial")
public class PanelAction extends JPanel implements ActionListener, KeyListener{
	
	boolean finTour;
	int cpt;
	Carte carte;
	Personnage perso;
	PanelGraphique panelGraphique;
	JButton bas, haut, gauche, droite, hautGauche, hautDroite, basGauche, basDroite, avancer, passerTour, action;
	JLabel label;
	
	/**
	 * Action a realiser
	 * @param perso
	 * 		personnage qui effectue l'action
	 * @param carte
	 * 		carte a modifier
	 */
	
	public PanelAction(Personnage perso, Carte carte) {
		finTour = false;
		int taille  = carte.parcelles.length;
		this.setSize(taille * 32, 96);
		this.setLayout(null);
		this.perso = perso;
		this.carte = carte;
		
		cpt = 0;
		
		bas = new JButton(new ImageIcon("ressource/button/flecheBas.png"));
		bas.addActionListener(this);
		bas.setBounds( 42, 64, 32, 32);
		bas.setFocusable(false);
		this.add(bas);
			
		haut = new JButton(new ImageIcon("ressource/button/flecheHaut.png"));
		haut.addActionListener(this);
		haut.setBounds( 42, 0, 32, 32);
		haut.setFocusable(false);
		this.add(haut);
		
		gauche = new JButton(new ImageIcon("ressource/button/flecheDroite.png"));
		gauche.addActionListener(this);
		gauche.setBounds( 9, 32, 32, 32);
		gauche.setFocusable(false);
		this.add(gauche);
			
		droite = new JButton(new ImageIcon("ressource/button/flecheGauche.png"));
		droite.addActionListener(this);
		droite.setBounds( 75, 32, 32, 32);
		droite.setFocusable(false);
		this.add(droite);
		
		hautGauche = new JButton(new ImageIcon("ressource/button/flecheHautGauche.png"));
		hautGauche.addActionListener(this);
		hautGauche.setBounds( 9, 0, 32, 32);
		hautGauche.setFocusable(false);
		this.add(hautGauche);
		
		hautDroite = new JButton(new ImageIcon("ressource/button/flecheHautDroite.png"));
		hautDroite.addActionListener(this);
		hautDroite.setBounds( 75, 0, 32, 32);
		hautDroite.setFocusable(false);
		this.add(hautDroite);
		
		basGauche = new JButton(new ImageIcon("ressource/button/flecheBasGauche.png"));
		basGauche.addActionListener(this);
		basGauche.setBounds( 9, 64, 32, 32);
		basGauche.setFocusable(false);
		this.add(basGauche);
		
		basDroite = new JButton(new ImageIcon("ressource/button/flecheBasDroite.png"));
		basDroite.addActionListener(this);
		basDroite.setBounds( 75, 64, 32, 32);
		basDroite.setFocusable(false);
		this.add(basDroite);
		
		avancer = new JButton(new ImageIcon("ressource/button/flecheAction.png"));
		avancer.addActionListener(this);
		avancer.setBounds( 42, 32, 32, 32);
		avancer.setFocusable(false);
		this.add(avancer);
		
		passerTour = new JButton(new ImageIcon("tour"));
		passerTour.addActionListener(this);
		passerTour.setBounds( (this.getWidth() - 110), 30, 100, 25);
		passerTour.setFocusable(false);
		passerTour.setText("passer");
		this.add(passerTour);
	
		action = new JButton("action");
		action.addActionListener(this);
		action.setBounds( (this.getWidth() - 110), 3, 100, 25);
		action.setFocusable(false);
		this.add(action);
		
		panelGraphique = new PanelGraphique(this.perso);
		panelGraphique.setBounds((this.getWidth() - 120) / 2, 5, panelGraphique.getWidth(), panelGraphique.getHeight());
		this.add(panelGraphique);
	}
	
	/**
	 * Selectionner le personnage
	 * @param perso
	 */
	public void setPersonnage(Personnage perso){
		this.perso.select = false;
		this.perso = perso;
		this.perso.select = true;
		this.perso.pm = this.perso.pmInitiale;
		panelGraphique.perso = perso;
		panelGraphique.repaint();
		cpt = 0;
		if(perso.estDansBateau()){
			perso.vie = perso.vieMax;
			perso.energie = perso.energieMax;
		}
		if(perso.energie <= 0){
			perso.vie -= 20; 
		}
		setActionBouton();
		setButton();
		testDead();
	}
	
	public void setButton(){
		if(perso.typePerso == Personnage.VOLEUR || perso.typePerso == Personnage.PIEGEUR){
			hautDroite.setVisible(true);
			hautGauche.setVisible(true);
			basDroite.setVisible(true);
			basGauche.setVisible(true);
		}else{
			hautDroite.setVisible(false);
			hautGauche.setVisible(false);
			basDroite.setVisible(false);
			basGauche.setVisible(false);
		}
	}
	
	/**
	 * differente action
	 */
	public void setActionBouton(){
		HashMap<String, String> map = new HashMap<>();
		map.put(Personnage.EXPLORATEUR, "Fouiller");
		map.put(Personnage.EXPLORATEUR + "1", "Balisée");
		map.put(Personnage.GUERRIER, "Attaque");
		map.put(Personnage.GUERRIER + "1", "Brisée");
		map.put(Personnage.PIEGEUR, "Pieger");
		map.put(Personnage.VOLEUR, "Volé");
		map.put(Personnage.VOLEUR + "1", "donner clé");

		Parcelle test = new Parcelle();
		if (perso.oriantaion == Personnage.FACE)
			test = carte.parcelles[perso.pos_x][perso.pos_y + 1];
		if (perso.oriantaion == Personnage.DOS)
			test = carte.parcelles[perso.pos_x][perso.pos_y - 1];
		if (perso.oriantaion == Personnage.GAUCHE)
			test = carte.parcelles[perso.pos_x - 1][perso.pos_y];
		if (perso.oriantaion == Personnage.DROITE)
			test = carte.parcelles[perso.pos_x + 1][perso.pos_y];

		if(perso.typePerso == Personnage.VOLEUR){
			if( perso.cle && test.contenu instanceof Personnage && ((Personnage) test.contenu).typePerso == Personnage.EXPLORATEUR && ((Personnage) test.contenu).numEquipe == perso.numEquipe){
				// donne la clÃ© a l'explorateur
				action.setText(map.get(Personnage.VOLEUR + "1"));
			}else{
				// vole la personne
				action.setText(map.get(perso.typePerso));
			}
		}else if(perso.typePerso == Personnage.EXPLORATEUR){
			if( test.contenu instanceof Rocher){
				action.setText(map.get(Personnage.EXPLORATEUR));
			}else{
				action.setText(map.get(Personnage.EXPLORATEUR + "1"));
			}
		}else if(perso.typePerso == Personnage.GUERRIER){
			if( test.contenu instanceof Rocher){
				action.setText(map.get(Personnage.GUERRIER + "1"));
			}else{
				action.setText(map.get(Personnage.GUERRIER));
			}
		}else{
			action.setText(map.get(perso.typePerso));
		}
	}
	
	public void mouve(){
		if(perso.oriantaion == Personnage.FACE){
			if(cpt < this.perso.pmInitiale){
				boolean abouge = carte.mouveBas(perso);
				if(abouge){
					cpt++;
					this.perso.pm--;
				}
			}
		}else if(perso.oriantaion == Personnage.DOS){
			if(cpt < this.perso.pmInitiale){
				boolean abouge = carte.mouveHaut(perso);
				if(abouge){
					cpt++;
					this.perso.pm--;
				}
			}
		}else if(perso.oriantaion == Personnage.GAUCHE){
			if(cpt < this.perso.pmInitiale){
				boolean abouge = carte.mouveGauche(perso);
				if(abouge){
					cpt++;
					this.perso.pm--;
				}
			}
		}else if(perso.oriantaion == Personnage.DROITE){
			if(cpt < this.perso.pmInitiale){
				boolean abouge = carte.mouveDroite(perso);
				if(abouge){
					cpt++;
					this.perso.pm--;
				}
			}
		}
	}
	
	public void mouveHautGauche(){
		if(cpt < this.perso.pmInitiale){
			boolean abouge = carte.mouveHautGauche(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
	}
	
	public void mouveHautDroite(){
		if(cpt < this.perso.pmInitiale){
			boolean abouge = carte.mouveHautDroite(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
	}
	
	public void mouveBasGauche(){
		if(cpt < this.perso.pmInitiale){
			boolean abouge = carte.mouveBasGauche(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
	}
	
	public void mouveBasDroite(){
		if(cpt < this.perso.pmInitiale){
			boolean abouge = carte.mouveBasDroite(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
	}
	

	/**
	 * bouger vers le bas
	 */
	public void mouveBas(){
		perso.changerOriantation(Personnage.FACE);
	}
	/**
	 * bouger vers le haut
	 */
	
	public void mouveHaut(){
		perso.changerOriantation(Personnage.DOS);
	}
	/**
	 * bouger vers la gauche
	 */
	public void mouveGauche(){
		perso.changerOriantation(Personnage.GAUCHE);
	}
	/**
	 * bouger vers la droite
	 */
	public void mouveDroite(){
		perso.changerOriantation(Personnage.DROITE);
	}
	
	/**
	 * action effectue
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bas){
			mouveBas();
		}
		if(e.getSource() == this.haut){
			mouveHaut();
		}
		if(e.getSource() == this.gauche){
			mouveGauche();
		}
		if(e.getSource() == this.droite){
			mouveDroite();
		}
		if(e.getSource() == this.hautGauche){
			mouveHautGauche();
		}
		if(e.getSource() == this.hautDroite){
			mouveHautDroite();
		}
		if(e.getSource() == this.basGauche){
			mouveBasGauche();
		}
		if(e.getSource() == this.basDroite){
			mouveBasDroite();
		}
		if(e.getSource() == this.avancer){
			mouve();
			testDead();
		}
		if(e.getSource() == this.passerTour){
			finTour = true;
		}
		if(e.getSource() == this.action){
			carte.interaction(perso);
		}
		setActionBouton();
	}
	/**
	 * Voir un personnage est mort
	 */
	void testDead(){
		if(perso.vie<=0){
			if(perso.cle){
				carte.lacherCle(perso.pos_x, perso.pos_y);
				perso.cle = false;
			}else if(perso.tresor){
				carte.lacherTresor(perso.pos_x, perso.pos_y);
				perso.tresor = false;
			}
			finTour=true;
		}
	}

	@Override
	/**
	 * detection des touches 
	 */
	public void keyPressed(KeyEvent e) {
		if(perso.typePerso == Personnage.VOLEUR || perso.typePerso == Personnage.PIEGEUR){
			if(e.getKeyCode() == KeyEvent.VK_NUMPAD7){
				mouveHautGauche();
			}else if(e.getKeyCode() == KeyEvent.VK_NUMPAD9){
				mouveHautDroite();
			}else if(e.getKeyCode() == KeyEvent.VK_NUMPAD1){
				mouveBasGauche();
			}else if(e.getKeyCode() == KeyEvent.VK_NUMPAD3){
				mouveBasDroite();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD2){
			mouveBas();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_NUMPAD8){
			mouveHaut();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_NUMPAD4){
			mouveGauche();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_NUMPAD6){
			mouveDroite();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			mouveBas();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_UP){
			mouveHaut();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			mouveGauche();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			mouveDroite();
			mouve();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			carte.interaction(perso);
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			finTour = true;
		}
		setActionBouton();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}