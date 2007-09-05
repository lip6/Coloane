package fr.lip6.move.coloane.results;

import java.util.Observable;
import java.util.Vector;

/**
 * La liste de resultats est observable.
 * Chaque ajout ou suppression provoque la mise a jour de la vue
 */
public class ResultsList extends Observable {
	private String actionName;
	private Vector<Result> resultsList;

	/**
	 * Constructeur
	 * @param question Le service auquel correspond cette liste de resultats
	 */
	public ResultsList(String question) {
		this.actionName = question;
		resultsList = new Vector<Result>();
	}

	/**
	 * Ajoute un resultat atomique a la liste de resultats
	 * @param result
	 */
	public final void add(Result result) {
		resultsList.add(result);
		setChanged();
		notifyObservers();
	}

	/**
	 * Supprime tous les resultats de la liste
	 */
	public final void removeAll() {
		resultsList.removeAllElements();
		setChanged();
		notifyObservers();
	}

	/**
	 * Retourne la chaine de caracteres qui s'affiche dans la premiere partie de la fenetre Results
	 * @return String
	 */
	public final String getActionName() {
		return actionName;
	}

	/**
	 * Retourne le resultat designe par son index
	 * @param index L'index du resultat qu'on souhaite obtenir
	 * @return Le resultat
	 */
	public final Result getResult(int index) {
		return resultsList.get(index);
	}

	/**
	 * Retourne le nombre de resultats contenus dans la liste
	 * @return int
	 */
	public final int getResultsNumber() {
		return resultsList.size();
	}

	/**
	 * Indique une nouvelle chaine de caractere pour la description des resultats
	 * @param newActionName La nouvelle description
	 */
	public final void setActionName(String newActionName) {
		this.actionName = newActionName;
	}
}
