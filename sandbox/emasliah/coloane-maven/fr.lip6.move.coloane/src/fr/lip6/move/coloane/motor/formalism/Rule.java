package fr.lip6.move.coloane.motor.formalism;

import java.io.Serializable;

/**
 * Regle de connexion pour un formalisme
 */
public class Rule implements Serializable {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Nom de la regle. */
	//private String name;
	
	/** Element en entree de l'arc. */
	public NodeFormalism elementIn;
	
	/** Element en sortie de l'arc. */
	public NodeFormalism elementOut;
	
	/** Commentaire lie a la regle quand celle-ci est declenchee. */
	//private String comment = null;
	
	/**
	 * Constructeur de la classe.
	 * 
	 * @param name Nom de la regle.
	 * @param comment Commentaire lie a la violation de la regle.
	 */
	public Rule(String name, String comment) {
		//this.name    = name;
		//this.comment = comment;
	}
	
	/**
	 * Retourne l'element en entree de l'arc.
	 * @return ElementBase
	 * @see ElementBase
	 */
	public ElementBase getElementIn() {
		return elementIn;
	}

	/**
	 * Retourne l'element en sortie de l'arc.
	 * @return ElementBase
	 * @see ElementBase
	 */
	public ElementBase getElementOut() {
		return elementOut;
	}
	
	/**
	 * Etabli quels sont les Node que l'on peut pas connecter a partir de leur nom.
	 * 
	 * @param form formalisme auquel elemIn et elemOut appartiennent.
	 * @param elemIn Nom de l'element en entree de l'arc.
	 * @param elemOut Nom de l'element en sortie de l'arc.
	 */
	public void forbidenRule(Formalism form, String elemIn, String elemOut) {
		this.elementIn  = form.string2Node(elemIn);
		this.elementOut = form.string2Node(elemOut);
	}
}
