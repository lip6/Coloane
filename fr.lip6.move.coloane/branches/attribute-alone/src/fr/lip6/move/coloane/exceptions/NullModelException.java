package fr.lip6.move.coloane.exceptions;

public class NullModelException extends Exception {
    
    /**
     * numéro de série par défaut
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Message d'information sur l'exception
     */
    private String msg;
    
    /**
     * Constructeur
     * @author Selvaratnam SENTHURAN
     * @param s message associé avec l'exception
     */
    public NullModelException(String s) {
        super(s);
        msg = s;
    }
    
    /**
     * @return string message de l'exception
     * @author Selvaratnam SENTHURAN
     */
    public String getMessage() {
        return msg;
    }
}
