package fr.lip6.move.coloane.interfaces.api.objects;

/**
 * Cette interface définie les informations sur un connexction.<br>
 * <ul>
 * 	<li> Le nom de FrameKit </li>
 * 	<li> Le numéro de version majeur </li>
 * 	<li> Le numéro de version mineur </li>
 * </ul>
 */
public interface IConnectionInfo {

	/**
	 * Retourne le nom de FrameKit.
	 * @return String
	 */
	String getFkName();


	/**
	 * Retourne le numéro de version majeur.
	 * @return int
	 */
	int getFkMajor();


	/**
	 * Retourne le numéro de version mineur.
	 * @return int
	 */
	int getFkMinor();
}
