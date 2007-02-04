package fr.lip6.move.coloane.api.exceptions;

/**
 * 
 * erreur de syntaxe dans le protocole CAMI
 */
public class SyntaxErrorException extends Exception {
	
    /**
     *  pour conformite...
     */
    private static final long serialVersionUID = 1L;
    
    /**
	 * message
	 */
	private String msg;
	
	/**
	 * constructeur vide
	 */
	public SyntaxErrorException() {
		super();
	}
	
	/**
	 * construteur avec message d'erreur personnalise
	 * @param msgPar ...
	 */
	public SyntaxErrorException(String msgPar) {
		super(msgPar);
		this.msg = msgPar; 
	}
		
	/**
	 * @return string
	 */
    public String toString() {
    	if (msg != null) {
    		return new String("SyntacticErrorException" + " : " + msg);
        } else {
    		return "SyntacticErrorException";
        }
    }
}
