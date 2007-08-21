package fr.lip6.move.coloane.interfaces.objects;

import java.util.Vector;

/**
 * Interface fournie par l'interface a Coloane pour la transmission de resultats.
 */
public interface IResultsCom {

	/**
	 * Accede a la commande RQ
	 * @return String la commande RQ
	 */
	String getQuestion();

	/**
	 * Modifie la commande RQ
	 * @param newQuestion la commande à modifier
	 */
	void setcmdRQ(String newQuestion);


	/**
	 * Ajoute une liste de sous resultats DE et FE dans la liste des sous_resulats
	 * @param sr la commande à ajouter
	 */
	void addResultats(SousResultsCom sr);

	/**
	 * Enleve un element de la liste des sous resultats
	 * @param sr la commande à enlever
	 */
	void removeResultats(SousResultsCom sr);

	/**
	 * Accede au sous resultat compris entre DE et FE
	 * @return Vector<SousResultsCom> la liste de sous resultats compris entre DE et FE
	 */
	Vector<SousResultsCom> getSubResults();
}
