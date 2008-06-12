package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;

/**
 * Classe permettant la reconstruction d'un attribut
 */
public class AttributContainer {
	private int id;
	private String value;

	/**
	 * @param attr
	 */
	public AttributContainer(IAttributeImpl attr) {
		id = attr.getId();
		value = attr.getValue();
	}

	/**
	 * @return id de l'attribut à passer au setPropertyValue d'un objet du model
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return valeur de l'attribut
	 */
	public final String getValue() {
		return value;
	}
}
