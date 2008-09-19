package fr.lip6.move.coloane.apicami.objects;

import fr.lip6.move.coloane.interfaces.api.objects.IConnectionInfo;

/**
 * Cette classe contient toutes les informations renvoyées par la plate-forme lors d'une connexion
 *
 * @author Kahina Bouarab
 * @author Youcef Belattaf
 *
 */
public class ConnectionInfo implements IConnectionInfo {
	/** Le nom de la plate-forme */
	private String fkName;

	/** Le numéro de version majeur de la plate-forme */
	private int fkMajor;

	/** LE numéro de version mineur de la plate-forme */
	private int fkMinor;

	/**
	 * le constructeur de notre classe
	 * @param fkName le nom du FrameKit.
	 */
	public ConnectionInfo(String fkName) {
		this.fkName = fkName;
		this.fkMajor = Integer.valueOf(fkMajor);
		this.fkMinor = Integer.valueOf(fkMinor);
	}

	/**
	 * Indique le numero de version majeur de la plate-forme
	 * @param fkMajor Le numéro de version (majeur)
	 */
	public final void setFkMajor(String fkMajor) {
		this.fkMajor = Integer.valueOf(fkMajor);
	}

	/**
	 * Indique le numero de version mineur de la plate-forme
	 * @param fkMinor Le numéro de version (mineur)
	 */
	public final void setFkMinor(String fkMinor) {
		this.fkMinor = Integer.valueOf(fkMinor);
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getFkMajor() {
		return this.fkMajor;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getFkMinor() {
		return this.fkMinor;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getFkName() {
		return this.fkName;
	}
}
