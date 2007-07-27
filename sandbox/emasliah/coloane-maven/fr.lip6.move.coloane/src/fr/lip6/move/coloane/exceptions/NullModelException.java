package fr.lip6.move.coloane.exceptions;

public class NullModelException extends Exception {
    
    /**
     * num�ro de s�rie par d�faut
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Message d'information sur l'exception
     */
    private String msg;
    
    /**
     * Constructeur
     * @author Selvaratnam SENTHURAN
     * @param s message associ� avec l'exception
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
