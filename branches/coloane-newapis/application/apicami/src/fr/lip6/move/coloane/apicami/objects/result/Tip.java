package fr.lip6.move.coloane.apicami.objects.result;

import fr.lip6.move.coloane.interfaces.objects.result.ITip;

/**
 * Une information attachée au résultat.<br>
 *
 * @author Jean-Baptiste Voron
 */
public class Tip implements ITip {
	/** L'identifiant de l'objet concerné par l'information */
	private int idObject;
	/** Le nom de l'information */
	private String name;
	/** La valeur de l'information */
	private String value;

	/**
	 * Constructeur
	 * @param idObject L'identifiant de l'objet concerné par l'information
	 * @param name Le nom de l'information
	 * @param value La valeur de l'information
	 */
	public Tip(int idObject, String name, String value) {
		this.idObject = idObject;
		this.name = name;
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getIdObject() {
		return this.idObject;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getValue() {
		return this.value;
	}

}
