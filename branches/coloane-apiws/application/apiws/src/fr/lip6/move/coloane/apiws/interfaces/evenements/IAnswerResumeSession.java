package fr.lip6.move.coloane.apiws.interfaces.evenements;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IMMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.service.IServicesAvailable;

public interface IAnswerResumeSession {
	
	public String getFormalism();

	public String getIdSession();

	public IMMenu getMenus();
	
	/**
	 * Recupere les services disponible pour la session
	 * @return les services disponible pour la session
	 */
	public IServicesAvailable getServicesAvailable();
}
