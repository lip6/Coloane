package fr.lip6.move.coloane.communications.objects;

import fr.lip6.move.coloane.exceptions.WrongArgumentValueException;

/**
 * Message de trace ou alerte envoye par Framekit a l'IHM
 * @author DQS equipe 2 (Styx)
 */
public class FramekitMessage {
	
	/**
	 * trace d'excution
	 */
	public static final int TRACE = 1;
	/**
	 * message warnig
	 */
	public static final int WARNING = 2;
	/**
	 * message special
	 */
	public static final int SPECIAL = 3;
	
	/**
	 * message sur l'etat du service en cours
	 */
	public static final int STATE = 4;
	
	/**
	 * message sur l'etat du service en cours
	 */
	public static final int ERRORSYNTAXCHECK = 5;
	
	/**
	 * message avec la liste des identifiant de node
	 */	
	public static final int LISTEID = 6;
	
	/**
	 * type du message ( 1:trace_message ; 2:warning_message ; 3:special_message)
	 */
	private int type;
	
	/**
	 * texte du message
	 */
	private String text;

	/**
	 * type du message special (1 ; 2 ; 3 ; 4)
	 */
	private int specialType;
	
	
	/**
	 * Constructeur
	 * @param type type de message (1,2,3,4,5 ou 6)
	 * @param texte contenu du message
	 * @param speType type du message special (1,2,3 ou 4)
	 * @throws WrongArgumentValueException si typ n'est pas entre 1 et 4 et/ou si speType n'est pas entre 1 et 4
	 */
	public FramekitMessage(int type, String texte, int speType) throws WrongArgumentValueException {
		if (type < 1 || type > 6) {
			throw new WrongArgumentValueException("Le type n'est pas correct (valeur 1, 2, 3, 4, 5 ou 6)");
		} else if (type == 3 && (speType < 1 || speType > 4)) {
			throw new WrongArgumentValueException("Le type special n'est pas correct (valeur 1, 2, 3 ou 4)");
		}
		
		this.type = type;
		this.text = texte;
		this.specialType = speType;
	}
	
	/**
	 * permet de recuperer le type de message
	 * @return un entier qui correspond au type de message 
	 * ( 1:trace_message ; 2:warning_message ; 3:special_message)
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * permet de recuperer le type du message special
	 * @return un entier qui correspond au type de message 
	 * 1:mot du jour de l'administrateur
	 * 2:avertissement court et urgent
	 * 3:message de copyright
	 * 4:statistique d'executions
	 */
	public int getSpecialType() {
		return this.specialType;
	}
	
	/**
	 * permet de recuperer le contenu du message
	 * @return est le message lu
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * transforme l'objet en CAMI
	 * @return un StringBuffer contenant tout le CAMI
	 */
	public StringBuffer translateToCAMI() {
		StringBuffer tmpStrBuf = new StringBuffer("");
		StringBuffer strBuf = new StringBuffer("");
		
		switch(type) {
		case 1 : tmpStrBuf.append("TR(" + text.length() + ":" + text + ")"); break;
		case 2 : tmpStrBuf.append("WN(" + text.length() + ":" + text + ")"); break;
		case 3 : tmpStrBuf.append("MO(" + specialType + "," + text.length() + ":" + text + ")"); break;
		default : break;
		}
		
		strBuf.append((char) tmpStrBuf.length());
		strBuf.append(tmpStrBuf);
		
		return strBuf;
	}
}
