package fr.lip6.move.coloane.interfaces.api.services;

import fr.lip6.move.coloane.interfaces.objects.services.IService;

/**
 * Define what a API service should be.<br>
 * Such a service is based on a {@link IService}.<br>
 * But, an API service should provide some information about itself. 
 * 
 * @author Jean-Baptiste Voron
 */
public interface IApiService extends IService {

	/**
	 * @return The service name
	 */
	String getName();
	
	/**
	 * @return A simple description of the service
	 */
	String getDescription();
}
