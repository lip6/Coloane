package fr.lip6.move.coloane.apiws.interfaces.evenements;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.service.IServicesAvailable;

public interface IAnswerOpenSession {
	
	/**
	 * Recupere le formalism de la session
	 * @return le formalism de la session
	 */
	public String getFormalism();
	
	/**
	 * Recupere l'identifiant de la session
	 * @return l'identifiant de la session
	 */
	public String getIdSession();
	
	/**
	 * Recupere le nom de la session
	 * @return le nom de la session
	 */
	public String getSessionName();
	
	/**
	 * Recupere le nom du service
	 * @return le nom du service
	 */
	public String getNameService();
	
	/**
	 * Recupere les information en rapport avec le service
	 * @return les information en rapport avec le service
	 */
	public String getAboutService();
	
	/**
	 * Recupere le numero d'incremental
	 * @return le numero d'incremental
	 */
	public String getIncremental();
	
	/**
	 * Recupere si le resultat est deja calculer
	 * @return 1 si resultat deja calcules, 2 si non calcules
	 */
	public int getResultatCalcule();
	
	/**
	 * Recupere les menus des services disponible pour la session
	 * @return les menus des services disponible pour la session
	 */
	public IMMenu getMenus();
	
	/**
	 * Recupere les services disponible pour la session
	 * @return les services disponible pour la session
	 */
	public IServicesAvailable getServicesAvailable();
	
}
