package fr.lip6.move.coloane.core.motor.formalism;

public class CardinalityRule implements IRule{

	/** Element en entree de l'arc. */
	private ElementFormalism elementIn;

	/** Element en sortie de l'arc. */
	private ElementFormalism elementOut;

	private Formalism poss;

	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param eltIn Element en entree
	 * @param eltOut Element en Sortie
	 * @param idIn identifiant en Entrée
	 * @param idIn identifiant en Sortie
	 */

	// Une nouvelle regle a developper  ***** Debut
	
	public CardinalityRule(ElementFormalism eltIn,ElementFormalism eltOut) {
		this.elementIn = eltIn;
		this.elementOut = eltOut;

	}
	
	public boolean isThereConnexion(ElementFormalism elemIn,ElementFormalism elemOut){
		
		return poss.isLinkAllowed(elemIn, elemOut);
		
	}

	public final boolean permissionToLink(ElementFormalism elemIn, ElementFormalism elemOut){
	
		ElementFormalism elemOut2 = null;
		if (this.isThereConnexion(elemIn, elemOut)){
		
			poss.addRule(new ConnexionRule(poss.getNodeFormalism(elemIn.toString()),poss.getNodeFormalism(elemOut2.toString())));
			return false;
		}
		return true;
	
	}
	
	

	// Une nouvelle regle a developper  ***** Fin
	
	/**
	 * Retourne l'element en entree de l'arc.
	 * @return ElementBase
	 * @see ElementFormalism
	 */
	public final ElementFormalism getElementIn() {
		return elementIn;
	}

	/**
	 * Retourne l'element en sortie de l'arc.
	 * @return ElementBase
	 * @see ElementFormalism
	 */
	public final ElementFormalism getElementOut() {
		return elementOut;
	}

}