package fr.lip6.move.coloane.motor.formalism;

import java.io.Serializable;

/**
 * Regle de connexion pour un formalisme
 */
public class Rule implements Serializable {

	/** Id pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Nom de la regle. */
	private String name;
	
	/** Element en entree de l'arc. */
	public NodeFormalism elementIn;
	
	/** Element en sortie de l'arc. */
	public NodeFormalism elementOut;
	
	/** Commentaire lie a la regle quand celle-ci est declenchee. */
	private String comment = null;
	
	/**
	 * Constructeur
	 * @param name Nom de la regle
	 */
	public Rule(String name) {
		this.name = name;
	}

	/**
	 * Constructeur de la classe.
	 * 
	 * @param name Nom de la regle.
	 * @param comment Commentaire lie a la violation de la regle.
	 */
	public Rule(String name, String comment) {
		this.name    = name;
		this.comment = comment;
	}
	
	/**
	 * Retourne le nom de la regle
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de la regle
	 * @param name Le nom de la regle
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retourne le commentaire lie a cette regle.
	 * @return String
	 */
	public String getComment() {
		return comment;
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
	
	/**
	 * Etabli quels sont les Node que l'on peut pas connecter a partir de leur ID.
	 * 
	 * @param form formalisme auquel elemIn et elemOut appartiennent.
	 * @param elemIn ID de l'element en entree de l'arc.
	 * @param elemOut ID de l'element en sortie de l'arc.
	 */
	public void forbidenRule(Formalism form, int elemIn, int elemOut) {
		this.elementIn  = form.int2Node(elemIn);
		this.elementOut = form.int2Node(elemOut);
	}

}
