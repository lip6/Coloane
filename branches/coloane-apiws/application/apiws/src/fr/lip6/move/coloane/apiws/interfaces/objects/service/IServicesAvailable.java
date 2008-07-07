package fr.lip6.move.coloane.apiws.interfaces.objects.service;

import java.util.Hashtable;

public interface IServicesAvailable {
	
	/**
	 * Recupere la liste de tous les services diponible
	 * @return la liste de tous les services diponible
	 */
	public Hashtable<String, IQuestion> getServices();
	
	/**
	 * Recupere la liste de tous les options diponible
	 * @return la liste de tous les options diponible
	 */
	public Hashtable<String, IQuestion> getOptions();
}
