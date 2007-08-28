/**
 * 
 */
package fr.lip6.move.coloane.api;

import fr.lip6.move.coloane.api.session.controller.Controller;
import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;
import fr.lip6.move.coloane.interfaces.utils.IApiFactory;

public class ApiFactory implements IApiFactory {

	public IApi create(IComApi com) {
		return new API(new Controller());
	}

	public ApiFactory() {
		
	}
	
}
