package fr.lip6.move.coloane.apiws.interfaces.objects.menu;

public interface IRootMenu {
	
	/**
	 * Recupere le menu principale
	 * @return le menu principale
	 */
	public ISubMenu getRoot();
	
	/**
	 * Recupere le nom du menu principale
	 * @return le nom du menu principale
	 */
	public String getName();
}
