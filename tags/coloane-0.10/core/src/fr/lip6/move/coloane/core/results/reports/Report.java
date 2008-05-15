package fr.lip6.move.coloane.core.results.reports;

import fr.lip6.move.coloane.core.results.ResultsList;
import fr.lip6.move.coloane.interfaces.objects.IResultsCom;

public abstract class Report implements IReport {

	/** Liste des resultats envoyes par FK */
	private IResultsCom resultCom;

	/** La liste des resultats */
	private ResultsList resultList;

	/**
	 *Le constructeur
	 *@param resultCom la liste de resultat envoyes par FK
	 */
	public Report(String label, IResultsCom results) {
		this.resultCom = results;
		this.resultList = new ResultsList(label);
		this.buildReport();
	}

	/**
	 * Construction effective du rapport :
	 * <ul>
	 * 	<li>Liste de resultats</li>
	 *  <li>Tous les resultats atomiques</li>
	 * </ul>
	 */
	protected abstract void buildReport();

	/**
	 * Retourne la liste des resultats a afficher
	 */
	public final ResultsList getResultList() {
		return this.resultList;
	}

	/**
	 * Retourne la liste des resultats renvoyes par la com
	 * @return La liste des resultats en provenance de la com
	 */
	protected final IResultsCom getResultsCom() {
		return this.resultCom;
	}

	/**
	 * Modifie le label du resultat
	 * @param l Le nouveau label
	 */
	protected final void setLabel(String l) {
		this.resultList.setActionName(l);
	}
}
