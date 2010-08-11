package fr.lip6.move.coloane.interfaces.objects.services;

/**
 * Define what a API service should be.<br>
 * Such a service is based on a {@link IService}.<br>
 * But, an API service should provide some information about itself. 
 * 
 * @author Jean-Baptiste Voron
 */
public interface IApiService extends IService {

	/**
	 * @return le nom du service
	 */
	String getName();
}
