package fr.lip6.move.coloane.apiws.interfaces.evenements;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.service.IServicesAvailable;

public interface IAnswerCloseSession {
	
	/**
	 * Recupere le formalism de la prochaine session
	 * @return le formalism de la prochaine session
	 */
	public String getFormalism();
	
	/**
	 * Recupere l'identifiant de la prochaine session
	 * @return l'identifiant de la prochaine session
	 */
	public String getIdSession();
	
	/**
	 * Recupere les menus des services disponible pour la prochaine session
	 * @return les menus des services disponible pour la prochaine session
	 */
	public IMMenu getMenus();

	
	/**
	 * Recupere les services disponible pour la session
	 * @return les services disponible pour la session
	 */
	public IServicesAvailable getServicesAvailable();
}
