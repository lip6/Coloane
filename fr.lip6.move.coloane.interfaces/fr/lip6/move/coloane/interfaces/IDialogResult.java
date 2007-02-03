package fr.lip6.move.coloane.interfaces;

import java.util.ArrayList;

/**
 * Interface fournie par Coloane a l'API
 * Elle contient toutes les informations pour manipuler les resultats
 * envoye par Coloane a la suite d'un affichage de boite de dialogue
 */

public interface IDialogResult {
	
	public ArrayList<String> getText();
	
	public int getAnswerType();
	
	public int getDialogId();
	
	public boolean hasBeenModified();
	
}
