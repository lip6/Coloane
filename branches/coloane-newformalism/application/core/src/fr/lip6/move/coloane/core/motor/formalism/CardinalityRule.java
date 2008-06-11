package fr.lip6.move.coloane.core.motor.formalism;

import java.util.ArrayList;

public class CardinalityRule implements IRule{

	/** Element en entree de l'arc. */
	private ElementFormalism elementIn;

	/** Element en sortie de l'arc. */
	private ElementFormalism elementOut;

	private Formalism poss;

	private ArrayList<ConnexionRule> listOfRules;
	
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
		this.listOfRules = new ArrayList<ConnexionRule>();
	}
	
	public boolean canConnect(ElementFormalism elemIn,ElementFormalism elemOut){
		
		for (ConnexionRule r : listOfRules) {
			if (r.getElementIn().equals(elemIn) && r.getElementOut().equals(elemOut)) {
				return false;
			}
		}
		return true;
	}

	public final boolean permissionToLink(ElementFormalism elemIn, ElementFormalism elemOut){
	
		if (this.canConnect(elemIn, elemOut)){
			//if (elemIn instanceof 
			poss.addRuleCard(new CardinalityRule(poss.getNodeFormalism("Pays"),poss.getNodeFormalism("Continent")));
			return false;
		}
		return false;
	
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