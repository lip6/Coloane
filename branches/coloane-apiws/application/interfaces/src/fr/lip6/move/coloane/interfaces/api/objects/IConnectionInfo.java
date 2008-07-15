package fr.lip6.move.coloane.interfaces.api.objects;

public interface IConnectionInfo {

	/**
	 * Retourne le nom de FrameKit.
	 * @return String
	 */
	public String getFkName();


	/**
	 * Retourne le numéro de version majeur.
	 * @return int
	 */
	public int getFkMajor();


	/**
	 * Retourne le numéro de version mineur.
	 * @return int
	 */
	public int getFkMinor();
}
