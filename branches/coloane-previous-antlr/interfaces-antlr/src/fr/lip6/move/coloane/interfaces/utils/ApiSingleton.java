package fr.lip6.move.coloane.interfaces.utils;

import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;

public final class ApiSingleton {

	public static final class FactoryException extends Exception {
		private static final long serialVersionUID = -8361990948371530822L;		
	}
	
	private static IApi INSTANCE = null;
	
	private ApiSingleton() { // Not instanciable
	}
	
	public static synchronized IApi getInstance(IApiFactory f, IComApi com) throws FactoryException {
		if (INSTANCE == null) {
			INSTANCE = f.create(com);
			if (INSTANCE == null) {
				throw new FactoryException();
			}
		}
		return INSTANCE;
	}

}
