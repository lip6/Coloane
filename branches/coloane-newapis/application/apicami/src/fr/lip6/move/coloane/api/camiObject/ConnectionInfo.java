package fr.lip6.move.coloane.api.camiObject;

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
     * @param fkMajor le numero majeur de FrameKit.
     * @param fkMinor le numero mineur de FrameKit.
     */
	public ConnectionInfo(String fkName, String fkMajor, String fkMinor) {
     this.fkName = fkName;
     this.fkMajor = Integer.valueOf(fkMajor);
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
