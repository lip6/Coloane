package fr.lip6.move.coloane.api.exceptions;

/**
 * Exception leve lorsqu'on construit un objet a partir du CAMI et que les commandes donnees
 * en parametre sont inconnues
 * @author DQS equipe 2 (Styx)
 */
public class UnexpectedCamiCommand extends Exception {

	/**
	 * identifie l'exception pour la serialization
	 */
	static final long serialVersionUID = 7092611880189329094L;
	
	/**
	 * construceur 
	 * @param arg0 message d'erreur supplementaire
	 */
	public UnexpectedCamiCommand(String arg) {
		super(arg);
	}

}
