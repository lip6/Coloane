package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IAttribute;

/**
 * cette classe represente un attribut.
 * @author kahoo & UU.
 *
 */
public class Attribute implements IAttribute {

	/** Nom de l'attribut sous forme de chaine de caracteres. */
	private String name;

	/** Valeur de l'attribut. Cette valeur peut etre de n'importe qu'elle type de String java. */
	private String value;

	/** Position absolue horizontale depuis le bord gauche de la fenetre d'affichage du modele. */
	private int xPosition;

	/** Position absolue verticale depuis le bord haut de la fenetre d'affichage du modele. */
	private int yPosition;

	/** Identifiant unique de l'element du modele possedant cette attribut. */
	private int refId;



	/**
	 * Constructeur de la classe Attribute.
	 *
	 * @param name le nom de l'attribut
	 * @param value la valeur de l'attribut
	 * @param refId l'id de l'objet possedant cette attribut.
	 */
	public Attribute(String attributeName,int attributeRefId, String attributeValue,int xPosition,int yPosition) {
		this.name = attributeName;
		this.value = new String(attributeValue);
		this.refId = attributeRefId;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}


	public final String getName() {
		return this.name;
	}


	public final void setPosition(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}

	public final int getXPosition() {
		return this.xPosition;
	}


	public final int getYPosition() {
		return this.yPosition;
	}


	public final int getRefId() {
		return this.refId;
	}


	public final void setRefId(int ref) {
		this.refId = ref;
	}


	public final void setValue(String attributeValue) {
		this.value = attributeValue;
	}


	public final String getValue() {
		return (String) this.value;
	}
}
