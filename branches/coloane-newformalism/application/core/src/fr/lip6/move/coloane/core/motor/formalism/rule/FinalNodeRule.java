package fr.lip6.move.coloane.core.motor.formalism.permissionRule;


import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

/**
 * Cette Classe permet à un Noeud de recevoir des arcs d'autres noeuds mais pas d'en envoyer
 */
public class FinalNodeRule implements IRule{

	/** Element en entree de l'arc. */
	private ElementFormalism elementFinal;

	/** l'element Final */
	private final int finalNode;
	
	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param eltIn Element en entree
	 * @param eltOut Element en Sortie
	 */

	public FinalNodeRule(ElementFormalism eltFinal) {
		this.elementFinal = eltFinal;
		this.finalNode = 1;
	}
	
	/**	
	 * Methode qui verifie la possibilité de relier deux elements avec Maximum
	 * @param eltIn : est l'element en entrée
	 * @param eltOut: est l'element en sortie
	*/
	public boolean canConnect(INodeImpl elemIn,INodeImpl elemFinal){
		if ( elemFinal.getSourceArcs().size() >= finalNode) {
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
		return null;
	}

	/**
	 * Retourne l'element en sortie de l'arc.
	 * @return ElementBase
	 * @see ElementFormalism
	 */
	public final ElementFormalism getElementOut() {
		return elementFinal;
	}

}