package fr.lip6.move.coloane.interfaces;

public interface IComUi {
	
	/** Demande d'un service a la plateforme FK */
	public void askForService(String rootMenuName, String parentName, String serviceName);

}
