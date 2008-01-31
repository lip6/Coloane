package fr.lip6.move.coloane.api.session.controller;


public interface IState {

	public class RewindException extends Exception {
		private static final long serialVersionUID = 7813190613177365773L;		
	}
	
	public class ErrorException extends Exception {
		private static final long serialVersionUID = 1882292943740356666L;
	}
	
	public IState apply(IMessage m) throws RewindException, ErrorException;
		
}
