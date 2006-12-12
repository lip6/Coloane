package fr.lip6.move.coloane.communications.objects;

import fr.lip6.move.coloane.exceptions.WrongArgumentValueException;


/**
 * Reponse interactive envoye par l'IHM a Framekit
 * @author DQS equipe 2 (Styx)
 */
public class InteractiveResponse extends Dialogue {
	
	/**
	 * type de dialogue (1; 2 ; 3 ; 4)
	 */
	private int type;
	
	/**
	 * Constructeur
	 * @param uId id unique de la boite de dialogue
	 * @param msg contenu du message vehiculé dans la boite de dialogue
	 * @param typ type de dialogue
	 * @throws WrongArgumentValueException si typ n'est pas entre 1 et 4
	 */
	public InteractiveResponse(int uId, String msg, int typ) throws WrongArgumentValueException {
		super(uId, msg);
		if (typ < 1 || typ > 4) {
			throw new WrongArgumentValueException("le type n'est pas correct (valeur 1, 2, 3 ou 4)");
		}
		this.type = typ;
	}
	
	/**
	 * permet de recuperer le type de dialogue
	 * @return le type du dialogue
	 * 1:message normal avec affichage de ce qui a ete saisi dans la fenetre de service
	 * 2:message normal
	 * 3:massage de warrning
	 * 4:message d'erreur
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * transforme l'objet en CAMI
	 * @return un StringBuffer contenant tout le CAMI
	 */
	public String[] translateToCAMI() {
		
		switch (type) {
		default : break;
		}
		
		return null;
	}
}
