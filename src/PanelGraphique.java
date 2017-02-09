import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
@SuppressWarnings("serial")
public class PanelGraphique extends JPanel{
	
	Personnage perso;
	/**
	 * information sur le perso
	 * @param perso
	 */
	public PanelGraphique(Personnage perso){
		this.setSize(100, 55);
		this.perso = perso;
	}
	
	/**
	 * affichage de la couleur de l'equipe 
	 */
	
	public void paint(Graphics g) {
		g.draw3DRect(0, 0, this.getWidth(), this.getHeight(), false);
		if(perso.numEquipe == 0){
			g.setColor(Color.BLUE);
		}else{
			g.setColor(Color.RED);
		}
		
		g.fillRect(1, 1, 98, 53);
		g.drawImage(perso.image, 65, 8, null);
		g.setColor(Color.WHITE);
		g.drawString("VIE : " + perso.vie, 5, 15);
		g.drawString("PM : " + perso.pm, 5, 30);
		g.drawString("NRJ : " + perso.energie, 5, 45);
	}
}
