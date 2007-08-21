package fr.lip6.move.coloane.results.reports;

import fr.lip6.move.coloane.interfaces.objects.IResultsCom;
import fr.lip6.move.coloane.interfaces.objects.SubResultsCom;
import fr.lip6.move.coloane.results.Result;
import fr.lip6.move.coloane.results.ResultsList;

public class SyntaxCheckerReport implements IReport {

	/** Liste des resultats envoyes par FK */
	private IResultsCom resultCom;

	/** Le nom du service */
	private static final String LABEL = "Petri Net Syntax Checker";

	/** La liste des resultats */
	private ResultsList resultList;

	/**
	 * Constructeur du rapport
	 * @param results
	 */
	public SyntaxCheckerReport(IResultsCom results) {
		this.resultCom = results;
		this.resultList = new ResultsList(LABEL);
		this.buildReport();
	}

	/**
	 * Construction effective du rapport :
	 * <ul>
	 * 	<li>Liste de resultats</li>
	 *  <li>Tous les resultats atomiques</li>
	 * </ul>
	 */
	private void buildReport() {
		String description = "";

		// Parcours de tous les DE-FE
		for (SubResultsCom sr : this.resultCom.getSubResults()) {
			String label = sr.getCmdRT().get(0);

			if (label.equalsIgnoreCase("List of unnamed places.")) {
				description = "This place is unnamed";
			} else if (label.equalsIgnoreCase("List of unnamed transitions.")) {
				description = "This transition is unnamed";
			}

			// Parcours de tous les objets mis en valeur
			for (String object : sr.getCmdRO()) {
				this.resultList.add(new Result(object, description));
			}
		}
	}

	/**
	 * Retourne la liste des resultats a afficher
	 */
	public final ResultsList getResultList() {
		return this.resultList;
	}
}
