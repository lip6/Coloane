package fr.lip6.move.coloane.communications.objects;

import java.util.Vector;


import fr.lip6.move.coloane.communications.utils.CamiParser;
import fr.lip6.move.coloane.exceptions.WrongArgumentValueException;

/**
 * Reponse simple a un dialogue initie par l'outil
 * @author DQS equipe 2 (Styx)
 */
public class SimpleDialogueResponse extends Dialogue {
	
	/**
	 * permet de savoir s'il y a eu une modification ou non
	 */
	private boolean modified;
	
	/**
	 * type de reponse (1;2)
	 */
	private int type;
	
	/**
	 * Constructeur
	 * @param uId id unique de la boite de dialogue
	 * @param msg contenu du message vehicule dans la boite de dialogue
	 * @param modif permet de savoir s'il y a eu une modifaication ou non
	 * @param typ type de reponse (1;2)
	 * @throws WrongArgumentValueException si typ n'est pas egal a 1 ou 2
	 */
	public SimpleDialogueResponse(int uId, String msg, boolean modif, int typ) throws WrongArgumentValueException {
		super(uId, msg);
		if (typ != 1 && typ != 2) {
			throw new WrongArgumentValueException("le type n'est pas correcte (valeur 1 ou 2)");
		}
		this.type = typ;
		this.modified = modif;
		
	}
	
	/**
	 * Constructeur
	 * @param uId id unique de la boite de dialogue
	 * @param msg contenu du message vehicule dans la boite de dialogue
	 * @param modif permet de savoir s'il y a eu une modifaication ou non
	 * @param typ type de reponse (1;2)
	 * @throws WrongArgumentValueException si typ n'est pas egal a 1 ou 2
	 */
	public SimpleDialogueResponse(int uId, String [] msg, boolean modif, int typ) throws WrongArgumentValueException {
		super(uId, msg);
		if (typ != 1 && typ != 2) {
			throw new WrongArgumentValueException("le type n'est pas correcte (valeur 1 ou 2)");
		}
		this.type = typ;
		this.modified = modif;
		
	}
	
	/**
	 * permet de savoir si une entrée de la boite de dialogue a ete modifie ou non
	 * @return true (qui est egal a 1 en CAMI) si il n'y a pas eu une modification
	 * 		   false (qui est egal a 2 en CAMI) si il y a  eu une modification	
	 */
	public boolean isModified() {
		return this.modified;
	}
	
	/**
	 * permet de recuperer le type de reponse
	 * @return (1 :l'utilisateur est sorti en appuyant sur OK
	 * 		    2 :l'utilisateur est sorti en appuyant sur CANCEL) 	
	 */
	public int getType() {
		return this.type;
	}
	
	/**
	 * transforme l'objet en CAMI en tableau de String
	 * @return un String[] contenant tout le CAMI 
	 */
	public String[] translateToCAMI() {
		int i = 1;
		Vector<String> vec = new Vector<String>();
		String value = CamiParser.stringToCAMI(super.getMessage()[0]);
		vec.add("DP()");
		
		if (isModified()) {
			vec.add("RD(" + super.getUniqueId() + "," + type + ",2," + value + ")");
			
		} else {
			vec.add("RD(" + super.getUniqueId() + "," + type + ",1," + value + ")");
		}

		 while (super.getMessage()[i] != null) {
			value = CamiParser.stringToCAMI(super.getMessage()[i]);
			vec.add("DE()");
			vec.add("DS(" + super.getUniqueId() + "," + value + ")");
			vec.add("FE()");
			i++;
		}

		vec.add("FP()");
		String[] cami = new String[vec.size()];
		vec.toArray(cami);

		return cami;
	}
}
