package fr.lip6.move.coloane.api.interfaces;

/**
 * cette interface est retournée en asynchrone par OpenConnection,
 * qui se trouve dans IAPIConnection.
 * @author KAHOO & UU
 *
 */

public interface IConnectionVersion {


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
