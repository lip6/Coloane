package fr.lip6.move.coloane.interfaces.utils;

import fr.lip6.move.coloane.interfaces.IApi;
import fr.lip6.move.coloane.interfaces.IComApi;



public interface IApiFactory {

	public IApi create(IComApi com);
	
}
