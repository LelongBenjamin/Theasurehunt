/**
 * 
 * @author gilmantl
 *
 */
public class Ward extends Contenu{
	int numEquipe;
	boolean type; //true = vision des furtifs false = vision normale
	/**
	 * ward
	 * @param numEquipe
	 * @param type
	 */
	public Ward(int numEquipe, boolean type) {
		super("ressource/OBJECTS/Ward"+color(type)+".png");
		this.numEquipe = numEquipe;
		this.type = type;
	}
	
	/**
	 * Couleur de la ward
	 * @param type
	 * 	Type de ward
	 * @return la couleur de la ward
	 */
	static String color(boolean type){
		if(type){
			return "Bleu";
		}else{
			return "Verte";
		}
	}
}