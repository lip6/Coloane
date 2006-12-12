package fr.lip6.move.coloane.communications.objects;

import fr.lip6.move.coloane.exceptions.WrongArgumentValueException;

/**
 * Message envoye dans certains cas, lors d'une sasie avec un abort autorisé dans windowedDialogue
 * @author DQS equipe 2 (Styx)
 */
public class UrgentMessage extends Dialogue {
	
	/**
	 * type de l'urgence (1;2)
	 */
	private int type;
	
	/**
	 * Constructeur
	 * @param uId id unique de la boite de dialogue
	 * @param typ type de l'urgence
	 * @throws WrongArgumentValueException si typ n'est pas egal a 1 ou 2
	 */
	public UrgentMessage(int uId, int typ) throws WrongArgumentValueException {
		super(uId, "");
			
		if (typ != 1 && typ != 2) {
			throw new WrongArgumentValueException("le type n'est pas correcte (valeur 1 ou 2)");
		}
		this.type = typ;
	}
	
	/**
	 * parmet de recuperer le type de l'urgence 
	 * @return 1:Abort(controle-C)
	 * 	       2:Quit
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * transforme l'objet en CAMI
	 * @return un StringBuffer contenant tout le CAMI
	 */
	public String [] translateToCAMI() {
		String [] strTab = new String [1];

		strTab[0] = "MU(" + super.getUniqueId() + "," + type + ")";
		
		return strTab;
	}
}
