package fr.lip6.move.coloane.communications.objects;

import fr.lip6.move.coloane.api.exceptions.WrongArgumentValueException;
import fr.lip6.move.coloane.main.Coloane;

/**
 * Message de trace ou alerte envoye par Framekit a l'IHM
 * @author DQS equipe 2 (Styx)
 */
public class FramekitMessage {

	/** Trace d'excution */
	public static final int TRACE = 1;

	/** Message warning */
	public static final int WARNING = 2;

	/** Message special */
	public static final int SPECIAL = 3;

	/** Message sur l'etat du service en cours */
	public static final int STATE = 4;

	/** Message sur l'etat du service en cours */
	public static final int ERRORSYNTAXCHECK = 5;

	/** Message avec la liste des identifiant de node */
	public static final int LISTEID = 6;

	/** Type du message ( 1:trace_message ; 2:warning_message ; 3:special_message) */
	private int type;

	/** Texte du message */
	private String text;

	/** Type du message special (1 ; 2 ; 3 ; 4) */
	private int specialType;


	/**
	 * Constructeur
	 * @param type type de message (1,2,3,4,5 ou 6)
	 * @param texte contenu du message
	 * @param speType type du message special (1,2,3 ou 4)
	 * @throws WrongArgumentValueException si typ n'est pas entre 1 et 4 et/ou si speType n'est pas entre 1 et 4
	 */
	public FramekitMessage(int messageType, String texte, int speType) throws WrongArgumentValueException {
		if (messageType < TRACE || messageType > LISTEID) {
			throw new WrongArgumentValueException(Coloane.getTranslate().getString("communications.objects.FramekitMessage.0")); //$NON-NLS-1$
		} else if (messageType == SPECIAL && (speType < TRACE || speType > STATE)) {
			throw new WrongArgumentValueException(Coloane.getTranslate().getString("communications.objects.FramekitMessage.1")); //$NON-NLS-1$
		}

		this.type = messageType;
		this.text = texte;
		this.specialType = speType;
	}

	/**
	 * permet de recuperer le type de message
	 * @return un entier qui correspond au type de message
	 * ( 1:trace_message ; 2:warning_message ; 3:special_message)
	 */
	public final int getType() {
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
	public final int getSpecialType() {
		return this.specialType;
	}

	/**
	 * permet de recuperer le contenu du message
	 * @return est le message lu
	 */
	public final String getText() {
		return this.text;
	}

	/**
	 * transforme l'objet en CAMI
	 * @return un StringBuffer contenant tout le CAMI
	 */
	public final StringBuffer translateToCAMI() {
		StringBuffer tmpStrBuf = new StringBuffer(""); //$NON-NLS-1$
		StringBuffer strBuf = new StringBuffer(""); //$NON-NLS-1$

		switch(type) {
			case TRACE :
				tmpStrBuf.append("TR(" + text.length() + ":" + text + ")"); break; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			case WARNING :
				tmpStrBuf.append("WN(" + text.length() + ":" + text + ")"); break; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			case SPECIAL :
				tmpStrBuf.append("MO(" + specialType + "," + text.length() + ":" + text + ")"); break; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			default :
				break;
		}

		strBuf.append((char) tmpStrBuf.length());
		strBuf.append(tmpStrBuf);

		return strBuf;
	}
}
