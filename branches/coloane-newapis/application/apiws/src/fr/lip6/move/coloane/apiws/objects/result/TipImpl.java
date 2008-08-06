package fr.lip6.move.coloane.apiws.objects.result;

import fr.lip6.move.coloane.interfaces.objects.result.ITip;

/**
 * Cette classe représent les informations sur un objet
 */
public class TipImpl implements ITip {

	private int idObject;

	private String name;

	private String value;

	/**
	 * Constructeur
	 * @param idObject l'identifiant de l'objet sur lequel s'applique la modification
	 * @param name le nom de l'attribut
	 * @param value la valeur de l'attribut
	 */
	public TipImpl(int idObject, String name, String value) {
		this.idObject = idObject;
		this.name = name;
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getIdObject() {
		return idObject;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getValue() {
		return value;
	}

}
