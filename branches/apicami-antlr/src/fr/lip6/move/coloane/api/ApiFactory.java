/**
 * 
 */
package fr.lip6.move.coloane.api;

import java.io.IOException;

import fr.lip6.move.coloane.api.session.controller.Controller;
import fr.lip6.move.coloane.api.session.controller.IController;
import fr.lip6.move.coloane.api.session.controller.IState;
import fr.lip6.move.coloane.api.session.controller.IController.UnknownSessionException;
import fr.lip6.move.coloane.api.session.states.authentication.AuthenticationState;
import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;
import fr.lip6.move.coloane.interfaces.utils.IApiFactory;

public class ApiFactory implements IApiFactory {

	public IApi create(IComApi com) {
		
		IController controller = null;
		
		try {
			controller = new Controller();
			IState initialState = new AuthenticationState(controller);
		
			controller.changeSession(controller.register(initialState));
		
		} catch (UnknownSessionException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return new API(controller);
	}
	
}
