package fr.lip6.move.coloane.apiws.interfaces.evenements;

public interface IAnswerOpenConnection {
	
	/**
	 * Recupere la version du FrameKit
	 * @return la version du FrameKit
	 */
	public String getFkVersion();
	
	/**
	 * Recupere le numero de version majeur
	 * @return le numero de version majeur
	 */
	public int getFkMajor();
	
	/**
	 * Recupere le numero de version mineur
	 * @return le numero de version mineur
	 */
	public int getFkMinor();

	/**
	 * Recupere les identifiants de la connection
	 * @return les identifiants de la connection
	 */
	public int[] getId();
}
