package fr.lip6.move.coloane.core.motor.formalism.permissionRule;


import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

public class CardinalityRule implements IRule{

	/** Element en entree de l'arc. */
	private ElementFormalism elementIn;

	/** Element en sortie de l'arc. */
	private ElementFormalism elementOut;
	
	/** Maximum d'arcs en Sortie de l'element elemIn */
	private int maximumIn;
	
	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param eltIn Element en entree
	 * @param eltOut Element en Sortie
	 * @param maximumIn Maximum d'arcs en Sortie 
	 */

	public CardinalityRule(ElementFormalism eltIn,int maximumIn,ElementFormalism eltOut) {
		this.elementIn = eltIn;
		this.elementOut = eltOut;
		this.maximumIn = maximumIn;
	}
	
	/**	
	 * Methode qui verifie la possibilité de relier deux elements avec Maximum
	 * @param eltIn : est l'element en entrée
	 * @param eltOut: est l'element en sortie
	*/
	public boolean canConnect(INodeImpl elemIn,INodeImpl elemOut){
		if ( elemIn.getSourceArcs().size() >= maximumIn ) {
			return false;
		}
		return true;
	}

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