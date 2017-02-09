/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
public class Piege extends Contenu{
	int numEquipe;
	/**
	 * Creer piege
	 * @param numEquipe
	 * 		piege de l'equipe
	 */
	public Piege(int numEquipe) {
		super("ressource/OBJECTS/Piege.png");
		this.numEquipe = numEquipe;
	}
}