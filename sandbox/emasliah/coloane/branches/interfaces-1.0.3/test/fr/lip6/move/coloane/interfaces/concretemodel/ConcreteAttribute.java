package fr.lip6.move.coloane.interfaces.concretemodel;

import fr.lip6.move.coloane.interfaces.model.Attribute;

/**
 * Attribut concret pour permettre les tests unitaires
 */
public class ConcreteAttribute extends Attribute {

	/**
	 * Constructeur
	 * @param name Le nom de l'attribut
	 * @param value La valeur de l'attribut
	 * @param refId L'element referent de l'attribut
	 */
	public ConcreteAttribute(String name, String value, int refId) {
		super(name, value, refId);
	}

	// Pas besoin de traduction pour cet objet
	@Override
	public final String[] translate() {
		return null;
	}

}
