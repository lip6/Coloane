package fr.lip6.move.coloane.core.motor.formalism.permissionRule;

import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

/**
 * Regle de connexion pour un formalisme
 */
public class ConnexionRule implements IRule{

	/** Element en entree de l'arc. */
	private ElementFormalism elementIn;

	/** Element en sortie de l'arc. */
	private ElementFormalism elementOut;
	
	/**
	 * Constructeur
	 * Etablit quelles sont les connexions impossibles
	 * @param eltIn Element en entree
	 * @param eltOut Element en Sortie
	 */
	public ConnexionRule(ElementFormalism eltIn, ElementFormalism eltOut) {
		this.elementIn = eltIn;
		this.elementOut = eltOut;
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
	
	/**	
	 * Methode qui verifie la possibilité de relier deux elements
	 * @param eltIn : est l'element en entrée
	 * @param eltOut: est l'element en sortie
	*/
	public boolean canConnect(INodeImpl eltIn, INodeImpl eltOut) {
		if (this.getElementIn().equals(eltIn.getElementBase()) && this.getElementOut().equals(eltOut.getElementBase())) {
			return false;
		}
		return true;
	}
}
