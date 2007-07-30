package fr.lip6.move.coloane.interfaces.exceptions;

/**
 * Erreur de syntaxe dans la reception ou l'envoi de commandes a la plateforme
 */
public class SyntaxErrorException extends Exception {
	
    /** Pour la conformite */
    private static final long serialVersionUID = 1L;
    
    /** Message associee a l'exception */
	private String msg;
	
	/** Constructeur */
	public SyntaxErrorException() {
		super();
	}
	
	/** 
	 * Construteur 
	 * Ce construteur permet d'associer un message d'erreur a l'exception qu'on leve
	 * @param message Me message d'explication
	 */
	public SyntaxErrorException(String message) {
		super(message);
		this.msg = message; 
	}
		
	/**
	 * Production d'une chaine de caractere depuis l'exception
	 * @return string Le messaeg complet d'erreur
	 */
    public String toString() {
    	if (msg != null) {
    		return new String("SyntacticErrorException : " + msg);
        } else {
    		return "Syntax Error";
        }
    }
}
