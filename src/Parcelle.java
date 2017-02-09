import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
public class Parcelle {
	Image imageFond;
	Contenu contenu;
	boolean traversable;
	
	static String EAU = "ressource/TERRAIN/eau.png";
	static String HERBE = "ressource/TERRAIN/herbe.png";
	static String HERBEHaut = "ressource/TERRAIN/haut.png";
	static String HERBEBas = "ressource/TERRAIN/bas.png";
	static String HERBEDroite = "ressource/TERRAIN/droite.png";
	static String HERBEGauche = "ressource/TERRAIN/gauche.png";
	static String HERBEBasDroite = "ressource/TERRAIN/angleBasDroite.png";
	static String HERBEBasGauche = "ressource/TERRAIN/angleBasGauche.png";
	static String HERBEHautDroite = "ressource/TERRAIN/angleHautDroite.png";
	static String HERBEHautGauche = "ressource/TERRAIN/angleHautGauche.png";
	
	public Parcelle(){}
	
	/**
	 * parcelle de la carte
	 * @param URLimagefond
	 * 		image de la parcelle
	 * @param traversable
	 * 		savoir si c'est traversable
	 */
	public Parcelle(String URLimagefond, boolean traversable) {
		this.imageFond = new ImageIcon(URLimagefond).getImage();
		this.contenu = null;
		this.traversable = traversable;
	}
	
	/**
	 * ajouter contenu
	 * @param contenu
	 * 		contenu a ajouter
	 * @param traversable
	 * 		savoir si c'est traversable
	 */
	
	public void ajouterContenu(Contenu contenu, boolean traversable){
		this.contenu = contenu;
		this.traversable = traversable;
	}
	/**
	 * Vider contenu
	 */
	public void videContenu(){
		this.contenu = null;
		this.traversable = true;
	}
}
