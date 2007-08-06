package fr.lip6.move.coloane.interfaces.model;

import java.io.Serializable;

/**
 * Cette classe represente un attribut generique d'un modele. <br>
 * Un attribut se structure de la maniere suivante :
 * <ul>
 * 	<li> Nom de l'attribut sous forme de chaine de caracteres
 * 	<li> La valeur de l'attribut (ensemble de chaine de caracteres java  representant les differentes lignes)
 * 	<li> Abscisse et ordonnee de l'attribut
 * 	<li> Identifiant de l'element d'un modele auquel il est attache
 * </ul>
 *
 */
public abstract class Attribute implements IAttribute, Serializable {

	/** Utilise lors de la deserialization afin de s'assurer que les versions des classes Java soient concordantes. */
	private static final long serialVersionUID = 1L;

	/** Nom de l'attribut sous forme de chaine de caracteres. */
	protected String name;

	/** Valeur de l'attribut. Cette valeur peut etre de n'importe qu'elle type de String java. */
	private String value;

	/** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
	protected int xPosition;

	/** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
	protected int yPosition;

	/** Identifiant unique de l'element du modele possedant cette attribut. */
	protected int refId;



	/**
	 * Constructeur de la classe Attribute.
	 *
	 * @param name le nom de l'attribut
	 * @param value la valeur de l'attribut
	 * @param refId l'id de l'objet possedant cette attribut.
	 */
	public Attribute(String attributeName, String attributeValue, int attributeRefId) {
		this.name = attributeName;
		this.value = new String(attributeValue);
		this.refId = attributeRefId;
		this.xPosition = 0;
		this.yPosition = 0;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getName()
	 */
	public final String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#setPosition(int, int)
	 */
	public final void setPosition(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getXPosition()
	 */
	public final int getXPosition() {
		return this.xPosition;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getYPosition()
	 */
	public final int getYPosition() {
		return this.yPosition;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getRefId()
	 */
	public final int getRefId() {
		return this.refId;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#setRefId(int)
	 */
	public final void setRefId(int ref) {
		this.refId = ref;
	}

	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#setValue(java.lang.String)
	 */
	public final void setValue(String attributeValue) {
		this.value = attributeValue;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#getValue()
	 */
	public final String getValue() {
		return (String) this.value;
	}


	/* (non-Javadoc)
	 * @see fr.lip6.move.coloane.interfaces.model.IAttribute#translateToCAMI()
	 */
	public abstract String[] translate();
}
