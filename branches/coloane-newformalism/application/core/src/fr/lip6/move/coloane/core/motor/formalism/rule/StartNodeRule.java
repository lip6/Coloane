package fr.lip6.move.coloane.core.motor.formalism.permissionRule;


import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

/**
 * Cette Classe permet à un Noeud de d'envoyer des arcs vers d'autres noeuds mais n'en reçoit pas
 */
public class StartNodeRule implements IRule{

	/** Element en entree de l'arc. */
	private ElementFormalism elementStart;

	/** l'element Final */
	private final int startNode;
	
	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param eltIn Element en entree
	 * @param eltOut Element en Sortie
	 */

	public StartNodeRule(ElementFormalism eltStart) {
		this.elementStart = eltStart;
		this.startNode = 1;
	}
	
	/**	
	 * Methode qui verifie la possibilité de relier deux elements avec Maximum
	 * @param eltIn : est l'element en entrée
	 * @param eltOut: est l'element en sortie
	*/
	public boolean canConnect(INodeImpl elemStart,INodeImpl elemOut){
		if ( elemStart.getTargetArcs().size() >= startNode) {
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
		return elementStart;
	}

	/**
	 * Retourne l'element en sortie de l'arc.
	 * @return ElementBase
	 * @see ElementFormalism
	 */
	public final ElementFormalism getElementOut() {
		return null;
	}

}